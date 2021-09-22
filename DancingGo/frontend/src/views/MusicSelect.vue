<template>
  <div>
    <v-img id="background" src="images/home/home_background.jpg"></v-img>
    <MusicList />
  </div>
</template>

<script>
import MusicList from '@/components/musicselect/MusicList.vue'
export default {
  name: 'MusicSelect',
  components: {
    MusicList
  }

}
</script>

<style>

</style>