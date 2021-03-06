import Vuex from "vuex";
import Vue from "vue";

Vue.use(Vuex);

import http from "@/common/axios.js";
import router from '@/router/routers.js'

export default new Vuex.Store({
  state: {
    // login, NavBar
    login: {
      isLogin: false,
      userId: 'test',
      userPassword: 'test',
      userName: '',
      userProfileImageUrl: '',
    },

    
    user: {


      userId: "",
      userName: "",
      userProfileImageUrl: "",
      userPhone: "",
      userPosition: "",
      userEmail: "",

      userFavoriteHouseList: [],
      userFavoriteAgentList: [],
    },
    //
    board: {
      // list
      list: [],
      limit: 10,
      offset: 0,
      searchWord: "",

      // pagination
      listRowCount: 10,
      pageLinkCount: 10,
      currentPageIndex: 1,

      totalListItemCount: 0,

      // detail, update, delete
      boardId: 0,
      title: "",
      content: "",
      userName: "",
      regDt: {},
      readCount: 0,
      fileList: [],
      sameUser: false,
    },


    house: {
      // list
      list: [],
      limit: 10,
      offset: 0,
      searchWord: "",

      houseNo: 0,
      area: "",
      code: "",
      dong: "",
      houseName: "",
      buildYear: "",
      lat: "",
      lng: "",
      dealList: [],
      agentName: "",
      agentNo: 0,
      img : "",

    },

    agent: {
      list: [],
      
      agentNo: 0,
      agentName: "",
      agentRate: "",
      agentPhone : "",
      agentEmail : "",
      agentProfileImageUrl : "",
      agentDesc : "",
      agentHouseList: [],


  
    },

  },
  // state 상태를 변경하는 유일한 방법
  mutations: {
    SET_LOGIN(state, payload) {
      state.login.isLogin = payload.isLogin;
      state.login.userName = payload.userName;
      state.login.userPassword = payload.userPassword;
      state.login.userId = payload.userId;
      state.login.userProfileImageUrl = payload.userProfileImageUrl;
      
      state.user.userNo = payload.userNo;
      state.user.userId = payload.userId;
      state.user.userName = payload.userName;
      state.user.userProfileImageUrl = payload.userProfileImageUrl;
      state.user.userPhone = payload.userPhone;
      state.user.userPosition = payload.userPosition;
      state.user.userEmail = payload.userEmail;


    },
    
    SET_LOGOUT(state) {
      state.login.isLogin = false;
      state.login.userName = "";
      state.login.userProfileImageUrl = "";
      state.login.userId = "";
      state.login.userPassword = "";


      state.user.userNo = "";
      state.user.userId = "";
      state.user.userName = "";
      state.user.userProfileImageUrl = "";
      state.user.userPhone = "";
      state.user.userPosition = "";
      state.user.userEmail = "";
    },

    SET_BOARD_LIST(state, list) {
      state.board.list = list;

    },

    SET_AGENT_LIST(state, list) {
      state.agent.list = list;
    },
    SET_FAVORITE_AGENT_LIST(state, list) {
      state.user.userFavoriteAgentList = list;
    },
    SET_AGENT_NO(state, agentNo) {
      state.agent.agentNo = agentNo;
    },
    SET_AGENT_DETAIL(state, payload) {
      state.agent.agentName = payload.agentName;
      state.agent.agentNo = payload.agentNo;
      state.agent.agentEmail = payload.agentEmail;
      state.agent.agentPhone = payload.agentPhone;
      state.agent.agentRate = payload.agentRate;
      state.agent.agentDesc = payload.agentDesc;
      state.agent.agentHouseList = payload.agentHouseList;
      state.agent.agentProfileImageUrl = payload.agentProfileImageUrl;
    },

    SET_BOARD_TOTAL_LIST_ITEM_COUNT(state, count) {
      state.board.totalListItemCount = count;
    },
    SET_HOUSE_LIST(state, list) {
      state.house.list = list;
      
    },

    SET_HOUSE_TOTAL_LIST_ITEM_COUNT(state, count) {
      state.house.totalListItemCount = count;
    },

    SET_BOARD_MOVE_PAGE(state, pageIndex) {
      state.board.offset = (pageIndex - 1) * state.board.listRowCount;
      state.board.currentPageIndex = pageIndex;
    },
    SET_HOUSE_MOVE_PAGE(state, pageIndex) {
      state.house.offset = (pageIndex - 1) * state.house.listRowCount;
      state.house.currentPageIndex = pageIndex;
    },
    // mutateSetBoardBoardId(state, boardId){
    //   state.board.boardId = boardId
    // },

    SET_BOARD_DETAIL(state, payload) {
      state.board.boardId = payload.boardId;
      state.board.title = payload.title;
      state.board.content = payload.content;
      state.board.userName = payload.userName;
      state.board.regDt = payload.regDt;
      state.board.fileList = payload.fileList;
      state.board.sameUser = payload.sameUser;
    },

    SET_HOUSE_DETAIL(state, payload) {
      state.house.houseNo = payload.houseNo;
      state.house.area = payload.area;
      state.house.code = payload.code;
      state.house.dong = payload.dong;
      state.house.houseName = payload.houseName;
      state.house.buildYear = payload.buildYear;
      state.house.lat = payload.lat;
      state.house.lng = payload.lng;
      state.house.dealList = payload.dealList;
      state.agent.agentName = payload.agentName;
      state.agent.agentNo = payload.agentNo;
      state.agent.agentEmail = payload.agentEmail;
      state.agent.agentPhone = payload.agentPhone;
      state.agent.agentRate = payload.agentRate;
      state.agent.agentDesc = payload.agentDesc;
      state.agent.agentProfileImageUrl = payload.agentProfileImageUrl;
      state.house.img = payload.img;
    },
    // for UpdateModal title v-modal
    SET_BOARD_TITLE(state, title) {
      state.board.title = title;
    },

    // mutateSetBoardInit(state){
    //   state.board.boardId = 0;
    //   state.board.title = '';
    //   state.board.content = '';
    //   state.board.userName = '';
    //   state.board.regDt = {};
    //   state.board.readCount = 0;
    //   state.board.fileList = [];
    //   state.board.sameUser = false;
    // },
  },
  // for async method
  actions: {
    boardList(context) {
      http
        .get("/boards", {
          // get query string
          params: {
            limit: this.state.board.limit,
            offset: this.state.board.offset,
            searchWord: this.state.board.searchWord,
          },
        })
        .then(({
          data
        }) => {
          console.log("BoardMainVue: data : ");
          console.log(data);
          if (data.result == "login") {
            router.push("/login");
          } else {
            context.commit("SET_BOARD_LIST", data.list);
            context.commit("SET_BOARD_TOTAL_LIST_ITEM_COUNT", data.count);

          }
        });
    },
    // setMessage(context, message){
    //   context.commit('mutateSetMessage', message)
    // },

    houseList(context) {
      http
        .get("/houses", {
          // get query string
          params: {
            searchWord: this.state.house.searchWord,
          },
        })
        .then(({
          data
        }) => {
          console.log("house: data : ");
          console.log(data);
          if (data.result == "login") {
            router.push("/login");
          } else {
            context.commit("SET_HOUSE_LIST", data.list);
          }
        });
    },

    houseListWithFilter(context,payload) {

     
      http
        .get("/houses/"+payload.min+"/"+payload.max, {
          // get query string
          params: {
            searchWord: this.state.house.searchWord,
          },
        })
        .then(({
          data
        }) => {
          console.log("house: data : ");
          console.log(data);
          if (data.result == "login") {
            router.push("/login");
          } else {
            context.commit("SET_HOUSE_LIST", data.list);
          }
        });
    },

    agentList(context) {
      http
        .get("/agents/list/"+this.state.user.userNo, {
          // get query string
          params: {
          },
        })
        .then(({
          data
        }) => {
          console.log("BoardMainVue: data : ");
          console.log(data);
          if (data.result == "login") {
            router.push("/login");
          } else {
            context.commit("SET_AGENT_LIST", data.list);
          }
        });
    },

    
    
    // setMessage(context, message){
    //   context.commit('mutateSetMessage', message)
    // },
  },
  getters: {
    isLoginGetters: function (state) {
      return state.login.isLogin;
    },

    getBoardList: function (state) {
      return state.board.list;
    },
    // pagination
    getPageCount: function (state) {
      return Math.ceil(
        state.board.totalListItemCount / state.board.listRowCount
      );
    },
    getStartPageIndex: function (state) {
      if (state.board.currentPageIndex % state.board.pageLinkCount == 0) {
        //10, 20...맨마지막
        return (
          (state.board.currentPageIndex / state.board.pageLinkCount - 1) *
          state.board.pageLinkCount +
          1
        );
      } else {
        return (
          Math.floor(state.board.currentPageIndex / state.board.pageLinkCount) *
          state.board.pageLinkCount +
          1
        );
      }
    },
    getEndPageIndex: function (state, getters) {
      let ret = 0;
      if (state.board.currentPageIndex % state.board.pageLinkCount == 0) {
        //10, 20...맨마지막
        ret =
          (state.board.currentPageIndex / state.board.pageLinkCount - 1) *
          state.board.pageLinkCount +
          state.board.pageLinkCount;
      } else {
        ret =
          Math.floor(state.board.currentPageIndex / state.board.pageLinkCount) *
          state.board.pageLinkCount +
          state.board.pageLinkCount;
      }
      // 위 오류나는 코드를 아래와 같이 비교해서 처리
      return ret > getters.getPageCount ? getters.getPageCount : ret;
    },
    getPrev: function (state) {
      if (state.board.currentPageIndex <= state.board.pageLinkCount) {
        return false;
      } else {
        return true;
      }
    },
    getNext: function(state, getters){
      if( ( Math.floor( getters.getPageCount / state.board.pageLinkCount ) * state.board.pageLinkCount ) < state.board.currentPageIndex){
        return false;
      }else{
        return true;
      }
    }
  },
});
