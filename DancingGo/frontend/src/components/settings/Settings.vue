<template>
  <v-card class="text-center" id="settings">
    <v-card-title 
      v-if="this.$store.getters.langMode=='한국어'"
      class="text-h5 justify-center txt">
      환경설정
    </v-card-title>
    <v-card-title 
      v-else class="text-h5 justify-center txt">
      Settings
    </v-card-title>
    <hr style="margin: 0px; background: white;">
    <v-card-text>
      <h6 
      v-if="this.$store.getters.langMode=='한국어'"
      style="color: white;" class="txt mt-4 mb-5">배경음 볼륨 조절</h6>
      <h6 v-else style="color: white;" class="txt mt-4 mb-5">Background sound Control</h6>
      <v-slider
        color="white"
        @change="changeBackgroundVol($event)"
        v-model="backgroundVolume"
        step="10"
        ticks
      ></v-slider>
      <h6
      v-if="this.$store.getters.langMode=='한국어'"
      style="color: white;" class="mt-4 mb-5">효과음 볼륨 조절</h6>
      <h6 v-else style="color: white;" class="txt mt-4 mb-5">Effect sound Control</h6>
      <v-slider
        color="white"
        @change="changeEffectVol($event)"
        v-model="effectVolume"
        step="10"
        ticks
      ></v-slider>
    </v-card-text>
    <!-- <v-btn
      id="button"
      @click="checkNickname"
    >
      확인
    </v-btn> -->
  </v-card>
</template>

<script>
import { mapMutations } from 'vuex'
export default {
  name: 'Settings',
  data () {
    return {
      backgroundVolume: this.$store.getters.backgroundVolume,
      effectVolume: this.$store.getters.effectVolume,
    }
  },
  watch: {
    backgroundVolume: function (val) {
      // localStorage.setItem('backgroundVolume', val)
      this.changeBackgroundVol(val)
    },
    effectVolume: function (val) {
      // localStorage.setItem('effectVolume', val)
      this.changeEffectVol(val)
    }
  },
  computed: {
    // ...mapGetters(['backgroundVolume', 'effectVolume']),
  },
  methods: {
    ...mapMutations(['CHANGEVOLUMEBACKGROUND', 'CHANGEVOLUMEEFFECT']),
    changeBackgroundVol (event) {
      // console.log('event', event)
      this.CHANGEVOLUMEBACKGROUND(event)
    },
    changeEffectVol (event) {
      this.CHANGEVOLUMEEFFECT(event)
    }
  },
  created() {

  }
}
</script>

<style>
#settings {
  background-color: rgba(43, 29, 59, 0.8);
  color: white;
  /* text-shadow: 0 0 5px gray; */
}
#button {
  color: white;
  background-color: purple;
  box-shadow: 0 0 15px white;
}
#txt {
  text-shadow: 0 0 10px purple;
}
</style>