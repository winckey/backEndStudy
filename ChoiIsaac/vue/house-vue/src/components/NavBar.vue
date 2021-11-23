<template>
  <div>
    <nav class="navbar navbar-default navbar-trans navbar-expand-lg ">
      <div class="container">
        <button class="navbar-toggler collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#navbarDefault"
          aria-controls="navbarDefault" aria-expanded="false" aria-label="Toggle navigation">
          <span></span>
          <span></span>
          <span></span>
        </button>
        <router-link to="/main" class="navbar-brand text-brand">Estate<span class="color-b">Agency</span></router-link>
        <div class="navbar-collapse collapse justify-content-center" id="navbarDefault">
          <ul class="navbar-nav">
            <li class="nav-item">
              <router-link to="/main" class="nav-link">Main</router-link>
            </li>

            <li class="nav-item">
              <router-link to="/notify" class="nav-link">Notify</router-link>
            </li>

            <li class="nav-item">
              <router-link to="/deal" class="nav-link">Deal</router-link>
            </li>
            <a v-show="$store.state.login.isLogin" class="navbar-brand" href="#">
              <li class="nav-item dropdown">

                <img src="" alt="Logo" style="width:24px; height: 24px; border-radius: 50%;">
                {{ $store.state.login.userName }}
                <!-- 사용자 정보에따른 이미지 출력 -->

                <div class="dropdown-menu">
                  <div class="dropdown-item" href="#">내정보</div>
                  <div class="dropdown-item" @click="logout">로그아웃</div>
                </div>
              </li>
            </a>
          </ul>


        </div>


      </div>
    </nav>
    <!-- End Header/Navbar -->
  </div>
</template>



<script>
  import Vue from "vue";
  import VueAlertify from "vue-alertify";

  Vue.use(VueAlertify);
  import http from "@/common/axios.js";

  export default {

    methods: {
      logout() {
        http
          .get("/logout", {})
          .then(() => {
            console.log("로그아웃됬습니다.");
            this.$store.commit("SET_LOGOUT");
            console.log( this.$store.state.login.userName);
            this.$router.push("/");

          })
          .catch((error) => {
            console.log("logout error : ");
            console.log(error);
            this.$alertify.error("Opps!! 서버에 문제가 발생했습니다.");
          });
      },
    },
  }
</script>


<style>

</style>