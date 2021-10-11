<template>
  <v-card class="text-center" id="nickname-modal">
    <v-card-title v-if="this.isKorean" class="text-h5 justify-center">
      닉네임 입력
    </v-card-title>
    <v-card-title v-else class="text-h5 justify-center">
      nickname
    </v-card-title>
    <hr style="margin: 0px; background: white;">
    <v-card-text>
      <h6 v-if="this.isKorean" style="color: white;" class="mt-4 mb-5">사용하실 닉네임을 입력해주세요.</h6>
      <h6 v-else style="color: white;" class="mt-4 mb-5">enter your nickname</h6>
      <v-text-field
        v-if="this.isKorean"
        label="닉네임 입력 (6자 까지)"
        v-model="nicknameInput"
        solo
        ></v-text-field>
        <v-text-field
        v-else
        label="enter nickname (6 characters.)"
        v-model="nicknameInput"
        solo
        ></v-text-field>
      <v-btn
        v-if="this.isKorean"
        id="button"
        @click="checkNickname"
      >
        확인
      </v-btn>
      <v-btn
        v-else
        id="button"
        @click="checkNickname"
      >
        OK
      </v-btn>
    </v-card-text>
    <div v-if="this.isKorean">
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
    </div>
    <div v-else>
      <v-dialog
        v-model="isOverlap"
        max-width="350px">
        <Modal1btn
            style="background-color: rgba(58, 58, 58, 1); color: white;"
            :modalTitle="'Alert'"
            :modalContent="'This nickname is already in use.'"
            :buttonO="'CLOSE'"
            @clickO="closeOneModal"
         />
    </v-dialog>
    <v-dialog
        v-model="isOkNickname"
        max-width="350px">
        <Modal1btn
            style="background-color: rgba(58, 58, 58, 1); color: white;"
            :modalTitle="'Alert'"
            :modalContent="'Nickname setting was successful.'"
            :buttonO="'CLOSE'"
            @clickO="closeModal"
         />
    </v-dialog>
    </div>
    
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
      isOkNickname:false,
      isKorean: true,
    }
  },
  components:{
        Modal1btn
  },
  computed:{
        ...mapGetters(['user'])
  },
  mounted() {
        if (this.$store.getters.langMode=='한국어') {
        this.isKorean = true
        } else {
        this.isKorean = false
        }
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
        http.get("/user/nickname/" + nickname)
        .then((res) => {
          if (res.data === true) {
            this.isOverlap = true
          } else {
            const body = {
                userNickname: this.nicknameInput,
                userId: this.$store.state.account.user.userId,
            }
            http.put("/user/nickname/", body)
            .then(() => {
              this.user.userNickname = this.nicknameInput
              this.setUser(this.user)
              this.isOkNickname = true
            })

          }
        })
      }

    },
    closeModal(){
      this.isOkNickname = false
      this.$emit("closeModal")
    },
    closeOneModal(){
      this.isOverlap = false
    }
  }
}
</script>

<style>
#nickname-modal {
  background-color: rgba(43, 29, 59, 0.8);
  color: white;
}
#button {
  color: white;
  background-color: purple;
  box-shadow: 0 0 15px white;
}
</style>