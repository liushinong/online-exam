import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/* Router Modules */

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    noCache: true                if set true, the page will no be cached(default is false)
    affix: true                  if set true, the tag will affix in the tags-view
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/redirect',
    component: Layout,
    hidden: true,
    children: [
      {
        path: '/redirect/:path(.*)',
        component: () => import('@/views/redirect/index')
      }
    ]
  },
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },
  {
    path: '/auth-redirect',
    component: () => import('@/views/login/auth-redirect'),
    hidden: true
  },
  {
    path: '/404',
    component: () => import('@/views/error-page/404'),
    hidden: true
  },
  {
    path: '/401',
    component: () => import('@/views/error-page/401'),
    hidden: true
  },
  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [
      {
        path: 'dashboard',
        component: () => import('@/views/dashboard/index'),
        name: 'Dashboard',
        meta: { title: 'dashboard', icon: 'dashboard', affix: true }
      }
    ]
  },
  {
    path: '/profile',
    component: Layout,
    redirect: '/profile/index',
    hidden: true,
    children: [
      {
        path: 'index',
        component: () => import('@/views/profile/index'),
        name: 'Profile',
        meta: { title: 'profile', icon: 'user', noCache: true }
      }
    ]
  },
  {
    path: '/novel',
    component: Layout,
    children: [
      {
        path: 'index',
        component: () => import('@/views/novel/index'),
        name: 'Novel',
        meta: {
          title: '学生管理', icon: 'user', noCache: true
        }
      }
    ]
  },
  {
    path: '/subject',
    component: Layout,
    children: [
      {
        path: 'index',
        component: () => import('@/views/subject/index'),
        name: 'Subject',
        meta: {
          title: '学科管理', icon: 'el-icon-s-order', noCache: true
        }
      }
    ]
  },
  {
    path: '/paper',
    component: Layout,
    name: 'Paper',
    meta: {
      title: '试卷管理',
      icon: 'el-icon-s-order'
    },
    children: [
      {
        path: '/paper/list',
        component: () => import('@/views/paper/list'),
        name: 'PaperList',
        meta: {
          title: '试卷列表',
          noCache: true
        }
      },
      {
        path: '/paper/edit',
        component: () => import('@/views/paper/edit'),
        name: 'EditPaper',
        meta: {
          title: '试卷创编',
          noCache: true
        }
      }
    ]
  },
  {
    path: '/question',
    component: Layout,
    name: 'Question',
    meta: {
      title: '题库管理',
      icon: 'el-icon-s-order'
    },
    children: [
      {
        path: '/question/list',
        component: () => import('@/views/question/list'),
        name: 'QuestionList',
        meta: {
          title: '题目列表',
          noCache: true
        }
      },
      {
        path: '/edit/singleChoice',
        component: () => import('@/views/question/edit/single-choice'),
        name: 'SingleChoicePage',
        meta: {
          title: '单选题编辑',
          noCache: true,
          activeMenu: '/question/list',
          hidden: true
        }
      },
      {
        path: '/edit/multipleChoice',
        component: () => import('@/views/question/edit/multiple-choice'),
        name: 'MultipleChoicePage',
        meta: {
          title: '多选题编辑',
          noCache: true,
          activeMenu: '/question/list',
          hidden: true
        }
      },
      {
        path: '/edit/trueFalse',
        component: () => import('@/views/question/edit/true-false'),
        name: 'TrueFalse',
        meta: {
          title: '判断题编辑',
          noCache: true,
          activeMenu: '/question/list',
          hidden: true
        }
      },
      {
        path: '/edit/gapFilling',
        component: () => import('@/views/question/edit/gap-filling'),
        name: 'GapFilling',
        meta: {
          title: '填空题编辑',
          noCache: true,
          activeMenu: '/question/list',
          hidden: true
        }
      },
      {
        path: '/edit/shortAnswer',
        component: () => import('@/views/question/edit/short-answer'),
        name: 'ShortAnswer',
        meta: {
          title: '简答题编辑',
          noCache: true,
          activeMenu: '/question/list',
          hidden: true
        }
      }
    ]
  },
  {
    path: '/answer',
    component: Layout,
    name: 'Answer',
    meta: {
      title: '答卷管理',
      icon: 'el-icon-s-order'
    },
    children: [
      {
        path: '/checkList',
        component: () => import('@/views/paper-answer/checkList'),
        name: 'CheckList',
        meta: {
          title: '批改列表',
          noCache: true
        }
      },
      {
        path: '/completeList',
        component: () => import('@/views/paper-answer/completeList'),
        name: 'CompleteList',
        meta: {
          title: '试卷完成',
          noCache: true
        }
      },
      {
        path: '/editPaper',
        component: () => import('@/views/paper-answer/edit'),
        name: 'EditPaper',
        hidden: true,
        meta: {
          title: '批改试卷',
          noCache: true
        }
      }
    ]
  },
  {
    path: '/error',
    component: Layout,
    redirect: 'noRedirect',
    name: 'ErrorPages',
    hidden: true,
    meta: {
      title: 'errorPages',
      icon: '404'
    },
    children: [
      {
        path: '401',
        component: () => import('@/views/error-page/401'),
        name: 'Page401',
        meta: { title: 'page401', noCache: true }
      },
      {
        path: '404',
        component: () => import('@/views/error-page/404'),
        name: 'Page404',
        meta: { title: 'page404', noCache: true }
      }
    ]
  },

  {
    path: '/error-log',
    component: Layout,
    hidden: true,
    children: [
      {
        path: 'log',
        component: () => import('@/views/error-log/index'),
        name: 'ErrorLog',
        meta: { title: 'errorLog', icon: 'bug' }
      }
    ]
  },
  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

/**
 * asyncRoutes
 * the routes that need to be dynamically loaded based on user roles
 */
export const asyncRoutes = [

]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
