/**
 * Created by anzer on 2016/9/18.
 */
/*
 {
 "code": 200,
 "status": "正常",
 "data": {
 "user_id": "anzerwall",
 "user_name": "",
 "school_id": 1,
 "school": "肇庆学院",
 "role_id": 1,
 "token": "8d80c244b51bf4ccb8b79abf19803fe2e6c2f69c7c044fcbdc1040b705f7618e"
 }
 }
 */
import { CHANGE_LOGIN_STATE,LOAD_LOGIN_STATE } from '../mutation-types.js'
import Storage from '../../storage/storage'
// 该模块的初始状态
let state = {
  login_state:{
    user_id: "",
    user_name: "",
    school_id: 0,
    school: "",
    role_id: 0,
    token: ""
  },
  token:"",
  is_login:false

};

// 相关的 mutations
let mutations = {
  [CHANGE_LOGIN_STATE](state,login_state){
    state.login_state=login_state;
    state.token=  login_state.token;
    state.is_login=(typeof state.token==="string")&&state.token!="";
    Storage.set("login_state",JSON.stringify(login_state))
  },
  [LOAD_LOGIN_STATE](state){

    let login_state_data=Storage.get("login_state");

    if(login_state_data){
      try{
        let login_state=JSON.parse(login_state_data);
        state.login_state=login_state;
        state.token=  login_state.token;
        state.is_login=(typeof state.token==="string")&&state.token!="";
        console.log("[LocalStorage] load login data");
       // console.log(login_state);
      }catch (e){
        console.error(e);
      }
    }
  }
};

export default {
  state,
  mutations
}
