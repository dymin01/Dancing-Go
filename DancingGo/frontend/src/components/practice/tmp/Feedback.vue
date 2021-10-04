<template>
  <div id="feedback-modal" class="d-flex flex-column">
    <div class="d-flex justify-content-between p-3" style="border-bottom: 0px solid black; position: relative;">
      <div style="font-size: 20px; font-weight: bold;">feedback</div>
      <div @click="$emit('closeFeedback')">X</div>
    </div>
    <div class="d-flex justify-content-center" style="height: 35px;">
      <button class="btn btn-success me-2" style="height: 70%; width: 100px; padding: 1px" @click="isSkeleton = false">사진</button>
      <button class="btn btn-primary" style="height: 70%; width: 100px; padding: 1px" @click="isSkeleton = true">스켈레톤</button>
    </div>
    <div class="d-flex justify-content-around p-4" v-if="isSkeleton">
      <VideoSkeletonShape />
      <WebcamSkeletonShape />
    </div>
    <div class="d-flex justify-content-around p-4" v-else>
      <img :src="feedbackData[2]" alt="">
      <img :src="feedbackData[3]" style="transform: scaleX(-1);" alt="">
    </div>
    <div class="p-5 d-flex justify-content-between">
      <div>
        맞은 부위: {{ this.correct }}
        틀린 부위: {{ this.fail }}
      </div>
      <div>
        ????
      </div>
      <button @click="$emit('moveFeedback')">확인하기</button>
    </div>
  </div>
</template>

<script>
import VideoSkeletonShape from '@/components/practice/VideoSkeletonShape.vue'
import WebcamSkeletonShape from '@/components/practice/WebcamSkeletonShape.vue'

export default {
  data() {
    return {
      vectorNames: { 0: '목', 1: '우측 허리', 2: '좌측 허리', 3: '어깨 라인', 4: '힙 라인', 5: '오른 팔뚝', 6: '오른 팔', 
                    7: '왼 팔뚝', 8: '왼 팔', 9: '오른 허벅지', 10:'오른 종아리', 11:'왼 허벅지', 12:'왼 종아리'},
      seeing: [],
      notSeeing: [],
      feedbackString: [],
      correct: [],
      fail: [],
      isSkeleton: false,
    }
  },
  props: {
    feedbackData: Array,
  },
  components: {
    VideoSkeletonShape,
    WebcamSkeletonShape,
  },
  watch: {
    feedbackData: function() {
      const feedbackString = []
      const videoSkeleton = this.feedbackData[4]
      const camSkeleton = this.feedbackData[5]
      const correct = []
      const fail = []
      for (let i=0; i < Object.keys(this.vectorNames).length; i++) {
        if (videoSkeleton[i] != null) {
          if (camSkeleton[i] != null) {
            const x1 = videoSkeleton[i][0]
            const y1 = videoSkeleton[i][1]
            const x2 = camSkeleton[i][0]
            const y2 = camSkeleton[i][1]
            const angle = Math.acos((x1*x2 + y1*y2)/(((x1**2 + y1**2)**0.5) * ((x2**2 + y2**2)**0.5)))*(180/Math.PI)
            if (angle > 10) {
              fail.push(this.vectorNames[i] + '가 틀렸습니다. 각도: ' + angle)
            } else {
              correct.push(this.vectorNames[i] + '가 맞았습니다. 각도: ' + angle)
            }
            feedbackString.push(this.vectorNames[i] + '의 각도 : ' + angle)
          } else {
            feedbackString.push(this.vectorNames[i] + '가 웹캠에서 보이지 않습니다')
          }
        } else {
          feedbackString.push(this.vectorNames[i] + '가 가이드에서 보이지 않습니다')
        }
      }
      this.correct = correct
      this.fail = fail
    }
  }
}
</script>

<style>
#feedback-modal {
  position: absolute;
  width: 70vw;
  height: 86vh;
  border: 2px solid black;
  left: 15vw;
  top: 7vh;
  border-radius: 10px;
  background-color: white;
}

i:hover {
  cursor: pointer;
}
</style>