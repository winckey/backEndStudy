import axios from "axios";// 설치했기떄문에 바로 import가능

/// axios에 default 속성을 주기위함
export default axios.create({// create import한 axios객체 생성 
  //생성해서 반환 해줘야함 export할때 그냥은 단순 객체(object) axios는 따로 생성해서
  baseURL: "http://localhost:8080",
  headers: {
    "Content-Type": "application/json"
  },

  withCredentials: true
});