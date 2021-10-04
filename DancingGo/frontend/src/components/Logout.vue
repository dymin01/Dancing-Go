<template>
  <v-card class="text-center" id="logout">
    <v-card-title class="text-h5 justify-center">
      로그아웃
    </v-card-title>
    <hr style="margin: 0px; background: white;">
    <v-card-text class="d-flex row">
        <div class="cols-6">
            <h4 style="color: white;" class="mt-4 mb-5">로그아웃 하시겠습니까?</h4>
        </div>
    </v-card-text>
    <v-btn
      id="button"
      style="margin-left: auto; margin-bottom:20px"
      @click="logout"
    >
      확인
    </v-btn>
    <v-btn
      id="button"
      style="margin-left: 20px; margin-bottom:20px"
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