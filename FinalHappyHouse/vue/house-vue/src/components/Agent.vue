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
            <div class="form-check form-switch">
              <input class="form-check-input" type="checkbox" id="flexSwitchCheckDefault" />
              <label class="form-check-label" for="flexSwitchCheckDefault">Favorite</label>
            </div>
            <!-- agent -->
            <div
              class="col-md-4"
              v-for="(agent, index) in this.$store.state.agent.list"
              v-bind:key="index"
            >
              <!-- v-for="(favoriteAgentNo, index2) in this.$store.state.user.userFavoriteAgentList"
                v-show="agent.agentNo == favoriteAgentNo"
                v-bind:key="index2" -->
              <div class="card-box-d">
                <div class="card-img-d">
                  <img src="assets/img/agent-4.jpg" alt="" class="img-d img-fluid" />
                </div>
                <div class="card-overlay card-overlay-hover">
                  <button type="button" class="btn btn-warning">
                    <i class="bi bi-star"></i>
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
  methods: {
    agentList() {
      this.$store.dispatch("agentList");
    },
    agentDetail(agentNo) {
      this.$store.commit("SET_AGENT_NO", agentNo);
      this.$router.push("/agentDetail");
    },

    userFavoriteAgentList(userNo) {
      http
        .get("/user/favoriteAgents/" + userNo, {
          // get query string
        })
        .then(({ data }) => {
          console.log("AgentVue userFavoriteAgentNo : data : ");
          console.log(data);
          if (data.result == "login") {
            this.$router.push("/login");
          } else {
            this.$store.commit("SET_FAVORITE_AGENT_LIST", data.list);
          }
        });
    },
  },

  created() {
    this.agentList();
  },
  // computed() {
  //   testfunc: function() {

  //     for(){

  //       if (this.$store.agent.list.agentNo != ) {
  //           return true;
  //         }
  //       return false;
  //     }

  //   }
  // }
};
</script>
