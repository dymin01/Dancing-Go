<template>
  <div id="c">
    <button @click="fadeIn">toast</button>
    <div id="toast" class="d-none" ref="toast">toast</div>
  </div>
</template>

<script>
export default {
  data() {
    return {

    }
  },
  methods: {
    fadeIn() {
      this.$refs.toast.className = 'fade-in-box'
      setTimeout(this.fadeOut, 5000)
    },
    fadeOut() {
      this.$refs.toast.className = 'fade-out-box'
      setTimeout(this.dNone, 1000)
    },
    dNone() {
      this.$refs.toast.className = 'd-none'
    }
  }
}
</script>

<style>
#c {
  display: flex;
  justify-content: flex-start;
  position: relative;
  text-align: center;
}

#toast {
  position: absolute;
  top: 5vh;
  left: 40vw;
  width: 20vw;
  height: 10vh;
  border: black solid 1px;
  border-radius: 5px;
  background: rgba(255, 255, 255, 1)
}

.fade-in-box {
  animation: fadein 1s;
}

.fade-out-box {
  animation: fadeout 1s;
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