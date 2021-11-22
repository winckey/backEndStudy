<template>
  <div class="container2">
    <div class="item">
      <div id="warpper">
        <div ></div>
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
    components: {
      DetailHouseModal
    },
    data() {
      return {
        liCondition : false,
        textCondition : true,
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
                  agentName: data.agentName,
                  agentNo: data.agentNo,
                  img: "assets/img/post-1.jpg",
                }
              );
              console.log("134Line houseName : " +this.$store.state.house.dealList);
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