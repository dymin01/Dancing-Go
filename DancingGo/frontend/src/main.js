import '@babel/polyfill'
import 'mutationobserver-shim'
import Vue from 'vue'
import './plugins/bootstrap-vue'
import './plugins/axios'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify'
import Carousel3d from 'vue-carousel-3d'
import axios from 'axios'
import * as TastyBurgerButton from 'vue-tasty-burgers'


import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.use(Carousel3d)
Vue.use(TastyBurgerButton)

Vue.config.productionTip = false

// const BACKEND_PORT = ":8080"
// const BACKEND_URL = `${location.protocol}//${location.hostname}${BACKEND_PORT}`
// axios.defaults.baseURL = `${BACKEND_URL}`

//로컬
// axios.defaults.baseURL = 'http://localhost:8080'

//서버
axios.defaults.baseURL = 'https://j5a105.p.ssafy.io:8080'

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
