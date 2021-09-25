<template>
  <v-app>
    <Header
    v-if="isheader" />
    <router-view/>
  </v-app>
</template>

<script>
import Header from '@/views/Header.vue'
export default {
  name: 'App',
  components: {
    Header
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
