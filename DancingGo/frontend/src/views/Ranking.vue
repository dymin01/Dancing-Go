<template>
  <div>
    <img src="" alt="" id="background" ref="background">
    <div id="shade"></div>
    <Gameover v-if="isGameover" />
    <audio src="/effect/gameover.wav" ref="gameover"></audio>
    <div style="padding: 40px" id="container">
      <div id="navbar" class="mb-5 px-5">
        <!-- <ExitButton /> -->
        <v-btn
          id="button"
          style="opacity: 80%;"
          @click="openModal"
        >
          종료
        </v-btn>
        <v-dialog
          v-model="isModalOpen"
          persistent
          max-width="370px">
          <Modal
            v-if="this.$store.getters.langMode=='한국어'"
            :modalTitle="'종료하시겠습니까?'"
            :modalContent="'진행상황은 저장되지 않습니다.'"
            :buttonO="'종료'"
            :buttonX="'취소'"
            @clickO="exitDance"
            @clickX="closeModal"
          />
        </v-dialog>
      </div>

      <!-- 체력바 -->
      <div id="hpBarBox">
        <div class="progress mx-3" id="progress" ref="progress" style="width: 40%; height: 15px; background-color: rgba( 255, 255, 255, 0.1 );">
          <div ref="healthBar" id="progress-bar" class="progress-bar bg-danger hpBar" role="progressbar" style="width: 100%" aria-valuenow="15" aria-valuemin="0" aria-valuemax="100"></div>
        </div>
      </div>

      <!-- cam -->
      <div id="midBox">
        <div class="sidebar">
          <div>
            <div class="combo-text">MAX</div>
            <div class="combo-text pb-3">COMBO</div> 
          </div>
          <div class="combo-number">{{ maxcombo }}</div> 
        </div>
        <div id="camBox">
          <video ref="webcam" id="webcam" playsinline height="540" width="840"></video>
          <canvas class="d-none" ref="webcamCanvas"></canvas>
        </div>
        <div class="sidebar2">
          <div class="combo-text">COMBO</div> 
          <div class="combo-number">{{ combo }}</div> 
        </div>
      </div>

      <!-- 진행도 빠 -->
      <div class="progress mt-5 mx-3" id="progress" ref="progress" style="height: 4px; background-color: rgba( 255, 255, 255, 0.1 );">
        <div ref="progressBar" id="progress-bar" class="progress-bar bg-success" role="progressbar" style="width: 0%" aria-valuenow="15" aria-valuemin="0" aria-valuemax="100"></div>
      </div>

      <!-- 점수 -->
      <span class="scoreText hidden" ref="scoreText">{{ this.frameScore }}</span>

      <!-- <div id="rank-bottom">
        <div>
          <span>{{ this.frameScore }}</span>
        </div>
        <div style="font-size: 20px; font-weight: bold; color: white">
          {{ nowTime }} / {{ endTime }}
        </div>
      </div> -->

      <!-- 소리와 시간 -->
      <div id="time-box" style='color: white' class="justify-end">
        {{ nowTime }} / {{ endTime }}
      </div>

      <Countdown style="z-index: 99999" @countdownEnd="startRanking" v-if="isCountdown" />
      <div id="videoBox">
        <video src="" height="420" ref="video"></video>
        <canvas ref="videoCanvas" class="d-none"></canvas>
      </div>
    </div>
  </div>
</template>

<script>
import Webcam from 'webcam-easy'
import http from '@/http.js';
import router from '@/router/index.js'
import Countdown from '@/components/ranking/Countdown.vue'
import Gameover from '@/components/ranking/Gameover.vue'
import Modal from '@/components/Modal.vue'
import axios from 'axios'

