import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate'
import account from './modules/account'
import music from './modules/music'
import ranking from './modules/ranking'

Vue.use(Vuex)

const debug = process.env.NODE_ENV !== 'production'

export default new Vuex.Store({
  state: {
    language: '한국어'
  },
  getters: {
    langMode: (state) => {
      return state.language
    }
  },
  mutations: {
    CHANGELANG: function (state, data) {
      state.language = data
    }
  },
  actions: {
    changeLang: function ({ commit }, data) {
      commit('CHANGELANG', data)
    }
  },
  plugins: [ createPersistedState({ storage: window.sessionStorage }) ],
  modules: { account, music, ranking },
  strict: debug
})
