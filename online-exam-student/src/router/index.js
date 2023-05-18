import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '@/components/login/Login.vue'
Vue.use(VueRouter)

import Cookies from 'js-cookie'

const routes = [
  {
    path: '/login',
    name: 'Login',
    component: Login,
  },
  {
    path: '/',
    name: 'Home',
    component: () => import("@/views/homepage/Homepage.vue"),
    redirect: '/home',
    children: [
      {
        path: '/home',
        name: 'Home page',
        component: () => import("@/views/homepage/Home.vue"),
      },
      {
        path: '/paper',
        name: 'Paper',
        component: () => import("@/views/homepage/paper.vue"),
      },
      {
        path: '/record',
        name: 'Record',
        component: () => import("@/views/homepage/record.vue"),
      },
      {
        path: '/error',
        name: 'Error',
        component: () => import("@/views/homepage/error.vue"),
      },
    ]
  }
]

const router = new VueRouter({
  routes
})

router.beforeEach((to, from, next) => {
  if (to.path === '/login' || to.path === '/register') {
    next()
  } else {
    let token = Cookies.get('token')
    if (token === null || token === "" || token === undefined) {
      next('/login')
    } else {
      next()
    }
  }
})

export default router
