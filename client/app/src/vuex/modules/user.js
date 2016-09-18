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
//import { RECEIVE_PRODUCTS, ADD_TO_CART } from '../mutation-types.js'
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
let login_state_data=Storage.get("login_state");
if(login_state_data){
  state.login_state=JSON.parse(login_state_data);
  state.token=  state.login_state.token;
  state.is_login=(typeof state.token==="string")&&state.token!="";
}

// 相关的 mutations
let mutations = {

};

export default {
  state,
  mutations
}
