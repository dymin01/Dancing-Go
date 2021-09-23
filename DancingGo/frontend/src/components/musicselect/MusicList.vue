<template>
  <div>
    <div class="example-3d">
      <swiper class="swiper" :options="swiperOption">
        <swiper-slide>
          <v-img @keyup="sample($event)" src="images/musicselect/nextlevel.png"></v-img>
        </swiper-slide>
        <swiper-slide>
          <v-img @keyup="sample($event)" src="images/musicselect/nextlevel.png"></v-img>
        </swiper-slide>
        <swiper-slide>
          <v-img @keyup="sample($event)" src="images/musicselect/nextlevel.png"></v-img>
        </swiper-slide>
        <swiper-slide>
          <v-img @keyup="sample($event)" src="images/musicselect/nextlevel.png"></v-img>
        </swiper-slide>
        <swiper-slide>
          <v-img @keyup="sample($event)" src="images/musicselect/nextlevel.png"></v-img>
        </swiper-slide>
        <swiper-slide>
          <v-img @keyup="sample($event)" src="images/musicselect/nextlevel.png"></v-img>
        </swiper-slide>
        <swiper-slide>
          <v-img @keyup="sample($event)" src="images/musicselect/nextlevel.png"></v-img>
        </swiper-slide>
        <!-- <div class="swiper-pagination" slot="pagination"></div> -->
      </swiper>
    </div>
    <!-- <div>
      <slick-slide
        ref="gallery"
        :options="topSliderOptions"
        @beforeChange="syncSliders">
        <div v-for="(slide, idx) in scopedData" :key="idx">
          <slick>{{ slide }}</slick>
        </div>

      </slick-slide>
      <slick
        ref="slick"
        :options="slickOptions"
        @afterChange="handleAfterChange"
        @beforeChange="handleBeforeChange"
        @breakpoint="handleBreakpoint"
        @destroy="handleDestroy"
        @edge="handleEdge"
        @init="handleInit"
        @reInit="handleReInit"
        @setPosition="handleSetPosition"
        @swipe="handleSwipe"
        @lazyLoaded="handleLazyLoaded"
        @lazyLoadError="handleLazeLoadError">
        <v-img src="images/musicselect/nextlevel.png"></v-img>
        <v-img src="images/musicselect/nextlevel.png"></v-img>
        <v-img src="images/musicselect/nextlevel.png"></v-img>
      </slick>
    </div> -->
  </div>
</template>

<script>
  import { Swiper, SwiperSlide } from 'vue-awesome-swiper'
  // import Slick from 'vue-slick'
  import 'swiper/css/swiper.css'

  export default {
    name: 'swiper-example-3d-coverflow',
    title: '3D Coverflow effect',
    components: {
      Swiper,
      SwiperSlide,
      // Slick
    },
    data() {
      return {
        scopedData: [

        ],
        slickOptions: {
          slidesToShow: 7,
        },
        swiperOption: {
          // direction: 'vertical',
          effect: 'coverflow',
          loop: true,
          grabCursor: true,
          keyboard: {
            enabled: true,
          },
          centeredSlides: true,
          // centeredSlidesBounds: true,
          slidesPerView: 5,
          coverflowEffect: {
            rotate: 10,
            stretch: 10,
            depth: 100,
            modifier: 1,
            slideShadows : true
          },
          pagination: {
            el: '.swiper-pagination'
          }
        },
      }
    },
    methods: {
      sample (event) {
        console.log(event)
      },
      next() {
            this.$refs.slick.next();
      },

      prev() {
          this.$refs.slick.prev();
      },

      reInit() {
          // Helpful if you have to deal with v-for to update dynamic lists
          this.$nextTick(() => {
              this.$refs.slick.reSlick();
          });
      },

      // Events listeners
      handleAfterChange(event, slick, currentSlide) {
          console.log('handleAfterChange', event, slick, currentSlide);
      },
      handleBeforeChange(event, slick, currentSlide, nextSlide) {
          console.log('handleBeforeChange', event, slick, currentSlide, nextSlide);
      },
      handleBreakpoint(event, slick, breakpoint) {
          console.log('handleBreakpoint', event, slick, breakpoint);
      },
      handleDestroy(event, slick) {
          console.log('handleDestroy', event, slick);
      },
      handleEdge(event, slick, direction) {
          console.log('handleEdge', event, slick, direction);
      },
      handleInit(event, slick) {
          console.log('handleInit', event, slick);
      },
      handleReInit(event, slick) {
          console.log('handleReInit', event, slick);
      },
      handleSetPosition(event, slick) {
          console.log('handleSetPosition', event, slick);
      },
      handleSwipe(event, slick, direction) {
          console.log('handleSwipe', event, slick, direction);
      },
      handleLazyLoaded(event, slick, image, imageSource) {
          console.log('handleLazyLoaded', event, slick, image, imageSource);
      },
      handleLazeLoadError(event, slick, image, imageSource) {
          console.log('handleLazeLoadError', event, slick, image, imageSource);
      },
    }
  }
</script>

<style lang="scss" scoped>
  .example-3d {
    position: absolute;
    width: 100vw;
    height: 100vh;
    top: 50vh;
    left: 0vw;
    padding-top: 50px;
    padding-bottom: 50px;
  }

  .swiper {
    height: 100vh;
    width: 100vw;
    // bottom: 5vh;

    .swiper-slide {
      display: flex;
      justify-content: center;
      align-items: center;
      width: 150px;
      height: 150px;
      overflow: visible;
      // text-align: center;
      // font-weight: bold;
      // // font-size: $font-size-huge * 2;
      // background-color: #2C8DFB;
      // background-position: center;
      // background-size: cover;
      // color: white;
    }

    .swiper-pagination {
      .swiper-pagination-bullet.swiper-pagination-bullet-active {
        background-color: white;
      }
    }
  }
</style>