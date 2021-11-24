<template>
  <div class="container">
    <section
      class="
        section
        register
        min-vh-100
        d-flex
        flex-column
        align-items-center
        justify-content-center
        py-4
      "
    >
      <div class="container">
        <div class="row justify-content-center">
          <div
            class="col-lg-4 col-md-6 d-flex flex-column align-items-center justify-content-center"
          >
            <div class="d-flex justify-content-center py-4">
              <a href="index.html" class="d-flex align-items-center">
                <i class="bi bi-house"></i>
                <span class="d-none d-lg-block">HappyHouse</span>
              </a>
            </div>
            <!-- End Logo -->

            <div class="card mb-3">
              <div class="card-body">
                <div class="pt-4 pb-2">
                  <h5 class="card-title text-center pb-0 fs-4">Login to Your Account</h5>
                  <p class="text-center small">Enter your ID & password to login</p>
                </div>

                <div class="row g-3 needs-validation" novalidate>
                  <div class="col-12">
                    <label for="Id" class="form-label">ID</label>
                    <div class="input-group has-validation">
                      <input type="text" v-model="$store.state.login.userId" class="form-control" />
                      <div class="invalid-feedback">Please enter your Id.</div>
                    </div>
                  </div>

                  <div class="col-12">
                    <label for="yourPassword" class="form-label">Password</label>

                    <input
                      type="password"
                      class="form-control"
                      v-model="$store.state.login.userPassword"
                    />
                    <div class="invalid-feedback">Please enter your password!</div>
                  </div>

                  <!-- <div class="col-12">
                    <div class="form-check">
                      <input
                        class="form-check-input"
                        type="checkbox"
                        name="remember"
                        value="true"
                        id="rememberMe"
                      />
                      <label class="form-check-label" for="rememberMe">Remember me</label>
                    </div>
                  </div> -->
                  <div class="col-12 mt-4">
                    <button @click="login" class="btn btn-success w-100">Login</button>
                  </div>
                  <div class="col-12">
                    <p class="small mb-0">
                      Don't have account?
                      <router-link to="/register">Create an account</router-link>
                    </p>
                  </div>
                </div>
              </div>
            </div>

            <div class="credits">
              <!-- All the links in the footer should remain intact. -->
              <!-- You can delete the links only if you purchased the pro version. -->
              <!-- Licensing information: https://bootstrapmade.com/license/ -->
              <!-- Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/ -->
              Designed by Ssafy
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import Vue from "vue";
import VueAlertify from "vue-alertify";

Vue.use(VueAlertify);
import http from "@/common/axios.js";

export default {
  name: "Login",

  methods: {
    login() {
      http
        .post("/login", {
          userId: this.$store.state.login.userId,
          userPassword: this.$store.state.login.userPassword,
        })
        .then(({ data }) => {
          console.log("LoginVue: data : ");
          console.log(data);

          this.$store.commit("SET_LOGIN", {
            isLogin: true,
            userNo: data.userNo,
            userName: data.userName,
            userProfileImageUrl: data.userProfileImageUrl,
            userId: data.userId,
            userPhone: data.userPhone,
            userEmail: data.userEmail,
            userPosition: data.userPosition,
            userPassword: data.userPassword,
          });

          //== <route-link :to=”path”>

          this.$router.push("/main");
        })
        .catch((error) => {
          console.log("LoginVue: error : ");
          console.log(error);
          if (error.response.status == "404") {
            this.$alertify.error("아이디 또는 비밀번호를 확인하세요.");
          } else {
            this.$alertify.error("Opps!! 서버에 문제가 발생했습니다.");
          }
        });
    },
  },
};
//https://joshua1988.github.io/web-development/vuejs/vuex-getters-mutations/참고 블로그
// getter
// 여러 컴포넌트에서 같은 로직을 비효율적으로 중복 사용/
// Vuex 의 데이터 (state) 변경을 각 컴포넌트에서 수행하는 게 아니라,
// Vuex 에서 수행하도록 하고 각 컴포넌트에서 수행 로직을 호출하면,
// 코드 가독성도 올라가고 성능증가 -> 상속의 느낌

// ex)
// export const store = new Vuex.Store({
//   // ...
//   getters: {
//     getCounter: function (state) {
//       return state.counter;
//     }
//   }
// });
// Child.vue
// computed: {
//   childCounter() {
//     this.$store.getters.getCounter;
//   }
// },

//mutaion과 action : vuex의 state를 바꾸기위함 함수 집함(setter)
// Mutations 는 동기적 로직을 정의 =>Mutations 의 성격상 안에 정의한 로직들이 순차적으로 일어나야 각 컴포넌트의 반영 여부를 제대로 추적할 수가 있기 때문
// Actions 는 비동기적 로직을 정의

// state에서 직접 변결할경우 어느 컴포넌트에서 호출해서 변경되는지 추적이 어려움=> 테스드 , 디버깅에서 손해
// mutation 사용  -> commit
// this.$store.mutations.addCounter;(X)
// this.$store.commit('addCounter');(O)
// this.$store.commit('addCounter', 10);(O)
// this.$store.commit('addCounter', {
//   value: 10,
//   arr: ["a", "b", "c"]
// });(O)

// action 사용법 -> dispatch
// this.$store.dispatch('addCounter');
</script>

<style></style>