export default {
  data() {
    return {
      videoShow: false,
      health: 100,
      isGameover: false,
      nowTime: '00:00',
      endTime: '',
      timeInterval: '',
      isCountdown: true,
      vectorInfos: [[15, 0], [2, 9], [5, 12], [2, 5], [9, 12], [2, 3], [3, 4], [5, 6], [6, 7], [9, 10], [10, 11], [12, 13], [13, 14]],
      vectorNames: { 0: '목', 1: '우측 허리', 2: '좌측 허리', 3: '어깨 라인', 4: '힙 라인', 5: '오른 팔뚝', 6: '오른 팔', 
                    7: '왼 팔뚝', 8: '왼 팔', 9: '오른 허벅지', 10:'오른 종아리', 11:'왼 허벅지', 12:'왼 종아리'},
      criticalPoints: { 0: '정수리', 1: '목', 
                        2: '우측 어깨', 3: '우측 팔꿈치', 4: '우측 손목', 
                        5: '좌측 어깨', 6: '좌측 팔꿈치', 7: '좌측 손목', 
                        8: '골반', 9: '우측 엉덩이', 10: '우측 무릎',  
                        11: '우측 발목', 12: '좌측 엉덩이', 13: '좌측 무릎', 14: '좌측 발목', 15: '몸통'},
      scoreMatch: { 0: 0, 1: 1, 2: 2, 3: 3, 4: 4, 5: 5, 6: 6, 7: 7 },
      frameScore: '',
      scores: [0, 0, 0, 0, 0],
      isVolumeControl: false,
      volume: 100,
      tmpVolume: 100,
      isMute: false,
      combo: 0,
      maxcombo: 0,
      isModalOpen: false,
      scoreShadow: {
        'perfect': 'color: white; text-shadow: 0 0 3px #eee, 0 0 10px rgb(57, 123, 247), 0 0 21px rgb(57, 123, 247), 0 0 42px rgb(57, 123, 247);',
        'great': 'color: white; text-shadow: 0 0 3px #eee, 0 0 10px rgb(123, 255, 71), 0 0 21px rgb(123, 255, 71), 0 0 42px rgb(123, 255, 71);',
        'good': 'color: white; text-shadow: 0 0 3px #eee, 0 0 10px rgb(252, 255, 82), 0 0 21px rgb(252, 255, 82), 0 0 42px rgb(252, 255, 82);',
        'bad': 'color: white; text-shadow: 0 0 3px #eee, 0 0 10px rgb(90, 0, 112), 0 0 21px rgb(90, 0, 112), 0 0 42px rgb(90, 0, 112);',
        'miss': 'color: white; text-shadow: 0 0 3px #eee, 0 0 10px rgb(187, 39, 39), 0 0 21px rgb(187, 39, 39), 0 0 42px rgb(187, 39, 39);',
      }
    }
  },
  components: {
    Countdown,
    Gameover,
    Modal
  },
  methods: {
    startCam() {
      const webcamEl = this.$refs.webcam
      const canvasEl = this.$refs.canvas
      const webcam = new Webcam(webcamEl, 'user', canvasEl)
      webcam.start()
      .then(result => {
        console.log(result)
      })
      .catch(err => {
        console.log(err)
      })
    },
    checkTime() {
      var timeInt = parseInt(this.$refs.video.currentTime)
      let videoPos = timeInt / this.endTimeInt * 100
      this.$refs.progressBar.style = 'width: ' + String(videoPos) + '%'
      let minute = parseInt(timeInt / 60)
      let second = timeInt % 60
      this.nowTime = String(minute).padStart(2, '0') + ':' + String(second).padStart(2, '0')
      if (this.nowTime == this.endTime) {
        clearInterval(this.timeInterval)
        clearInterval(this.captureInterval)
        this.$store.dispatch('ranking/setScores', this.scores)
        setTimeout(function() {
          router.push('/rankingscore')
        }, 2000)
      }
    },
    refreshHealth() {
      this.$refs.healthBar.style = "width: " + String(this.health) + "%"
    },
    startRanking() {
      this.isCountdown = false
      this.$refs.video.play()
      this.timeInterval = setInterval(this.checkTime, 500)
      this.captureInterval = setInterval(this.dancingGo, 2000)
    },
    dancingGo() {
      var video = this.$refs.video
      var videoCanvas = this.$refs.videoCanvas
      var webcam = this.$refs.webcam
      var webcamCanvas = this.$refs.webcamCanvas
      // 가이드에서 프레임 캡쳐
      var videoImages = this.videoCapture(video, videoCanvas)
      var webcamImage = this.webcamCapture(webcam, webcamCanvas)
      this.openpose(videoImages, webcamImage)
      .then((res) => {
        var videoPoints = res[0]
        var webcamPoints = res[1]
        var videoVectors = this.getVideoVector(videoPoints)
        console.log('가이드')
        console.log(videoVectors)
        var webcamVectors = this.getCamVector(webcamPoints, videoVectors)
        console.log('캠')
        console.log(webcamVectors)
        var frameScore = this.checkAngle(videoVectors, webcamVectors)
        this.changeHealth(frameScore)
      })
    },
    videoCapture(video, canvas) {
      var context = canvas.getContext('2d')
      canvas.width = video.clientWidth
      canvas.height = video.clientHeight
      context.scale(-1, 1)
      context.translate(-canvas.width, 0)
      context.drawImage(video, 0, 0, video.clientWidth, video.clientHeight)
      var videoCanvas = canvas.toDataURL()
      // console.log(videoCanvas)
      return videoCanvas
    },
    webcamCapture(video, canvas) {
      var context = canvas.getContext('2d')
      canvas.width = video.clientWidth
      canvas.height = video.clientHeight
      context.drawImage(video, 0, 0, video.clientWidth, video.clientHeight)
      var webcamCanvas = canvas.toDataURL()
      // console.log(webcamCanvas)
      return webcamCanvas
    },
    async openpose(videoImage, webcamImage) {
      var params = {
        'images': [videoImage, webcamImage]
      }
      let skeletons = []
      // await axios.post('http://localhost:8000/api/v1/', params)
      await axios.post('http://70.12.130.110:8000/api/v1/', params)
      .then(function(res) {
        skeletons = res.data.skeletons
      })
      return skeletons
    },
    getVideoVector(videoPoints) {
      const vector = Array()
      if (videoPoints[0] != null && videoPoints[8] != null) {
        videoPoints.push([(videoPoints[0][0] + videoPoints[8][0])/2, (videoPoints[0][1] + videoPoints[8][1])/2])
      } else {
        videoPoints.push(null)
      }
      console.log(videoPoints)
      for (let i = 0; i < this.vectorInfos.length; i++) {
        let vectorInfo = this.vectorInfos[i]
        let start = vectorInfo[0]
        let end = vectorInfo[1]
        if (videoPoints[start] != null && videoPoints[end] != null) {
          let x1 = videoPoints[start][0]
          let y1 = videoPoints[start][1]
          let x2 = videoPoints[end][0]
          let y2 = videoPoints[end][1]
          vector.push([x2-x1, y2-y1])
        } else {
          vector.push(null)
        }
      }
      return vector
    },
    getCamVector(webcamPoints, videoVectors) {
      const vector = Array()

      if (webcamPoints[0] != null && webcamPoints[8] != null) {
        webcamPoints.push([(webcamPoints[0][0] + webcamPoints[8][0])/2, (webcamPoints[0][1] + webcamPoints[8][1])/2])
      } else {
        webcamPoints.push(null)
      }
      console.log(webcamPoints)
      for (let i = 0; i < this.vectorInfos.length; i++) {
        if (videoVectors[i] != null) {
          let vectorInfo = this.vectorInfos[i]
          let start = vectorInfo[0]
          let end = vectorInfo[1]
          if (webcamPoints[start] != null && webcamPoints[end] != null) {
            let x1 = webcamPoints[start][0]
            let y1 = webcamPoints[start][1]
            let x2 = webcamPoints[end][0]
            let y2 = webcamPoints[end][1]
            vector.push([x2-x1, y2-y1])
          } else {
            vector.push(null)
          }
        } else {
          vector.push(null)
        }
      }
      return vector
    },
    checkAngle(videoVectors, webcamVectors) {
      let frameScore = 100
      let notSeeingCount = 0
      for (let i=0; i < Object.keys(this.vectorNames).length; i++) {
        if (videoVectors[i] != null) {
          if (webcamVectors[i] != null) {
            const x1 = videoVectors[i][0]
            const y1 = videoVectors[i][1]
            const x2 = webcamVectors[i][0]
            const y2 = webcamVectors[i][1]
            const angle = Math.acos((x1*x2 + y1*y2)/(((x1**2 + y1**2)**0.5) * ((x2**2 + y2**2)**0.5)))*(180/Math.PI)
            frameScore -= this.scoreMatch[this.checkScore(angle)]
            console.log(this.vectorNames[i] + '각도: ' + angle)
          } 
          else {
            notSeeingCount += 1
          }
        }
      }
      if (notSeeingCount > 3) {
        console.log('notSeeing')
        frameScore = 0
      }
      return frameScore
    },
    checkScore(angle) {
      if (angle <= 10) {
        return 0
      }
      var tmp = Math.ceil((angle - 10) / 5)
      if (tmp > 7) {
        tmp = 7
      }
      return tmp
    },
    changeHealth(score) {
      var health = this.health
      if (score >= 85) {
        this.frameScore = 'perfect'
        this.$refs.scoreText.style = this.scoreShadow[this.frameScore]
        this.combo += 1
        this.scores[0] += 1
        health += 3
      } else if (score >= 70) {
        this.frameScore = 'great'
        this.$refs.scoreText.style = this.scoreShadow[this.frameScore]
        this.combo += 1
        this.scores[1] += 1
        health += 2
      } else if (score >= 55) {
        this.frameScore = 'good'
        this.$refs.scoreText.style = this.scoreShadow[this.frameScore]
        this.combo += 1
        this.scores[2] += 1
        health += 1
      } else if (score >= 30) {
        this.frameScore = 'bad'
        this.$refs.scoreText.style = this.scoreShadow[this.frameScore]
        this.scores[3] += 1
        this.combo = 0
        health -= 3
      } else {
        this.frameScore = 'miss'
        this.scores[4] += 1
        this.$refs.scoreText.style = this.scoreShadow[this.frameScore]
        this.combo = 0
        health -= 5
      }
      if (this.combo > this.maxcombo) {
        this.maxcombo = this.combo
      }
      if (health > 100) {
        health = 100
      }
      if (health <= 0 && this.isGameover == false) {
        setTimeout(this.gameover, 700)
      }
      this.$refs.scoreText.classList.toggle('hidden')
      // this.$refs.scoreText.classList.toggle('move-box')
      this.$refs.scoreText.classList.toggle('fade-out')
      setTimeout(function() {
        // this.$refs.scoreText.classList.toggle('move-box')
        this.$refs.scoreText.classList.toggle('fade-out')
        this.$refs.scoreText.classList.toggle('hidden')
      }.bind(this), 1201)
      this.health = health
      this.refreshHealth()
    },
    gameover() {
      clearInterval(this.timeInterval)
      clearInterval(this.captureInterval)
      this.isGameover = true
      this.$refs.video.pause()
      setTimeout(this.$refs.gameover.play(), 500)
    },
    mute() {
      this.isMute = true
      this.tmpVolume = this.volume
      this.volume = 0
      this.$refs.video.volume = this.volume
    },
    unmute() {
      this.isMute = false
      this.volume = this.tmpVolume
      this.$refs.video.volume = this.volume
    },
    onVolumeControl() {
      this.isVolumeControl = true
    },
    offVolumeControl() {
      this.isVolumeControl = false
    },
    changeVolume() {
      this.volume = this.$refs.volume.value
      this.$refs.video.volume = this.volume * 0.01
    },
    exitDance() {
      router.push({ name: 'MusicSelect', query: {'mode': localStorage.getItem('mode')} })
    },
    openModal () {
        this.isModalOpen = true
      },
    closeModal () {
        this.isModalOpen = false
    },
  },
  mounted() {
    const songId = this.$route.params.songId
    localStorage.setItem('mode', 'Ranking')
    localStorage.setItem('songId', songId)
    this.songId = songId
    this.startCam()
    http.get('/song/getSong/' + songId)
    .then(res => {
      const songInfo = res.data
      this.songInfo = songInfo
      localStorage.setItem('songName', songInfo.fileName)
      this.$refs.background.src = '/images/musicselect/' + songInfo.fileName + '.png'
      this.$refs.video.src = '/guides/' + songInfo.fileName + '.mp4'
      var songLength = songInfo.songLen
      let minute = parseInt(songLength / 60)
      let second = songLength % 60
      this.endTime = String(minute).padStart(2, '0') + ':' + String(second).padStart(2, '0')
      this.endTimeInt = songLength
    })
    .catch(err => {
      console.log(err)
    })
  },
  beforeDestroy() {
    console.log('hi')
    clearInterval(this.timeInterval)
  },
  destroyed() {
    console.log('hi2')
    clearInterval(this.timeInterval)
  }
}
</script>

