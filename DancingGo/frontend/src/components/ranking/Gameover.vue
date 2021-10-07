<template>
  <div>
    <Snackbar
    :totalScore="0"
    :gameover="1"
    @snackbarfinished="openModal"
    style="z-index: 99999"
    />
    <v-dialog style="z-index: 99999"
      persistent
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
    <img style="z-index: 10" src="/images/gameover/gameover.jpg" alt="" id="background-gameover">
    <audio src="/sounds/select.mp3" ref="selecteffect"></audio>

  </div>
</template>

<script>
import Modal from '@/components/Modal.vue'
import router from '@/router/index.js'
import Snackbar from '@/components/badge/snackbar.vue'
import http from '@/http.js'
import { mapGetters } from 'vuex'

export default {
  data() {
    return {
      isModalOpen: false
    }
  },
  components: {
    Modal,
    Snackbar
  },
  computed: {
    ...mapGetters(['user']),
  },
  methods: {
    openModal () {
      // console.log('openmodal')
      setTimeout(function() {
        this.isModalOpen = true
      }.bind(this), 2000)
    },
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
    // this.sendResult()
    http.get('/user/gameover/' + this.user.userNickname)
    // setTimeout(function() {
    //   if (!this.isModalOpen) {
    //     this.isModalOpen = true
    //   }
    // }.bind(this), 2000)

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