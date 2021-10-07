<template>
  <v-card id="rankBoard">
    <v-img id="rank" src="images/HallofFame/rank.png"></v-img>
    <div class="first" v-if="check(firstRank)">
      <v-img class="myImg" :src="getImg(firstRank)" />
      <span class="myScore"> {{firstRank.value}} </span>
      <span class="myName">{{firstRank.userNickname}}</span>
    </div>
    <div class="second" v-if="check(secondRank)">
      <v-img class="myImg" :src="getImg(secondRank)" />
      <span class="myScore"> {{secondRank.value}} </span>
      <span class="myName">{{secondRank.userNickname}}</span>
    </div>
    <div class="third" v-if="check(thirdRank)">
      <v-img class="myImg" :src="getImg(thirdRank)" />
      <span class="myScore"> {{thirdRank.value}} </span>
      <span class="myName">{{thirdRank.userNickname}}</span>
    </div>
    <hr>
    <div class="list"> 
      <template>
        <v-simple-table
          class = "table transparent white--text"
          fixed-header
          height="100%"
        >
          <template v-slot:default>
            <tbody>
              <tr
                v-for="rank in rankList"
                :key="rank.rank"
              >
                <td style="width: 10vh; font-size: 2vh; text-align: center; vertical-align: middle;">{{ rank.rank }}</td>
                <td style="width: 4vw; padding-right: 0px;"><v-img class="listImg mt-2 mb-2" :src="getImg(rank)" /></td>
                <td style="font-size: 2vh; padding: 0px; text-align: center; vertical-align: middle;">{{ rank.userNickname }}</td>
                <td style="font-size: 2vh; padding: 0px; text-align: center; vertical-align: middle;">{{ rank.value }}</td>
              </tr>
            </tbody>
          </template>
        </v-simple-table>
      </template>
    </div>
    <div class="me transparent white--text"> 
      <div style="font-size: 2vh; text-align: center;" v-if="checkMinus(myRank.rank)">{{ myRank.rank }}</div>
      <div style="font-size: 2vh; text-align: center;" v-else>-</div>
      <div><v-img class="listImg" :src="getImg(myRank)" /></div>
      <div style="font-size: 2vh; padding: 0px; text-align: center;">{{ myRank.userNickname }}</div>
      <div style="font-size: 2vh; padding: 0px; text-align: center;">{{ myRank.value }}</div>
    </div>
  </v-card>
</template>

<script>
import { mapGetters } from 'vuex'
import http from '@/http.js';

export default {
    data(){
        return {
          rankList: [],
          firstRank: {},
          secondRank: {},
          thirdRank: {},
          myRank: {
            rank: "",
            value: "",
            userNickname: "",
            userImg: ""
          }
        }
    },
    props: {
      songId: {
        type: Number,
      }
    },
    computed:{
        ...mapGetters(['token', 'user']),
    },
    methods:{
      getImg(rankInfo) {
        const profileImg = rankInfo.userImg
        if(profileImg === "") {
          return 'images/HallofFame/mirror-ball.png'
        } else {
          return profileImg;
        }
      },
      check(userInfo) {
        if(userInfo == null) {
          return false
        } else {
          return true
        }
      },
      checkMinus(rankValue) {
        if(rankValue == -1) {
          return false
        } else {
          return true
        }
      }
    },
    mounted() {
      const body = {
        songId: this.songId,
        userNickname: this.user.userNickname
      }
      http
        .get('/score/songRank/'+this.songId)
        .then((res) => {
          this.rankList = res.data.slice(3, res.data.length);
          this.firstRank = res.data[0]
          this.secondRank = res.data[1]
          this.thirdRank = res.data[2]
      }),
      http
        .post('/score/findMyScore/', body)
        .then((res) => {
          console.log(res.data);
          this.myRank.value = res.data.value
          this.myRank.rank = res.data.rank
          this.myRank.userNickname = this.user.userNickname
          this.myRank.userImg = this.user.profileImageUrl
      })
    },
    watch: {
      songId : function() {
        const body = {
          songId: this.songId,
          userNickname: this.user.userNickname
        }
        http
          .get('/score/songRank/'+this.songId)
          .then((res) => {
            console.log(this.songId)
            this.rankList = res.data.slice(3, res.data.length);
            this.firstRank = res.data[0]
            this.secondRank = res.data[1]
            this.thirdRank = res.data[2]
        }),
        http
          .post('/score/findMyScore/', body)
          .then((res) => {
            console.log(res.data);
            this.myRank.value = res.data.value
            this.myRank.rank = res.data.rank
            this.myRank.userNickname = this.user.userNickname
            this.myRank.userImg = this.user.profileImageUrl
        })
      }
    }
}
</script>

