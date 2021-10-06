<template>
  <div>
    <v-img id="background" src="images/home/home2.jpg"></v-img>
    <!-- <button @click="logout">로그아웃</button> -->
    <ModeSelect />

    <!-- <NicknameModal /> -->

    <v-dialog
    v-model="isNicknameModalOpen"
    max-width="400px"
    >
      <NicknameModal
      @closeModal="closeNicknameModal" />
      <!-- <Mypage @closeMypage="closeMypage" class="mypageModal"/> -->
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
      // backgroundVolume: localStorage.getItem('backgroundVolume'),
      // effectVolume: localStorage.getItem('effectVolume')
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
      console.log(val)
      this.$refs.backgroundSound.volume = val
      console.log(this.$refs.backgroundSound.volume)
    },
  },
  methods: {
    ...mapMutations(['setToken', 'setUser']),
    // logout () {
    //   this.setToken(null)
    //   this.setUser(null)
    //   alert('로그아웃되었습니다.')
    //   if (this.$route.path !== '/') this.$router.push('/')
    // },
    closeNicknameModal () {
      this.isNicknameModalOpen = false
    }
  },
  mounted () {
    // user 닉네임이 없으면 모달 오픈
    console.log("테스트용")
    console.log(this.user)
    const userNickname = this.user.userNickname
    // this.$store.state.account.user.userNickname
    console.log(userNickname)
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