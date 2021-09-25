
<template>
  <div>
    <div id="now" @click="goToPractice(musics[activeIndex].songId)">
      <!-- {{ musics[activeIndex].fileName }} -->
      <div id="difficulty">
        <span v-if="musics[activeIndex].difficulty===1">★</span>
        <span v-else-if="musics[activeIndex].difficulty===2">★★</span>
        <span v-else-if="musics[activeIndex].difficulty===3">★★★</span>
      </div>
      <img :src="'images/musicselect/'+musics[activeIndex].fileName+'.png'" alt="">
      <div id="active-music-info" class="text-center my-3">
        <p>{{ musics[activeIndex].songNameKor }}</p>
        <p>{{ musics[activeIndex].singerKor }}</p>
      </div>
    </div>
    <div class="example-3d">
      <swiper class="swiper" ref="musiclist" @slide-change-transition-end="getActive" :options="swiperOptionThumbs">
        <Music
          v-for="(music, idx) in musics"
          :key="idx"
          :music="music"
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
  import { mapState } from 'vuex'

  export default {
    name: 'MusicList',
    components: {
      Swiper,
      // SwiperSlide,
      Music,
    },
    computed: {
      ...mapState('music', {
        musics: state => state.musics
      }),
      activeIndex () {
        return this.activeIdx
      }
    },
    data() {
      return {
        activeIdx: 0,
        // musics: [['nextlevel', 0], ['permissiontodance', 1], ['nextlevel', 2], ['permissiontodance', 3],
        //         ['nextlevel', 4], ['permissiontodance', 5], ['nextlevel', 6], ['permissiontodance', 7]],
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
          },
          slideToClickedSlide: true
          // pagination: {
          //   el: '.swiper-pagination'
          // }
        }
      }
    },
    methods: {
      getActive () {
        const swiper = this.$refs.musiclist.$swiper
        this.activeIdx = swiper.activeIndex
        // console.log(this.activeIdx)
        // const selected = document.querySelector("div.swiper-slide-active div.v-image div.v-image__image")
        // const imgUrl = selected.style.backgroundImage.split('"')[1]
        // const centerImg = document.querySelector('#now')
        // if (centerImg.hasChildNodes()) {
        //   centerImg.removeChild(centerImg.childNodes[0])
        // }
        // const newImg = document.createElement("img")
        // newImg.src = imgUrl
        // newImg.width = 200
        // newImg.height = 200
        // centerImg.appendChild(newImg)
      },
      goToPractice (songId) {
        this.$router.push({ name: 'Practice', params: { songId: songId } })
      }
    },
    created () {
      this.$store.dispatch('music/setMusics')
      // this.$refs.musiclist.$swiper.mousewheel.enable()
    }
  }
</script>

<style lang="scss" scoped>
  #now {
    // background-color: black;
    background-color: rgba( 0, 0, 0, 0.8 );
    // width: 200px;
    // height: 200px;
    position: absolute;
    top: 15vh;
    left: 43%;
    box-shadow: 0 0 20px white;
    border-radius: 2%;

    img {
      width: 200px;
      height: 200px;
      border-radius: 2%;
    }
  }

  #difficulty {
    // background-color: rgba( 0, 0, 0, 0.5 );
    // border-radius: 10%;
    text-shadow: 0 0 4px yellow;
    color: white;
    -webkit-text-stroke: 0.3px purple;
    font-size: 30pt;
    position: absolute;
    transform: translate(-50%, -50%);
    left: 50%;
  }

  #active-music-info {
    line-height: 70%;
    text-shadow: 0 0 4px purple;
    color: white;
    -webkit-text-stroke: 0.3px purple;
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
    // align-items: center;
    width: 100%;
    height: 250px;
    // padding-top: 50px;
    // padding-bottom: 50px;
    position: absolute;
    bottom: 0vh;
    display: flex;
    object-fit: visible;
    // vertical-align: middle;
    // align-items: center;
    // margin: auto;
  }
  .swiper {
    height: 100%;
    width: 100%;
    vertical-align: middle;

    .swiper-slide {
      display: flex;
      justify-content: center;
      // align-items: center;
      width: 180px;
      height: 180px;
      text-align: center;
      font-weight: bold;
      // font-size: $font-size-huge * 2;
      background-color: #2C8DFB;
      background-position: center;
      background-size: cover;
      // color: $white;
      border-radius: 2%;
    }

    // .swiper-pagination {
    //   .swiper-pagination-bullet.swiper-pagination-bullet-active {
    //     // background-color: $white;
    //   }
    // }
  }
</style>