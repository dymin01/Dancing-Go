<template>
  <v-app>
    <div v-if="isRealHeader">
      <Header
      v-if="isheader" />
      <LanguageSetting
      v-else />
    </div>
    <router-view />
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
      console.log("from " + from.path)
      console.log("to " + to.path)
      if(to.path.includes('ranking') || to.path.includes('practice')){
        this.isRealHeader = false
      }else{
        this.isRealHeader = true
      }
    }
  },
  mounted () {
    const path = document.location.pathname
    this.path = path
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
