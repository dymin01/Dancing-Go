<template>
    <v-card class="px-5, py-5" id="mypage">
      <div class="d-flex justify-content-center py-3">
          <v-card-title class="text">
              <span clss="text-h4 title" v-if="this.isKorean">닉네임 수정</span>
              <span clss="text-h4 title" v-else>Edit Nickname</span>
          </v-card-title>
      </div>
      <v-form>
          <v-container>
              <v-row>
                  <v-col cols=1>
                  </v-col>
                  <v-col cols="9">
                      <v-text-field
                      v-if="this.isKorean"
                      v-model="userNickname"
                      label="닉네임"
                      required
                      color="#E3F2FD"
                      dark
                      ></v-text-field>
                      <v-text-field
                      v-else
                      v-model="userNickname"
                      label="Nickname"
                      required
                      color="#E3F2FD"
                      dark
                      ></v-text-field>
                  </v-col>

                  <v-col cols="2" style="padding-top: 28px">
                      <button @click="changeNickname" class="text" v-if="this.isKorean">수정</button>
                      <button @click="changeNickname" class="text" v-else>Edit</button>
                  </v-col>
              </v-row>
          </v-container>
      </v-form>
    <v-dialog
        v-model="isOverlap"
        max-width="350px">
        <Modal1btn
            v-if="this.isKorean"
            :modalTitle="'알림'"
            :modalContent="'중복된 닉네임 입니다.'"
            :buttonO="'확인'"
            @clickO="closeOneModal"
         />
        <Modal1btn
            v-else
            :modalTitle="'Alert'"
            :modalContent="'This nickname is already in use.'"
            :buttonO="'Close'"
            @clickO="closeOneModal"
         />
    </v-dialog>
    <v-dialog
        v-model="isOkNickname"
        max-width="350px">
        <Modal1btn
            style="background-color: rgba(43, 29, 59, 0.8);color: white;"
            :modalTitle="'알림'"
            :modalContent="'닉네임이 변경되었습니다.'"
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
    data(){
        return{
            userNickname:'',
            isOverlap:false,
            isOkNickname:false,
            isKorean: true,
        }
    },
    mounted() {
        if (this.$store.getters.langMode=='한국어') {
        this.isKorean = true
        } else {
        this.isKorean = false
        }
    },
    components:{
        Modal1btn
    },
    computed:{
        ...mapGetters(['user'])
    },
    methods:{
        ...mapMutations(['setUser']),
        changeNickname(){
            event.preventDefault()
            const nickname = this.userNickname;
            if(nickname.length > 6){
                if(this.isKorean){
                    alert('닉네임은 6자까지 입력 가능합니다.')
                }else{
                    alert('Nicknames can contain up to 6 characters.')
                }
            }else{

            http.get("/user/nickname/"+nickname)
            .then((res) => {
                const body = {
                    userNickname: this.userNickname,
                    userId: this.$store.state.account.user.userId,
                }
                if(res.data == false){
                    http.put("/user/nickname/", body)
                    .then(() => {
                        this.isOkNickname = true
                    })
                    
                    this.user.userNickname = nickname
                    this.setUser(this.user)
                    this.closeModal()
                }else{
                    this.isOverlap = true
                }
            })
        }
        },
        closeModal(){
            this.isOkNickname = false
            this.$emit("closeEdit")
        },
        closeOneModal(){
            this.isOverlap = false
        }
    }
}
</script>

<style scoped>

.text {
    text-shadow: 0 0 7px #fff, 0 0 10px #fff, 0 0 21px #fff, 0 0 42px #0fa, 0 0 82px #0fa, 0 0 92px #0fa, 0 0 102px #0fa, 0 0 151px #0fa;
}

</style>