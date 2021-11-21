import Vue from "vue";
import App from "./App.vue";
import router from "./router/routers.js";
import store from "@/store/store.js"


Vue.config.productionTip = false;

// import store from "./store/store.js";
new Vue({
  // vue-router , vuex 사용 설정하면서 주입된 객체 
  router,
  store,
  
  // App.vue component  연결 
  // render : template로 단순히 component로 연결이 가능하지만 render는 컴파일 기능이 있음
  // 
  // render(h) {
  //  return h(this.ViewComponent)
  // },

  // h == hyperscript
  // virtual Dom 생성
  render: (h) => h(App),

}).$mount("#app");
// index.html el 인스턴스를 붙여줌 <- 다른파일로 분리되어있으니까~
// url에 #이 붙게 되는데 이것을 피하고 싶다면 VueRouter에 mode:history를 적용시키면 된다.
// js에서 import시킨 vue파일의 내부의 함수를 사용 할때 $를 붙임 
// $mount() : el 속성과 같이 인스턴스를 화면에 붙이는 역할을 한다.
//  생성하고 나서 $mount()를 붙이면 강제로 인스턴스를 화면에 붙일 수 있다. 
// 뷰 라우터는 el을 사용하지 않고 $mount()를 사용하여 인스턴스를 붙이는 것을 지향