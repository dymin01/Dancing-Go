<template>
  <div id="feedback-modal" class="d-flex flex-column justify-content-between">
    <div class="d-flex justify-content-between p-4" style="border-bottom: 1px solid black;">
      <div>feedback</div>
      <div @click="$emit('closeFeedback')">X</div>
    </div>
    <div class="d-flex justify-content-around p-4">
      <img :src="feedbackData[2]" alt="">
      <img :src="feedbackData[3]" style="transform: rotateY(180deg);" alt="">
    </div>
    <div class="p-5 d-flex justify-content-between">
      <div>
        <div>보이는 부위: {{this.seeing}}</div>
        <div>보이지 않는 부위: {{this.notSeeing}}</div>
        <div>각도가 10도 이상 차이나는 부위: {{this.feedbackString}}</div>
      </div>
      <button @click="$emit('moveFeedback')">확인하기</button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      vectorInfos: [[17, 0], [5, 11], [6, 12], [5, 6], [11, 12], [5, 7], [7, 9], [6, 8], [8, 10], [11, 13], [13, 15], [12, 14], [14, 16]],
      vectorNames: { 0: '목', 1: '왼허리', 2: '오른허리', 3: '어깨라인', 4: '힙라인', 5: '왼팔뚝', 6: '왼팔', 
                    7: '오른팔뚝', 8: '오른팔', 9: '왼허벅지', 10:'왼종아리', 11:'오른허벅지', 12:'오른종아리' },
      criticalPoints: { 0: '코', 5: '왼쪽 어깨', 6: '오른쪽 어깨', 7: '왼쪽 팔꿈치', 8: '오른쪽 팔꿈치', 
                                9: '왼쪽 손목', 10: '오른쪽 손목', 11: '왼쪽 엉덩이', 12: '오른쪽 엉덩이', 
                                13: '왼쪽 무릎', 14: '오른쪽 무릎', 15: '왼쪽 발목', 16: '오른쪽 발목', 17: '몸통'},
      seeing: [],
      notSeeing: [],
      feedbackString: []
    }
  },
  props: {
    feedbackData: Array,
  },
  mounted() {
    const videoSkeleton = this.feedbackData[4]
    const camSkeleton = this.feedbackData[5]
    const videoSeeing = this.feedbackData[6]
    const camNotSeeing = this.feedbackData[7]
    console.log(videoSkeleton)
    console.log(camSkeleton)
    for (let i=0; i < videoSeeing.length; i++) {
      this.seeing.push(this.criticalPoints[videoSeeing[i]])
    }
    for (let k=0; k < camNotSeeing.length; k++) {
      this.notSeeing.push(this.criticalPoints[camNotSeeing[k]])
    }
    for (let j=0; j < Object.keys(videoSkeleton).length / 2; j++) {
      const x1 = videoSkeleton[j*2]
      const y1 = videoSkeleton[j*2+1]
      const x2 = camSkeleton[j*2]
      const y2 = camSkeleton[j*2+1]
      const angle = Math.acos((x1*x2 + y1*y2)/(((x1**2 + y1**2)**0.5) * ((x2**2 + y2**2)**0.5)))*(180/Math.PI)
      // console.log('10도이상 틀린 부위: ' + this.feedbackData[8][j])
      // console.log(angle)
      if (angle > 10) {
        this.feedbackString.push(this.feedbackData[8][j])
        console.log('10도이상 틀린 부위: ' + this.feedbackData[8][j])
        console.log(angle)
      }
    }
  }
}
</script>

<style>
#feedback-modal {
  position: absolute;
  width: 80vw;
  height: 80vh;
  border: 2px solid black;
  left: 10vw;
  top: 10vh;
  border-radius: 10px;
  background-color: white;
}
</style>