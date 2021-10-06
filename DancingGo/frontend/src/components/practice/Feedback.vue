<template>
  <div id="feedback-modal" class="d-flex flex-column elevation-20">
    <div class="d-flex justify-content-between p-3" style="border-bottom: 0px solid black; position: relative;">
      <div style="font-size: 20px; font-weight: bold;">feedback</div>
      <div @click="$emit('closeFeedback')">X</div>
    </div>
    <div class="d-flex justify-content-around p-4">
      <img :src="feedbackData[2]" alt="" id="feedback-video">
      <WebcamSkeletonShape :angles="this.angles" />
      <img :src="feedbackData[3]" style="transform: scaleX(-1);" alt="" id="feedback-webcam">
    </div>
    <div class="p-5 d-flex justify-content-between">
      <div>
        틀린 부위: {{ this.fail }}
      </div>
      <button class="btn btn-success" @click="$emit('moveFeedback')">확인하기</button>
    </div>
  </div>
</template>

<script>
import WebcamSkeletonShape from '@/components/practice/WebcamSkeletonShape.vue'

export default {
  data() {
    return {
      vectorNames: { 0: '목', 1: '우측 허리', 2: '좌측 허리', 3: '어깨 라인', 4: '힙 라인', 5: '오른 팔뚝', 6: '오른 팔', 
                    7: '왼 팔뚝', 8: '왼 팔', 9: '오른 허벅지', 10:'오른 종아리', 11:'왼 허벅지', 12:'왼 종아리'},
      seeing: [],
      notSeeing: [],
      fail: [],
      angles: [],  
    }
  },
  props: {
    feedbackData: Array,
  },
  components: {
    WebcamSkeletonShape,
  },
  watch: {
    feedbackData: function() {
      const videoSkeleton = this.feedbackData[4]
      const camSkeleton = this.feedbackData[5]
      const fail = []
      const angles = []
      for (let i=0; i < Object.keys(this.vectorNames).length; i++) {
        console.log(i)
        if (videoSkeleton[i] != null) {
          if (camSkeleton[i] != null) {
            const x1 = videoSkeleton[i][0]
            const y1 = videoSkeleton[i][1]
            const x2 = camSkeleton[i][0]
            const y2 = camSkeleton[i][1]
            const angle = Math.acos((x1*x2 + y1*y2)/(((x1**2 + y1**2)**0.5) * ((x2**2 + y2**2)**0.5)))*(180/Math.PI)
            angles.push(angle)
            if (angle > 10) {
              fail.push(this.vectorNames[i] + '가 틀렸습니다. 각도: ' + angle)
            }
          } else {
            angles.push(null)
          }
        } else {
          angles.push(null)
        }
      }
      this.fail = fail
      this.angles = angles
    }
  },
  mounted() {
    const videoSkeleton = this.feedbackData[4]
    const camSkeleton = this.feedbackData[5]
    const fail = []
    const angles = []
    for (let i=0; i < Object.keys(this.vectorNames).length; i++) {
      console.log(i)
      if (videoSkeleton[i] != null) {
        if (camSkeleton[i] != null) {
          const x1 = videoSkeleton[i][0]
          const y1 = videoSkeleton[i][1]
          const x2 = camSkeleton[i][0]
          const y2 = camSkeleton[i][1]
          const angle = Math.acos((x1*x2 + y1*y2)/(((x1**2 + y1**2)**0.5) * ((x2**2 + y2**2)**0.5)))*(180/Math.PI)
          angles.push(angle)
          if (angle > 10) {
            fail.push(this.vectorNames[i] + '가 틀렸습니다. 각도: ' + angle)
          }
        } else {
          angles.push(null)
        }
      } else {
        angles.push(null)
      }
    }
    this.fail = fail
    this.angles = angles
  }
}
</script>

<style>
#feedback-modal {
  position: absolute;
  width: 80vw;
  height: 86vh;
  left: 10vw;
  top: 7vh;
  border: 3px solid black;
  border-radius: 10px;
  background-color: rgb(184, 145, 199);
}

i:hover {
  cursor: pointer;
}

#feedback-video {
  width: 400px;
  height: 300px;
  object-fit: cover;
}

#feedback-webcam {
  width: 400px;
  height: 300px;
  object-fit: cover;
}
</style>