<style scoped>

.v-card > *[data-v-37ae665f]:last-child:not(.v-btn):not(.v-chip):not(.v-avatar) {
    border-bottom-left-radius: 20px;
    border-bottom-right-radius: 20px;
}

.v-card > *:last-child:not(.v-btn):not(.v-chip):not(.v-avatar) {
    border-bottom-left-radius: 20px;
    border-bottom-right-radius: 20px;
}

hr {
  position: absolute;
  width: 22vw;
  height: 0.5px;
  left: 4vw;
  bottom: 46vh;
  border: none;
  background-color:white;
}

#rankBoard {
  position: absolute;
  background-color: rgba( 0, 0, 0, 0.8 );
  /* background-color: rgba( 255, 255, 255, 0.1 ); */
  color:white;
  width: 30vw;
  height: 75vh;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  border-radius: 40px;
}

tbody, td, tfoot, th, thead, tr {
    border-color: inherit;
    border-style: none;
    border-width: 0;
}

tr:hover {
  background-color: transparent !important;
}

.table td:nth-child(n) {
  border-top: none;
}

#rank {
  position: absolute;
  width: 60%;
  height: 30%;
  left: 6.5vw;
  bottom: 46vh;
}

.first {
  position: absolute;
  width: 4vw;
  height: 6.5vw;
  top: 1vh;
  left: 13.2vw;
  /* transform: translate(-50%, -243%); */
  text-align: center;
  /* background-color: yellow; */
  /* 가득차게 */
  /* overflow:hidden; */
  margin:0 auto;
}

.second {
  position: absolute;
  width: 4vw;
  height: 6.5vw;
  top: 4.2vh;
  left: 9.2vw;
  margin:0 auto;
  text-align: center;
  /* transform: translate(-190%, -300%); */
  /* background-color: yellow; */
}

.third {
  position: absolute;
  width: 4vw;
  height: 6.5vw;
  top: 5.5vh;
  left: 17.2vw;
  margin:0 auto;
  text-align: center;
  /* transform: translate(-190%, -300%); */
  /* background-color: yellow; */
}

.myScore {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -180%);
  font-weight: bold;
  font-size: 2.5vh;
  color: white;
}

.myImgDiv {
  position: absolute;
  width: 100%;
  height: 80%;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}

.myImg {
  position: absolute;
  width: 3.5vw;
  height: 3.5vw;
  object-fit: cover;
  border-radius: 70%;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}

.myName {
  position: absolute;
  /* top: 50%; */
  /* left: 50%; */
  width: 100%;
  bottom: 5px;
  left: 0px;
  /* transform: translate(50%, 125%); */
  font-weight: bold;
  font-size: 0.5vw;
  color: white;
  margin-top: 2px;
}

.list {
  position: absolute;
  width: 22vw;
  height: 35vh;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -26%);
  color: #fff;
  text-shadow: 0 0 7px #fff, 0 0 10px #fff, 0 0 21px #fff, 0 0 42px #0fa,
    0 0 82px #0fa, 0 0 92px #0fa, 0 0 102px #0fa, 0 0 151px #0fa;
  /* background-color: red; */
    background-color: rgba( 255, 255, 255, 0.1 );
  border-radius: 40px;
}

.me {
  display: flex;
  position: absolute;
  width: 22vw;
  height: 8vh;
  top: 50%;
  left: 50%;
  transform: translate(-50%, 350%);
  display: flex;
  /* justify-content: center; */
  align-items: center;
  justify-content: space-evenly;
  color: #fff;
  box-shadow: 0 0 7px #fff, 0 0 10px yellow, 0 0 21px yellow, 0 0 42px yellow;
  border-radius: 20px;
}

.listImg {
  width: 2.5vw;
  height: 2.5vw;
  object-fit: cover;
  border-radius: 70%;
}

.table {
  width: 100%;
  height: 100%;
}

</style>