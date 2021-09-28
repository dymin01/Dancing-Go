<template>
  <div class="text-center">
    <v-btn
      dark
      color="indigo"
      @click="snackbar = true"
    >
    Open Snackbar
    </v-btn>

    <v-snackbar
      v-model="snackbar"
      :vertical="vertical"
      :height="height"
      :width="width"
      :timeout="timeout"
      centered
    >
      <img src="images/badgeImg/1001.png" width="200px">
      {{ text }}
      <template v-slot:action="{ attrs }">
        <v-btn
          color="indigo"
          text
          v-bind="attrs"
          @click="snackbar = false"
        >
        </v-btn>
      </template>
    </v-snackbar>
  </div>
</template>
<script>
export default {
  data () {
    return{
      text: `Hello, I'm a snackbar`,
      snackbar: false,
      vertical: true,
      height: '300px',
      width: '500px',
      timeout : 2000,
    }
  }
}
</script>

<style>

</style>