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
      http.get('/song/allSongs')
        .then((res) => {
          commit('SETMUSICS', res.data)
        })
        .catch((err) => {
          console.log(err)
        })
    }
  }
}