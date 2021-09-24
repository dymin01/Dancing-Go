<template>
  <div>
    <h1>연습모드</h1>
    <p>{{ songId }}</p>
  </div>
</template>

<script>
export default {
  name: 'Practice',
  data () {
    return {
      songId: this.$route.params.songId
    }
  }

}
</script>

<style>

</style>