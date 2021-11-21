<template>
  <div class="container2">
    <div class="item">
      <div id="warpper">
        <div id="map"></div>
        <div id="search" class="">
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


    <detail-house-modal></detail-house-modal>
  </div>








</template>

<script>
  import {
    Modal
  } from 'bootstrap';

  import DetailHouseModal from './modals/DetailHouseModal.vue';
  import http from "@/common/axios.js";

  export default {
    name: "KakaoMap",
    components: {
      DetailHouseModal
    },
    data() {
      return {
        map: null,
        list: [],
        liCondition: false,
        textCondition: true,
        markers: [],
        infowindow: null,
        detailHouseModal: null,
      };
    },

    computed: {
      // gttters 이용
    },
    methods: {

      houseList() {
        this.$store.dispatch('houseList');


        this.liCondition = true;
        this.textCondition = false;
        // this.list = this.$store.state.house.list;
        // if (this.list.length > 0) {
        //   this.liCondition = true;
        //   this.textCondition = false;
        // }
      },

      houseDetail(houseNo) {
        // store 변경
        // this.boardId = boardId;
        // this.$store.commit('mutateSetBoardBoardId', boardId);

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
                  dealList: data.dto.dealList,
                  agentName: data.dto.agentName,
                  agentNo: data.dto.agentNo,
                }
              );
              this.detailHouseModal.show();
            }
          })
          .catch((error) => {
            console.log("DetailModalVue: error ");
            console.log(error);
          });

      },



      initMap() {
        const container = document.getElementById("map");
        const options = {
          center: new kakao.maps.LatLng(33.450701, 126.570667),
          level: 5,
        };
        this.map = new kakao.maps.Map(container, options);
      },
      changeSize(size) {
        const container = document.getElementById("map");
        container.style.width = `${size}px`;
        container.style.height = `${size}px`;
        this.map.relayout();
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
      displayInfoWindow() {
        if (this.infowindow && this.infowindow.getMap()) {
          //이미 생성한 인포윈도우가 있기 때문에 지도 중심좌표를 인포윈도우 좌표로 이동시킨다.
          this.map.setCenter(this.infowindow.getPosition());
          return;
        }

        var iwContent =
          '<div style="padding:5px;">Hello World!</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
          iwPosition = new kakao.maps.LatLng(33.450701, 126.570667), //인포윈도우 표시 위치입니다
          iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

        this.infowindow = new kakao.maps.InfoWindow({
          map: this.map, // 인포윈도우가 표시될 지도
          position: iwPosition,
          content: iwContent,
          removable: iwRemoveable,
        });

        this.map.setCenter(iwPosition);
      },
    },
    mounted() {

      this.detailHouseModal = new Modal(document.getElementById('detailHouseModal'));

      if (window.kakao && window.kakao.maps) {
        this.initMap();
      } else {
        const script = document.createElement("script");
        /* global kakao */
        script.onload = () => kakao.maps.load(this.initMap);
        script.src =
          "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=915cffed372954b7b44804ed422b9cf0";
        document.head.appendChild(script);
      }
    },

  };
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  #map {
    width: 700px;
    height: 700px;
    object-fit: fill;
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

  .item:nth-child(1) {
    flex: 1;
  }

  #wrapper {
    position: relative;

  }

  #search {
    position: absolute;


    top: 20%;


  }
</style>