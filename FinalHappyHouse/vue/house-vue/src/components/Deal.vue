<template>
  <div class="container2">
    <div class="item">
      <div id="wrapper">
        <div id="map"></div>
        <div class="input-group" id="search">
          <div class="form-outline mt-2 ms-2">
            <input id="form1" class="form-control" v-model="$store.state.house.searchWord" @keydown.enter="houseList"
              style="height: 38.5px; width: 168px" />
          </div>
          <button @click="houseList" class="btn btn-primary mt-2 ms-1" type="button" style="border-radius: 10px">
            <i class="bi bi-search"></i>
          </button>

          <div class="form-outline">
            <div type="text" placeholder="price range" id="form1" class="form-control text-center lh-1 m-2"
              style="height: 30px; width: 177px">
              <div v-if="isFilter">{{ min }}만 &nbsp;&nbsp; ~ &nbsp; &nbsp;{{ max }}만</div>
            </div>
          </div>
          <button @click="btnPriceRange()" type="button" class="btn btn-sm btn-primary" style="border-radius: 10px">
            <i class="bi bi-gear"></i>
          </button>
          <div v-show="isBtnSet" class="mx-2 mt-1 mb-2 row">
            <div class="form-check ms-1 mt-1">
              <input @click="manShow()" class="form-check-input" type="checkbox" value="" id="flexCheckDefault" />
              <label class="form-check-label" for="flexCheckDefault"> 필터적용 </label>
            </div>
            <div class="d-flex justify-content-center mt-1">
              <input type="text" placeholder="   Min" class="form-control col-5" id="customRange1"
                style="height: 38px; width: 85px" v-model="min" />
              <div class="col-2" style="font-size: 1.8rem">&nbsp;~</div>
              <input type="text" placeholder="   Max" class="form-control col-5" id="customRange2"
                style="height: 38px; width: 85px" v-model="max" />
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="item">
      <div class="row">
        <div class="searchTitle m-3" style="font-size: 2rem">검색결과</div>
        <hr style=" height : 10px;   background-color: gold;">
      </div>
      <div class="row">
        <div id="listDiv">
          <ul v-show="liCondition" class="list-group">
            <li style="cursor: pointer" v-for="(house, index) in this.$store.state.house.list"
              @click="houseDetail(house.houseNo)" v-bind:key="index" class="list-group-item">
              <div id="pCard" class="card mb-3" style="text-align: center">
                <div class="row g-0">
                  <div class="col-md-4">
                    <img id="cardImg" v-bind:src="house.img" alt="" />
                  </div>
                  <div class="col-md-8" style="text-align: left">
                    <div class="card-body">
                      <h5 id="text" class="card-title">{{ house.title }}</h5>
                      <p id="text" class="card-text">
                        name : {{ house.houseName }}<br />
                        지역 : {{ house.dong }}<br />
                        건축연도 : {{ house.buildYear }}<br />
                      </p>
                    </div>
                  </div>
                </div>
              </div>
            </li>
          </ul>
        </div>
      </div>
    </div>

    <detail-house-modal v-on:call-parent-close="closeModal"></detail-house-modal>
  </div>
</template>

