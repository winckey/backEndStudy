<template>
  <div class="modal" tabindex="-1" id="detailHouseModal">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title">상세정보</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <div>

            <!-- ======= Intro Single ======= -->
            <section>
              <div class="container">
                <div class="row">
                  <div class="col-md-12 col-lg-8">
                    <div class="title-single-box">

                    </div>
                  </div>
                </div>
              </div>
            </section><!-- End Intro Single-->

            <!-- ======= Property Single ======= -->
            <section class="property-single nav-arrow-b">
              <div class="container">
                <div class="row justify-content-center">
                  <div class="col-lg-8">

                    <div>

                      <img class="image-thumbnail" src="assets/img/slide-1.jpg" alt="">

                    </div>

                    <div class="property-single-carousel-pagination carousel-pagination"></div>
                  </div>
                </div>

                <div class="row">
                  <div class="col-sm-12">

                    <div class="row justify-content-between">
                      <div class="col-md-5 col-lg-4">

                        <div class="property-summary">
                          <div class="row">
                            <div class="col-sm-12">
                              <div class="title-box-d section-t4">
                                <h3 class="title-d">Quick Summary</h3>
                              </div>
                            </div>
                          </div>
                          <div class="summary-list">
                            <ul class="list">
                              <li class="d-flex justify-content-between">
                                <strong>Property ID:</strong>
                                <span>{{ $store.state.house.houseNo }}</span>
                              </li>
                              <li class="d-flex justify-content-between">
                                <strong>house name:</strong>
                                <span>{{ $store.state.house.houseName }}</span>
                              </li>
                              <li class="d-flex justify-content-between">
                                <strong>Location:</strong>
                                <span> {{ $store.state.house.dong }}</span>
                              </li>
                              <li class="d-flex justify-content-between">
                                <strong>buildYear:</strong>
                                <span>{{ $store.state.house.buildYear }}</span>
                              </li>
                              <li class="d-flex justify-content-between">
                                <strong>Status:</strong>
                                <span>Sale</span>
                              </li>

                            </ul>
                          </div>
                        </div>
                      </div>
                      <div class="col-md-7 col-lg-7 section-md-t3">


                        <div class="row section-t3">
                          <div class="col-sm-12">
                            <div class="title-box-d">
                              <br>
                              <br>
                              <h3 class="title-d">거래내역</h3>
                            </div>
                          </div>
                        </div>
                        <div class="mydiv" id="mydiv">
                          <table class="table table-hover">
                            <thead>
                              <tr>
                                <th>일시</th>
                                <th>가격</th>
                                <th>층수</th>
                                <th>크기</th>

                              </tr>
                            </thead>
                            <tbody>
                              <!-- store 사용 -->

                              <!-- 직접 store 에 접근해도 된다. -->
                              <!-- <tr v-for="(board, index) in $store.state.board.list" @click="boardDetail(board.boardId)" v-bind:key="index"> -->

                              <!-- getters 를 이용하는 코드 -->
                              <!-- computed - listGetters - getBoardList  -->
                              <tr style="cursor:pointer" v-for="(deal, index) in this.$store.state.house.dealList"
                                v-bind:key="index">
                                <!-- 직접 접근하지 않고 함수로 접근  -->
                                <td>{{deal.dealYear}}.{{deal.dealMonth}}.{{deal.dealDay}} </td>
                                <td>{{ deal.dealAmount }}</td>
                                <td>{{ deal.floor }}</td>
                                <td>{{ deal.area }}</td>
                              </tr>
                            </tbody>
                          </table>
                        </div>
                      </div>
                    </div>
                  </div>

                  <div class="col-md-12">
                    <div class="row section-t3">
                      <div class="col-sm-12">
                        <div class="title-box-d">
                          <h3 class="title-d">Contact
                            Agent</h3>
                        </div>
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-md-6 col-lg-4">
                        <img src="assets/img/agent-4.jpg" alt="" class="img-fluid">
                      </div>
                      <div class="col-md-6 col-lg-4">
                        <div class="property-agent">
                          <h4 class="title-agent">Anabella Geller</h4>
                          <p class="color-text-a">
                            Nulla porttitor accumsan tincidunt. Vestibulum ac diam sit amet quam vehicula elementum sed
                            sit amet
                            dui. Quisque velit nisi,
                            pretium ut lacinia in, elementum id enim.
                          </p>
                          <ul class="list-unstyled">
                            <li class="d-flex justify-content-between">
                              <strong>Phone:</strong>
                              <span class="color-text-a">(222) 4568932</span>
                            </li>
                            <li class="d-flex justify-content-between">
                              <strong>Mobile:</strong>
                              <span class="color-text-a">777 287 378 737</span>
                            </li>
                            <li class="d-flex justify-content-between">
                              <strong>Email:</strong>
                              <span class="color-text-a">annabella@example.com</span>
                            </li>
                            <li class="d-flex justify-content-between">
                              <strong>Skype:</strong>
                              <span class="color-text-a">Annabela.ge</span>
                            </li>
                          </ul>
                          <div class="socials-a">
                            <ul class="list-inline">
                              <li class="list-inline-item">
                                <a href="#">
                                  <i class="bi bi-facebook" aria-hidden="true"></i>
                                </a>
                              </li>
                              <li class="list-inline-item">
                                <a href="#">
                                  <i class="bi bi-twitter" aria-hidden="true"></i>
                                </a>
                              </li>
                              <li class="list-inline-item">
                                <a href="#">
                                  <i class="bi bi-instagram" aria-hidden="true"></i>
                                </a>
                              </li>
                              <li class="list-inline-item">
                                <a href="#">
                                  <i class="bi bi-linkedin" aria-hidden="true"></i>
                                </a>
                              </li>
                            </ul>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </section><!-- End Property Single-->

          </div><!-- End #main -->
        </div>
      </div>
    </div>
  </div>
</template>


<script>
  // 아래 import 사용 X
  // import http from "@/common/axios.js";
  // import util from "@/common/util.js";
  export default {



    methods: {}
  }
</script>

<style>
  .image-box {
    width: 380px;
    height: 220px;
    overflow: hidden;
    margin: 0 auto;
  }

  .image-thumbnail {
    width: 100%;
    height: 100%;
    object-fit: cover;
  }

  .modal-dialog.modal-80size {
    width: 80%;
    height: 80%;
    margin: 0;
    padding: 0;
  }

  .modal-content.modal-80size {
    height: auto;
    min-height: 80%;
  }

  .modal.modal-center {
    text-align: center;
  }

  @media screen and (min-width: 768px) {
    .modal.modal-center:before {
      display: inline-block;
      vertical-align: middle;
      content: " ";
      height: 100%;
    }
  }

  .modal-dialog.modal-center {
    display: inline-block;
    text-align: left;
    vertical-align: middle;
  }

  .mydiv {
    border: 1px solid #735e5e;
    height: 170px;
    overflow: auto;
  }
</style>