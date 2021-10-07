<template>
  <div>
    <v-img id="background" src="images/home/hall2.jpg"></v-img>
    <!-- <v-img id="background" src="images/HallofFame/HallofFame.png"></v-img> -->
    <!-- <v-img id="Firecracker" src="images/HallofFame/Firecracker.png"></v-img> -->
    <div class="board"> 

    </div>
    <v-img id="rank" src="images/HallofFame/rank.png"></v-img>
    <div class="first" v-if="check(firstRank)">
      <v-img class="myImg" :src="getImg(firstRank)" />
      <span class="myScore"> {{firstRank.totalScore}} </span>
      <span class="myName">{{firstRank.userNickname}}</span>
    </div>
    <div class="second" v-if="check(secondRank)">
      <v-img class="myImg" :src="getImg(secondRank)" />
      <span class="myScore"> {{secondRank.totalScore}} </span>
      <span class="myName">{{secondRank.userNickname}}</span>
    </div>
    <div class="third" v-if="check(thirdRank)">
      <v-img class="myImg" :src="getImg(thirdRank)" />
      <span class="myScore"> {{thirdRank.totalScore}} </span>
      <span class="myName">{{thirdRank.userNickname}}</span>
    </div>
    <hr class="my-hr">
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
                <td style="width: 12vh; font-size: 2.2vh; text-align: center; vertical-align: middle;">{{ rank.rank }}</td>
                <td style="width: 4vw; padding-left: 0px;"><v-img class="listImg mt-2 mb-2" :src="getImg(rank)" /></td>
                <td style="width: 19vh; font-size: 2.2vh; padding: 0px; text-align: center; vertical-align: middle;">{{ rank.userNickname }}</td>
                <td style="font-size: 2.2vh; padding-right: 10px; padding: 0px; text-align: center; vertical-align: middle;">{{ rank.totalScore }}</td>
              </tr>
            </tbody>
          </template>
        </v-simple-table>
      </template>
    </div>
    <div class="me white--text"> 
      <div style="font-size: 2.2vh; text-align: center;" v-if="checkMinus(myRank.rank)">{{ myRank.rank }}</div>
      <div style="font-size: 2.2vh; text-align: center;" v-else>-</div>
      <div><v-img class="listImg" :src="getImg(myRank)" /></div>
      <div style="font-size: 2.2vh; padding: 0px; text-align: center;">{{ myRank.userNickname }}</div>
      <div style="font-size: 2.2vh; padding: 0px; text-align: center;">{{ myRank.totalScore }}</div>
    </div>
    <audio src="songs/halloffame.mp3" ref="backgroundSound"></audio>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import http from '@/http.js';

export default {
  name: 'HallofFame',
  data() {
    return {
      rankList: [],
      firstRank: {},
      secondRank: {},
      thirdRank: {},
      myRank: {}
    }
  },
  methods: {
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
  computed: {
    ...mapGetters(['token', 'user']),
    changeBg () {
      return this.$store.getters.backgroundVolume*(0.01)
    },
  },
  watch: {
    changeBg (val) {
      console.log(val)
      this.$refs.backgroundSound.volume = val
      console.log(this.$refs.backgroundSound.volume)
    },
  },
  mounted() {
    http
      .get('/user/rank')
      .then((res) => {
        this.rankList = res.data.slice(3, res.data.length);
        this.firstRank = res.data[0]
        this.secondRank = res.data[1]
        this.thirdRank = res.data[2]
    }),
    http
      .get('/user/info/' + this.user.userId)
      .then((res) => {
        this.myRank = res.data
        console.log(this.myRank)
    })
    this.$refs.backgroundSound.volume = this.$store.getters.backgroundVolume*(0.01)
    this.$refs.backgroundSound.play()
  }
}
</script>

<style scoped>

tbody, td, tfoot, th, thead, tr {
    border-color: inherit;
    border-style: none;
    border-width: 0;
}

tr:hover {
  background-color: transparent !important;
}

html {
  overflow: hidden;
  scrollbar-width: none;
  -ms-overflow-style: none;
}

html::-webkit-scrollbar {
  display: none;
}

.my-hr {
  position: absolute;
  width: 30vw;
  left: 35vw;
  bottom: 56.5vh;
  border: 0;
  height: 3px;
  background-color:white;
}

.table td:nth-child(n) {
  border-top: none;
}

#background {
  position: absolute;
  width: 100vw;
  height: 100vh;
}

#Firecracker {
  position: absolute;
  width: 100vw;
  height: 100vh;
}

.board {
 position: absolute;
 background-color: black;
 width: 40vw;
 height: 90vh;
 /* 투명도 */
 opacity: 0.6;
 /* 가운데 정렬 */
 top: 50%;
 left: 50%;
 transform: translate(-50%, -50%);
 border-radius: 40px;
}

#rank {
  position: absolute;
  width: 30%;
  height: 30%;
  left: 35.5vw;
  bottom: 56vh;
}

.first {
  position: absolute;
  width: 6vw;
  height: 9vw;
  top: 8vh;
  left: 47vw;
  /* transform: translate(-50%, -243%); */
  text-align: center;
  /* background-color: yellow; */
  /* 가득차게 */
  /* overflow:hidden; */
  margin:0 auto;
}

.second {
  position: absolute;
  width: 6vw;
  height: 9vw;
  top: 13vh;
  left: 40.5vw;
  margin:0 auto;
  text-align: center;
  /* transform: translate(-190%, -300%); */
  /* background-color: yellow; */
}

.third {
  position: absolute;
  width: 6vw;
  height: 9vw;
  top: 14vh;
  left: 53.5vw;
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
  transform: translate(-50%, -18%);
  color: #fff;
  text-shadow: 0 0 7px #fff, 0 0 10px #fff, 0 0 21px #fff, 0 0 42px #0fa,
    0 0 82px #0fa, 0 0 92px #0fa, 0 0 102px #0fa, 0 0 151px #0fa;
    /* background-color: black; */
  background-color: rgba( 0, 0, 0, 0.8 );
  border-radius: 40px;
}

.me {
  display: flex;
  position: absolute;
  width: 30vw;
  height: 9vh;
  top: 50%;
  left: 50%;
  transform: translate(-50%, 387%);
  display: flex;
  /* justify-content: center; */
  align-items: center;
  justify-content: space-evenly;
  color: #fff;
  box-shadow: 0 0 7px #fff, 0 0 10px yellow, 0 0 21px yellow, 0 0 42px yellow;
  /* background-color: rgba( 0, 0, 0, 0.8 ); */
  border-radius: 20px;
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