<script>
  import {
    Modal
  } from "bootstrap";

  import DetailHouseModal from "./modals/DetailHouseModal.vue";
  import http from "@/common/axios.js";

  export default {
    components: {
      DetailHouseModal,
    },
    data() {
      return {
        map: null,

        markerPositions1: [],

        markers: [],

        infowindow: null,

        liCondition: false,
        textCondition: true,
        detailHouseModal: null,
        isBtnSet: false,
        isFilter: false,

        min: "",
        max: "",
        minPlusMax: this.min + this.max,
      };
    },

    computed: {
      // gttters 이용
    },
    methods: {
      initMap() {
        const container = document.getElementById("map");
        const options = {
          center: new kakao.maps.LatLng(37.5743822, 126.9688505),
          level: 3,
        };
        this.map = new kakao.maps.Map(container, options);
      },

      houseList() {
        console.log("this.isFilter : " + this.isFilter);
        if (this.isFilter) {
          console.log("this.state.house.searchWord : " + this.$store.state.house.searchWord);
          this.$store.dispatch("houseListWithFilter", {
            min: this.min,
            max: this.max,
          });
        } else {
          this.$store.dispatch("houseList");
        }
        this.liCondition = true;
        this.textCondition = false;

        var houseList = this.$store.state.house.list;

        var mapContainer = document.getElementById("map"), // 지도를 표시할 div
          mapOption = {
            center: new kakao.maps.LatLng(houseList[1].lat, houseList[1].lng), // 지도의 중심좌표
            level: 3, // 지도의 확대 레벨
          };

        this.map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

        // 마커를 표시할 위치와 내용을 가지고 있는 객체 배열입니다
        var positions = [];
        for (let index = 0; index < houseList.length; index++) {
          positions.push({
            content: "<div>" + houseList[index].houseName + "</div>",
            houseNo: houseList[index].houseNo,
            latlng: new kakao.maps.LatLng(houseList[index].lat, houseList[index].lng),
          });
        }

        for (var i = 0; i < positions.length; i++) {
          // 마커를 생성합니다
          var marker = new kakao.maps.Marker({
            map: this.map, // 마커를 표시할 지도
            position: positions[i].latlng, // 마커의 위치
          });

          // 마커에 표시할 인포윈도우를 생성합니다
          var infowindow = new kakao.maps.InfoWindow({
            content: positions[i].content, // 인포윈도우에 표시할 내용
          });

          kakao.maps.event.addListener(
            marker,
            "mouseover",
            this.makeOverListener(this.map, marker, infowindow)
          );
          kakao.maps.event.addListener(marker, "mouseout", this.makeOutListener(infowindow));
        }
      },

      closeModal() {
        this.detailHouseModal.hide();
        this.$router.push("/agentDetail");
      },

      // 인포윈도우를 닫는 클로저를 만드는 함수입니다

      houseDetail(houseNo) {
        http
          .get(
            "/houses/" + houseNo // props variable
          )
          .then(({
            data
          }) => {
            console.log("DetailHouseModalVue: data : ");
            console.log(data);

            if (data.result == "login") {
              this.$router.push("/login");
            } else {
              this.$store.commit("SET_HOUSE_DETAIL", {
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
                agentProfileImageUrl: data.agentDto.agentProfileImageUrl,
                img: data.dto.img,
              });
              console.log("134Line houseName : " + this.$store.state.house.dealList);
              this.detailHouseModal.show();
            }
          })
          .catch((error) => {
            console.log("DetailModalVue: error ");
            console.log(error);
          });
      },
      btnPriceRange() {
        if (this.isBtnSet) {
          this.isBtnSet = false;
        } else this.isBtnSet = true;
      },
      manShow() {
        if (this.isFilter) {
          this.isFilter = false;
        } else this.isFilter = true;
      },
      makeOverListener(map, marker, infowindow) {
        return function () {
          infowindow.open(map, marker);
        };
      },

      // 인포윈도우를 닫는 클로저를 만드는 함수입니다
      makeOutListener(infowindow) {
        return function () {
          infowindow.close();
        };
      },
     
    },
    mounted() {
      this.detailHouseModal = new Modal(document.getElementById("detailHouseModal"));
    },
    created() {
      if (window.kakao && window.kakao.maps) {
        this.initMap();
      } else {
        const script = document.createElement("script");
        /* global kakao */
        script.onload = () => kakao.maps.load(this.initMap);
        script.src =
          "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=bd170057fb7676f1775e92470777138d";
        document.head.appendChild(script);
      }
    },
  };
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  #map {
    width: 100%;
    height: 100vh;
  }

  #pCard {
    margin: auto;
    height: 100px;
    border-radius: 1000px;
  }

  #searchTitle {
    font-size: large;
    text-align: center;
    margin: 10px;
    height: 100vh;
  }

  .button-group {
    margin: 10px 0px;
  }

  button {
    margin: 0 3px;
  }

  .container2 {
    margin: 0;
    padding: 0;

    overflow-x: hidden;
    width: 100vw;
    height: 100vh;
    display: flex;
    background: white;
  }

  .item:nth-child(1) {
    flex: 4;
  }

  .item:nth-child(2) {
    flex: 1;
    height: 100vh;
    overflow-x: hidden;
    overflow-y: scroll;
  }

  #wrapper {
    position: relative;
  }

  #search {
    position: absolute;
    top: 3vh;
    left: 2vh;
    z-index: 100;
    background: #e3e6ed;
    width: 233px;
    border: solid 1px;
    border-color: #9fb3e6;
    border-radius: 5px;
  }

  .card {
    width: 500px;
    border: none;
    border-radius: 20px;
  }

  #cardImg {
    width: 150px;
    height: 11vh;
  }

  #text {
    font-weight: 600;
    font-size: 15px;
  }

  #listDiv {}

  .form-control {
    border-radius: 7px;
    border: 1.5px solid #e3e6ed;
  }

  input.form-control:focus {
    box-shadow: none;
    border: 1.5px solid #e3e6ed;
    background-color: #f7f8fd;
    letter-spacing: 1px;
  }

  .btn-primary {
    background-color: #5878ff !important;
    border-radius: 7px;
  }

  .btn-primary:focus {
    box-shadow: none;
  }

  .text {
    font-size: 13px;
    color: #9ca1a4;
  }

  .price {
    background: #f5f8fd;
    border-top-right-radius: 10px;
    border-bottom-right-radius: 10px;
    width: 97px;
  }

  .flex-row {
    border: 1px solid #f2f2f4;
    border-radius: 10px;
    margin: 0 1px 0;
  }

  .flex-column p {
    font-size: 14px;
  }

  span.mb-2 {
    font-size: 12px;
    color: #8896bd;
  }

  h5 span {
    color: #869099;
  }

  @media screen and (max-width: 450px) {
    .card {
      display: flex;
      justify-content: center;
      text-align: center;
    }

    .price {
      border: none;
      margin: 0 auto;
    }
  }

  .li {
    width: 100px;
    /* 정렬하려는 요소의 넓이를 반드시 지정 */
    margin: 0 auto;
  }
</style>
