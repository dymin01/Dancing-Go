<template>
  <!-- <div id="header" class="d-flex justify-end m-3 pe-4"> -->
  <div id="header" class="d-flex m-3 pe-4" style="z-index:100;">
    <div style="float:left;">
      <i id="icon" class="fas fa-chevron-left"  @click="goBack"></i>
    </div>
    <div style="margin-left:auto;">
      <i v-if="isHamburgerOpen" id="icon" class="mx-2 far fa-user-circle" @click="openMypage"></i>
      <i v-if="isHamburgerOpen" id="icon" class="mx-2 fas fa-cog" @click="openSettings"></i>
      <i v-if="isHamburgerOpen" id="icon" class="mx-2 fas fa-sign-out-alt" @click="logout"></i>
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
  </div>
</template>

<script>
import Mypage from '@/components/mypage/mypage.vue'
import Settings from '@/components/settings/Settings.vue'
import { mapMutations, mapGetters } from 'vuex'

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
    }
  },
  components:{
    Mypage,
    Settings,
  },
  computed:{
    ...mapGetters(['token', 'user']),
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
      this.isMypageOpen = true
    },
    closeMypage(){
      this.isMypageOpen = false
    },
    openSettings(){
      this.isSettingsOpen = true
    },
    closeSettings(){
      this.isSettingsOpen = false
    },
    onToggle (active) {
      // console.log(active)
      this.isHamburgerOpen = active
    },
    goBack () {
      this.$router.go(-1)
    }
  },
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