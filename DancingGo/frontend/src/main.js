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

Vue.use(Carousel3d)

Vue.config.productionTip = false

const BACKEND_PORT = process.env.BACKEND_PORT === null ? '' : `:${process.env.BACKEND_PORT}`
const BACKEND_DOMAIN = process.env.BACKEND_DOMAIN === null ? `${location.protocol}//${location.hostname}` : process.env.BACKEND_DOMAIN
axios.defaults.baseURL = `${BACKEND_DOMAIN}${BACKEND_PORT}`

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
