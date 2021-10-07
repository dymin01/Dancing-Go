<template>
  <div>
      <button @click="checkBadge">이거눌러봐요</button>
      <!-- <div v-if="getBadge">
        <BadgeToast v-bind:badgeNum="badgeNum" :badgeName="badgeName" :badgeImg="badgeImg"/>
      </div> -->
      <b-toast id="my-toast"
    toaster='b-toaster-top-center'
    append-toast
    body-class="toast_body"
    header-class="toast_header"
    toast-class="toast_toast"
    no-close-button
    >
      <template #toast-title>
      </template>
      <div class="d-flex justify-content-center badge_content">
        <b-spinner class="mt-3" type="grow" style="width: 3rem; height: 3rem;"/>
        <div class="mr-5">
        </div>
        <img :src="badgeImg" width="100vw">
        <div class="mr-5">
        </div>
        <div class="mt-4">
        <h3><b>{{badgeName}}</b></h3>
        </div>
        <div class="mr-5">
        </div>
        <b-spinner class="mt-3" type="grow" style="width: 3rem; height: 3rem;"/>
      </div>
    </b-toast>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import http from '@/http.js';
// import BadgeToast from '@/components/badge/BadgeToast.vue'
// import Vue from 'vue'

export default {
    // components:{
    //     BadgeToast,
    // },
    data(){
        return{
            userInfo: {},
            badgeInfo: [],
            score:'', // 점수 계산이 되어야함..
            badgeNum: '',
            badgeName: '',
            badgeImg: '',
            getBadge : false,
            userNickname:'',
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
            console.log('닉넴'+ this.userNickname)
        })
        // console.log('여기요'+this.userNickname)
        // http.get("/challenge/onlyMyBadge/"+ this.userNickname)
        // .then((res)=>{
        //     this.badgeInfo = res.data
        // })
    },
    methods:{
        checkBadge(){
            console.log('여기요'+this.userNickname)
            http.get("/challenge/onlyMyBadge/"+ this.userNickname)
            .then((res)=>{
                this.badgeInfo = res.data
            })
            const gameoverCnt = this.userInfo.gameoverCnt
            console.log('게임오버' + gameoverCnt)
            // const totalPlayCnt = this.userInfo.totalPlayCnt
            http.get("/user/gameover/"+this.userNickname)
            .then((res)=>{
                console.log('유저유저'+res)
            }),
            http.get("/user/gameinfo/"+this.user.userId)
            .then((res)=>{
                console.log('드루와1')
                // 1001, 첫 완곡
                // if(totalPlayCnt==0 && res.data.totalPlayCnt==1){
                //     const challengeAddReq = {
                //         badgeId : 1001,
                //         userNickname : this.userInfo.userNickname,
                //     }
                //     http.post("/challenge/addChallenge", challengeAddReq)
                //     .then((res)=>{
                //         alert('1001번 뱃지 획득!')
                //         console.log(res)
                //         this.badgeNum = 1001
                //         this.badgeName = '시작'
                //         this.badgeImg = 'images/badgeImg/1001.png'
                //         //여기다가 toast를 꽂고 싶은데 어케 하는걸까..
                //     })
                // }
                // 1002, 첫 게임오버
                console.log('오버카운트: '+gameoverCnt)
                console.log('지금바로오버카운트 : '+res.data.gameoverCnt)
                if(gameoverCnt==0 && res.data.gameoverCnt==1){
                    const challengeAddReq = {
                        badgeId : 1002,
                        userNickname : this.userInfo.userNickname,
                    }
                    http.post("/challenge/addChallenge", challengeAddReq)
                    .then((res)=>{
                        alert('1002번 뱃지 획득!')
                        console.log(res)
                        this.badgeNum = 1002
                        this.badgeName = '실패'
                        this.badgeImg = 'images/badgeImg/1002.png'
                        //여기다가 toast를 꽂고 싶은데 어케 하는걸까..
                        this.getBadge = true
                        console.log('show 직전')
                        this.$bvToast.show('my-toast')
                        console.log('show 직후')
                    })
                }
                // 1003, top 100
                // if(res.data.rank >= 100 && !this.badgeInfo.includes(1003)){
                //     const challengeAddReq = {
                //         badgeId : 1003,
                //         userNickname : this.userInfo.userNickname,
                //     }
                //     http.post("/challenge/addChallenge" ,challengeAddReq)
                //     .then((res)=>{
                //         alert('1003번 뱃지 획득!')
                //         console.log(res)
                //         //여기다가 toast를 꽂고 싶은데 어케 하는걸까..
                //     })
                // }
                // // 1004, top 10
                // if(res.data.rank >= 10 && !this.badgeInfo.includes(1004)){
                //     const challengeAddReq = {
                //         badgeId : 1004,
                //         userNickname : this.userInfo.userNickname,
                //     }
                //     http.post("/challenge/addChallenge", challengeAddReq)
                //     .then((res)=>{
                //         alert('1004번 뱃지 획득!')
                //         console.log(res)
                //         //여기다가 toast를 꽂고 싶은데 어케 하는걸까..
                //     })
                // }
                // // 1005, top 3
                // if(res.data.rank >= 3 && !this.badgeInfo.includes(1005)){
                //     const challengeAddReq = {
                //         badgeId : 1005,
                //         userNickname : this.userInfo.userNickname,
                //     }
                //     http.post("/challenge/addChallenge", challengeAddReq)
                //     .then((res)=>{
                //         alert('1005번 뱃지 획득!')
                //         console.log(res)
                //         //여기다가 toast를 꽂고 싶은데 어케 하는걸까..
                //     })
                // }
                // // 1006, top 2
                // if(res.data.rank >= 2 && !this.badgeInfo.includes(1006)){
                //     const challengeAddReq = {
                //         badgeId : 1006,
                //         userNickname : this.userInfo.userNickname,
                //     }
                //     http.post("/challenge/addChallenge", challengeAddReq)
                //     .then((res)=>{
                //         alert('1006번 뱃지 획득!')
                //         console.log(res)
                //         //여기다가 toast를 꽂고 싶은데 어케 하는걸까..
                //     })
                // }
                // // 1007, top 1
                // if(res.data.rank == 1 && !this.badgeInfo.includes(1007)){
                //     const challengeAddReq = {
                //         badgeId : 1007,
                //         userNickname : this.userInfo.userNickname,
                //     }
                //     http.post("/challenge/addChallenge", challengeAddReq)
                //     .then((res)=>{
                //         alert('1007번 뱃지 획득!')
                //         console.log(res)
                //         //여기다가 toast를 꽂고 싶은데 어케 하는걸까..
                //     })
                // }
                // // 1008, 점수가 10점이하
                // if(this.score <= 10 && !this.badgeInfo.includes(1008)){
                //     const challengeAddReq = {
                //         badgeId : 1008,
                //         userNickname : this.userInfo.userNickname,
                //     }
                //     http.post("/challenge/addChallenge", challengeAddReq)
                //     .then((res)=>{
                //         alert('1008번 뱃지 획득!')
                //         console.log(res)
                //         //여기다가 toast를 꽂고 싶은데 어케 하는걸까..
                //     })
                // }
                // // 1009, 점수가 100점
                // if(this.score == 100 && !this.badgeInfo.includes(1009)){
                //     const challengeAddReq = {
                //         badgeId : 1009,
                //         userNickname : this.userInfo.userNickname,
                //     }
                //     http.post("/challenge/addChallenge", challengeAddReq)
                //     .then((res)=>{
                //         alert('1009번 뱃지 획득!')
                //         console.log(res)
                //         //여기다가 toast를 꽂고 싶은데 어케 하는걸까..
                //     })
                // }

            })




        }
    }
}
</script>

<style>

</style>