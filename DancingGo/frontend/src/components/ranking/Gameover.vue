<template>
  <div>
    <img src="/images/gameover/gameover.jpg" alt="" id="background-gameover">
    <v-dialog style="z-index: 99999"
      v-model="isModalOpen"
      max-width="350px">
      <Modal
        v-if="this.$store.getters.langMode=='한국어'"
        :modalTitle="'알림'"
        :modalContent="'재도전 하시겠습니까?'"
        :buttonO="'예'"
        :buttonX="'아니오'"
        @clickO="retry"
        @clickX="goMusicSelect"
      />
      <Modal
        v-else
        :modalTitle="'Notice'"
        :modalContent="'Would you like to try again?'"
        :buttonO="'Yes'"
        :buttonX="'No'"
        @clickO="retry"
        @clickX="goMusicSelect"
      />
    </v-dialog>
    <audio src="/sounds/select.mp3" ref="selecteffect"></audio>
  </div>
</template>

<script>
import Modal from '@/components/Modal.vue'
import router from '@/router/index.js'
import http from '@/http.js';
import { mapGetters } from 'vuex'

export default {
  data() {
    return {
      isModalOpen: false
    }
  },
  components: {
    Modal
  },
  computed: {
    ...mapGetters(['user']),
  },
  methods: {
    retry() {
      this.$refs.selecteffect.play()
      setTimeout(function() {
        this.$router.go()
      }.bind(this), 500)
    },
    goMusicSelect() {
      this.$refs.selecteffect.play()
      setTimeout(function() {
        router.push({ name: 'MusicSelect', query: {'mode': 'Ranking'} })
      }.bind(this), 500)
    },
    sendResult() {
      http.get('/user/gameover/' + this.user.userNickname)
      .then((res) => {
        console.log(res)
      })
      .catch((err) => {
        console.log(err)
      })
    }
  },
  mounted() {
    this.$refs.selecteffect.volume = this.$store.getters.effectVolume*(0.01)
    this.sendResult()
    setTimeout(function() {
      this.isModalOpen = true
    }.bind(this), 2000)

  }
}
</script>

<style>
#background-gameover {
  position: absolute;
  left: 0px;
  top: 0px;
  width: 100vw;
  height: 100vh;
  z-index: 99998;
}
</style>