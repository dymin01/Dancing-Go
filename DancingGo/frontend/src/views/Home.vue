<template>
  <div>
    <v-img id="background" src="images/home/home_background.jpg"></v-img>
    <ModeSelect />
  </div>
</template>

<script>
import ModeSelect from '@/components/home/ModeSelect.vue'
export default {
  name: 'Home',
  components: {
    ModeSelect
  }
}
</script>

<style scoped>
#background {
  width: 100vw;
  height: 100vh;
}
</style>