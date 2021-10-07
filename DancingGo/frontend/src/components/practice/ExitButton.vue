<template>
  <div>
    <button type="button" class="btn" id="exit-button" data-bs-toggle="modal" data-bs-target="#exampleModal">
      종료
    </button>

  <!-- Modal -->
    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">종료하시겠습니까?</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            진행상황은 저장되지 않습니다.
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" ref="modalClose">닫기</button>
            <button type="button" class="btn btn-danger" @click="exitDance">종료</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import router from '@/router/index.js'

export default {
  data() {
    return {

    }
  },
  methods: {
    exitDance() {
      this.$refs.modalClose.click()
      router.push({ name: 'MusicSelect', query: {'mode': localStorage.getItem('mode')} })
    }
  }
}
</script>

<style>
#exit-button {
  color: white;
  background-color: purple;
  box-shadow: 0 0 10px white;
}
#exit-button:hover {
  color: white;
}
</style>