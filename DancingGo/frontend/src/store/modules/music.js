import axios from 'axios'
const SERVER_URL = 'http://localhost:8080'

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
      axios.get(SERVER_URL+'/song/allSongs')
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