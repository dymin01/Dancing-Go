<template>
  <div>
    <v-img id="background" src="images/main/main_background.jpg"></v-img>
    <v-img id="logo" src="images/main/dancinggo_logo.png"></v-img>
    <div id="loginBox" class="d-flex">
      <div class="socal_login_container d-flex">
          <div v-for="social in socials" :key="social.id" class="mx-3">
            <a class="social_btn" :href="socialLoginUrl(social.socialType)">
              <img class="social_login" :src="social.src" :style="{width: social.width, height: social.height}"/>
            </a>
          </div>
      </div>
      <!-- <router-link :to="{name: 'Home'}" exact> -->
        <!-- <v-img id="loginBtn" class="shadowEffect" src="images/main/google_btn.png"></v-img> -->
      <!-- </router-link> -->
      <!-- <v-img id="loginBtn" class="shadowEffect" src="images/main/kakao_btn.png"></v-img> -->
    </div>
  </div>
</template>

<script>
import router from '@/router/index.js'
import $ from '@/utils'
import accountApi from '@/api/account'
import { mapActions, mapMutations } from 'vuex'


export default {
  name: 'Main',
  data () {
    return {
      isProcess: false,
      cannotLogin: false,
      isLoginFailed: false,
      socials: [
        {
          socialType: 'google',
          src: $.getSocialImage('google'),
          width: '50px',
          height: '50px',
          comment: '구글 로그인'
        },
        // {
        //   socialType: 'facebook',
        //   src: $.getSocialImage('facebook'),
        //   width: '32px',
        //   height: '32px',
        //   comment: '페이스북 로그인'
        // },
        {
          socialType: 'naver',
          src: $.getSocialImage('naver'),
          width: '50px',
          height: '50px',
          comment: '네이버 로그인'
        },
        {
          socialType: 'kakao',
          src: $.getSocialImage('kakao'),
          width: '50px',
          height: '50px',
          comment: '카카오 로그인'
        }
      ]
    }
  },
  methods: {
    ...mapActions(['fetchUser']),
    ...mapMutations(['setToken']),
    login () {
      if (this.isProcess) return
      if (this.id.trim() === '' || this.password.trim() === '') {
        this.cannotLogin = true
        return
      }
      accountApi.login(
        {
          id: this.id,
          password: this.password,
          socialType: 'LOCAL'
        },
        body => {
          this.setToken(body.token)
          this.id = this.password = ''
          this.isProcess = false
          this.fetchUser(() => {
            this.$emit('onCloseModal')
          })
        },
        err => {
          if (err.response.data.status === 401) {
            this.isLoginFailed = true
          }
        }
      )
    },
    inputChanged () {
      if (!this.cannotLogin) return
      if (this.id.trim() !== '' && this.password.trim() !== '') {
        this.cannotLogin = false
      }
    },
    socialLoginUrl (socialType) {
      return $.getSocialLoginUrl(socialType)
    },
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
