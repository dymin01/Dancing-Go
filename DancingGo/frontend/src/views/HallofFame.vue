<template>
  <div>
    <v-img id="background" src="images/HallofFame/stage3.jpg"></v-img>
    <v-img id="Firecracker" src="images/HallofFame/Firecracker.png"></v-img>
    <v-img id="rank" src="images/HallofFame/rank.png"></v-img>
  </div>
</template>

<script>
export default {

}
</script>

<style>

html {
  overflow: hidden;
  scrollbar-width: none;
  -ms-overflow-style: none;
}

html::-webkit-scrollbar {
  display: none;
}

#background {
  position: absolute;
  width: 100vw;
  height: 100vh;
}

#Firecracker {
  position: absolute;
  width: 100vw;
  height: 100vh;
}

#rank {
  position: absolute;
  width: 50%;
  left: 25vw;
  bottom: 35vh;
}

</style>