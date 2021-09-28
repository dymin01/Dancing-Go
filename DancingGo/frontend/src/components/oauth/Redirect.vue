<template>
  <div>
  </div>
</template>

<script>
import { mapMutations, mapActions } from 'vuex'

export default {
  created () {
    const token = this.$route.query.token
    console.log('token', token)
    if (token) {
      this.setToken(token)
      this.fetchUser()
      setTimeout(function(){
        this.$router.replace('/home')
      }.bind(this) , 100)
      
    }else{
      this.$router.replace('/')
    }
  },
  methods: {
    ...mapActions(['fetchUser']),
    ...mapMutations(['setToken'])
  }
}
</script>
