import http from '@/http.js';

export default {
  namespaced: true,
  state: {
    musics: [],
  },
  getters: {
  },
  mutations: {
    SETMUSICS: function (state, data) {
      state.musics = data
    }
  },
  actions: {
    setMusics: function ({ commit }) {
      // console.log(commit)
      // console.log('가져왔다')
      http.get('/song/allSongs')
        .then((res) => {
          // console.log(res.data)
          commit('SETMUSICS', res.data)
        })
        .catch((err) => {
          console.log(err)
        })
    }
  }
}