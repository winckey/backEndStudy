<template>
  <div id="app">
    <nav-bar></nav-bar>
    <div class="container mt-5">
      <router-view></router-view>
      <footer-a></footer-a>

      <!--router 요청 URI에 따라 브라우져에서 돔을 변경하는 방식이 -->
      <!-- router-view)<router-view>태그는 페이지의 url이 이동했을 때 그 범위내에서 뿌려주는 태그 -->
      <!-- router-link)<router-link> 태그는 라우터에서 페이지 이동을 위한 태그<a>태그와 비슷한 기능을 가짐 -->
    </div>
  </div>
</template>
<script>
import NavBar from "./components/NavBar.vue";
import footerA from "./components/Footer.vue";

export default {
  name: "app",
  components: {
    NavBar,
    footerA,
  },
};
</script>
<style></style>
