<template>
  <div>
    <img src="" alt="" id="background" ref="background">
    <div id="shade"></div>
    <!-- <img src="./video/bg.png" alt="" id="background" ref="background"> -->
    <div style="padding: 40px;" id="container">

      <!-- 네브빠 -->
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
        <button :disabled="this.isPlaying" class="btn button" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasRight" aria-controls="offcanvasRight">피드백 확인</button>
        <div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasRight" aria-labelledby="offcanvasRightLabel">
          <div class="offcanvas-header">
            <h5 id="offcanvasRightLabel">피드백</h5>
            <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close" ref="feedbackClose"></button>
          </div>
          <div class="offcanvas-body" id="offcanvas-body">
            <div id="offcanvas-button-box" class="d-flex justify-content-around" style="width: 100%">
              <button class="btn btn-success" style="width: 49%; padding: 1px;" @click="showAllFeedback">피드백 목록</button>
              <button class="btn btn-primary" style="width: 49%; padding: 1px;" @click="showSavedFeedback">보관함</button>
            </div>
            <div v-if="isTemporaryFeedback">
              <div v-for="(feedback, index) in feedbacks" v-bind:key="index">
                <FeedbackCard :feedback="feedback" 
                @showFeedback="showFeedback(feedback)"
                @saveFeedback="saveFeedback(feedback)"
                @deleteFeedback="deleteFeedback(index)"
                 />
              </div>
            </div>
            <div v-else>
              <div v-for="(feedback, index) in savedFeedbacks" v-bind:key="index">
                <SavedFeedbackCard :feedback="feedback" 
                @showFeedback="showFeedback(feedback)" 
                @deleteSavedFeedback="deleteSavedFeedback(index)"
                />
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- 가이드 & 동영상 -->
      <div id="midBox">
        <div id="videoBox" ref="videoBox">
          <video src="" height="420" ref="video" @timeupdate="repeat" id="video"></video>
          <canvas class="d-none" ref="videoCanvas"></canvas>
        </div>
        <div id="camBox">
          <video ref="webcam" id="webcam" autoplay playsinline height="420"></video>
          <canvas class="d-none" ref="webcamCanvas"></canvas>
        </div>
      </div>

      <!-- 진행도 빠 -->
      <div class="progress mt-5 mx-3" id="progress" ref="progress" @click="changePosition($event)" style="height: 4px; background-color: white;">
        <div ref="progressBar" id="progress-bar" class="progress-bar bg-danger" role="progressbar" style="width: 0%" aria-valuenow="15" aria-valuemin="0" aria-valuemax="100"></div>
      </div>

      <!-- 버튼 빠 -->
      <div id="bottom-box">
        <div id="button-box">
          <i ref="play" class="txt fas fa-play mx-4 fs-3 play-menu" style="color: white;" @click="countdown"></i>
          <i ref="pause" class="txt fas fa-pause fs-3 play-menu" @click="pauseVideo" style="color: crimson"></i>
          <div @click="repeatCheck" class="txt ms-4">
            <span ref="A" style="color: white;">A</span>
            <span ref="B" style="color: white;">B</span>
          </div>
          <div class="txt btn-group dropend ms-3">
            <!-- <button type="button" class="btn btn-secondary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
              반복 횟수 
            </button> -->
            <div class="align-self-center mr-2" style="font-size:25px; color: white;">반복 횟수 : </div>
            <!-- <i class="fas fa-sync-alt fa-2x align-self-center mr-2 ml-5" style="color: rgb(150,150,150)"></i> -->
            <i class="fas fa-caret-left fa-3x" style="color: white;" @click="minus"></i>
            <div class="ml-2 mr-2 align-self-center" style="font-size:25px; color: white;">{{maxRepeatCount}}</div>
            <i class="fas fa-caret-right fa-3x" style="color: white;" @click="plus"></i>
            <!-- <div class="dropdown-menu ms-2" style="width: 50px;">
              <input class="px-2" type="text" v-model="maxRepeatCount" style="width: 50px;">
            </div> -->
          </div>
        </div>
        <div id="time-box" class="txt" style='color: white'>
          <div id="volume-box" class="mx-4 d-flex" @mouseover="onVolumeControl" @mouseleave="offVolumeControl">
            <input type="range" style="background-color: red;" min="0" max="100" :value="volume" id="volume" class="me-3" v-if="isVolumeControl" @mousemove="changeVolume" ref="volume">
            <div style="width: 32px">
              <i class="fas fa-volume-mute fs-3" style='color: white' v-if="this.volume == 0 || isMute" @click="unmute"></i>
              <i class="fas fa-volume-up fs-3" style='color: white' v-else-if="this.volume >= 50 && !isMute" @click="mute"></i>
              <i class="fas fa-volume-down fs-3" style='color: white' v-else-if="!isMute" @click="mute"></i>
            </div>
          </div>
          {{ nowTime }} / {{ endTime }}
        </div>
      </div>
    </div>
    <Feedback ref="feedback" v-if="this.feedbackVisible" :feedbackData="feedbackData" id="feedback-modal"
    @moveFeedback="moveVideo(feedbackData[0])"
    @closeFeedback="hideFeedback()" />
    <Countdown style="z-index: 99999" v-if="isCountdown" @countdownEnd="countdownEnd" />
  </div>
