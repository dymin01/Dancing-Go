<template>
  <v-card class="px-5, py-5" id="mypage">
      <div class="d-flex justify-content-center">
          <v-card-title class="">
              <span clss="text-h4 title" v-if="this.isKorean">마이페이지</span>
              <span clss="text-h4 title" v-else>My page</span>
          </v-card-title>
      </div>
      <hr style="margin: 0px; margin-top: 10px; margin-bottom: 30px; background: white;">
      <v-form>
          <v-container>
              <v-row class="py-1">
                  <v-col cols="6" class="wrapper">
                      <v-container class="background">
                          <v-row class="py-1">
                              <v-col cols="12" class="text-center title" v-if="this.isKorean">
                                내 정보
                              </v-col>
                              <v-col cols="12" class="text-center title" v-else>
                                My Info
                              </v-col>
                          </v-row>
                        <v-row>
                            <v-col cols="6" class="d-flex justify-center align-center pl-5">
                                <div id="box">
                                    <img :src=userProfile style="max-width:55px">
                                </div>
                            </v-col>
                            <v-col cols="6" class="d-flex align-center text pr-10" style="padding-left: 0px; font-size: 16px; justify-content: space-between;">
                                <!-- {{ changeNickname }} -->
                                <div style="width: 96px; text-align: center" >{{user.userNickname}}</div>
                                <button @click="openEdit($event)" class="modifyBtn"><i class="fas fa-pencil-alt" style="font-size:13px; width: 13px; height: 13px;"></i></button>
                            </v-col>
                            <!-- <v-col cols="2" class="d-flex justify-start align-center" style="padding: 0px;">
                                <button @click="openEdit($event)" class="">수정</button>
                            </v-col> -->
                        </v-row>
                        <v-row>
                            <v-col cols="6" class="d-flex justify-center align-center pl-5">
                                <img id="profile" src="images/mypage/rank.png" style="max-width:70px">
                            </v-col>
                            <v-col cols="6" class="d-flex justify-center align-center text pr-10" style="padding-left: 0px; font-size: 20px;" v-if="checkMinus(userRank)">
                                {{userRank}}
                            </v-col>
                            <v-col cols="6" class="d-flex justify-center align-center text pr-10" style="padding-left: 0px; font-size: 20px;" v-else>
                                -
                            </v-col>
                        </v-row>
                        <v-row>
                            <v-col cols="6" class="d-flex justify-center align-center pl-5" v-if="this.isKorean">
                                주간총점
                            </v-col>
                            <v-col cols="6" class="d-flex justify-center align-center pl-5" v-else>
                                Weekly Score
                            </v-col>
                            <v-col cols="6" class="d-flex justify-center align-center text pr-10" style="padding-left: 0px; font-size: 20px;">
                                {{userTotalscore}}
                            </v-col>
                        </v-row>
                      </v-container>
                  </v-col>
                  <v-col cols="6" class="wrapper">
                      <v-container class="background py-4" >
                          <v-row>
                              <v-col cols="12" class="text-center title" v-if="this.isKorean">
                                뱃지 목록
                              </v-col>
                              <v-col cols="12" class="text-center title" v-else>
                                Badge List
                              </v-col>
                          </v-row>
                          <v-row>
                              <v-col class="text-center" cols="4" v-for="(badge,idx) in allBadgeList" :key="idx">
                                <img :src="'images/badge/'+badge.badgeId+'.png'" style="max-width:50px" v-if="badgeList.some(item => item.badgeId == badge.badgeId)">
                                <img :src="'images/badge/'+badge.badgeId+'.png'" style="max-width:50px" v-else class="badgeImg" >
                              </v-col>
                          </v-row>
                      </v-container>
                  </v-col>
              </v-row>
          </v-container>
      </v-form>

    <v-dialog
    v-model="isEditOpen"
    max-width="650px"
    > <EditModal @getUserInfo="getUserInfo" @closeEdit="closeEdit" class="editModal"/>
    </v-dialog>

  </v-card>
</template>

<script>
import { mapGetters } from 'vuex'
import http from '@/http.js';
import EditModal from '@/components/mypage/EditModal.vue';

export default {
    data(){
        return {
            userNickname: "",
            userProfile: "",
            userRank: 0,
            userTotalscore: 0,
            badgeList: [],
            isEditOpen: false,
            allBadgeList: [],
            isKorean: true,
        }
    },
    components:{
        EditModal
    },
    computed:{
        ...mapGetters(['token', 'user']),
        changeNickname() {
            return this.userNickname
        }
    },
    mounted: function() {
        console.log(this.user)
        if (this.$store.getters.langMode=='한국어') {
            this.isKorean = true
        } else {
            this.isKorean = false
        }
        this.getUserInfo()
        setTimeout(this.getUserBadge, 100)
        // this.getUserBadge()
        http.get('/badge/findAllBadgeList')
            .then((res) => {
                this.allBadgeList = res.data
                console.log(this.allBadgeList)
            })
            .catch((e) => {
                console.log(e)
            })
    },
    methods:{
        openEdit(event){
            event.preventDefault()
            this.isEditOpen = true
        },
        closeEdit(){
            this.isEditOpen = false
        },
        getUserInfo(){
            console.log("함수 들어왔어요~")
            const userId = this.user.userId;
            http.get("/user/info/"+userId)
            .then((res) => {
                console.log("유저")
                console.log(res)
                this.userNickname = res.data.userNickname

                if(res.data.userImg == "" || res.data.userImg == null){
                    this.userProfile = "images/HallofFame/mirror-ball.png"
                }else{
                    this.userProfile = res.data.userImg
                }

                this.userRank = res.data.rank
                this.userTotalscore = res.data.totalScore

            })
            .catch((e) => {
                console.log(e)
            })
        },
        getUserBadge(){
            http.get("/challenge/myBadge/"+this.userNickname)
            .then((res) => {
                console.log("뱃지")
                this.badgeList = res.data
                console.log(this.badgeList)
            })
            .catch((e) => {
                console.log(e)
            })
        },
        checkMinus(rankValue) {
            if(rankValue == -1) {
                return false
            } else {
                return true
            }
        }, 

    }
}
</script>

<style scoped>
#mypage {
    background-color: rgba(43, 29, 59, 0.97);
    color:white;
}

.wrapper {
    padding: 0px 12px;
}

.background {
    background-color: rgba( 255, 255, 255, 0.1 );
    height: 100%;
    border-radius: 10%;
}

#box {
    width: 55px;
    height: 55px; 
    border-radius: 70%;
    overflow: hidden;
}

#profile {
    width: 100%;
    height: 100%;
    object-fit: cover;
}

.text {
    text-shadow: 0 0 7px #fff, 0 0 10px #0fa, 0 0 21px #0fa;
}

.badgeImg {
    opacity: 0.25; 
    filter: alpha(opacity=25);
}

.modifyBtn {
    width: 13px; 
    height: 13px; 
    padding: 0px;
    font-size: 10px;
    color: white;
}

</style>