<template>
  <div>
    <v-img id="background" src="images/home/home_background.jpg"></v-img>
    <button @click="logout">로그아웃</button>
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

  </div>
</template>

<script>
import ModeSelect from '@/components/home/ModeSelect.vue'
import NicknameModal from '@/components/home/NicknameModal.vue'
import { mapMutations } from 'vuex'

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
  methods: {
    ...mapMutations(['setToken', 'setUser']),
    logout () {
      this.setToken(null)
      this.setUser(null)
      alert('로그아웃되었습니다.')
      if (this.$route.path !== '/') this.$router.push('/')
    },
    closeNicknameModal () {
      this.isNicknameModalOpen = false
    }
  },
  created () {
    // user 닉네임이 없으면 모달 오픈
    const userNickname = this.$store.state.account.user.userNickname
    if (!userNickname) {
      this.isNicknameModalOpen = !this.isNicknameModalOpen
    }
  }
}
</script>

<style scoped>
#background {
  width: 100vw;
  height: 100vh;
}
</style>