<template>
  <div>
    <v-img id="background" src="images/home/dance3.jpg"></v-img>
    <MusicList
      :mode="mode"
    />
  </div>
</template>

<script>
import MusicList from '@/components/musicselect/MusicList.vue'
export default {
  name: 'MusicSelect',
  data () {
    return {
      mode: this.$route.query.mode
    }
  },
  components: {
    MusicList
  }

}
</script>

<style scoped>
#background {
  width: 100vw;
  height: 100vh;
}
</style>