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
    <v-dialog
        v-model="isOverlap"
        max-width="350px">
        <Modal1btn
            style="background-color: rgba(58, 58, 58, 1); color: white;"
            :modalTitle="'알림'"
            :modalContent="'중복된 닉네임입니다.'"
            :buttonO="'확인'"
            @clickO="closeOneModal"
         />
    </v-dialog>
    <v-dialog
        v-model="isOkNickname"
        max-width="350px">
        <Modal1btn
            style="background-color: rgba(58, 58, 58, 1); color: white;"
            :modalTitle="'알림'"
            :modalContent="'닉네임 설정에 성공했습니다.'"
            :buttonO="'확인'"
            @clickO="closeModal"
         />
    </v-dialog>
  </v-card>
</template>

<script>
import http from '@/http.js';
import { mapMutations, mapGetters } from 'vuex'
import Modal1btn from '@/components/Modal_1btn.vue'
export default {
  name: 'NicknameModal',
  data () {
    return {
      nicknameInput: '',
      isOverlap:false,
      isOkNickname:false
    }
  },
  components:{
        Modal1btn
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
        console.log(nickname)
        http.get("/user/nickname/" + nickname)
        .then((res) => {
          console.log("중복결과")
          console.log(res.data)
          if (res.data === true) {
            // alert('중복된 닉네임입니다.')
            console.log("중복된 닉네임입니다.")
            this.isOverlap = true
          } else {
            const body = {
                userNickname: this.nicknameInput,
                userId: this.$store.state.account.user.userId,
            }
            http.put("/user/nickname/", body)
            .then(() => {
              // alert('닉네임 설정에 성공했습니다.')
              console.log("닉네임 변경에 성공 1234")
              this.user.userNickname = this.nicknameInput
              this.setUser(this.user)
              this.isOkNickname = true
              
              // this.closeModal()
              // this.$emit('closeModal')
            })

          }
        })
      }

    },
    closeModal(){
      console.log("모달 닫기")
      this.isOkNickname = false
      this.$emit("closeModal")
    },
    closeOneModal(){
      console.log('확인 모달 닫기')
      this.isOverlap = false
    }
  }
}
</script>

<style>
#nickname-modal {
  /* background-color: rgba(58, 58, 58, 0.8); */
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