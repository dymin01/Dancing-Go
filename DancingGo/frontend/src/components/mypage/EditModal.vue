<template>
    <v-card class="px-5, py-5" id="mypage">
      <div class="d-flex justify-content-center py-3">
          <v-card-title class="text">
              <span clss="text-h4 title">닉네임 수정</span>
          </v-card-title>
      </div>
      <v-form>
          <v-container>
              <v-row>
                  <v-col cols=1>
                  </v-col>
                  <v-col cols="9">
                      <v-text-field
                      v-model="userNickname"
                      label="닉네임"
                      required
                      color="#E3F2FD"
                      dark
                      ></v-text-field>
                  </v-col>

                  <v-col cols="2">
                      <button @click="changeNickname" class="text">수정</button>
                  </v-col>
              </v-row>
          </v-container>
      </v-form>
    </v-card>
</template>

<script>
import http from '@/http.js';
import { mapMutations, mapGetters } from 'vuex'
export default {
    data(){
        return{
            userNickname:'',
        }
    },
    computed:{
        ...mapGetters(['user'])
    },
    methods:{
        ...mapMutations(['setUser']),
        changeNickname(){
            event.preventDefault()
            console.log("닉네임 바꿀거야")
            console.log(this.userNickname);
            const nickname = this.userNickname;
            // console.log(this.$store.state.account.user.userId)
            
            http.get("/user/nickname/"+nickname)
            .then((res) => {
                console.log("있나 없나?")
                console.log(res);
                const body = {
                    userNickname: this.userNickname,
                    userId: this.$store.state.account.user.userId,
                }
                if(res.data == false){
                    http.put("/user/nickname/", body)
                    .then((res) => {
                        alert("닉네임을 바꿨습니다.")
                        console.log("바꿨습니다.")
                        console.log(res)
                    })
                    
                    //this.$emit("getUserInfo")
                    this.user.userNickname = nickname
                    this.setUser(this.user)
                    // this.$store.state.account.user.userNickname = nickname
                    this.closeModal()
                    // console.log("체크")
                    // console.log(this.$store.state.account.user.userNickname)
                }else{
                    alert("중복된 닉네임입니다.")
                }
            })
        },
        closeModal(){
            console.log("모달 닫기")
            this.$emit("closeEdit")
        }
    }
}
</script>

<style>

.text {
    text-shadow: 0 0 7px #fff, 0 0 10px #fff, 0 0 21px #fff, 0 0 42px #0fa, 0 0 82px #0fa, 0 0 92px #0fa, 0 0 102px #0fa, 0 0 151px #0fa;
}

</style>