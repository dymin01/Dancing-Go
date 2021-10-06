<template>
  <div class="text-center">
    <button @click="checkBadge">이거눌러봐요</button>
    <v-btn
      dark
      color="indigo"
      @click="snackbar = true"
    >
    Open Snackbar
    </v-btn>

    <v-snackbar
      v-model="snackbar"
      :vertical="vertical"
      :height="height"
      :width="width"
      :timeout="timeout"
      centered
    >
      <img :src="badge" width="200px">
      {{ badgeNameKor }}
      {{ badgeNameEng }}
      <template v-slot:action="{ attrs }">
        <v-btn
          color="indigo"
          text
          v-bind="attrs"
          @click="snackbar = false"
        >
        </v-btn>
      </template>
    </v-snackbar>
  </div>
</template>
<script>
import { mapGetters } from 'vuex'
import http from '@/http.js';

export default {
  data () {
    return{
      userInfo: {},
      badgeInfo: [],
      gameover: false,
      userNickname:'',
      badgeNum: '',
      badgeNameKor: '',
      badgeNameEng: '',
      badge: '',
      badgelist:[],
      //--- snackbar 부분 ---
      snackbar: false,
      vertical: true,
      height: '300px',
      width: '500px',
      timeout : 1000,
    }
  },
  computed:{
    ...mapGetters(['token', 'user']),
  },
  mounted() {
    http.get("/user/gameinfo/" + this.user.userId)
    .then((res)=>{
      this.userInfo = res.data
      this.userNickname = res.data.userNickname
      console.log('닉네임 : '+ this.userNickname)
    })
  },
  methods:{
    gameOver(){
      http.get("/user/gameover/"+this.userNickname)
      .then(()=>{
        this.gameover=true
      })
    },
    // checkBadge() > getBadge(addChellenge(), addBadgeList()) > showBadge()
    checkBadge(){
      http.get("/challenge/onlyMyBadge/"+ this.userNickname)
      .then((res)=>{
        this.badgeInfo = res.data
      })

      const gameoverCnt = this.userInfo.gameoverCnt //mounted
      const totalPlayCnt = this.userInfo.totalPlayCnt //mounted

      this.gameOver() // GAMEOVER method

      // 획득 조건 판단 후 badgelist에 {bNumber:번호(Number), bNameKor:한글뱃지이름(String), bNameEng:영어뱃지이름(String)} push
      setTimeout(function(){
        this.getBadge(totalPlayCnt, gameoverCnt)
      }.bind(this),100)

      // badgelist에 담긴 획득한 뱃지를 순차적으로 보여줌
      let i =0;
      var sT = setTimeout(function run(){
        if(i==this.badgelist.length){
          clearTimeout(sT)
        }else{
          this.showBadge(this.badgelist[i++])
          setTimeout(run.bind(this),2000);
        }
      }.bind(this),2000)

      // 담은 후 비워줘야하나? => 새로고침 해서 통신을 다시 하면 해결 가능 or 비워주기
      // this.badgelist = [];
    },

    showBadge(badgelist){
        this.badgeNum = badgelist.bNumber
        this.badgeNameKor = badgelist.bNameKor
        this.badgeNameEng = badgelist.bNameEng
        this.badge = 'images/badge/'+badgelist.bNumber+'.png'
        this.snackbar = true
    },

    addChallenge(challengeAddReq){
      http.post("/challenge/addChallenge", challengeAddReq)
    },

    addBadgeList(badgeNum, badgeNameKor, badgeNameEng){
      this.badgelist.push({bNumber:badgeNum, bNameKor:badgeNameKor, bNameEng:badgeNameEng})
    },

    getBadge(totalPlayCnt, gameoverCnt){
      http.get("/user/gameinfo/"+this.user.userId)
      .then((res)=>{
        // 1001, 첫 완곡
        if(totalPlayCnt==0 && res.data.totalPlayCnt==1){
          const challengeAddReq = {
            badgeId : 1001,
            userNickname : this.userInfo.userNickname,
          }
          this.addChallenge(challengeAddReq)
          this.addBadgeList(1001,"시작이 반이다","Well begun is half done")
        }
        // 1002, 첫 게임오버
        if(gameoverCnt==0 && res.data.gameoverCnt==1){
          const challengeAddReq = {
            badgeId : 1002,
            userNickname : this.userInfo.userNickname,
          }
          this.addChallenge(challengeAddReq)
          this.addBadgeList(1002,"실패는 성공의 어머니","Failure is but a stepping stone to success")
        }
        // 1003, top 100
        if(res.data.rank <= 100 && !this.badgeInfo.includes(1003)){
          const challengeAddReq = {
            badgeId : 1003,
            userNickname : this.userInfo.userNickname,
          }
          this.addChallenge(challengeAddReq)
          this.addBadgeList(1003,"Top 100!","Top 100!")
        }
        // 1004, top 10
        if(res.data.rank <= 10 && !this.badgeInfo.includes(1004)){
          const challengeAddReq = {
            badgeId : 1004,
            userNickname : this.userInfo.userNickname,
          }
          this.addChallenge(challengeAddReq)
          this.addBadgeList(1004,"Top 10!","Top 10!")
        }
        // 1005, top 3
        if(res.data.rank <= 3 && !this.badgeInfo.includes(1005)){
          const challengeAddReq = {
            badgeId : 1005,
            userNickname : this.userInfo.userNickname,
          }
          this.addChallenge(challengeAddReq)
          this.addBadgeList(1005,"단상에 내 이름이!","Take podium")
        }
        //1006, top 2
        if(res.data.rank <= 2 && !this.badgeInfo.includes(1006)){
          const challengeAddReq = {
            badgeId : 1006,
            userNickname : this.userInfo.userNickname,
          }
          this.addChallenge(challengeAddReq)
          this.addBadgeList(1006,"앞으로 한명!","One to go!")
        }
        // 1007, top 1
        console.log(res.data.rank) // 나 혼자인데 랭크가 2 나옴
        if(res.data.rank == 1 && !this.badgeInfo.includes(1007)){
          const challengeAddReq = {
            badgeId : 1007,
            userNickname : this.userInfo.userNickname,
          }
          this.addChallenge(challengeAddReq)
          this.addBadgeList(1007,"내가 바로 춤.신.춤.왕.","I am the king of dance!!")
        }
        // 1008, 점수가 10점이하
        if(this.score <= 10 && !this.badgeInfo.includes(1008)){
          const challengeAddReq = {
            badgeId : 1008,
            userNickname : this.userInfo.userNickname,
          }
          this.addChallenge(challengeAddReq)
          this.addBadgeList(1008,"춤이 추고 싶어?","Do you want to dance?")
        }
        // 1009, 점수가 100점
        if(this.score == 100 && !this.badgeInfo.includes(1009)){
          const challengeAddReq = {
            badgeId : 1009,
            userNickname : this.userInfo.userNickname,
          }
          this.addChallenge(challengeAddReq)
          this.addBadgeList(1009,"최고의 댄서이시군요!","You are the best dancer!")
        }
      })
    }
  }
}
</script>

<style>

</style>