<style scoped>
.sidebar {
  text-align: center;
  position: relative;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  width: 250px;
  height: 300px;
  background: rgba(43, 29, 59, 0.8);
  padding-top: 40px;
  padding-bottom: 30px;
  border-radius: 10px;
  -webkit-text-stroke: 1px black;
  font-weight: bold;
}

.sidebar2 {
  text-align: center;
  position: relative;
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
  width: 250px;
  height: 300px;
  background: rgba(43, 29, 59, 0.8);
  border-radius: 10px;
  -webkit-text-stroke: 1px black;
  font-weight: bold;
}

.combo-text {
  font-size: 45px;
  color: rgb(17, 17, 17); 
  text-shadow: 0 0 5px #fff, 0 0 5px yellow, 0 0 10px yellow, 0 0 20px yellow;
}

.combo-number {
  font-size: 40px;
  color: rgb(17, 17, 17); 
  text-shadow: 0 0 5px #fff, 0 0 5px yellow, 0 0 10px yellow, 0 0 20px yellow;
}

#shade {
  position: absolute;
  left: 0px;
  top: 0px;
  width: 100vw;
  height: 100vh;
  background-color: black;
  opacity: 0.7;
}

#background {
  position: absolute;
  left: 0px;
  top: 0px;
  width: 100vw;
  height: 100vh;
  opacity: 0.5;
}


