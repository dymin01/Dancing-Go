export default {
  namespaced: true,
  state: {
    scores: Array
  },
  mutations: {
    SET_SCORES(state, scores) {
      state.scores = scores
    }
  },
  actions: {
    setScores({ commit }, data) {
      commit('SET_SCORES', data)
    }
  },
}
