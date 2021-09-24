<template>
  <div>
    <v-img id="background" src="images/HallofFame/stage3.jpg"></v-img>
    <v-img id="Firecracker" src="images/HallofFame/Firecracker.png"></v-img>
    <div class="board"> 

    </div>
    <v-img id="rank" src="images/HallofFame/rank.png"></v-img>
    <div class="first">
      <v-img class="myImg" :src="getImg(firstRank)" />
      <span class="myScore"> {{firstRank.totalScore}} </span>
      <span class="myName">{{firstRank.userNickname}}</span>
    </div>
    <div class="second">
      <v-img class="myImg" :src="getImg(secondRank)" />
      <span class="myScore"> {{secondRank.totalScore}} </span>
      <span class="myName">{{secondRank.userNickname}}</span>
    </div>
    <div class="third">
      <v-img class="myImg" :src="getImg(thirdRank)" />
      <span class="myScore"> {{thirdRank.totalScore}} </span>
      <span class="myName">{{thirdRank.userNickname}}</span>
    </div>
    <div class="list"> 
      <template>
        <v-simple-table
          class = "table transparent white--text"
          fixed-header
        >
          <template v-slot:default>
            <!-- <thead>
              <tr>
                <th class="text-left">
                  Rank
                </th>
                <th class="text-left">
                  Img
                </th>
                <th class="text-left">
                  Nickname
                </th>
                <th class="text-left">
                  Score
                </th>
              </tr>
            </thead> -->
            <tbody>
              <tr
                v-for="rank in rankList"
                :key="rank.rank"
                style="border-style: none;"
              >
                <td style="width: 10vh; font-size: 3vh; text-align: center;">{{ rank.rank }}</td>
                <td style="width: 4vw;"><v-img class="listImg mt-2 mb-2" :src="getImg(rank)" /></td>
                <td style="font-size: 3vh; padding: 0px; text-align: center;">{{ rank.userNickname }}</td>
                <td style="font-size: 3vh; padding: 0px; ztext-align: center;">{{ rank.totalScore }}</td>
              </tr>
            </tbody>
          </template>
        </v-simple-table>
      </template>
    </div>
    <div class="me"> </div>
  </div>
</template>

<script>
import axios from 'axios'

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
    }
  },
  mounted() {
     axios
      .get('http://localhost:8080/user/rank')
      .then((res) => {
        this.rankList = res.data;
        console.log(this.rankList)
        console.log(this.rankList.length)
        this.firstRank = this.rankList[0]
        this.secondRank = this.rankList[1]
        this.thirdRank = this.rankList[2]
      }),
      axios
      .get('http://localhost:8080/user/info/117154352607372629256')
      .then((res) => {
        this.myRank = res.data
      })
  }
}
</script>

<style>

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
 width: 75vw;
 height: 95vh;
 /* 투명도 */
 opacity: 0.6;
 /* 가운데 정렬 */
 top: 50%;
 left: 50%;
 transform: translate(-50%, -50%);
}

#rank {
  position: absolute;
  width: 30%;
  height: 30%;
  left: 35.5vw;
  bottom: 55vh;
}

.first {
  position: absolute;
  width: 6vw;
  height: 9vw;
  top: 5.5vh;
  left: 47vw;
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
  top: 11vh;
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
  top: 12.5vh;
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
  transform: translate(-50%, -14%);
  /* background-color: tomato; */
}

.me {
  position: absolute;
  width: 30vw;
  height: 9vh;
  top: 50%;
  left: 50%;
  transform: translate(-50%, 405%);
  background-color: lime;
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