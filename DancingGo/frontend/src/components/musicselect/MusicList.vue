
<template>
  <div>
    <div id="now">
      <img :src="'images/musicselect/'+musics[0][0]+'.png'" alt="">
    </div>
    <div class="example-3d">
      <swiper class="swiper" @slide-change-transition-end="getActive" :options="swiperOptionThumbs">
        <Music
          v-for="(music, idx) in musics"
          :key="idx"
          :fileName="music"
        />
      </swiper>

      <!-- <swiper class="swiper" :options="swiperOptionThumbs">
        <swiper-slide><v-img src="images/musicselect/permissiontodance.png"></v-img></swiper-slide>
        <swiper-slide><v-img src="images/musicselect/nextlevel.png"></v-img></swiper-slide>
        <swiper-slide><v-img src="images/musicselect/permissiontodance.png"></v-img></swiper-slide>
        <swiper-slide><v-img src="images/musicselect/nextlevel.png"></v-img></swiper-slide>
        <swiper-slide><v-img src="images/musicselect/permissiontodance.png"></v-img></swiper-slide>
        <swiper-slide><v-img src="images/musicselect/nextlevel.png"></v-img></swiper-slide>
        <swiper-slide><v-img src="images/musicselect/permissiontodance.png"></v-img></swiper-slide>
        <swiper-slide><v-img src="images/musicselect/nextlevel.png"></v-img></swiper-slide>
        <swiper-slide><v-img src="images/musicselect/permissiontodance.png"></v-img></swiper-slide>
        <swiper-slide><v-img src="images/musicselect/nextlevel.png"></v-img></swiper-slide>
        <swiper-slide><v-img src="images/musicselect/permissiontodance.png"></v-img></swiper-slide>
        <swiper-slide><v-img src="images/musicselect/nextlevel.png"></v-img></swiper-slide>
        <swiper-slide><v-img src="images/musicselect/permissiontodance.png"></v-img></swiper-slide>
        <div class="swiper-pagination" slot="pagination"></div>
      </swiper> -->
    </div>
  </div>
</template>

<script>
  import { Swiper } from 'vue-awesome-swiper'
  import Music from '@/components/musicselect/Music.vue'
  import 'swiper/css/swiper.css'

  export default {
    name: 'MusicList',
    components: {
      Swiper,
      // SwiperSlide,
      Music,
    },
    data() {
      return {
        musics: [['nextlevel', 0], ['permissiontodance', 1], ['nextlevel', 2], ['permissiontodance', 3],
                ['nextlevel', 4], ['permissiontodance', 5], ['nextlevel', 6], ['permissiontodance', 7]],
        swiperOptionTop: {
          spaceBetween: 10
        },
        swiperOptionThumbs: {
          effect: 'coverflow',
          grabCursor: true,
          centeredSlides: true,
          slidesPerView: 'auto',
          // loop: true,
          // loopedSlides: 2,
          loopAdditionalSlides: 3,
          coverflowEffect: {
            rotate: 0,
            stretch: 0,
            depth: 100,
            modifier: 1,
            slideShadows : true
          },
          keyboard: {
            enabled: true
          }
          // pagination: {
          //   el: '.swiper-pagination'
          // }
        }
      }
    },
    methods: {
      getActive () {
        const selected = document.querySelector("div.swiper-slide-active div.v-image div.v-image__image")
        const imgUrl = selected.style.backgroundImage.split('"')[1]
        const centerImg = document.querySelector('#now')
        if (centerImg.hasChildNodes()) {
          centerImg.removeChild(centerImg.childNodes[0])
        }
        const newImg = document.createElement("img")
        newImg.src = imgUrl
        newImg.width = 200
        newImg.height = 200
        centerImg.appendChild(newImg)
      }
    }
  }
</script>

<style lang="scss" scoped>
  #now {
    width: 200px;
    height: 200px;
    position: absolute;
    top: 15vh;
    left: 43%;
    box-shadow: 0 0 20px white;
    border-radius: 2%;

    img {
      width: 200px;
      height: 200px;
    }
  }
  .example-3d {
    width: 100%;
    height: 250px;
    // padding-top: 50px;
    // padding-bottom: 50px;
    position: absolute;
    bottom: 0vh;
  }
  .swiper-wrapper {
    align-items: center;
    width: 100%;
    height: 250px;
    // padding-top: 50px;
    // padding-bottom: 50px;
    position: absolute;
    bottom: 0vh;
    // display: flex;
    object-fit: visible;
  }
  .swiper {
    height: 100%;
    width: 100%;

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
    }

    // .swiper-pagination {
    //   .swiper-pagination-bullet.swiper-pagination-bullet-active {
    //     // background-color: $white;
    //   }
    // }
  }
</style>