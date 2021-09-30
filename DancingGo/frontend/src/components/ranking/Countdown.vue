<template>
  <div id="countdown-whole">
    <div ref="count" class="d-none" style="font-size: 200px; font-weight: bold">3</div>
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
      var interval = setInterval(function() {
        this.$refs.count.innerText = this.countdownString[this.count]
        this.$refs.count.className = 'fade-in-box'
        setTimeout(function() {
          this.$refs.count.className = 'fade-out-box'
          this.count += 1
        }.bind(this), 2500)
        if (this.count == 4) {
          clearInterval(interval)
          this.$emit('countdownEnd')
        }
      }.bind(this))
    },
  },
  mounted() {
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
  animation: fadein 2s;
}

.fade-out-box {
  animation: fadeout 2s;
}

@keyframes fadein {
    from {
        opacity: 0;
    }
    to {
        opacity: 1;
    }
}

@keyframes fadeout {
    from {
        opacity: 1;
    }
    to {
        opacity: 0;
    }
}


</style>