<template>
  <v-card class="px-5, py-5" id="mypage">
      <div class="d-flex justify-content-center">
          <v-card-title class="">
              <span clss="text-h4 title">마이페이지</span>
          </v-card-title>
      </div>
      <v-form>
          <v-container>
              <v-row class="py-1">
                  <v-col cols="6" class="wrapper">
                      <v-container class="background">
                          <v-row class="py-1">
                              <v-col cols="12" class="text-center title">
                                내 정보
                              </v-col>
                          </v-row>
                        <v-row>
                            <v-col cols="6" class="d-flex justify-center align-center pl-5">
                                <div id="box">
                                    <img :src=userProfile style="max-width:55px">
                                </div>
                            </v-col>
                            <v-col cols="4" class="d-flex justify-end align-center text pr-4" style="font-size: 20px;">
                                <!-- {{ changeNickname }} -->
                                {{user.userNickname}}
                            </v-col>
                            <v-col cols="2" class="d-flex justify-start align-center" style="padding: 0px;">
                                <button @click="openEdit($event)" class="">수정</button>
                            </v-col>
                        </v-row>
                        <v-row>
                            <v-col cols="6" class="d-flex justify-center align-center pl-5">
                                <img id="profile" src="images/mypage/rank.png" style="max-width:70px">
                            </v-col>
                            <v-col cols="6" class="d-flex justify-center align-center text" style="font-size: 20px;" v-if="checkMinus(userRank)">
                                {{userRank}}
                            </v-col>
                            <v-col cols="6" class="d-flex justify-center align-center text" style="font-size: 20px;" v-else>
                                -
                            </v-col>
                        </v-row>
                        <v-row>
                            <v-col cols="6" class="d-flex justify-center align-center pl-5">
                                주간총점
                            </v-col>
                            <v-col cols="6" class="d-flex justify-center align-center text" style="font-size: 20px;">
                                {{userTotalscore}}
                            </v-col>
                        </v-row>
                      </v-container>
                  </v-col>
                  <v-col cols="6" class="wrapper">
                      <v-container class="background py-4" >
                          <v-row>
                              <v-col cols="12" class="text-center title">
                                뱃지 목록
                              </v-col>
                          </v-row>
                          <v-row>
                              <v-col class="text-center" cols="4" v-for="(badge,idx) in allBadgeList" :key="idx">
                                <img :src="'images/badge/'+badge.badgeId+'.png'" style="max-width:50px">
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
import axios from 'axios';
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
            allBadgeList: []
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
        this.getUserInfo()
        setTimeout(this.getUserBadge, 100)
        // this.getUserBadge()
        axios.get('/badge/findAllBadgeList/')
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
            axios.get("/user/info/"+userId)
            .then((res) => {
                console.log("유저")
                console.log(res)
                this.userNickname = res.data.userNickname

                if(res.data.userImg == "" || res.data.userImg == null){
                    this.userProfile = "images/mapage/noImg.png"
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
            axios.get("/challenge/myBadge/"+this.userNickname)
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
        }
    }
}
</script>

<style>
#mypage {
    background-color: rgba( 0, 0, 0, 1 );
    color:white;
}

.wrapper {
    padding: 0px;
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


</style>