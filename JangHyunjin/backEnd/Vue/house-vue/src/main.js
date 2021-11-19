import Vue from "vue";
import App from "./App.vue";
import router from "./router/routers.js";
import store from "@/store/store.js";

Vue.config.productionTip = false;
// url에 #이 붙게 되는데 이것을 피하고 싶다면 VueRouter에 mode:history를 적용시키면 된다.
new Vue({
  router,
  store,
  // App.vue component  연결 
  // render : template로 단순히 component로 연결이 가능하지만 render는 컴파일 기능이 있음
  // ex) template(app.vue)의 기능들을 사용할수 있는상태로 연결해줌

  

  // h == hyperscript
  render: (h) => h(App),
  // // #1
  // render: function (createElement =dom의 집합) {
  //   return createElement(App);
  // }
  // // #2
  // render(createElement) {
  //   return createElement(App);
  // }
  // // #3
  // render(h) {
  //   return h(App);
  // }
  // // #4
  // // 참고 : h는 hyperscript의 약자로 HTML 구조를 생성하는 스크립트를 의미. Virtual DOM 구현에서 관행적으로 사용
  // render: h => h(App);
}).$mount("#app");
// $mount() : el 속성과 같이 인스턴스를 화면에 붙이는 역할을 한다.
//  생성하고 나서 $mount()를 붙이면 강제로 인스턴스를 화면에 붙일 수 있다. 
// 뷰 라우터는 el을 사용하지 않고 $mount()를 사용하여 인스턴스를 붙이는 것을 지향
//$ 는 자바스크립트에서 변수명에 쓸 수 있는 문자입니다. $mount 는 Vue 내부적으로 정의된 함수이름입니다.
