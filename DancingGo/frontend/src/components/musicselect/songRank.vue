<template>
  <v-card class="px-5, py-5" id="rank">
      <div class="d-flex justify-content-center py-3">
          <v-card-title class="text">
              <span clss="text-h4 title">곡별랭킹</span>
          </v-card-title>
      </div>
  </v-card>
</template>

<script>
import { mapGetters } from 'vuex'
// import axios from 'axios';

export default {
    data(){
        return {

        }
    },
    computed:{
        ...mapGetters(['token', 'user']),
    },
    methods:{
        
    }
}
</script>

<style scoped>

#rank {
    background-color: rgba( 0, 0, 0, 0.8 );
    color:white;
    height: 95vh;
}

</style>