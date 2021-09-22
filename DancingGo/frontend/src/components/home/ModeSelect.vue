<template>
  <carousel-3d id="carousel" :width="600" :height="400">
    <slide :index="0" class="slide">
      <img  @click="isCurrent($event, 'MusicSelect')" src="images/main/main_background.jpg" alt="practicemode_img">
    </slide>
    <slide :index="1" class="slide">
        <img @click="isCurrent($event, 'MusicSelect')" src="images/home/home_background.jpg" alt="rankingmode_img">
    </slide>
    <slide :index="2" class="slide">
      <img @click="isCurrent($event, 'HallofFame')" src="images/home/sample.jpg" alt="halloffame_img">
    </slide>
  </carousel-3d>
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
    isCurrent(event, url) {
      if (event.path[1].classList.contains('current')) {
        router.push({ name: url })
      }
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
</style>