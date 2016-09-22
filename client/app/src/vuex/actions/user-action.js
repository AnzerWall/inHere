/**
 * Created by anzer on 2016/9/18.
 */
import request from '../../api/base.js'
import {java_api_base as url_base,checkResult} from '../../api/base.js'
import { CHANGE_LOGIN_STATE,LOAD_CHAT_DATA,LOAD_LOGIN_STATE } from '../mutation-types.js'


export  function login ({dispatch},username,password) {

  return request.post(`${url_base}/login`)
    .send({
      user_id:username,
      passwd:password
    })
    .then(checkResult)
    .then(function(data){
     // console.log(data);
        if(typeof data==="object"&&typeof data.token==='string' &&data.token){
          dispatch(CHANGE_LOGIN_STATE,data);
          dispatch(LOAD_CHAT_DATA,data.user_id)
        }else{
          throw new Error('Data Error')
        }
    })

}
export function loadLocalLoginState({state,dispatch}){

  dispatch(LOAD_LOGIN_STATE);
  dispatch(LOAD_CHAT_DATA,state.user.login_state.user_id);
}
