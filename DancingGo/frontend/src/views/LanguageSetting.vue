<template>
  <div id="header" class="d-flex justify-end m-3 pe-4" style="z-index:100;">
    <!-- <i id="icon" class="fas fa-chevron-left"  @click="goBack"></i> -->
    <i id="icon" class="fas fa-language"></i>
  </div>
</template>

<script>
export default {
  name: 'LanguageSetting'
}
</script>

<style>
#header {
  position: absolute;
  width: 100vw;
  max-width: 98%;
}
#icon {
  z-index: 100;
  color: white;
  font-size: 40pt;
  text-shadow: 0 0 10px purple;
}
#icon:hover {
  cursor: pointer;
}
</style>