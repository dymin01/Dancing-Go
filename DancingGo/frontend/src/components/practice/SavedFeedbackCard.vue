<template>
  <div id="feedback-box">
    <img :src="feedback[2]" alt="" id="feedback-preview-box" @click="clickFeedback">
    <div id="feedback-card-info">
      <div class="d-flex" style="width: 80px;">

      </div>
      <div style="font-weight: bold; color: #eee; text-shadow: 0 0 10px purple;">{{ feedback[1] }}</div>
      <div class="d-flex justify-content-end" style="width: 80px;">
        <i class="fas fa-trash-alt ms-5" style="font-size: 18px" @click="$emit('deleteSavedFeedback')"></i>
      </div>
    </div>
  </div>
</template>

<script>
export default {

  data() {
    return {
      notShow: false
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
  object-fit: cover;
}

#feedback-preview-box:hover {
  cursor: pointer;
}

#feedback-card-info {
  display: flex;
  width: 300px;
  justify-content: space-between;
}

i {
  color: whitesmoke;
  text-shadow: 0 0 10px purple;
}
</style>