<template>
  <div>
    <v-img id="background" src="images/home/home_background.jpg"></v-img>
    <button @click="logout">로그아웃</button>
    <ModeSelect />
  </div>
</template>

<script>
import ModeSelect from '@/components/home/ModeSelect.vue'
import { mapMutations } from 'vuex'

export default {
  name: 'Home',
  components: {
    ModeSelect
  },
  methods: {
    ...mapMutations(['setToken', 'setUser']),
    logout () {
      this.setToken(null)
      this.setUser(null)
      alert('로그아웃되었습니다.')
      if (this.$route.path !== '/') this.$router.push('/')
    }
  }
}
</script>

<style scoped>
#background {
  width: 100vw;
  height: 100vh;
}
</style>