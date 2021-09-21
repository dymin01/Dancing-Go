<template>
  <carousel-3d id="carousel" :width="600" :height="400">
    <slide :index="0" class="slide">
        <img src="images/main/main_background.jpg" alt="">
    </slide>
    <slide :index="1" class="slide">
        <img src="images/home/home_background.jpg" alt="">
    </slide>
    <slide :index="2" class="slide">
      <img src="images/home/sample.jpg" alt="">
    </slide>
  </carousel-3d>
</template>

<script>
import { Carousel3d, Slide } from 'vue-carousel-3d'
export default {
  name: 'ModeSelect',
  components: {
    Carousel3d,
    Slide,
  }
}
</script>

<style>
#carousel {
  position: absolute;
  bottom: 20vh;
  overflow: visible;
}
.slide {
  box-shadow: 0 0 20px white;
  border-radius: 2%;
}
</style>