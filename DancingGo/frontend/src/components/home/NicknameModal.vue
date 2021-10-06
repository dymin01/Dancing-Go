<template>
  <v-card class="text-center" id="nickname-modal">
    <v-card-title class="text-h5 justify-center">
      닉네임 입력
    </v-card-title>
    <hr style="margin: 0px; background: white;">
    <v-card-text>
      <h6 style="color: white;" class="mt-4 mb-5">사용하실 닉네임을 입력해주세요.</h6>
      <v-text-field
        label="닉네임 입력 (6자 까지)"
        v-model="nicknameInput"
        solo
        ></v-text-field>
      <!-- <div class="d-flex justify-center">
        <input type="text" placeholder="닉네임 입력 (6자 까지)" id="nickname-input">
      </div> -->
      <v-btn
        id="button"
        @click="checkNickname"
      >
        확인
      </v-btn>
    </v-card-text>
  </v-card>
</template>

<script>
import http from '@/http.js';
import { mapMutations, mapGetters } from 'vuex'
export default {
  name: 'NicknameModal',
  data () {
    return {
      nicknameInput: '',
    }
  },
  computed:{
        ...mapGetters(['user'])
  },
  methods: {
    ...mapMutations(['setUser']),
    checkNickname: function () {
      if (this.nicknameInput.length > 6) {
        alert('닉네임은 6자까지 입력 가능합니다.')
      } else if (!this.nicknameInput.trim()) {
        alert('닉네임을 입력해주세요.')
      } else {
        const nickname = this.nicknameInput
        console.log("은교바보")
        console.log(nickname)
        http.get("/user/nickname/" + nickname)
        .then((res) => {
          console.log("중복결과")
          console.log(res.data)
          if (res.data === true) {
            alert('중복된 닉네임입니다.')
          } else {
            const body = {
                userNickname: this.nicknameInput,
                userId: this.$store.state.account.user.userId,
            }
            http.put("/user/nickname/", body)
            .then(() => {
              alert('닉네임 설정에 성공했습니다.')
              this.user.userNickname = this.nicknameInput
              this.setUser(this.user)
              this.$emit('closeModal')
            })
          }
        })
      }

    }
  }
}
</script>

<style>
#nickname-modal {
  background-color: rgba(43, 29, 59, 0.8);
  color: white;
  /* text-shadow: 0 0 5px gray; */
}
#button {
  color: white;
  background-color: purple;
  box-shadow: 0 0 15px white;
}
</style>