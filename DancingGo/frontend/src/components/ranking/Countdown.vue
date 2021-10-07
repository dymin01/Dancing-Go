<template>
  <div id="countdown-whole">
    <audio src="/effect/beep.mp3" ref="beep"></audio>
    <div ref="count" style="font-size: 200px; font-weight: bold; color: white;">3</div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      countdownString: ['3', '2', '1', 'Go!'],
      count: 0
    }
  },
  methods: {
    show() {
      this.$refs.beep.play()
      var count = 1
      var interval = setTimeout(function countdown() {
        this.$refs.count.innerText = this.countdownString[count]
        // this.$refs.count.className = 'd-none'
        if (count == 4) {
          clearTimeout(interval)
          this.$emit('countdownEnd')
        } else {
          // this.$refs.count.className = 'fade-in-box'
          count += 1
          if (count == 4) {
            this.$refs.beep.src = '/effect/beep2.mp3'
            this.$refs.beep.playbackRate = 3
          }
          this.$refs.beep.play()
          setTimeout(countdown.bind(this), 1000);
        }
      }.bind(this), 1000)
    },
  },
  mounted() {
    this.$refs.beep.playbackRate = 2
    this.show()
  }
}
</script>

<style>
#countdown-whole {
  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;
  width: 100vw;
  height: 100vh;
  left: 0px;
  top: 0px;
  background: rgba(0, 0, 0, 0.7)
}


.fade-in-box {
  /* animation: fadein 1s; */
  animation: fadeout 1.1s;
}

@keyframes fadeout {
    from {
        opacity: 1;
    }
    to {
        opacity: 0;
    }
}

@keyframes fadein {
    from {
        opacity: 0;
    }
    to {
        opacity: 1;
    }
}



</style>