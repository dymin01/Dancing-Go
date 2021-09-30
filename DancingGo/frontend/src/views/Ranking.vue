<template>
  <div>
    <img src="" alt="" id="background" ref="background">
    <img src="./video/gameover.jpg" alt="" id="background-gameover" v-if="this.isGameover">
    <audio src="/effect/gameover.wav" ref="gameover"></audio>
    <div style="padding: 40px" id="container">
      <div id="navbar" class="mb-5">
        <exit-button />
      </div>
      <div id="midBox">
        <div id="camBox">
          <video ref="webcam" id="webcam" playsinline height="400"></video>
          <!-- <video ref="webcam" id="webcam" autoplay playsinline height="120"></video> -->
          <canvas class="d-none" ref="webcamCanvas"></canvas>
        </div>
      </div>
      <div class="progress mt-5 mx-3" id="progress" ref="progress">
        <div ref="healthBar" id="progress-bar" class="progress-bar" role="progressbar" style="width: 100%" aria-valuenow="15" aria-valuemin="0" aria-valuemax="100"></div>
      </div>
      <div id="rank-bottom">
        <div>
          <span @click="plusHealth">+</span>
          <span @click="minusHealth">-</span>
        </div>
        <div>
          {{ nowTime }} / {{ endTime }}
        </div>
      </div>
      <div id="videoBox">
        <video src="" height="420" ref="video" v-show="videoShow"></video>
        <!-- <video src="./video/sample.mp4" height="120" ref="video"></video> -->
        <canvas ref="videoCanvas"></canvas>
      </div>
      <Countdown style="z-index: 99999" @countdownEnd="startRanking" v-if="isCountdown" />
    </div>
  </div>
</template>

<script>
import Webcam from 'webcam-easy'
import axios from 'axios'
import router from '@/router/index.js'
import ExitButton from '@/components/practice/ExitButton.vue'
import Countdown from '@/components/ranking/Countdown.vue'
// import * as posenet from '@tensorflow-models/posenet'

export default {
  data() {
    return {
      videoShow: false,
      health: 100,
      isGameover: false,
      nowTime: '00:00',
      endTime: '03:41',
      timeInterval: '',
      isCountdown: true,
    }
  },
  components: {
    ExitButton,
    Countdown
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
      let minute = parseInt(timeInt / 60)
      let second = timeInt % 60
      this.nowTime = String(minute).padStart(2, '0') + ':' + String(second).padStart(2, '0')
      if (this.nowTime == this.endTime) {
        // clearInterval(this.captureInterval)
        clearInterval(this.timeInterval)
        router.push('/rankingscore')
      }
    },
    plusHealth() {
      this.health += 5
      if(this.health > 100) {
        this.health = 100
      }
      this.refreshHealth()
    },
    minusHealth() {
      this.health -= 5
      this.refreshHealth()
      if (this.health <= 0) {
        this.isGameover = true
        this.$refs.video.pause()
        setTimeout(this.$refs.gameover.play(), 500)
      }
    },
    refreshHealth() {
      this.$refs.healthBar.style = "width: " + String(this.health) + "%"
    },
    startRanking() {
      this.isCountdown = false
      this.$refs.video.play()
      this.timeInterval = setInterval(this.checkTime, 500)
    }
  },
  mounted() {
    const songId = this.$route.params.songId
    console.log(songId)
    this.songId = songId
    localStorage.setItem('songId', songId)
    localStorage.setItem('mode', 'Ranking')
    this.startCam()
    axios.get('/song/getSong/' + songId)
    .then(res => {
      const songInfo = res.data
      this.songInfo = songInfo
      this.$refs.background.src = '/images/musicselect/' + songInfo.fileName + '.png'
      this.$refs.video.src = '/guides/' + songInfo.fileName + '.mp4'
      // var songLength = songInfo.SongLen
      // let minute = parseInt(songLength / 60)
      // let second = songLength % 60
      // this.endTime = String(minute).padStart(2, '0') + ':' + String(second).padStart(2, '0')
      // this.endTimeInt = songLength
      // this.$refs.video.play()
      // this.timeInterval = setInterval(this.checkTime, 500)
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
#background {
  position: absolute;
  left: 0px;
  top: 0px;
  width: 100vw;
  height: 100vh;
  opacity: 0.5;
}

#background-gameover {
  position: absolute;
  left: 0px;
  top: 0px;
  width: 100vw;
  height: 100vh;
  z-index: 99999;
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
  display: flex;
  justify-content: space-around;
}

#videoBox {
  width: 560px;
  height: 420px;
  overflow: hidden;
  display: flex;
  justify-content: center;
}

#camBox {
  width: 700px;
  /* height: 500px; */
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

span:hover {
  cursor: pointer;
}

#bottom-box {
  display: flex;
  justify-content: space-between;
}

#container {
  position: relative;
}
</style>