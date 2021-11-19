import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);
// 화면의 단위를 잘게 쪼개면 쪼갤수록 한 컴포넌트의 데이터를 다른 컴포넌트의 화면에서 표시할 일이 많아집니다. 
// 만약 컴포넌트의 갯수가 무한정 많아진다면 어떻게 될까요? 
// 최상위 컴포넌트에서 맨 아래의 컴포넌트에 데이터를 전달하기 위해 중간 계층의 컴포넌트에 
// 모두 props, event emit을 선언해줘야 할 겁니다.
// 이제 이 비효율적인 컴포넌트 간 데이터 전달 방식을 Vuex로 해결
export default new Vuex.Store({
  state: {
    // login, NavBar
    login: {
      isLogin: false,
      userId: "test",
      userPassword: "test",
      userName: "",
      userProfileImageUrl: "",
      
    },
    //

  },
  // state 상태를 변경하는 유일한 방법
  mutations: {
    SET_LOGIN(state, payload) {
      state.login.isLogin = payload.isLogin;
      state.login.userName = payload.userName;
      state.login.userProfileImageUrl = payload.userProfileImageUrl;
    },
    SET_LOGOUT(state) {
      state.login.isLogin = false;
      state.login.userName = "";
      state.login.userProfileImageUrl = "";
      state.login.userId = "";
      state.login.userPassword = "";
    },


  },
  actions: {


  },
  getters: {
    isLoginGetters: function (state) {
      return state.login.isLogin;
    },
  },
 
});
