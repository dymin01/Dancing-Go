<template>
  <v-card class="text-center" id="nickname-modal">
    <v-card-title class="text-h5 justify-center">
      {{ modalTitle }}
    </v-card-title>
    <hr style="margin: 0px; background: white;">
    <v-card-text class="d-flex justify-center pb-0 mt-5">
      <h6 style="color: white;">
        {{ modalContent }}
      </h6>
    </v-card-text>
    <v-card-actions class="d-flex justify-center">
      <v-btn
        id="button"
        style="opacity: 80%;"
        @click="$emit('clickO')"
      >
        {{ buttonO }}
      </v-btn>
      <v-btn
        id="button"
        style="background-color: #7D7D7D; opacity: 80%;"
        @click="$emit('clickX')"
      >
        {{ buttonX }}
      </v-btn>
      </v-card-actions>
  </v-card>
</template>

<script>
export default {
  name: 'Modal',
  props: ['modalTitle',
          'modalContent',
          'buttonO',
          'buttonX'],
  data () {
    return {
    }
  }
}
</script>

<style>
#nickname-modal {
  background-color: rgba(43, 29, 59, 0.8);
  color: white;
  /* text-shadow: 0 0 5px gray; */
}
#button {
  color: white;
  background-color: purple;
  box-shadow: 0 0 10px white;
}
</style>