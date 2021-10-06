<template>
  <div>
    <img src="" alt="" id="background" ref="background">
    <div id="shade"></div>
    <div id="score-box" class="">

      <v-form class="score-half text-center" id="rank-box" >
        <v-container v-if="this.rankVisible">
          <v-row class="mt-4">
            <div ref="rank" class="text" style="font-size: 250px; line-height: 300px">{{this.rank}}</div>
          </v-row>
          <v-row>
            <v-col cols="6">
              <div class="text" style="font-size: 50px; padding-bottom:50px; padding-left:20px">Score</div>
            </v-col>
            <v-col cols="6">
              <div class="text" style="font-size: 50px; padding-bottom:50px; text-align: end; padding-right: 50px">{{totalScore}}</div>
            </v-col>
          </v-row>
        </v-container>
      </v-form>
      <div class="score-half text" id="scores-box">
        <div id="score-name">
          <div id="perfect">Perfect</div>
          <div id="great">Great</div>
          <div id="good">Good</div>
          <div id="bad">Bad</div>
          <div id="miss">Miss</div>
        </div>
        <div id="score-number">
          <div ref="perfect" id="perfect">{{ tmpScores[0] }}</div>
          <div ref="great" id="great">{{ tmpScores[1] }}</div>
          <div ref="good" id="good">{{ tmpScores[2] }}</div>
          <div ref="bad" id="bad">{{ tmpScores[3] }}</div>
          <div ref="miss" id="miss">{{ tmpScores[4] }}</div>
        </div>
      </div>
    </div>
    <div v-show="this.menuVisible" id="rank-menu-box">
      <div class="rank-menu mr-5">
        <div @click="retry">재도전</div>  
      </div>
      <div class="rank-menu ml-5">
        <div @click="goHome">곡선택</div>
      </div>
    </div>
    <Snackbar :totalScore="totalScore"/>
  </div>
</template>

<script>
import router from '@/router/index.js'
import http from '@/http.js';
import Snackbar from '@/components/badge/snackbar.vue'
import { mapGetters } from 'vuex'

export default {
  data() {
    return {
      rank: 'A',
      scores: Array,
      tmpScores: [0, 0, 0, 0, 0],
      rankVisible: false,
      menuVisible: false,
      totalScore: 0,
      totalScoreStr: '',
      rankColor: {
        'S': '#ff207a',
        'A': '#ff0000',
        'B': 'orange',
        'C': 'green',
        'D': 'navy',
        'F': 'rgb(59, 59, 59)'
        // 'F': 'red'
      }
    }
  },
  components:{
    Snackbar
  },
  computed: {
    ...mapGetters(['token', 'user'])
  },
  methods: {
    showPerfect() {
      var perfectInterval = setInterval(function() {
        this.$refs.perfect.innerText = this.tmpScores[0]
        if (this.tmpScores[0] >= this.scores[0]) {
          clearInterval(perfectInterval)
        }
        this.tmpScores[0] += 1
      }.bind(this), 20)
      setTimeout(this.showGreat, this.scores[0]*20 + 200)
    },
    showGreat() {
      var greatInterval = setInterval(function() {
        this.$refs.great.innerText = this.tmpScores[1]
        if (this.tmpScores[1] >= this.scores[1]) {
          clearInterval(greatInterval)
        }
        this.tmpScores[1] += 1
      }.bind(this), 20)
      setTimeout(this.showGood, this.scores[1]*20 + 200)
    },
    showGood() {
      var goodInterval = setInterval(function() {
        this.$refs.good.innerText = this.tmpScores[2]
        if (this.tmpScores[2] >= this.scores[2]) {
          clearInterval(goodInterval)
        }
        this.tmpScores[2] += 1
      }.bind(this), 20)
      setTimeout(this.showBad, this.scores[2]*20 + 200)
    },
    showBad() {
      var badInterval = setInterval(function() {
        this.$refs.bad.innerText = this.tmpScores[3]
        if (this.tmpScores[3] >= this.scores[3]) {
          clearInterval(badInterval)
        }
        this.tmpScores[3] += 1
      }.bind(this), 20)
      setTimeout(this.showMiss, this.scores[3]*20 + 200)
    },
    showMiss() {
      var missInterval = setInterval(function() {
        this.$refs.miss.innerText = this.tmpScores[4]
        if (this.tmpScores[4] >= this.scores[4]) {
          clearInterval(missInterval)
        }
        this.tmpScores[4] += 1
      }.bind(this), 20)
      setTimeout(this.showRank, this.scores[4]*20 + 1000)
    },
    showRank() {
      this.rankVisible = true
      setTimeout(function() {
        this.$refs.rank.style.color = this.rankColor[this.rank]
      }.bind(this), 10)
      setTimeout(this.showMenu, 1000)
      this.sendResult()
    },
    sendResult() {
      const body = {
        'songId': localStorage.getItem('songId') * 1,
        'userNickname': this.user.userNickname,
        'value': this.totalScore
      }
      console.log(body)
      http.post('/score/saveScoreValue/', body)
      .then((res) => {
        console.log(res)
      })
      .catch((err) => {
        console.log(err)
      })
    },
    showMenu() {
      this.menuVisible = true
    },
    retry() {
      router.push('/ranking/' + localStorage.getItem('songId'))
    },
    goHome() {
      router.push({ name: 'MusicSelect', query: {'mode': 'Ranking'} })
    }
  },
  mounted() {
    this.$refs.background.src = '/images/musicselect/' + localStorage.getItem('songName') + '.png'
    const scores = this.$store.state.ranking.scores
    this.scores = scores
    var frameNum = scores[0] + scores[1] + scores[2] + scores[3] + scores[4]
    var totalScore = (scores[0]*1 + scores[1]*0.9 + scores[2]*0.75 + scores[3]*0.5 + scores[4]*0)*100/frameNum
    totalScore = totalScore.toFixed(2)
    if (totalScore >= 90) { 
      this.rank = 'S'
    } else if (totalScore >= 80) {
      this.rank = 'A'
    } else if (totalScore >= 70) {
      this.rank = 'B'
    } else if (totalScore >= 60) {
      this.rank = 'C'
    } else if (totalScore >= 50) {
      this.rank = 'D'
    } else {
      this.rank = 'F'
    }
    this.totalScore = Number(totalScore)
    this.showPerfect()
  }
}
</script>

