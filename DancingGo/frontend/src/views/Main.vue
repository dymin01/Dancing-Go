<template>
  <div>
    <v-img id="background" src="images/main/main_background.jpg"></v-img>
    <v-img id="logo" src="images/main/dancinggo_logo.png"></v-img>
    <div id="loginBox" class="d-flex">
      <router-link :to="{name: 'Home'}" exact>
        <v-img id="loginBtn" class="shadowEffect" src="images/main/google_btn.png"></v-img>
      </router-link>
      <v-img id="loginBtn" class="shadowEffect" src="images/main/kakao_btn.png"></v-img>
    </div>
  </div>
</template>

<script>
import router from '@/router/index.js'
export default {
  name: 'Main',
  methods: {
    goToHome () {
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
