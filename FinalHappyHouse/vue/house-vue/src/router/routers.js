import Vue from "vue";
import VueRouter from "vue-router";


Vue.use(VueRouter);

import Login from '@/components/Login.vue';
import Main from '@/components/Main.vue';
import Deal from '@/components/Deal.vue';
import Register from '@/components/Register.vue';
import Mypage from '@/components/Mypage.vue';
import Notify from '@/components/Notify.vue';
import Agent from '@/components/Agent.vue';
import AgentDetail from '@/components/AgentDetail.vue';
import store from '@/store/store.js'

const routes = [
  {

    path: '/',
    component: Login,
  },
  {
    name: 'Main',
    path: '/main',
    component: Main,
    beforeEnter: (to, from, next) => {
      if(! store.state.login.isLogin ){
        next("/login");
      }else{
        return next();
      }
    }
  },
  {
    name: 'Deal',
    path: '/deal',
    component: Deal,
    beforeEnter: (to, from, next) => {
      if(! store.state.login.isLogin ){
        next("/login");
      }else{
        return next();
      }
    }
    
  },
  
  {
    name: 'Login',
    path: '/login',
    component: Login
  },
  
  {
    name: 'Register',
    path: '/register',
    component: Register
  },

  {
    name: 'Notify',
    path: '/notify',
    component: Notify,
    beforeEnter: (to, from, next) => {
      if(! store.state.login.isLogin ){
        next("/login");
      }else{
        return next();
      }
    }
  },
  
  {
    name: 'Mypage',
    path: '/mypage',
    component: Mypage
  },
  {
    name: 'Agent',
    path: '/agent',
    component: Agent
  },
  {
    name: 'AgentDetail',
    path: '/agentDetail',
    component: AgentDetail
  }

];
// 여러 컴포넌트 붙일땐  components 
const router = new VueRouter({
  // mode: history,
  routes,
});

export default router;