<style scoped>
#background {
  position: absolute;
  left: 0px;
  top: 0px;
  width: 100vw;
  height: 100vh;
  opacity: 0.5;
}

#shade {
  position: absolute;
  left: 0px;
  top: 0px;
  width: 100vw;
  height: 100vh;
  background-color: black;
  opacity: 0.7;
}

#score-box {
  position: absolute;
  /* left: 17vw;
  top: 8vh; */
  top: 42.5%;
  left: 50%;
  transform: translate(-50%, -50%);

  width: 70vw;
  height: 70vh;
  display: flex;
  justify-content: space-evenly;
  align-items: center;
  border-radius: 10px;
  /* background-color: rgba(0, 0, 0, 0.6); */
  background-color: rgba(43, 29, 59, 0.6);
  box-shadow: 0 0 20px white;

}

.score-half {
  width: 44%;
  height: 85%;
  background-color: rgb(215, 226, 255);
  border-radius: 10px;
  /* box-shadow: 0 0 20px white; */
  /* filter: blur(1.3px); */
}

#rank-box {
  display: flex;
  justify-content: center;
  align-items: center;

  /* background-color: rgb(43, 21, 68, 0.6); */
  background-color: rgba(58, 42, 75, 0);
}

.text {
  font-weight: bold;
  /* text-shadow: 0 0 10px #0fa, 0 0 21px #0fa; */
  color: rgb(59, 59, 59);
  /* text-shadow: 0 0 7px #fff, 0 0 10px #0fa, 0 0 21px #0fa; */
  text-shadow: 0 0 7px #fff, 0 0 10px #fff, 0 0 21px #fff, 0 0 42px #fff;
}

#scores-box {
  display: flex;
  justify-content: space-evenly;
  align-items: center;
  /* box-shadow: 0 0 20px white; */
  border: 4px solid rgb(255, 255, 255,0.5);
  /* border-block-color: rgb(43, 21, 68); */
  background-color: rgb(255,255,255, 0.2);
}

#score-name {
  text-align: left;
}

#score-name div {
  font-size: 40px;
  /* font-size: 50px; */
}

#score-number div {
  font-size: 40px;
  /* font-size: 50px; */
}

#rank-menu-box {
  position: absolute;
  /* top: 80vh; */
  /* left: 8vw; */
  top: 86%;
  left: 50%;
  transform: translate(-50%, -50%);

  width: 84vw;
  height: 80px;
  display: flex;
  /* justify-content: flex-end; */
  justify-content: center;
}

.rank-menu {
  /* background-color: rgba(0, 0, 0, 0.6); */
  /* background-color: rgb(97, 8, 97); */
  background-color: rgba(43, 29, 59, 0.6);
  border: 2px solid rgb(255, 255, 255,0.5);
  border-radius: 10px;
  color: rgb(255,255,255, 0.8);
  text-align: center;
  vertical-align: center;
  width: 150px;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 25px;
}
.rank-menu:hover {
  cursor: pointer;
}

#perfect {
  color: white;
  text-shadow: 0 0 3px #eee, 0 0 10px rgb(57, 123, 247), 0 0 21px rgb(57, 123, 247), 0 0 42px rgb(57, 123, 247);
}

#great {
  color: white;
  text-shadow: 0 0 3px #eee, 0 0 10px rgb(123, 255, 71), 0 0 21px rgb(123, 255, 71), 0 0 42px rgb(123, 255, 71);
}

#good {
  color: white;
  text-shadow: 0 0 3px #eee, 0 0 10px rgb(252, 255, 82), 0 0 21px rgb(252, 255, 82), 0 0 42px rgb(252, 255, 82);

}

#bad {
  color: white;
  text-shadow: 0 0 3px #eee, 0 0 10px rgb(90, 0, 112), 0 0 21px rgb(90, 0, 112), 0 0 42px rgb(90, 0, 112);
}

#miss {
  color: white;
  text-shadow: 0 0 3px #eee, 0 0 10px rgb(187, 39, 39), 0 0 21px rgb(187, 39, 39), 0 0 42px rgb(187, 39, 39);
}

</style>