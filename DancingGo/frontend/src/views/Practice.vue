<template>
  <div>
    <img src="./video/bg.png" alt="" id="background">
    <div style="padding: 40px; margin-top: 40px;" id="container">

      <!-- 네브빠 -->
      <div id="navbar" class="mb-5">
        <button class="btn btn-danger btn-lg d-flex justify-content-start">종료</button>
        <button :disabled="this.isPlaying" class="btn btn-primary" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasRight" aria-controls="offcanvasRight">틀린부분 확인하기</button>
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
          <video src="./video/sample2.mp4" height="420" ref="video" @timeupdate="repeat" id="video"></video>
          <!-- <video src="./video/sample.mp4" height="120" ref="video"></video> -->
          <canvas class="d-none" ref="videoCanvas"></canvas>
        </div>
        <div id="camBox">
          <video ref="webcam" id="webcam" autoplay playsinline height="420"></video>
          <!-- <video ref="webcam" id="webcam" autoplay playsinline height="120"></video> -->
          <canvas class="d-none" ref="webcamCanvas"></canvas>
        </div>
      </div>

      <!-- 진행도 빠 -->
      <div class="progress mt-5 mx-3" id="progress" ref="progress" @click="changePosition($event)">
        <div ref="progressBar" id="progress-bar" class="progress-bar" role="progressbar" style="width: 0%" aria-valuenow="15" aria-valuemin="0" aria-valuemax="100"></div>
      </div>

      <!-- 버튼 빠 -->
      <div id="bottom-box">
        <div id="button-box">
          <i ref="play" class="fas fa-play mx-4 fs-3 play-menu" style="color: grey" @click="playVideo"></i>
          <i ref="pause" class="fas fa-pause fs-3 play-menu" @click="pauseVideo" style="color: red"></i>
          <div @click="repeatCheck" class="ms-4">
            <span ref="A">A</span>
            <span ref="B">B</span>
          </div>
        </div>
        <div id="time-box">
          <div id="volume-box" class="mx-4 d-flex" @mouseover="onVolumeControl" @mouseleave="offVolumeControl">
            <input type="range" min="0" max="100" :value="volume" id="volume" class="me-3" v-if="isVolumeControl" @mousemove="changeVolume" ref="volume">
            <div style="width: 32px">
              <i class="fas fa-volume-mute fs-3" v-if="this.volume == 0 || isMute" @click="unmute"></i>
              <i class="fas fa-volume-up fs-3" v-else-if="this.volume >= 50 && !isMute" @click="mute"></i>
              <i class="fas fa-volume-down fs-3" v-else-if="!isMute" @click="mute"></i>
            </div>
          </div>
          {{ nowTime }} / {{ endTime }}
        </div>
      </div>
    </div>
    <Feedback ref="feedback" v-if="this.feedbackVisible" :feedbackData="feedbackData" id="feedback-modal"
    @moveFeedback="moveVideo(feedbackData[0])"
    @closeFeedback="hideFeedback()" />
  </div>
</template>

<script>
import Webcam from 'webcam-easy'
import * as posenet from '@tensorflow-models/posenet'
import Feedback from '@/components/practice/Feedback.vue'
import FeedbackCard from '@/components/practice/FeedbackCard.vue'
import SavedFeedbackCard from '@/components/practice/SavedFeedbackCard.vue'

