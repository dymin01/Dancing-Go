<template>
  <div>
    <carousel-3d id="carousel" :width="600" :height="400" @before-slide-change="soundEffect">
      <slide :index="0" class="slide">
        <img  @click="isCurrent($event, 'MusicSelect', 'Practice')" src="images/home/dance4.jpg" alt="practicemode_img">
        <h2 
        v-if="this.$store.getters.langMode=='한국어'"
        class="text-center modeText">연습모드</h2>
        <h2 v-else class="text-center modeText">Practice mode</h2>
      </slide>
      <slide :index="1" class="slide">
          <img @click="isCurrent($event, 'MusicSelect', 'Ranking')" src="images/home/stage4.jpg" alt="rankingmode_img">
          <h2
          v-if="this.$store.getters.langMode=='한국어'"
          class="modeText">랭킹모드</h2>
          <h2 v-else class="modeText">Rank mode</h2>
      </slide>
      <slide :index="2" class="slide">
        <img @click="isCurrent($event, 'HallofFame', 'HallofFame')" src="images/home/hall2.jpg" alt="halloffame_img">
        <h2
        v-if="this.$store.getters.langMode=='한국어'"
        class="modeText">명예의전당</h2>
        <h2 v-else class="modeText">Hall of Fame</h2>
      </slide>
    </carousel-3d>
    <!-- 선택 시 효과음 -->
    <audio src="sounds/select.wav" ref="selecteffect"></audio>
  </div>
</template>

<script>
import { Carousel3d, Slide } from 'vue-carousel-3d'
import router from '@/router/index.js'
export default {
  name: 'ModeSelect',
  components: {
    Carousel3d,
    Slide,
  },
  methods: {
    // 현재 슬라이드가 가운데 있으면 해당하는 라우터로 이동하는 함수
    isCurrent(event, url, Mode) {
      if (event.path[1].classList.contains('current')) {
        if (Mode === 'Practice' || Mode === 'Ranking') {
          router.push({ name: url, query: {'mode': Mode} })
        } else {
          router.push({ name: url })
        }
      }
    },
    soundEffect () {
      this.$refs.selecteffect.play()
    }
  }
}
</script>

<style>
#carousel {
  position: absolute;
  bottom: 15vh;
  overflow: visible;
}
.slide {
  box-shadow: 0 0 20px white;
  border-radius: 2%;
  cursor: pointer;
}
.modeText {
  z-index: 50;
  color: white;
  position: absolute;
  top: 70%;
  left: 50%;
  transform: translate(-50%, 50%);
  text-shadow: 0 0 20px purple;
  font-size: 30pt;
}
</style>