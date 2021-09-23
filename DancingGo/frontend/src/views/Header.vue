<template>
  <div id="header" class="d-flex justify-end m-3">
    <i id="icon" class="mx-2 far fa-user-circle"></i>
    <i id="icon" class="mx-2 fas fa-cog"></i>
    <i id="icon" class="mx-2 fas fa-sign-out-alt"></i>
  </div>
</template>

<script>
export default {
  name: 'Header'
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
  font-size: 25pt;
  text-shadow: 0 0 10px purple;
}
</style>