<template>
  <v-app>
    <Header />
    <router-view/>
  </v-app>
</template>

<script>
import Header from '@/views/Header.vue'
export default {
  name: 'App',
  components: {
    Header
  },
  data: () => ({
    //
  }),
};
</script>
