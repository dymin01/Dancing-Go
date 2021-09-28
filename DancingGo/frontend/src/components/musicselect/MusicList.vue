
<template>
  <div>
    <!-- <img id="rankIcon" :src="'images/musicselect/ranking.png'" @click="openRink" alt=""> -->
    <!-- <div id="now" @click="goToPractice(musics[activeIndex].songId)"></div> -->
    <div id="now">
      <!-- {{ musics[activeIndex].fileName }} -->
      <div id="difficulty">
        <span v-if="musics[activeIndex].difficulty===1">★</span>
        <span v-else-if="musics[activeIndex].difficulty===2">★★</span>
        <span v-else-if="musics[activeIndex].difficulty===3">★★★</span>
      </div>
      <img id="now-image" @click="openModal" :src="'images/musicselect/'+musics[activeIndex].fileName+'.png'" alt="">
      <!-- 연습모드 일 때 곡 정보 -->
      <div v-if="mode==='Practice'" id="active-music-info" class="text-center my-3">
        <p v-if="this.$store.getters.langMode=='한국어'">{{ musics[activeIndex].songNameKor }}</p>
        <p v-else>{{ musics[activeIndex].songNameEng }}</p>
        <p v-if="this.$store.getters.langMode=='한국어'">{{ musics[activeIndex].singerKor }}</p>
        <p v-else>{{ musics[activeIndex].singerEng }}</p>

      </div>
      <!-- 랭킹모드 일 때 곡 정보 -->
      <div v-else id="active-music-info" class="text-center my-3">
        <p v-if="this.$store.getters.langMode=='한국어'">
          {{ musics[activeIndex].songNameKor }}-{{ musics[activeIndex].singerKor }}</p>
        <p v-else>{{ musics[activeIndex].songNameEng }}-{{ musics[activeIndex].singerEng }}</p>
        <!-- <p>{{ musics[activeIndex].singerKor }}</p> -->
        <p v-if="this.$store.getters.langMode=='한국어'">곡별 최고 점수:</p>
        <p v-else>Highest score:</p>
        <v-btn
          v-if="this.$store.getters.langMode=='한국어'"
          @click="openRink"
          small
          id="button"
          style="opacity: 80%;">
          랭킹 확인 🏆
          <!-- <img id="rankIcon" :src="'images/musicselect/ranking.png'" @click="openRink" alt=""> -->
        </v-btn>
        <v-btn
          v-else
          @click="openRink"
          small
          id="button"
          style="opacity: 80%;">
          View Ranks 🏆
          <!-- <img id="rankIcon" :src="'images/musicselect/ranking.png'" @click="openRink" alt=""> -->
        </v-btn>
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
    </div>

    <v-dialog
      v-model="isRankOpen"
      max-width="500px"
      > <SongRank 
      :songId="musics[activeIndex].songId"
      @closeRank="isRankOpen" class="mypageModal"/>
    </v-dialog>
    <v-dialog
      v-model="isModalOpen"
      max-width="350px">
      <Modal
        v-if="this.$store.getters.langMode=='한국어'"
        :modalTitle="'알림'"
        :modalContent="'댄스 한 판 즐겨볼까요?💃'"
        :buttonO="'시작'"
        :buttonX="'취소'"
        @clickO="goToGame(musics[activeIndex].songId)"
        @clickX="closeModal"
      />
      <Modal
        v-else
        :modalTitle="'Notice'"
        :modalContent="'Let\'s enjoy a dance!💃'"
        :buttonO="'Start'"
        :buttonX="'Cancel'"
        @clickO="goToGame(musics[activeIndex].songId)"
        @clickX="closeModal"
      />
    </v-dialog>
  </div>
</template>

<script>
  import { Swiper } from 'vue-awesome-swiper'
  import Music from '@/components/musicselect/Music.vue'
  import Modal from '@/components/Modal.vue'
  import 'swiper/css/swiper.css'
  import { mapState } from 'vuex'
  import SongRank from '@/components/musicselect/songRank.vue'

  export default {
    name: 'MusicList',
    components: {
      Swiper,
      // SwiperSlide,
      Music,
      SongRank,
      Modal,
    },
    props: {
      mode: {
        type: String
      }
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
        isModalOpen: false,
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
        },
        isRankOpen: false
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
      },
      songRank (songId) {
        alert(songId);
      },
      openRink () {
        this.isRankOpen = true
      },
      closeRink() {
        this.isRankOpen = false
      },
      openModal () {
        this.isModalOpen = true
      },
      closeModal () {
        this.isModalOpen = false
      },
      goToGame (songId) {
        // console.log(this.mode)
        this.$router.push({ name: this.mode, params: { songId: songId } })
      }
    },
    created () {
      this.$store.dispatch('music/setMusics')
      // this.$refs.musiclist.$swiper.mousewheel.enable()
    }
  }
</script>

<style lang="scss" scoped>
  #button {
  color: white;
  background-color: black;
  box-shadow: 0 0 10px white;
  }

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
      cursor: pointer;
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

  #rankIcon {
    // position: absolute;
    object-fit: contain;
    width: 8vh;
    height: 8vh;
    // top: 28%;
    // left: 60%;
    /* 커서 올리면 손가락 */
    cursor: pointer;
  }
</style>