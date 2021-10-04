import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate'
import account from './modules/account'
import music from './modules/music'
import ranking from './modules/ranking'

Vue.use(Vuex)

const debug = process.env.NODE_ENV !== 'production'
// 개발모드면 true 배포모드면 false
// const DEVELOPMODE = true;
const DEVELOPMODE = false;
export default new Vuex.Store({
  state: {
    language: '한국어'
  },
  getters: {
    fileURL: function () {
      return DEVELOPMODE ? 'http://localhost:3000/' : 'https://j5a105.p.ssafy.io/';
    },
    DEVELOPMODE: function() {
      return DEVELOPMODE;
    },
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
