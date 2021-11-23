<template>
  <div class="container2">
    <div class="item">
      <div id="warpper">
        <div id="map"></div>
        <div id="search">
          <!-- store 사용 -->
          <input v-model="$store.state.house.searchWord" @keydown.enter="houseList" type="text" class="form-control">
          <button @click="houseList" class="btn btn-success" type="button">Search</button>
          <button class="btn btn-success" type="button">필터</button>
          <!-- enter나 버튼 클릭시 search 동작 -->
        </div>
      </div>
    </div>
    <div class="item">
      지역 목록
      <h1>List Group</h1>
      <div v-show="textCondition">
        검색결과가 없습니다!!!!!!!!!!!!!!!
      </div>
      <div>
        <ul v-show="liCondition" class="list-group">
          <li style="cursor:pointer" v-for="(house, index) in this.$store.state.house.list"
            @click="houseDetail(house.houseNo)" v-bind:key="index" class="list-group-item">
            <div class="card mb-3" style="max-width: 540px;">
              <div class="row g-0">
                <div class="col-md-4">
                  <img src="" alt="">
                </div>
                <div class="col-md-8">
                  <div class="card-body">
                    <h5 class="card-title">{{house.title}}</h5>
                    <p class="card-text">name : {{ house.houseName }}<br>
                      지역 : {{ house.dong }}<br>
                      건축연도 : {{ house.buildYear }}<br></p>
                  </div>
                </div>
              </div>
            </div>
          </li>
        </ul>
      </div>

    </div>


    <detail-house-modal v-on:call-parent-close="closeModal"></detail-house-modal>
  </div>
</template>

<script>
  import {
    Modal
  } from 'bootstrap';

  import DetailHouseModal from './modals/DetailHouseModal.vue';
  import http from "@/common/axios.js";

  export default {
    components: {
      DetailHouseModal
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

      displayMarker(markerPositions) {
        if (this.markers.length > 0) {
          this.markers.forEach((marker) => marker.setMap(null));
        }

        const positions = markerPositions.map(
          (position) => new kakao.maps.LatLng(...position)
        );

        if (positions.length > 0) {
          this.markers = positions.map(
            (position) =>
            new kakao.maps.Marker({
              map: this.map,
              position,
            })
          );

          const bounds = positions.reduce(
            (bounds, latlng) => bounds.extend(latlng),
            new kakao.maps.LatLngBounds()
          );

          this.map.setBounds(bounds);
        }
      },


      houseList() {
        this.$store.dispatch('houseList');
        this.liCondition = true;
        this.textCondition = false;


        var houseList = this.$store.state.house.list;

        var mapContainer = document.getElementById('map'), // 지도를 표시할 div  
          mapOption = {
            center: new kakao.maps.LatLng(houseList[1].lat, houseList[1].lng), // 지도의 중심좌표
            level: 3 // 지도의 확대 레벨
          };

        this.map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

        // 마커를 표시할 위치와 내용을 가지고 있는 객체 배열입니다 
        var positions = [];
        for (let index = 0; index < houseList.length; index++) {
          positions.push({
            content: '<div>' + houseList[index].houseName + '</div>',
            houseNo: houseList[index].houseNo,
            latlng: new kakao.maps.LatLng(houseList[index].lat, houseList[index].lng)
          });
        }

        for (var i = 0; i < positions.length; i++) {
          // 마커를 생성합니다
          var marker = new kakao.maps.Marker({
            map: this.map, // 마커를 표시할 지도
            position: positions[i].latlng, // 마커의 위치
            clickable: true
          });

          // 마커에 표시할 인포윈도우를 생성합니다 
          var infowindow = new kakao.maps.InfoWindow({
            content: positions[i].content // 인포윈도우에 표시할 내용
          });




          // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
          // 이벤트 리스너로는 클로저를 만들어 등록합니다 
          // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
          kakao.maps.event.addListener(marker, 'mouseover', this.makeOverListener(this.map, marker, infowindow));
          kakao.maps.event.addListener(marker, 'mouseout', this.makeOutListener(infowindow));
          kakao.maps.event.addListener(marker, 'click', this.houseDetail(positions[i].houseNo));
        }

      },

      closeModal() {
        this.detailHouseModal.hide();
        this.$router.push("/agentDetail");
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
              this.detailHouseModal.show();
            }
          })
          .catch((error) => {
            console.log("DetailModalVue: error ");
            console.log(error);
          });

      },

    },
    mounted() {
      this.detailHouseModal = new Modal(document.getElementById('detailHouseModal'));


      if (window.kakao && window.kakao.maps) {
        this.initMap();
      } else {
        const script = document.createElement('script');
        /* global kakao */
        script.onload = () => kakao.maps.load(this.initMap);
        script.src =
          'http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=bd170057fb7676f1775e92470777138d';
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
    background: lightblue;

  }

  .item:nth-child(1) {
    flex: 5;
  }

  .item:nth-child(2) {
    flex: 1;
    height: 100px;
  }

  #list-group {
    overflow: auto;
    height: 500px;
  }

  #wrapper {
    position: relative;
  }

  #search {
    position: absolute;
    top: 20vh;
    left: 2vh;
    z-index: 100;
    background: white;
  }
</style>