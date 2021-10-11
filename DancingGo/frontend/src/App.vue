<template>
  <v-app>
    <div v-if="isRealHeader">
      <Header
      v-if="isheader" />
      <LanguageSetting
      v-else />
    </div>
    <router-view />
    <!-- <audio src="songs/background.mp3" ref="background" autoplay></audio> -->
  </v-app>
</template>

<script>
import Header from '@/views/Header.vue'
import LanguageSetting from '@/views/LanguageSetting.vue'
export default {
  name: 'App',
  components: {
    Header,
    LanguageSetting
  },
  data () {
    return {
      isRealHeader: true,
      isheader: true,
    }
  },
  watch:{
    $route(to, from){
      if (to.path === '/') {
      this.isheader = false
    }
      if(to.path.includes('ranking') || to.path.includes('practice')){
        this.isRealHeader = false
      }else{
        this.isRealHeader = true
      }
    }
  },
  created () {
    localStorage.setItem('backgroundVolume', 50)
    localStorage.setItem('effectVolume', 50)
  },
  mounted () {
    const path = document.location.pathname
    this.path = path
    if (path === '/') {
      this.isheader = false
    }
    if (path.includes('ranking') || path.includes('practice')) {
      this.isRealHeader = false
    }
  }
};
</script>
