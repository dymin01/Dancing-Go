<template>
  <v-card class="text-center" id="settings">
    <v-card-title class="text-h5 justify-center">
      환경설정
    </v-card-title>
    <hr style="margin: 0px; background: white;">
    <v-card-text class="d-flex row">
      <div class="cols-6">
        <h6 style="color: white;" class="mt-4 mb-5">배경음 볼륨 조절</h6>
        <v-slider
          v-model="backgroundSound"
          step="10"
          ticks
        ></v-slider>
      </div>
      <div class="cols-6">
        <h6 style="color: white;" class="mt-4 mb-5">배경음 볼륨 조절</h6>
        <v-slider
          v-model="backgroundSound"
          step="10"
          ticks
        ></v-slider>
      </div>
    </v-card-text>
    <v-btn
      id="button"
      @click="checkNickname"
    >
      확인
    </v-btn>
  </v-card>
</template>

<script>
export default {
  name: 'Settings'
}
</script>

<style>
#settings {
  background-color: rgba(43, 29, 59, 0.8);
  color: white;
  /* text-shadow: 0 0 5px gray; */
}
#button {
  color: white;
  background-color: purple;
  box-shadow: 0 0 15px white;
}
</style>