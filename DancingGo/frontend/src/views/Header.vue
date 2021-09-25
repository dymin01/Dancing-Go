<template>
  <div id="header" class="d-flex justify-end m-3 pe-4">
    <i id="icon" class="fas fa-chevron-left" @click="goBack"></i>
    <i v-if="isHamburgerOpen" id="icon" class="mx-2 far fa-user-circle" @click="openMypage"></i>
    <i v-if="isHamburgerOpen" id="icon" class="mx-2 fas fa-cog"></i>
    <i v-if="isHamburgerOpen" id="icon" class="mx-2 fas fa-sign-out-alt"></i>
    <tasty-burger-button
        id="hamburgerButton"
        :type="buttonType"
        :active="isActive"
        :color="color"
        :active-color="color"
        @toggle="onToggle"
        />

    <v-dialog
    v-model="isMypageOpen"
    max-width="650px"
    > <Mypage @closeMypage="closeMypage" class="mypageModal"/>
    </v-dialog>
  </div>
</template>

<script>
import Mypage from '@/components/mypage/mypage.vue'
import { mapGetters } from 'vuex'

export default {
  name: 'Header',
  data(){
    return{
      isMypageOpen: false,
      buttonType: 'elastic',
      isActive: false,
      color: 'white',
      isHamburgerOpen: false,
    }
  },
  components:{
    Mypage
  },
  computed:{
    ...mapGetters(['token', 'user']),
  },
  methods:{
    openMypage(){
      this.isMypageOpen = true
    },
    closeMypage(){
      this.isMypageOpen = false
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