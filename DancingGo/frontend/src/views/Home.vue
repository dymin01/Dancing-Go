<template>
  <div>
    <v-img id="background" src="images/home/home2.jpg"></v-img>
    <ModeSelect />

    <v-dialog
    v-model="isNicknameModalOpen"
    max-width="400px"
    >
      <NicknameModal
      @closeModal="closeNicknameModal" />
    </v-dialog>
    <audio src="songs/background.mp3" ref="backgroundSound" autoplay loop></audio>
  </div>
</template>

<script>
import ModeSelect from '@/components/home/ModeSelect.vue'
import NicknameModal from '@/components/home/NicknameModal.vue'
import { mapMutations, mapGetters } from 'vuex'

export default {
  name: 'Home',
  components: {
    ModeSelect,
    NicknameModal
  },
  data () {
    return {
      isNicknameModalOpen: false,
    }
  },
  computed:{
      ...mapGetters(['user', 'backgroundVolume']),
      changeBg () {
        return this.$store.getters.backgroundVolume*(0.01)
      },
    },
  watch:{
    changeBg (val) {
      this.$refs.backgroundSound.volume = val
    },
  },
  methods: {
    ...mapMutations(['setToken', 'setUser']),
    closeNicknameModal () {
      this.isNicknameModalOpen = false
    }
  },
  mounted () {
    const userNickname = this.user.userNickname
    if (!userNickname || userNickname == null) {
      this.isNicknameModalOpen = !this.isNicknameModalOpen
    }
    this.$refs.backgroundSound.volume = this.$store.getters.backgroundVolume*(0.01)
  }
}
</script>

<style scoped>
#background {
  width: 100vw;
  height: 100vh;
}
</style>