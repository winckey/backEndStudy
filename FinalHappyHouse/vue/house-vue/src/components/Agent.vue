<template>
  <div>
    <main id="main">
      <!-- =======Intro Single ======= -->
      <section class="intro-single">
        <div class="container">
          <div class="row">
            <div class="col-md-12 col-lg-8">
              <div class="title-single-box">
                <h1 class="title-single">Our Amazing Agents</h1>
                <span class="color-text-a">Grid Properties</span>
              </div>
            </div>
            <div class="col-md-12 col-lg-4">
              <nav aria-label="breadcrumb" class="breadcrumb-box d-flex justify-content-lg-end">
                <ol class="breadcrumb">
                  <li class="breadcrumb-item">
                    <a href="#">Home</a>
                  </li>
                  <li class="breadcrumb-item active" aria-current="page">Agents Grid</li>
                </ol>
              </nav>
            </div>
          </div>
        </div>
      </section>
      <!-- End Intro Single-->

      <!-- ======= Agents Grid ======= -->
      <section class="agents-grid grid">
        <div class="container">
          <div class="row">
            <!-- favorite 토글 스위치  -->
            <div class="col-10"></div>
            <div class="col-2 form-check form-switch">
              <input
                @click="changeToggleData()"
                class="form-check-input"
                type="checkbox"
                id="toggle"
              />
              <label class="form-check-label" for="toggle">Favorite</label>
            </div>
            <!-- agent -->
            <div
              class="col-md-4"
              v-for="(agent, index) in this.$store.state.agent.list"
              v-bind:key="index"
              v-show="checkFavorite(agent, toggle)"
            >
              <div class="card-box-d">
                <div class="card-img-d">
                  <img v-bind:src="agent.agentProfileImageUrl" alt="" class="img-d img-fluid" />
                </div>
                <div class="card-overlay card-overlay-hover">
                  <button
                    @click="changeFavorite(agent)"
                    type="button"
                    class="btn btn-sm btn-warning mt-2"
                  >
                    <div v-if="agent.userFavoriteCheck != 0">
                      <i class="bi bi-star-fill"></i>
                    </div>
                    <div v-else>
                      <i class="bi bi-star"></i>
                    </div>
                  </button>
                  <div class="card-header-d">
                    <div class="card-title-d align-self-center">
                      <h3 class="title-d">
                        <div @click="agentDetail(agent.agentNo)">{{ agent.agentName }}</div>
                      </h3>
                    </div>
                  </div>
                  <div class="card-body-d">
                    <p class="content-d color-text-a">
                      {{ agent.agentDesc }}
                    </p>
                    <div class="info-agents color-a">
                      <p><strong>Phone: </strong>{{ agent.agentPhone }}</p>
                      <p><strong>Email: </strong> {{ agent.agentEmail }}</p>
                    </div>
                  </div>
                  <div class="card-footer-d">
                    <div class="socials-footer d-flex justify-content-center">
                      <ul class="list-inline">
                        <li class="list-inline-item">
                          <a href="#" class="link-one">
                            <i class="bi bi-facebook" aria-hidden="true"></i>
                          </a>
                        </li>
                        <li class="list-inline-item">
                          <a href="#" class="link-one">
                            <i class="bi bi-twitter" aria-hidden="true"></i>
                          </a>
                        </li>
                        <li class="list-inline-item">
                          <a href="#" class="link-one">
                            <i class="bi bi-instagram" aria-hidden="true"></i>
                          </a>
                        </li>
                        <li class="list-inline-item">
                          <a href="#" class="link-one">
                            <i class="bi bi-linkedin" aria-hidden="true"></i>
                          </a>
                        </li>
                      </ul>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <!-- agent -->
          </div>
        </div>
      </section>
    </main>
  </div>
</template>

<script>
import http from "@/common/axios.js";
export default {
  data() {
    return {
      toggle: "",
    };
  },
  methods: {
    agentList() {
      this.$store.dispatch("agentList");
    },
    agentDetail(agentNo) {
      this.$store.commit("SET_AGENT_NO", agentNo);
      this.$router.push("/agentDetail");
    },
    checkFavorite(agent, toggle) {
      if (toggle == false) {
        return true;
      } else {
        if (agent.userFavoriteCheck != 0) return true;
        else return false;
      }
    },
    changeToggleData() {
      if (this.toggle == true) this.toggle = false;
      else this.toggle = true;
    },
    changeFavorite(agent) {
      // 즐겨찾기 추가  X
      if (agent.userFavoriteCheck == 0) {
        // login Id , agentNo로 추가
        this.addFavorite(this.$store.state.user.userNo, agent.agentNo);
      }
      // 즐겨찾기 O -> 해당 agent 삭제
      else {
        this.removeFavorite(this.$store.state.user.userNo, agent.agentNo);
      }
    },

    addFavorite(userNo, agentNo) {
      // console.log("Adduserno : " + userNo);
      // console.log("AddagentNo : " + agentNo);
      http
        .post("/addFavorite/" + userNo + "/" + agentNo, {})
        .then(({ data }) => {
          console.log("addThisAgentFavorite: data : ");
          console.log(data);
          this.agentList();
        })
        .catch((error) => {
          console.log("addThisAgentFavorite: error : ");
          console.log(error);
          this.$alertify.error("Opps!! 서버에 문제가 발생했습니다.");
        });
    },
    removeFavorite(userNo, agentNo) {
      // console.log("rmUserNo : " + userNo);
      // console.log("rmAgentNo : " + agentNo);

      http
        .delete("/removeFavorite/" + userNo + "/" + agentNo, {})
        .then(({ data }) => {
          console.log("removeThisAgentFavorite: data : ");
          console.log(data);
          this.agentList();
        })
        .catch((error) => {
          console.log("removeThisAgentFavorite: error : ");
          console.log(error);
          this.$alertify.error("Opps!! 서버에 문제가 발생했습니다.");
        });
    },
  },

  created() {
    this.agentList();
  },
};
</script>
