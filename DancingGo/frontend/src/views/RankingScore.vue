<template>
  <div>
    <img src="./video/bg.png" alt="" id="background">
    <div id="score-box" class="shadow">
      <div class="score-half" id="rank-box">
        <div style="font-size: 300px;" v-if="this.rankVisible">{{this.rank}}</div>
      </div>
      <div class="score-half" id="scores-box">
        <div id="score-name">
          <div>Perfect</div>
          <div>Great</div>
          <div>Good</div>
          <div>Bad</div>
          <div>Miss</div>
        </div>
        <div id="score-number">
          <div ref="perfect">{{ tmpScores[0] }}</div>
          <div ref="great">{{ tmpScores[1] }}</div>
          <div ref="good">{{ tmpScores[2] }}</div>
          <div ref="bad">{{ tmpScores[3] }}</div>
          <div ref="miss">{{ tmpScores[4] }}</div>
        </div>
      </div>
    </div>
    <div v-show="this.menuVisible" id="rank-menu-box">
      <div class="rank-menu">
        <div @click="retry">재도전</div>  
      </div>
      <div class="rank-menu">
        <div @click="goHome">메인메뉴</div>
      </div>
    </div>
  </div>
</template>

<script>
import router from '@/router/index.js'

export default {
  data() {
    return {
      rank: 'A',
      scores: [100, 80, 20, 10, 4],
      tmpScores: [0, 0, 0, 0, 0],
      rankVisible: false,
      menuVisible: false,
    }
  },
  methods: {
    showPerfect() {
      var perfectInterval = setInterval(function() {
        this.tmpScores[0] += 1
        this.$refs.perfect.innerText = this.tmpScores[0]
        if (this.tmpScores[0] >= this.scores[0]) {
          clearInterval(perfectInterval)
        }
      }.bind(this), 20)
      setTimeout(this.showGreat, this.scores[0]*20 + 200)
    },
    showGreat() {
      var greatInterval = setInterval(function() {
        this.tmpScores[1] += 1
        this.$refs.great.innerText = this.tmpScores[1]
        if (this.tmpScores[1] >= this.scores[1]) {
          clearInterval(greatInterval)
        }
      }.bind(this), 20)
      setTimeout(this.showGood, this.scores[1]*20 + 200)
    },
    showGood() {
      var goodInterval = setInterval(function() {
        this.tmpScores[2] += 1
        this.$refs.good.innerText = this.tmpScores[2]
        if (this.tmpScores[2] >= this.scores[2]) {
          clearInterval(goodInterval)
        }
      }.bind(this), 20)
      setTimeout(this.showBad, this.scores[2]*20 + 200)
    },
    showBad() {
      var badInterval = setInterval(function() {
        this.tmpScores[3] += 1
        this.$refs.bad.innerText = this.tmpScores[3]
        if (this.tmpScores[3] >= this.scores[3]) {
          clearInterval(badInterval)
        }
      }.bind(this), 20)
      setTimeout(this.showMiss, this.scores[3]*20 + 200)
    },
    showMiss() {
      var missInterval = setInterval(function() {
        this.tmpScores[4] += 1
        this.$refs.miss.innerText = this.tmpScores[4]
        if (this.tmpScores[4] >= this.scores[4]) {
          clearInterval(missInterval)
        }
      }.bind(this), 20)
      setTimeout(this.showRank, this.scores[4]*20 + 1000)
    },
    showRank() {
      this.rankVisible = true
      setTimeout(this.showMenu, 1000)
    },
    showMenu() {
      this.menuVisible = true
    },
    plusScore() {
      this.tmpScores[0] += 1
      this.$refs.perfect.innerText = this.tmpScores[0]
    },
    retry() {
      router.push('/ranking/' + localStorage.getItem('songId'))
    },
    goHome() {
      router.push({ name: 'MusicSelect', query: {'mode': 'Ranking'} })
    }
  },
  mounted() {
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
}

#score-box {
  position: absolute;
  left: 8vw;
  top: 8vh;
  width: 84vw;
  height: 70vh;
  display: flex;
  justify-content: space-evenly;
  align-items: center;
  border-radius: 10px;
  background-color: rgba(0, 0, 0, 0.6);
}

.score-half {
  width: 44%;
  height: 85%;
  background-color: rgb(215, 226, 255);
  border-radius: 10px;
  /* filter: blur(1.3px); */
}

#rank-box {
  display: flex;
  justify-content: center;
  align-items: center;
}

#scores-box {
  display: flex;
  justify-content: space-evenly;
  align-items: center;
}

#score-name {
  text-align: left;
}

#score-name div {
  font-size: 40px;
}

#score-number div {
  font-size: 40px;
}

#rank-menu-box {
  position: absolute;
  top: 80vh;
  left: 8vw;
  width: 84vw;
  height: 80px;
  display: flex;
  justify-content: flex-end;
}

.rank-menu {
  background-color: rgba(0, 0, 0, 0.6);
  margin-left: 20px;
  border-radius: 10px;
  color: white;
  text-align: center;
  vertical-align: center;
  width: 150px;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 20px;
}
.rank-menu:hover {
  cursor: pointer;
}

</style>