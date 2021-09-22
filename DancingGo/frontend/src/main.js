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
import quickMenu from 'vue-quick-menu'

Vue.use(Carousel3d)

Vue.config.productionTip = false

new Vue({
  router,
  store,
  vuetify,
  quickMenu,
  render: h => h(App)
}).$mount('#app')
