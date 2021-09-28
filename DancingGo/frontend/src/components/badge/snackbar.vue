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
      <img :src="badgeImg" width="200px">
      {{ badgeName }}
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
import axios from 'axios'
export default {
  data () {
    return{
      userInfo: {},
      badgeInfo: [],
      gameover: false,
      userNickname:'',
      badgeNum: '',
      badgeName: '',
      badgeImg: '',

      snackbar: false,
      vertical: true,
      height: '300px',
      width: '500px',
      timeout : 2000,
    }
  },
  computed:{
    ...mapGetters(['token', 'user']),
  },
  mounted() {
    axios.get("/user/gameinfo/" + this.user.userId)
    .then((res)=>{
      this.userInfo = res.data
      this.userNickname = res.data.userNickname
      console.log('닉넴'+ this.userNickname)
    })
  },
  methods:{
    gameOver(){
      axios.get("/user/gameover/"+this.userNickname)
      .then((res)=>{
        console.log('GameOver함수'+res)
        this.gameover=true
      })
    },
    checkBadge(){
      console.log('여기요'+this.userNickname)
      axios.get("/challenge/onlyMyBadge/"+ this.userNickname)
      .then((res)=>{
        this.badgeInfo = res.data
        console.log('뱃지인포')
      })
      const gameoverCnt = this.userInfo.gameoverCnt //mounted
      console.log('원래 게임오버 카운트' + gameoverCnt)
      const totalPlayCnt = this.userInfo.totalPlayCnt
      // this.gameOver()
      axios.get("/user/gameover/"+this.userNickname)
      .then((res)=>{
        console.log('GameOver함수'+res)
        this.gameover=true
      })
      setTimeout(function(){
        this.cc(totalPlayCnt, gameoverCnt)
      }.bind(this),200)
    },
    addChall(challengeAddReq, badgeNum, badgeName){
      axios.post("/challenge/addChallenge", challengeAddReq)
      .then(()=>{
        alert(badgeNum+'번 뱃지 획득!')
        this.badgeNum = badgeNum
        this.badgeName = badgeName
        this.badgeImg = 'images/badgeImg/'+badgeNum+'.png'
        this.snackbar = true
      })
    },
    cc(totalPlayCnt, gameoverCnt){
      axios.get("/user/gameinfo/"+this.user.userId)
      .then((res)=>{
        console.log('드루와1')
        console.log(this.badgeInfo)
        // 1001, 첫 완곡
        if(totalPlayCnt==0 && res.data.totalPlayCnt==1){
          const challengeAddReq = {
            badgeId : 1001,
            userNickname : this.userInfo.userNickname,
          }
          axios.post("/challenge/addChallenge", challengeAddReq)
          .then((res)=>{
            alert('1001번 뱃지 획득!')
            console.log(res)
            this.badgeNum = 1001
            this.badgeName = '시작'
            this.badgeImg = 'images/badgeImg/1001.png'
          })
        }
        // 1002, 첫 게임오버
        console.log('오버카운트: '+gameoverCnt)
        console.log('지금바로오버카운트 : '+res.data.gameoverCnt)
        console.log(this.gameover)
        if(gameoverCnt==0 && res.data.gameoverCnt==1){
          const challengeAddReq = {
            badgeId : 1002,
            userNickname : this.userInfo.userNickname,
          }
          this.addChall(challengeAddReq, 1002, '실패에용')
        }
        // console.log('희망'+this.badgeInfo.includes("1002"))
        // console.log('희망2'+this.badgeInfo.badgeId.includes("1002"))
        console.log('뱃지인포쩜뱃지1'+ this.badgeInfo.includes(1002))
        // console.log('뱃지인포쩜뱃지2'+ this.badgeInfo.badgeId.includes(1002))
        // 1003, top 100
        if(res.data.rank >= 100 && !this.badgeInfo.includes(1003)){
          const challengeAddReq = {
            badgeId : 1003,
            userNickname : this.userInfo.userNickname,
          }
          axios.post("/challenge/addChallenge" ,challengeAddReq)
          .then((res)=>{
            alert('1003번 뱃지 획득!')
            console.log(res)
          })
        }
        // 1004, top 10
        if(res.data.rank >= 10 && !this.badgeInfo.includes(1004)){
          const challengeAddReq = {
            badgeId : 1004,
            userNickname : this.userInfo.userNickname,
          }
          axios.post("/challenge/addChallenge", challengeAddReq)
          .then((res)=>{
            alert('1004번 뱃지 획득!')
            console.log(res)
          })
        }
        // 1005, top 3
        if(res.data.rank >= 3 && !this.badgeInfo.includes(1005)){
          const challengeAddReq = {
            badgeId : 1005,
            userNickname : this.userInfo.userNickname,
          }
          axios.post("/challenge/addChallenge", challengeAddReq)
          .then((res)=>{
            alert('1005번 뱃지 획득!')
            console.log(res)
          })
        }
        // 1006, top 2
        if(res.data.rank >= 2 && !this.badgeInfo.includes(1006)){
          const challengeAddReq = {
            badgeId : 1006,
            userNickname : this.userInfo.userNickname,
          }
          axios.post("/challenge/addChallenge", challengeAddReq)
          .then((res)=>{
            alert('1006번 뱃지 획득!')
            console.log(res)
          })
        }
        // 1007, top 1
        if(res.data.rank == 1 && !this.badgeInfo.includes(1007)){
          const challengeAddReq = {
            badgeId : 1007,
            userNickname : this.userInfo.userNickname,
          }
          axios.post("/challenge/addChallenge", challengeAddReq)
          .then((res)=>{
            alert('1007번 뱃지 획득!')
            console.log(res)
          })
        }
        // 1008, 점수가 10점이하
        if(this.score <= 10 && !this.badgeInfo.includes(1008)){
          const challengeAddReq = {
            badgeId : 1008,
            userNickname : this.userInfo.userNickname,
          }
          axios.post("/challenge/addChallenge", challengeAddReq)
          .then((res)=>{
            alert('1008번 뱃지 획득!')
            console.log(res)
          })
        }
        // 1009, 점수가 100점
        if(this.score == 100 && !this.badgeInfo.includes(1009)){
          const challengeAddReq = {
            badgeId : 1009,
            userNickname : this.userInfo.userNickname,
          }
          axios.post("/challenge/addChallenge", challengeAddReq)
          .then((res)=>{
            alert('1009번 뱃지 획득!')
            console.log(res)
          })
        }
      })
    }
  }
}
</script>

<style>

</style>