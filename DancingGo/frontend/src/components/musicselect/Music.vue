<template>
  <swiper-slide>
    <v-img id="albumart" :src="'images/musicselect/'+fileName[0]+'.png'"></v-img>
  </swiper-slide>
</template>

<script>
import { SwiperSlide } from 'vue-awesome-swiper'
import 'swiper/css/swiper.css'
export default {
  name: 'Music',
  props: {
    fileName: {
      type: Array,
    }
  },
  components: {
    SwiperSlide
  }
}
</script>

<style lang="scss" scoped>
#albumart {
  box-shadow: 0 0 20px white;
  border-radius: 2%;
}
.swiper-wrapper {
    width: 100%;
    height: 250px;
    padding-top: 50px;
    // padding-bottom: 50px;
    position: absolute;
    bottom: 0vh;
    display: flex;
    align-items: center;
  }
  .swiper {
    height: 100%;
    width: 100%;
    overflow: visible;

    .swiper-slide {
      display: flex;
      justify-content: center;
      align-items: center;
      width: 180px;
      height: 180px;
      text-align: center;
      font-weight: bold;
      // font-size: $font-size-huge * 2;
      background-color: #2C8DFB;
      background-position: center;
      background-size: cover;
      // color: $white;
      overflow: visible;
    }
  }
</style>