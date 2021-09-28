<template>
  <v-app>
    <Header
    v-if="isheader" />
    <LanguageSetting
    v-else />
    <router-view/>
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
      isheader: true
    }
  },
  created () {
    if (document.location.pathname === '/') {
      this.isheader = false
    }
  }
};
</script>