#container {
  position: relative;
  overflow: hidden;
}

#navbar {
  display: flex;
  justify-content: space-between;
}

#midBox {
  position: relative;
  display: flex;
  justify-content: space-around;
  align-items: center;
}

#videoBox {
  width: 560px;
  height: 420px;
  overflow: hidden;
  display: flex;
  justify-content: center;
  position: absolute;
  top: 100vh;
}

#camBox {
  width: 700px;
  overflow: hidden;
  display: flex;
  justify-content: center;
}

#rank-bottom {
  display: flex;
  justify-content: space-between;
  padding: 20px;
}

span {
  font-size: 30px;
  font-weight: bold;
  margin-right: 20px;
}

/* span:hover {
  cursor: pointer;
} */

#bottom-box {
  display: flex;
  justify-content: space-between;
}

#container {
  position: relative;
  width: 100vw;
  height: 100vh;
}

#time-box {
  display: flex;
  align-items: center;
  margin-right: 30px;
  margin-top: 10px;
  font-size: 24px;
  font-weight: bold;
}

.hpBar {
  box-shadow: 0 0 7px #fff, 0 0 10px #fff, 0 0 21px red, 0 0 42px red,
    0 0 82px red, 0 0 92px red, 0 0 102px red;
}

#hpBarBox {
  position: absolute;
  height: 100%;
  width: 100%;
  top: 4.5vh;
  left: 29.15vw;
}

.scoreText {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -450%);
  color: white;
  font-size: 50px;
}


.fade-out {
  animation: fadeout 1.5s;
}

.move-box {
  animation: movebox 15s;
}

.hidden {
  display: none;
}

/* @keyframes movebox {
  100% {
    top: 10px;
  }
} */

@keyframes fadeout {
    from {
        opacity: 1;
    }
    to {
        opacity: 0;
        top: 350px;
    }
}
</style>