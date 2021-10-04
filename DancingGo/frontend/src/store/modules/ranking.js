export default {
  namespaced: true,
  state: {
    scores: Array
  },
  mutations: {
    SET_SCORES(state, scores) {
      console.log('저장햇다')
      state.scores = scores
      console.log(state.scores)
    }
  },
  actions: {
    setScores({ commit }, data) {
      console.log('왓다')
      commit('SET_SCORES', data)
    }
  },
}
