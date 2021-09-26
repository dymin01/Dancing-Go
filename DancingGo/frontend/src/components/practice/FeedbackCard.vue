<template>
  <div @click="clickFeedback" id="feedback-box">
    <div id="feedback-preview-box">
      <img :src="feedback[2]" alt="">
    </div>
    {{ feedback[1] }}
  </div>
</template>

<script>
export default {

  data() {
    return {

    }
  },
  props: {
    feedback: Array,
  },
  methods: {
    clickFeedback() {
      this.$emit('showFeedback')
    },
  }
}
</script>

<style>
#feedback-box {
  position: relative;
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 350px;
  border-bottom: 1px rgb(204, 204, 204) solid;
  padding-bottom: 10px;
}

#feedback-preview-box {
  position: relative;
  width: 300px;
  height: 200px;
  border: 1px solid black;
  margin-top: 30px;
  margin-bottom: 10px;
  overflow: hidden;
}


</style>