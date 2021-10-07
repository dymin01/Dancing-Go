<template>
  <swiper-slide>
    <!-- {{ music }} -->
    <div id="albumart">
      <v-img :src="'images/musicselect/'+music.fileName+'.png'"></v-img>
      <div class="musicInfo">
        <p v-if="this.$store.getters.langMode=='한국어'">{{ music.songNameKor }}</p>
        <p v-else>{{ music.songNameEng }}</p>
        <p v-if="this.$store.getters.langMode=='한국어'">{{ music.singerKor }}</p>
        <p v-else>{{ music.singerEng }}</p>
      </div>
    </div>
  </swiper-slide>
</template>

<script>
import { SwiperSlide } from 'vue-awesome-swiper'
import 'swiper/css/swiper.css'
export default {
  name: 'Music',
  props: {
    music: {
      type: Object,
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
  display: flex;
  justify-content: center;
  align-items: center;
  width: 180px;
  height: 180px;
  text-align: center;
}
.musicInfo {
  position: absolute;
  bottom: 0;
  line-height: 70%;
  text-shadow: 0 0 4px purple;
  color: white;
  -webkit-text-stroke: 0.3px purple;

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