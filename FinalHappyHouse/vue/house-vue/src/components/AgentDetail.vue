<template>
  <div>
    <main id="main">

      <!-- ======= Intro Single ======= -->
      <section class="intro-single">
        <div class="container">
          <div class="row">
            <div class="col-md-12 col-lg-8">
              <div class="title-single-box">
                <h1 class="title-single">{{$store.state.agent.agentName}}</h1>
                <span class="color-text-a">Agent</span>
              </div>
            </div>
          </div>
        </div>
      </section><!-- End Intro Single -->

      <!-- ======= Agent Single ======= -->
      <section class="agent-single">
        <div class="container">
          <div class="row">
            <div class="col-sm-12">
              <div class="row">
                <div class="col-md-6">
                  <div class="agent-avatar-box">
                    <img src="assets/img/agent-7.jpg" alt="" class="agent-avatar img-fluid">
                  </div>
                </div>
                <div class="col-md-5 section-md-t3">
                  <div class="agent-info-box">
                    <div class="agent-title">
                      <div class="title-box-d">
                        <h3 class="title-d">{{$store.state.agent.agentName}}
                        
                        </h3>
                      </div>
                    </div>
                    <div class="agent-content mb-3">
                      <p class="content-d color-text-a">
                        {{$store.state.agent.agentDesc}}
                      </p>
                      <div class="info-agents color-a">
                        <p>
                          <strong>Phone: </strong>
                          <span class="color-text-a">{{$store.state.agent.agentPhone}}</span>
                        </p>
                      
                        <p>
                          <strong>Email: </strong>
                          <span class="color-text-a">{{$store.state.agent.agentEmail}}</span>
                        </p>
                        <p>
                          <strong>rate: </strong>
                          <span class="color-text-a">{{$store.state.agent.agentRate}}</span>
                        </p>
                      </div>
                    </div>
                    <div class="socials-footer">
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
            <div class="col-md-12 section-t8">
              <div class="title-box-d">
                <h3 class="title-d">My Properties ({{$store.state.agent.agentHouseList.length}})</h3>
              </div>
            </div>
            <div class="row property-grid grid">
              <!-- agent house -->
              <div class="col-md-4" v-for="(house ,index) in this.$store.state.agent.agentHouseList" v-bind:key="index">
                <div class="card-box-a card-shadow">
                  <div class="img-box-a">
                    <img src="assets/img/property-1.jpg" alt="" class="img-a img-fluid">
                  </div>
                  <div class="card-overlay">
                    <div class="card-overlay-a-content">
                      <div class="card-header-a">
                        <h2 class="card-title-a">
                          <a href="#">No. {{ house.houseNo }}
                            <br/> {{ house.houseName }}</a>
                        </h2>
                      </div>
                      <div class="card-body-a">
                        <div class="price-box d-flex">
                          <span class="price-a">buildYear | {{ house.buildYear }}</span>
                        </div>
                        <a href="#" class="link-a">Click here to view
                          <span class="bi bi-chevron-right"></span>
                        </a>
                      </div>
                      <div class="card-footer-a">
                        <ul class="card-info d-flex justify-content-around">
                          <li>
                            <h4 class="card-info-title">Area</h4>
                            <span>{{ house.house }}
                              
                            </span>
                          </li>
                          <li>
                            <h4 class="card-info-title">Beds</h4>
                            <span>2</span>
                          </li>
                          <li>
                            <h4 class="card-info-title">Baths</h4>
                            <span>4</span>
                          </li>
                          <li>
                            <h4 class="card-info-title">Garages</h4>
                            <span>1</span>
                          </li>
                        </ul>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <!-- agent house -->


            </div>
          </div>
        </div>
      </section><!-- End Agent Single -->

    </main><!-- End #main -->

  </div>
</template>

<script>
  import http from "@/common/axios.js";
  export default {


    methods: {
      agentDetail() {
        // store 변경
        // this.boardId = boardId;
        // this.$store.commit('mutateSetBoardBoardId', boardId);

        http.get(
            '/agents/' + this.$store.state.agent.agentNo, // props variable
          )
          .then(({
            data
          }) => {
            console.log("agentDetail : data : ");
            console.log(data);

            
            this.$store.commit(
              'SET_AGENT_DETAIL', {
                agentName : data.agentDto.agentName,
                agentNo : data.agentDto.agentNo,
                agentEmail : data.agentDto.agentEmail,
                agentPhone : data.agentDto.agentPhone,
                agentRate : data.agentDto.agentRate,
                agentDesc : data.agentDto.agentDesc,
                agentHouseList : data.houselist,
                agentProfileImageUrl : data.agentDto.agentProfileImageUrl, // not data.dto.sameUser
              }
            );

          })
          .catch((error) => {
            console.log("agent Detail : error ");
            console.log(error);
          });
      },

    },

    created() {
      this.agentDetail();
    },

  };
</script>
