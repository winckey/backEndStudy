import Vue from "vue";
import VueRouter from "vue-router";


Vue.use(VueRouter);

import Login from '@/components/Login.vue';
import Main from '@/components/Main.vue';
import Deal from '@/components/Deal.vue';
import Register from '@/components/Register.vue';


const routes = [

  {
    path: '/',
    component: Login
  },
  {
    name: 'Login',
    path: '/login',
    component: Login
  },
 
  {
    name: 'Main',
    path: '/main',
    component: Main
  },
  {
    name: 'Deal',
    path: '/deal',
    component: Deal
  },
  {
    name : Register,
    path: '/register',
    component: Register
  },

];

const router = new VueRouter({
 // mode: 'history',
  routes,
});

export default router;