export default {
  components: {
    Feedback,
    FeedbackCard,
    SavedFeedbackCard
  },
  data() {
    return {
      volume: 100,
      tmpVolume: 100,
      isMute: false,
      nowTime: '00:00',
      endTime: '03:41',
      endTimeInt: 221,
      isVolumeControl: false,
      seeing: '',
      notSeeing: [],
      vectorInfos: [[17, 0], [5, 11], [6, 12], [5, 6], [11, 12], [5, 7], [7, 9], [6, 8], [8, 10], [11, 13], [13, 15], [12, 14], [14, 16]],
      vectorNames: { 0: '목', 1: '왼허리', 2: '오른허리', 3: '어깨라인', 4: '힙라인', 5: '왼팔뚝', 6: '왼팔', 
                    7: '오른팔뚝', 8: '오른팔', 9: '왼허벅지', 10:'왼종아리', 11:'오른허벅지', 12:'오른종아리' },
      criticalPoints: { 0: '코', 5: '왼쪽 어깨', 6: '오른쪽 어깨', 7: '왼쪽 팔꿈치', 8: '오른쪽 팔꿈치', 
                                9: '왼쪽 손목', 10: '오른쪽 손목', 11: '왼쪽 엉덩이', 12: '오른쪽 엉덩이', 
                                13: '왼쪽 무릎', 14: '오른쪽 무릎', 15: '왼쪽 발목', 16: '오른쪽 발목'},
      videoSkeleton: [],
      webcamSkeleton: [],
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
    }
  },
  methods: {
    playVideo() {
      clearInterval(this.captureInterval)
      clearInterval(this.timeInterval)
      this.removeFeedbacks(parseInt(this.$refs.video.currentTime))
      this.isPlaying = true
      this.$refs.video.play()
      this.$refs.pause.style = 'color: grey'
      this.$refs.play.style = 'color: red'
      this.timeInterval = setInterval(this.checkTime, 500)
      this.captureInterval = setInterval(this.dancingGo, 2000)
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
        clearInterval(this.captureInterval)
        clearInterval(this.timeInterval)
      }
    },
    pauseVideo() {
      this.isPlaying = false 
      this.$refs.video.pause()
      this.$refs.pause.style = 'color: red'
      this.$refs.play.style = 'color: grey'
      // this.dancingGo()
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
      this.$refs.video.currentTime = xPos/totalLength * 221
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
        this.$refs.A.style = 'color: red'
      } else if (!this.isRepeatEnd) {
        this.isRepeatEnd = true
        this.repeatEndTime = this.$refs.video.currentTime
        this.$refs.B.style = 'color: red'
      } else {
        this.$refs.A.style = 'color: black'
        this.$refs.B.style = 'color: black'
        this.isRepeatEnd = false
        this.isRepeatStart = false
        this.repeatEndTime = 0
        this.repeatStartTime = 0
        this.playVideo()
      }
    },
    repeat() {
      if (this.isRepeatEnd && this.$refs.video.currentTime > this.repeatEndTime) {
        this.$refs.video.currentTime = this.repeatStartTime
        this.playVideo()
      }
    },
    makeFeedback(videoSkeleton, webcamSkeleton) {
      var feedbackTime = parseInt(this.$refs.video.currentTime)
      var video = this.$refs.video
      var videoCanvas = this.$refs.videoCanvas
      var webcam = this.$refs.webcam
      var webcamCanvas = this.$refs.webcamCanvas
      var videoContext = videoCanvas.getContext('2d')
      var webcamContext = webcamCanvas.getContext('2d')
      videoCanvas.width = 300
      videoCanvas.height = 200
      webcamCanvas.width = 300
      webcamCanvas.height = 200
      videoContext.drawImage(video, 0, 0, 300, 200)
      webcamContext.drawImage(webcam, 0, 0, 300, 200)
      var feedbackVideoPreview = videoCanvas.toDataURL('image/png');
      var feedbackWebcamPreview = webcamCanvas.toDataURL('image/png');
      this.feedbacks.push([feedbackTime, this.nowTime, 
      feedbackVideoPreview, feedbackWebcamPreview, 
      videoSkeleton, webcamSkeleton, 
      this.seeing, this.notSeeing, this.seeingVector])
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
    async dancingGo() {
      var video = this.$refs.video
      var videoCanvas = this.$refs.videoCanvas
      var webcam = this.$refs.webcam
      var webcamCanvas = this.$refs.webcamCanvas
      var videoSkeleton = await this.videoCapture(video, videoCanvas)
      if (videoSkeleton === -1) {
        console.log(videoSkeleton)
        console.log('가이드가 인식되지 않았습니다')
      } else {
        var webcamSkeleton = await this.camCapture(webcam, webcamCanvas)
        if (webcamSkeleton !== -1) {
          this.makeFeedback(videoSkeleton, webcamSkeleton)
        } else {
          console.log('선생님의 캠이 정확하지 않습니다')
        }
      }
    },
    // 스샷
    async videoCapture(video, canvas) {
      var context = canvas.getContext('2d')
      canvas.width = video.clientWidth
      canvas.height = video.clientHeight
      context.drawImage(video, 0, 0, video.clientWidth, video.clientHeight)
      const skeleton = await this.getVideoSkeleton(canvas)
      return skeleton
    },
    async camCapture(video, canvas) {
      var context = canvas.getContext('2d')
      canvas.width = video.clientWidth
      canvas.height = video.clientHeight
      context.drawImage(video, 0, 0, video.clientWidth, video.clientHeight)
      const skeleton = await this.getCamSkeleton(canvas)
      return skeleton
    },
    async getVideoSkeleton(imageElement) {
      const vectorInfos = this.vectorInfos
      const criticalPoints = this.criticalPoints
      let arr = []
      let vectorArray = []
      let seeing = []
      let seeingVector = []
      await posenet.load()
      .then(async function(net) {
        const pose = await net.estimateSinglePose(imageElement, {
          flipHorizontal: true
        });
        return pose;
      })
      .then(function(pose){
        console.log(pose)
        for (let i=0; i < pose.keypoints.length; i++) {
          if (Object.keys(criticalPoints).includes(String(i)) && pose.keypoints[i].score > 0.85) {
            seeing.push(i)
          }
          let tx = pose.keypoints[i].position.x
          let ty = pose.keypoints[i].position.y
          arr.push(tx)
          arr.push(ty)

        }
        // 인식 체크
        console.log('seeing')
        console.log(seeing)
        arr.push((arr[10] + arr[12] + arr[22] + arr[24])/4)
        arr.push((arr[11] + arr[13] + arr[23] + arr[25])/4)
        seeing.push(17)
        for (let i=0; i < vectorInfos.length; i++) {
          const vectorInfo = vectorInfos[i]
          if (seeing.includes(vectorInfo[0]) && seeing.includes(vectorInfo[1])) {
            let tx = arr[vectorInfo[0]*2]
            let ty = arr[vectorInfo[0]*2 + 1]
            let tx2 = arr[vectorInfo[1]*2]
            let ty2 = arr[vectorInfo[1]*2 + 1]
            vectorArray.push(tx2-tx)
            vectorArray.push(ty2-ty)
            seeingVector.push(this.vectorNames[i])
          }
        }
      }.bind(this))
      if (seeing.length < 8) {
        return -1
      }
      this.seeing = seeing
      this.seeingVector = seeingVector
      this.videoVectors = vectorArray
      return vectorArray
    },
    async getCamSkeleton(imageElement) {
      const vectorInfos = this.vectorInfos
      let arr = []
      let vectorArray = []
      const seeing = this.seeing
      let notSeeing = []
      await posenet.load()
      .then(async function(net) {
        const pose = await net.estimateSinglePose(imageElement, {
          flipHorizontal: true
        });
        return pose;
      })
      .then(function(pose){
        for (let i=0; i < pose.keypoints.length; i++) {
          if (seeing.includes(i) && pose.keypoints[i].score < 0.7) {
            notSeeing.push(i)
          }
          let tx = pose.keypoints[i].position.x
          let ty = pose.keypoints[i].position.y
          arr.push(tx)
          arr.push(ty)
        }
        // 캠 인식 확인
        arr.push((arr[10] + arr[12] + arr[22] + arr[24])/4)
        arr.push((arr[11] + arr[13] + arr[23] + arr[25])/4)
        for (let i=0; i < vectorInfos.length; i++) {
          const vectorInfo = vectorInfos[i]
          if (seeing.includes(vectorInfo[0]) && seeing.includes(vectorInfo[1])) {
            let tx = arr[vectorInfo[0]*2]
            let ty = arr[vectorInfo[0]*2 + 1]
            let tx2 = arr[vectorInfo[1]*2]
            let ty2 = arr[vectorInfo[1]*2 + 1]
            vectorArray.push(tx2-tx)
            vectorArray.push(ty2-ty)
          }
        }
      }.bind(this))
      if (notSeeing.length > 2) {
        return -1
      }
      this.notSeeing = notSeeing
      this.camVectors = vectorArray
      return vectorArray
    },
    showAllFeedback() {
      this.isTemporaryFeedback = true
    },
    showSavedFeedback() {
      this.isTemporaryFeedback = false
    }
  },
  mounted() {
    this.startCam()
  }
}
</script>

<style>
#background {
  position: absolute;
  left: 0px;
  top: 0px;
  width: 100vw;
  height: 100vh;
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
}

#videoBox {
  width: 560px;
  height: 420px;
  overflow: hidden;
  display: flex;
  justify-content: center;
}

#camBox {
  width: 560px;
  height: 420px;
  overflow: hidden;
  display: flex;
  justify-content: center;
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
</style>