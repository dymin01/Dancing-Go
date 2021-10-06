
<template>
  <div id="skeleton-container">
    <div id="head"></div>
    <div id="spine" ref="a"></div>
    <div id="right-waist" ref="b"></div>
    <div id="left-waist" ref="c"></div>
    <div id="shoulder-line" ref="d"></div>
    <div id="heap" ref="e"></div>
    <div id="right-arm-up" ref="f"></div>
    <div id="right-arm-down" ref="g"></div>
    <div id="left-arm-up" ref="h"></div>
    <div id="left-arm-down" ref="i"></div>
    <div id="right-leg-up" ref="j"></div>
    <div id="right-leg-down" ref="k"></div>
    <div id="left-leg-up" ref="l"></div>
    <div id="left-leg-down" ref="m"></div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      colorMatch: {
        0: 'green',
        1: 'yellow',
        2: 'orange',
        3: 'red'
      }
    }
  },
  props: {
    angles: Array
  },
  methods: {
    changeNeck(angle) {
      this.$refs.a.style.background = this.colorMatch[angle]
    },
    changeRightWaist(angle) {
      this.$refs.c.style.background = this.colorMatch[angle]
    },
    changeLeftWaist(angle) {
      this.$refs.b.style.background = this.colorMatch[angle]
    },
    changeShoulderLine(angle) {
      this.$refs.d.style.background = this.colorMatch[angle]
    },
    changeHeapLine(angle) {
      this.$refs.e.style.background = this.colorMatch[angle]
    },
    changeRightArmUp(angle) {
      this.$refs.h.style.background = this.colorMatch[angle]
    },
    changeRightArmDown(angle) {
      this.$refs.i.style.background = this.colorMatch[angle]
    },
    changeLeftArmUp(angle) {
      this.$refs.f.style.background = this.colorMatch[angle]
    },
    changeLeftArmDown(angle) {
      this.$refs.g.style.background = this.colorMatch[angle]
    },
    changeRightLegUp(angle) {
      this.$refs.l.style.background = this.colorMatch[angle]
    },
    changeRightLegDown(angle) {
      this.$refs.m.style.background = this.colorMatch[angle]
    },
    changeLeftLegUp(angle) {
      this.$refs.j.style.background = this.colorMatch[angle]
    },
    changeLeftLegDown(angle) {
      this.$refs.k.style.background = this.colorMatch[angle]
    },
  },
  watch: {
    angles: function() {
      console.log('바뀌었다')
      console.log(this.angles)
      var angleScore = []
      for (let i=0; i < this.angles.length; i++) {
        var angle = this.angles[i]
        if (angle <= 10 || null) {
          angleScore.push(0)
        } else if (angle <= 20) {
          angleScore.push(1)
        } else if (angle <= 30) {
          angleScore.push(2)
        } else {
          angleScore.push(3)
        } 
      }
      this.changeNeck(angleScore[0])
      this.changeRightWaist(angleScore[1])
      this.changeLeftWaist(angleScore[2])
      this.changeShoulderLine(angleScore[3])
      this.changeHeapLine(angleScore[4])
      this.changeRightArmUp(angleScore[5])
      this.changeRightArmDown(angleScore[6])
      this.changeLeftArmUp(angleScore[7])
      this.changeLeftArmDown(angleScore[8])
      this.changeRightLegUp(angleScore[9])
      this.changeRightLegDown(angleScore[10])
      this.changeLeftLegUp(angleScore[11])
      this.changeLeftLegDown(angleScore[12])
    }
  },
}
</script>

<style scoped>
#skeleton-container {
  position: relative;
  width: 270px;
  height: 300px;
  border: 2px black solid;
}

#head {
  position: absolute;
  border-radius: 70%;
  border: 3px black solid;
  left: 120px;
  top: 55px;
  width: 30px;
  height: 40px;
}

#spine {
  position: absolute;
  border: 3px black solid;
  left: 135px;
  top: 70px;
  width: 75px;
  height: 10px;
  transform-origin: left;
  transform: rotate(90deg);
  background: white;
}

#shoulder-line {
  position: absolute;
  border: 3px black solid;
  left: 110px;
  top: 100px;
  width: 50px;
  height: 10px;
  background: white;
}

#left-waist {
  position: absolute;
  border: 3px black solid;
  left: 160px;
  top: 100px;
  width: 75px;
  height: 10px;
  transform-origin: left;
  transform: rotate(90deg);
}

#right-waist {
  position: absolute;
  border: 3px black solid;
  left: 110px;
  top: 100px;
  width: 75px;
  height: 10px;
  transform-origin: left;
  transform: rotate(90deg);
}

#heap {
  position: absolute;
  border: 3px black solid;
  left: 110px;
  top: 175px;
  width: 50px;
  height: 10px;
}

#left-arm-up {
  position: absolute;
  border: 3px black solid;
  left: 160px;
  top: 100px;
  width: 45px;
  height: 10px;
  transform-origin: left;
  transform: rotate(0deg);
}

#left-arm-down {
  position: absolute;
  border: 3px black solid;
  left: 205px;
  top: 100px;
  width: 45px;
  height: 10px;
  transform-origin: left;
  transform: rotate(0deg);
}

#right-arm-up {
  position: absolute;
  border: 3px black solid;
  left: 110px;
  top: 100px;
  width: 45px;
  height: 10px;
  transform-origin: left;
  transform: rotate(180deg);
}

#right-arm-down {
  position: absolute;
  border: 3px black solid;
  left: 65px;
  top: 100px;
  width: 45px;
  height: 10px;
  transform-origin: left;
  transform: rotate(180deg);
}

#left-leg-up {
  position: absolute;
  border: 3px black solid;
  left: 160px;
  top: 175px;
  width: 55px;
  height: 10px;
  transform-origin: left;
  transform: rotate(90deg);
}

#left-leg-down {
  position: absolute;
  border: 3px black solid;
  left: 160px;
  top: 230px;
  width: 50px;
  height: 10px;
  transform-origin: left;
  transform: rotate(90deg);
}

#right-leg-up {
  position: absolute;
  border: 3px black solid;
  left: 110px;
  top: 175px;
  width: 55px;
  height: 10px;
  transform-origin: left;
  transform: rotate(90deg);
}

#right-leg-down {
  position: absolute;
  border: 3px black solid;
  left: 110px;
  top: 230px;
  width: 50px;
  height: 10px;
  transform-origin: left;
  transform: rotate(90deg);
}

#foo {
  position: absolute;
  border-radius: 70%;
  border: 3px black solid;
  left: 125px;
  top: 70px;
  width: 20px;
  height: 30px;
}


</style>