</template>

<script>
import Webcam from 'webcam-easy'
import http from '@/http.js';
import router from '@/router/index.js'
// import ExitButton from '@/components/practice/ExitButton.vue'
import Feedback from '@/components/practice/Feedback.vue'
import FeedbackCard from '@/components/practice/FeedbackCard.vue'
import SavedFeedbackCard from '@/components/practice/SavedFeedbackCard.vue'
import Countdown from '@/components/ranking/Countdown.vue'
import Modal from '@/components/Modal.vue'

export default {
  components: {
    Feedback,
    FeedbackCard,
    SavedFeedbackCard,
    Countdown,
    // ExitButton,
    Modal,
  },
  data() {
    return {
      songId: '',
      songInfo: Object,
      volume: 100,
      tmpVolume: 100,
      isMute: false,
      nowTime: '00:00',
      endTime: '',
      endTimeInt: 0,
      isVolumeControl: false,
      vectorInfos: [[15, 0], [2, 9], [5, 12], [2, 5], [9, 12], [2, 3], [3, 4], [5, 6], [6, 7], [9, 10], [10, 11], [12, 13], [13, 14]],
      vectorNames: { 0: '목', 1: '우측 허리', 2: '좌측 허리', 3: '어깨 라인', 4: '힙 라인', 5: '오른 팔뚝', 6: '오른 팔', 
                    7: '왼 팔뚝', 8: '왼 팔', 9: '오른 허벅지', 10:'오른 종아리', 11:'왼 허벅지', 12:'왼 종아리'},
      criticalPoints: { 0: '정수리', 1: '목', 
                        2: '우측 어깨', 3: '우측 팔꿈치', 4: '우측 손목', 
                        5: '좌측 어깨', 6: '좌측 팔꿈치', 7: '좌측 손목', 
                        8: '골반', 9: '우측 엉덩이', 10: '우측 무릎',  
                        11: '우측 발목', 12: '좌측 엉덩이', 13: '좌측 무릎', 14: '좌측 발목', 15: '몸통'},
      repeatStartTime: 0,
      repeatEndTime: 0,
      isRepeatStart: false,
      isRepeatEnd: false,
      feedbacks: [],
      savedFeedbacks: [],
      feedbackData: [],
      feedbackVisible: false,
      isPoseReadable: true,
      isPlaying: false,
      timeInterval: '',
      captureInterval: '',
      isTemporaryFeedback: true,
      nowRepeatCount: 0,
      maxRepeatCount: 5,
      isCountdown: false,
      isModalOpen: false,
    }
  },
  methods: {
    exitDance() {
      router.push({ name: 'MusicSelect', query: {'mode': localStorage.getItem('mode')} })
    },
    openModal () {
        // this.$refs.selecteffect.play()
        this.isModalOpen = true
      },
    closeModal () {
        this.isModalOpen = false
    },
    plus(){
      if(this.maxRepeatCount < 10){
        this.maxRepeatCount++
      }
    },
    minus(){
      if(this.maxRepeatCount > 1){
        this.maxRepeatCount--
      }
    },
    countdown() {
      this.isCountdown = true
    },
    countdownEnd() {
      this.isCountdown = false
      this.playVideo()
    },
    playVideo() {
      clearInterval(this.timeInterval)
      // clearInterval(this.captureInterval)
      this.removeFeedbacks(parseInt(this.$refs.video.currentTime))
      this.isPlaying = true
      this.$refs.video.play()
      this.$refs.pause.style = 'color: white'
      this.$refs.play.style = 'color: crimson'
      this.timeInterval = setInterval(this.checkTime, 500)
      // this.captureInterval = setInterval(this.dancingGo, 2000)
      while (this.$refs.videoBox.querySelector('.tmp-box')) {
        this.$refs.videoBox.removeChild(this.$refs.videoBox.querySelector('.tmp-box'))
      }
    },
    checkTime() {
      var timeInt = parseInt(this.$refs.video.currentTime)
      let videoPos = timeInt / this.endTimeInt * 100
      this.$refs.progressBar.style = 'width: ' + String(videoPos) + '%'
      let minute = parseInt(timeInt / 60)
      let second = timeInt % 60
      this.nowTime = String(minute).padStart(2, '0') + ':' + String(second).padStart(2, '0')
      if (!this.isPlaying) {
        // clearInterval(this.captureInterval)
        clearInterval(this.timeInterval)
      }
    },
    pauseVideo() {
      this.isPlaying = false 
      this.$refs.video.pause()
      this.$refs.pause.style = 'color: crimson'
      this.$refs.play.style = 'color: white'
      this.dancingGo()
    },
    removeFeedbacks(time) {
      const len = this.feedbacks.length - 1
      for (let i=0; i < len; i++) {
        if (this.feedbacks[i][0] > time) {
          this.feedbacks.splice(i, len-i+1)
          return
        }
      }
    },
    moveVideo(time) {
      this.isPlaying = true 
      this.feedbackVisible = false
      this.$refs.feedbackClose.click()
      let now = time
      this.$refs.video.currentTime = now
      this.playVideo()
    },
    changePosition(event) {
      const xPos = event.offsetX
      const totalLength = this.$refs.progress.clientWidth
      this.$refs.video.currentTime = xPos/totalLength * this.endTimeInt
      this.playVideo()
    },
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
    repeatCheck() {
      if (!this.isRepeatStart) {
        this.isRepeatStart = true
        this.repeatStartTime = this.$refs.video.currentTime
        this.$refs.A.style = 'color: white'
      } else if (!this.isRepeatEnd) {
        this.isRepeatEnd = true
        this.repeatEndTime = this.$refs.video.currentTime
        this.$refs.B.style = 'color: white'
        this.$refs.video.currentTime = this.repeatStartTime
        this.pauseVideo()
        this.countdown()
      } else {
        this.$refs.A.style = 'color: rgb(150,150,150)'
        this.$refs.B.style = 'color: rgb(150,150,150)'
        this.isRepeatEnd = false
        this.isRepeatStart = false
        this.repeatEndTime = 0
        this.repeatStartTime = 0
        this.playVideo()
      }
    },
    repeat() {
      if (this.isRepeatEnd && this.$refs.video.currentTime > this.repeatEndTime) {
        this.nowRepeatCount += 1
        if (this.nowRepeatCount >= this.maxRepeatCount) {
          this.nowRepeatCount = 0
          this.$refs.A.style = 'color: rgb(150,150,150)'
          this.$refs.B.style = 'color: rgb(150,150,150)'
          this.isRepeatEnd = false
          this.isRepeatStart = false
          this.repeatEndTime = 0
          this.repeatStartTime = 0
          this.pauseVideo()
          return
        }
        this.$refs.video.currentTime = this.repeatStartTime
        this.playVideo()
      }
    },
    makeFeedback(videoImage, webcamImage, videoSkeleton, webcamSkeleton) {
      var feedbackTime = parseInt(this.$refs.video.currentTime)
      this.feedbacks.push([feedbackTime, this.nowTime, 
      videoImage, webcamImage , 
      videoSkeleton, webcamSkeleton])
    },
    saveFeedback(feedback) {
      if (this.savedFeedbacks.length < 10) {
        this.savedFeedbacks.push(feedback)
      } else {
        alert('10개가 넘었습니다. 보관함을 비워주세요.')
      }
    },
    deleteFeedback(index) {
      this.feedbacks.splice(index, 1)
    },
    deleteSavedFeedback(index) {
      this.savedFeedbacks.splice(index, 1)
    },
    showFeedback(feedback) {
      this.feedbackData = feedback
      this.feedbackVisible = true
    },
    hideFeedback() {
      this.feedbackImages = []
      this.feedbackVisible = false
    },
    //posenet 핵심 기능
    dancingGo() {
      var video = this.$refs.video
      var videoCanvas = this.$refs.videoCanvas
      var webcam = this.$refs.webcam
      var webcamCanvas = this.$refs.webcamCanvas
      // 가이드에서 프레임 캡쳐
      var videoImages = this.videoCapture(video, videoCanvas)
      var webcamImage = this.webcamCapture(webcam, webcamCanvas)
      this.openpose(videoImages[1], webcamImage)
      .then((res) => {
        var videoPoints = res[0]
        var webcamPoints = res[1]
        var videoVectors = this.getVideoVector(videoPoints)
        var webcamVectors = this.getCamVector(webcamPoints, videoVectors)
        this.makeFeedback(videoImages[0], webcamImage, videoVectors, webcamVectors)
      })
    },
    videoCapture(video, canvas) {
      var context = canvas.getContext('2d')
      canvas.width = video.clientWidth
      canvas.height = video.clientHeight
      context.drawImage(video, 0, 0, video.clientWidth, video.clientHeight)
      var originalCanvas = canvas.toDataURL()
      canvas.width = video.clientWidth
      canvas.height = video.clientHeight
      context.scale(-1, 1)
      context.translate(-canvas.width, 0)
      context.drawImage(video, 0, 0, video.clientWidth, video.clientHeight)
      var reversedCanvas = canvas.toDataURL()
      return [originalCanvas, reversedCanvas]
    },
    webcamCapture(video, canvas) {
      var context = canvas.getContext('2d')
      canvas.width = video.clientWidth
      canvas.height = video.clientHeight
      context.drawImage(video, 0, 0, video.clientWidth, video.clientHeight)
      return canvas.toDataURL()
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
    showAllFeedback() {
      this.isTemporaryFeedback = true
    },
    showSavedFeedback() {
      this.isTemporaryFeedback = false
    }
  },
  mounted() {
    const songId = this.$route.params.songId
    localStorage.setItem('mode', 'Practice')
    localStorage.setItem('songId', songId)
    this.songId = songId
    this.startCam()
    http.get('/song/getSong/' + songId)
    .then(res => {
      const songInfo = res.data
      this.songInfo = songInfo
      // this.$refs.background.src = '/images/home/home_background.jpg'
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

#shade {
  position: absolute;
  left: 0px;
  top: 0px;
  width: 100vw;
  height: 100vh;
  background-color: black;
  opacity: 0.7;
}

#container {
  position: relative;
}

#navbar {
  display: flex;
  justify-content: space-between;
}

#midBox {
  display: flex;
  justify-content: space-around;
  margin-top: 50px;
  margin-bottom: 60px;
}

#videoBox {
  width: 560px;
  height: 420px;
  overflow: hidden;
  display: flex;
  justify-content: center;
  box-shadow: 0 0 15px white;
  border-radius: 5px;
}

#camBox {
  width: 560px;
  height: 420px;
  overflow: hidden;
  display: flex;
  justify-content: center;
  box-shadow: 0 0 15px white;
  border-radius: 5px;
}

#bottom-box {
  display: flex;
  justify-content: space-between;
}

#button-box {
  display: flex;
  justify-content: flex-start;
  align-items: center;
  margin-top: 20px;
  padding-left: 10px;
}

#time-box {
  display: flex;
  align-items: center;
  margin-right: 30px;
  margin-top: 10px;
  font-size: 24px;
  font-weight: bold;
}

#progress:hover {
  cursor: pointer;
}

span, i:hover {
  cursor: pointer;
}

span {
  font-size: 30px;
  font-weight: bold;
}

#offcanvas-body {
  display: flex;
  flex-direction: column;
  align-items: center;
}

#feedback-modal {
  position: absolute;
  z-index: 99999;
}

.button {
  color: white;
  background-color: purple;
  box-shadow: 0 0 10px white;
}
.button:hover {
  color: white;
}

.txt {
  text-shadow: 0 0 4px purple;
}
</style>