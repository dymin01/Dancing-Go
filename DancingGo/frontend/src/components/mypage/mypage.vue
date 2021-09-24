<template>
  <v-card class="px-5, py-5" id="mypage">
      <div class="d-flex justify-content-center py-3">
          <v-card-title>
              <span clss="text-h4">마이페이지</span>
          </v-card-title>
      </div>
      <v-form>
          <v-container>
              <v-row>
                  <v-col cols="6">
                      <v-container>
                        <v-row>
                            <v-col cols="3" style="background-color:red">
                                <div id="box">
                                    <img :src=userProfile style="max-width:50px">
                                </div>
                            </v-col>
                            <v-col cols="9" style="background-color:blue" class="d-flex justify-end align-center">
                                {{userNickname}}
                            </v-col>
                        </v-row>
                        <v-row>
                            <v-col cols="3">
                                <img id="profile" src="images/mypage/rank.png" style="max-width:100%">
                            </v-col>
                            <v-col cols="9" style="background-color:yellow" class="text-end">
                                {{userRank}}
                            </v-col>
                        </v-row>
                        <v-row>
                            <v-col cols="3" style="background-color:blue">
                                총점
                            </v-col>
                            <v-col cols="9" style="background-color:blue" class="text-end">
                                {{userTotalscore}}
                            </v-col>
                        </v-row>
                      </v-container>
                  </v-col>
                  <v-col cols="6">
                      <v-container>
                          <v-row>
                              <v-col cols="12" style="background-color:red" class="text-center">
                                뱃지 목록
                              </v-col>
                          </v-row>
                          <v-row>
                              <v-col class="text-center" cols="4" style="background-color:yellow" v-for="(badge,idx) in badgeList" :key="idx">
                                <img :src="'images/badge/'+badge.badgeId+'.png'" style="max-width:50px">
                              </v-col>
                          </v-row>
                      </v-container>
                  </v-col>
              </v-row>
          </v-container>
      </v-form>
  </v-card>
</template>

<script>
import { mapGetters } from 'vuex'
import axios from 'axios';

export default {
    data(){
        return {
            userNickname: "",
            userProfile: "",
            userRank: 0,
            userTotalscore: 0,
            badgeList: []
        }
    },
    computed:{
        ...mapGetters(['token', 'user']),
    },
    mounted: function() {
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

            axios.get("/challenge/myBadge/"+this.userNickname)
            .then((res) => {
                console.log("뱃지")
                this.badgeList = res.data
                console.log(this.badgeList)
            })
            .catch((e) => {
                console.log(e)
            })
            
        })
        .catch((e) => {
            console.log(e)
        })
        
    },
    methods:{

    }
}
</script>

<style>
#mypage {
    background-color: rgba( 0, 0, 0, 0.8 );
    color:white;
}

#box {
    width: 50px;
    height: 50px; 
    border-radius: 70%;
    overflow: hidden;
}

#profile {
    width: 100%;
    height: 100%;
    object-fit: cover;
}

</style>