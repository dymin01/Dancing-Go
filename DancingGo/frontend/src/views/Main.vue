<template>
  <div>
    <v-img id="background" src="images/main_background.jpg"></v-img>
    <v-img id="logo" src="images/dancinggo_logo.png"></v-img>
    <div id="loginBox" class="d-flex">
      <v-img @click="goToMain" id="loginBtn" class="shadowEffect" src="images/google_btn.png"></v-img>
      <v-img id="loginBtn" class="shadowEffect" src="images/kakao_btn.png"></v-img>
    </div>
  </div>
</template>

<script>
import router from '@/router/index.js'
export default {
  name: 'Main',
  methods: {
    goToMain () {
      router.push({ name: 'Home' })
    }
  }
}
</script>
<style scoped>
#background {
  width: 100vw;
  height: 100vh;
}
#logo {
  position: absolute;
  width: 60%;
  left: 22vw;
  bottom: 25vh;
}
#loginBox {
  position: absolute;
  left: 45vw;
  bottom: 22vh;
}
#loginBtn {
  width: 60px;
  border-radius: 100%;
  margin: 4px;
}
.shadowEffect {
  box-shadow: 0 0 15px white;
}
</style>
