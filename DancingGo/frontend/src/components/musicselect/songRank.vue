<template>
  <v-card id="rankBoard">
    <v-img id="rank" src="images/HallofFame/rank.png"></v-img>
    <div class="first">
      <v-img class="myImg" :src="getImg(firstRank)" />
      <!-- <v-img id="myImg" src="images/HallofFame/mirror-ball.png" /> -->
      <span class="myScore"> {{firstRank.value}} </span>
      <span class="myName">{{firstRank.userNickname}}</span>
    </div>
    <div class="second">
      <v-img class="myImg" :src="getImg(secondRank)" />
      <!-- <v-img id="myImg" src="images/HallofFame/mirror-ball.png" /> -->
      <span class="myScore"> {{secondRank.value}} </span>
      <span class="myName">{{secondRank.userNickname}}</span>
    </div>
    <div class="third">
      <v-img class="myImg" :src="getImg(thirdRank)" />
      <!-- <v-img id="myImg" src="images/HallofFame/mirror-ball.png" /> -->
      <span class="myScore"> {{thirdRank.value}} </span>
      <span class="myName">{{thirdRank.userNickname}}</span>
    </div>
    <div class="list"> 
      <template>
        <v-simple-table
          class = "table transparent white--text"
          fixed-header
        >
          <template v-slot:default>
            <tbody>
              <tr
                v-for="rank in rankList"
                :key="rank.rank"
              >
                <td style="width: 10vh; font-size: 3vh; text-align: center; vertical-align: middle;">{{ rank.rank }}</td>
                <!-- <td style="width: 4vw;"><v-img class="listImg mt-2 mb-2" :src="getImg(rank)" /></td> -->
                <td style="width: 4vw;"><v-img class="listImg mt-2 mb-2" src="images/HallofFame/mirror-ball.png" /></td>
                <td style="font-size: 3vh; padding: 0px; text-align: center; vertical-align: middle;">{{ rank.userNickname }}</td>
                <td style="font-size: 3vh; padding: 0px; text-align: center; vertical-align: middle;">{{ rank.totalScore }}</td>
              </tr>
            </tbody>
          </template>
        </v-simple-table>
      </template>
    </div>
    <div class="me transparent white--text"> 
      <div style="font-size: 3vh; text-align: center;">{{ myRank.rank }}</div>
      <!-- <div><v-img class="listImg" :src="getImg(myRank)" /></div> -->
      <div><v-img class="listImg" src="images/HallofFame/mirror-ball.png" /></div>
      <div style="font-size: 3vh; padding: 0px; text-align: center;">{{ myRank.userNickname }}</div>
      <div style="font-size: 3vh; padding: 0px; text-align: center;">{{ myRank.totalScore }}</div>
    </div>
  </v-card>
</template>

<script>
import { mapGetters } from 'vuex'
import axios from 'axios'

export default {
    data(){
        return {
          rankList: [],
          firstRank: {},
          secondRank: {},
          thirdRank: {},
          myRank: {}
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
      }
    },
    mounted() {
      const body = {
        songId: this.songId,
        userNickname: this.user.userNickname
      }
      axios
        .get('/score/songRank/'+this.songId)
        .then((res) => {
          this.rankList = res.data;
          this.firstRank = this.rankList[0]
          this.secondRank = this.rankList[1]
          this.thirdRank = this.rankList[2]
      }),
      axios
        .post('/score/findMyScore/', body)
        .then((res) => {
          console.log(res.data);
          this.myRank = res.data
      })
    },
    watch: {
      songId : function() {
        const body = {
          songId: this.songId,
          userNickname: this.user.userNickname
        }
        axios
          .get('/score/songRank/'+this.songId)
          .then((res) => {
            this.rankList = res.data;
            this.firstRank = this.rankList[0]
            this.secondRank = this.rankList[1]
            this.thirdRank = this.rankList[2]
        }),
        axios
          .post('/score/findMyScore/', body)
          .then((res) => {
            console.log(res.data);
            this.myRank = res.data
        })
      }
    }
    // updated() {
    //   axios
    //     .get('/score/songRank/'+this.songId)
    //     .then((res) => {
    //       this.rankList = res.data;
    //       console.log(this.songId)
    //       this.firstRank = this.rankList[0]
    //       this.secondRank = this.rankList[1]
    //       this.thirdRank = this.rankList[2]
    //   }),
    //   axios
    //     .get('/user/info/' + this.user.userId)
    //     .then((res) => {
    //       this.myRank = res.data
    //   })
    // }
}
</script>

<style scoped>

.v-card > *:last-child:not(.v-btn):not(.v-chip):not(.v-avatar) {
  border-top-left-radius: inherit;
  border-top-right-radius: inherit;
  border-bottom-left-radius: inherit;
  border-bottom-right-radius: inherit;
}

#rankBoard {
  position: absolute;
  background-color: rgba( 0, 0, 0, 0.8 );
  /* background-color: rgba( 255, 255, 255, 0.1 ); */
  color:white;
  width: 75vw;
  height: 95vh;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}

tbody, td, tfoot, th, thead, tr {
    border-color: inherit;
    border-style: none;
    border-width: 0;
}

tr:hover {
  background-color: transparent !important;
}

/* .table td:nth-child(n) {
  border-top: none;
} */

#rank {
  position: absolute;
  width: 40%;
  height: 40%;
  left: 23vw;
  bottom: 49vh;
}

.first {
  position: absolute;
  width: 6vw;
  height: 9vw;
  top: 2vh;
  left: 34.5vw;
  /* transform: translate(-50%, -243%); */
  text-align: center;
  /* background-color: white; */
  /* 가득차게 */
  /* overflow:hidden; */
  margin:0 auto;
}

.second {
  position: absolute;
  width: 6vw;
  height: 9vw;
  top: 7.5vh;
  left: 28vw;
  margin:0 auto;
  text-align: center;
  /* transform: translate(-190%, -300%); */
  /* background-color: yellow; */
}

.third {
  position: absolute;
  width: 6vw;
  height: 9vw;
  top: 9.2vh;
  left: 41.5vw;
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
  font-size: 4vh;
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
  width: 6vw;
  height: 6vw;
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
  font-size: 0.8vw;
  color: white;
}

.list {
  position: absolute;
  width: 30vw;
  height: 40vh;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -14%);
  color: #fff;
  text-shadow: 0 0 7px #fff, 0 0 10px #fff, 0 0 21px #fff, 0 0 42px #0fa,
    0 0 82px #0fa, 0 0 92px #0fa, 0 0 102px #0fa, 0 0 151px #0fa;
  background-color: red;
}

.me {
  display: flex;
  position: absolute;
  width: 30vw;
  height: 9vh;
  top: 50%;
  left: 50%;
  transform: translate(-50%, 405%);
  display: flex;
  /* justify-content: center; */
  align-items: center;
  justify-content: space-evenly;
  color: #fff;
  box-shadow: 0 0 7px #fff, 0 0 10px yellow, 0 0 21px yellow, 0 0 42px yellow;
  border-radius: 15px;
}

.listImg {
  width: 3vw;
  height: 3vw;
  object-fit: cover;
  border-radius: 70%;
}

.table {
  width: 100%;
  height: 100%;
}

</style>