<template>
  <!-- <div id="header" class="d-flex justify-end m-3 pe-4"> -->
  <div id="header" class="d-flex m-3 pe-4" style="z-index:100;">
    <div v-if="isHome" style="float:left;">
      <i id="icon" class="fas fa-chevron-left"  @click="goBack"></i>
    </div>
    <div style="margin-left:auto;">
      <i v-if="isHamburgerOpen" id="icon" class="mx-2 far fa-user-circle" @click="openMypage"></i>
      <i v-if="isHamburgerOpen" id="icon" class="mx-2 fas fa-cog" @click="openSettings"></i>
      <i v-if="isHamburgerOpen" id="icon" class="mx-2 fas fa-sign-out-alt" @click="openLogout"></i>
      <tasty-burger-button
          id="hamburgerButton"
          :type="buttonType"
          :active="isActive"
          :color="color"
          :active-color="color"
          @toggle="onToggle"
          />
    </div>
    <v-dialog
    v-model="isMypageOpen"
    max-width="650px"
    > <Mypage @closeMypage="closeMypage" class="mypageModal"/>
    </v-dialog>

    <v-dialog
    v-model="isSettingsOpen"
    max-width="400px">
      <Settings @closeSettings="closeSettings" />
    </v-dialog>

    <v-dialog
    v-model="isLogoutOpen"
    max-width="400px">
      <Logout @closeSettings="closeLogout" />
    </v-dialog>

    <audio src="sounds/select.mp3" ref="selecteffect"></audio>
  </div>
</template>

<script>
import Mypage from '@/components/mypage/mypage.vue'
import Settings from '@/components/settings/Settings.vue'
import Logout from '@/components/Logout.vue'
import { mapMutations, mapGetters } from 'vuex'
import router from '@/router/index.js'

export default {
  name: 'Header',
  data(){
    return{
      isMypageOpen: false,
      isSettingsOpen: false,
      buttonType: 'arrowturn',
      isActive: false,
      color: 'white',
      isHamburgerOpen: false,
      isHome: true,
      isLogoutOpen: false,
    }
  },
  components:{
    Mypage,
    Settings,
    Logout,
  },
  computed:{
    ...mapGetters(['token', 'user']),
    changeEffect () {
      return this.$store.getters.effectVolume*(0.01)
    },
  },
  watch:{
    changeEffect (val) {
      this.$refs.selecteffect.volume = val
    },
    $route(to, from){
      console.log("from " + from.path)
      console.log("to " + to.path)
      if(to.path.includes('home')){
        this.isHome = false
      }else{
        this.isHome = true
      }
    }
  },
  methods:{
    ...mapMutations(['setToken', 'setUser']),
    logout () {
      this.setToken(null)
      this.setUser(null)
      alert('로그아웃되었습니다.')
      if (this.$route.path !== '/') this.$router.push('/')
    },
    openMypage(){
      this.$refs.selecteffect.play()
      this.isMypageOpen = true
    },
    closeMypage(){
      this.$refs.selecteffect.play()
      this.isMypageOpen = false
    },
    openSettings(){
      this.$refs.selecteffect.play()
      this.isSettingsOpen = true
    },
    closeSettings(){
      this.$refs.selecteffect.play()
      this.isSettingsOpen = false
    },
    onToggle (active) {
      this.$refs.selecteffect.play()
      // console.log(active)
      this.isHamburgerOpen = active
    },
    goBack () {
      this.$refs.selecteffect.play()
      router.push({name: "Home"})
    },
    openLogout(){
      this.$refs.selecteffect.play()
      this.isLogoutOpen = true
    },
    closeLogout(){
      this.$refs.selecteffect.play()
      this.isLogoutOpen = false
    }
  },
  mounted () {
    this.$refs.selecteffect.crossorigin = 'anonymous'
    this.$refs.selecteffect.volume = this.$store.getters.effectVolume*(0.01)
  }
}
</script>

<style>
#header {
  position: absolute;
  width: 100vw;
  max-width: 98%;
}
#icon {
  z-index: 100;
  color: white;
  font-size: 25pt;
  text-shadow: 0 0 10px purple;
}
#icon:hover {
  cursor: pointer;
}
#hamburgerButton {
  z-index: 100;
}

</style>