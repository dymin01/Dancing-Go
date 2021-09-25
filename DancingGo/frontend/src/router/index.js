import Vue from 'vue'
import VueRouter from 'vue-router'
import Main from '../views/Main.vue'
import Home from '../views/Home.vue'
import MusicSelect from '../views/MusicSelect.vue'
import Practice from '../views/Practice.vue'
import Ranking from '../views/Ranking.vue'
import HallofFame from '../views/HallofFame.vue'
import OauthRedirect from '@/components/oauth/Redirect'
import BadgeToast from '@/components/badge/BadgeToast.vue'
import BadgeGet from '@/components/badge/BadgeGet.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Main',
    component: Main
  },
  {
    path: '/home',
    name: 'Home',
    component: Home
  },
  {
    path: '/musicselect',
    name: 'MusicSelect',
    component: MusicSelect
  },
  {
    path: '/practice/:songId',
    name: 'Practice',
    component: Practice
  },
  {
    path: '/ranking',
    name: 'Ranking',
    component: Ranking
  },
  {
    path: '/halloffame',
    name: 'HallofFame',
    component: HallofFame
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
    path: '/oauth/redirect',
    name: 'OauthRedrect',
    component: OauthRedirect
  },
  {
    path: '/badgetoast',
    name: 'BadgeToast',
    component: BadgeToast
  },
  {
    path: '/badgeget',
    name: 'BadgeGet',
    component: BadgeGet
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
