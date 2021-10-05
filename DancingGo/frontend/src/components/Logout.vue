<template>
  <v-card class="text-center" id="logout">
    <v-card-title
    v-if="this.$store.getters.langMode=='한국어'"
    class="text-h5 justify-center">
      알림
    </v-card-title>
    <v-card-title v-else class="text-h5 justify-center">
      Notice
    </v-card-title>
    <hr style="margin: 0px; background: white;">
    <v-card-text class="py-2">
      <h5
      v-if="this.$store.getters.langMode=='한국어'"
      style="color: white;" class="mt-4 mb-5">로그아웃 하시겠습니까?</h5>
      <h5 v-else style="color: white;" class="mt-4 mb-5">Do you want to log out?</h5>
    </v-card-text>
    <v-btn
      id="button"
      style="margin-left: auto; margin-bottom:20px; opacity: 80%;"
      class="mx-1"
      @click="logout"
    >
      확인
    </v-btn>
    <v-btn
      id="button"
      class="mx-1"
      style="margin-left: auto; margin-bottom:20px; background-color: #7D7D7D; opacity: 80%;"
      @click="cancel"
    >
      취소
    </v-btn>
  </v-card>
</template>

<script>

import { mapMutations } from 'vuex'
import router from '@/router/index.js'
export default {

    methods:{
        ...mapMutations(['setToken', 'setUser']),
        logout () {
            this.setToken(null)
            this.setUser(null)
            alert('로그아웃되었습니다.')
            this.$emit('closeSettings')
            router.push('/');
        },
        cancel(){
            this.$emit('closeSettings')
        }
    }

}
</script>

<style>
#logout {
  background-color: rgba(43, 29, 59, 0.8);
  color: white;
  /* text-shadow: 0 0 5px gray; */
}


</style>