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
                <span class="color-text-a mt-2" style="font-size : 22px;">Agent</span>
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
                    <img v-bind:src="$store.state.agent.agentProfileImageUrl" alt="" class="agent-avatar img-fluid">
                  </div>
                </div>
                <div class="col-md-5 section-md-t3 ms-3">
                  <div class="agent-info-box">
                    <div class="agent-title">
                      <div class="title-box-d">
                        <h3 class="title-d" >{{$store.state.agent.agentName}}

                        </h3>
                      </div>
                    </div>
                    <div class="agent-content mb-3" >
                      <p class="content-d color-text-a mt-5 mb-5" style="font-size : 22px;">
                        {{$store.state.agent.agentDesc}}
                      </p>
                      <div class="info-agents color-a" >
                        <p>
                          <strong style="font-size : 22px;" >Phone : </strong>
                          <span class="color-text-a" style="font-size : 22px;">{{$store.state.agent.agentPhone}}</span>
                        </p>

                        <p>
                          <strong style="font-size : 22px;">Email : </strong>
                          <span class="color-text-a" style="font-size : 22px;">{{$store.state.agent.agentEmail}}</span>
                        </p>
                        <p>
                          <strong style="font-size : 22px;">rate : </strong>
                          <span class="color-text-a" style="font-size : 22px;">{{$store.state.agent.agentRate}}</span>
                        </p>
                      </div>
                    </div>
                    <div class="socials-footer mt-5">
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
                    <img v-bind:src="house.img" alt="" class="img-a img-fluid">
                  </div>
                  <div class="card-overlay">
                    <div class="card-overlay-a-content">
                      <div class="card-header-a">
                        <h2 class="card-title-a">
                          <a href="#">No. {{ house.houseNo }}
                            <br /> {{ house.houseName }}</a>
                        </h2>
                      </div>
                      <div class="card-body-a">
                        <div class="price-box d-flex">
                          <span class="price-a">buildYear | {{ house.buildYear }}</span>
                        </div>
                        <div id="detail" @click="houseDetail(house.houseNo)">Click here to view
                          <span class="bi bi-chevron-right"></span>
                        </div>
                      </div>
                      <div class="card-footer-a">
                        <ul class="card-info d-flex justify-content-around">
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
    <house-modal></house-modal>
  </div>
</template>

<script>
  import {
    Modal
  } from 'bootstrap';

  import HouseModal from './modals/HouseModal.vue';
  import http from "@/common/axios.js";

  export default {


    components : {
      HouseModal,
    },
    data() {
      return {
        houseModal: null,
      }
    },
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
                agentName: data.agentDto.agentName,
                agentNo: data.agentDto.agentNo,
                agentEmail: data.agentDto.agentEmail,
                agentPhone: data.agentDto.agentPhone,
                agentRate: data.agentDto.agentRate,
                agentDesc: data.agentDto.agentDesc,
                agentHouseList: data.houselist,
                agentProfileImageUrl: data.agentDto.agentProfileImageUrl, // not data.dto.sameUser
              }
            );

          })
          .catch((error) => {
            console.log("agent Detail : error ");
            console.log(error);
          });
      },


      houseDetail(houseNo) {
        http.get(
            '/houses/' + houseNo, // props variable
          )
          .then(({
            data
          }) => {
            console.log("DetailHouseModalVue: data : ");
            console.log(data);

            if (data.result == 'login') {
              this.$router.push("/login")
            } else {
              this.$store.commit(
                'SET_HOUSE_DETAIL', {
                  houseNo: data.dto.houseNo,
                  area: data.dto.area,
                  code: data.dto.code,
                  dong: data.dto.dong,
                  houseName: data.dto.houseName,
                  buildYear: data.dto.buildYear,
                  lat: data.dto.lat,
                  lng: data.dto.lng,
                  dealList: data.dealList,
                  agentName: data.agentDto.agentName,
                  agentNo: data.agentDto.agentNo,
                  agentEmail: data.agentDto.agentEmail,
                  agentPhone: data.agentDto.agentPhone,
                  agentRate: data.agentDto.agentRate,
                  agentDesc: data.agentDto.agentDesc,
                  img: "assets/img/post-1.jpg",
                }
              );
              console.log("134Line houseName : " + this.$store.state.house.dealList);
              this.houseModal.show();
            }
          })
          .catch((error) => {
            console.log("DetailModalVue: error ");
            console.log(error);
          });

      },

    },
    mounted() {
      this.houseModal = new Modal(document.getElementById('houseModal'));

    },
    created() {
      this.agentDetail();
    },

  };
</script>

<style>

  #detail {
    color: wheat;
    font-weight: bold;
  }
</style>