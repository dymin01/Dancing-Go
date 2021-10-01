<template>
  <v-app>
    <div v-if="isRealHeader">
      <Header
      v-if="isheader" />
      <LanguageSetting
      v-else />
    </div>
    <router-view />
    <audio src="songs/background.mp3" ref="background" autoplay></audio>
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
      isheader: true
    }
  },
  created () {
    var path = document.location.pathname
    console.log(path)
    if (path === '/') {
      this.isheader = false
    }
    if (path.includes('ranking') || path.includes('practice')) {
      this.isRealHeader = false
    }
  }
};
</script>
