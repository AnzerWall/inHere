/**
 * Created by anzer on 2016/10/12.
 */
import request from '../../api/base.js'
import {java_api_base as url_base,checkResult} from '../../api/base.js'
import {LOAD_TOOL_LIST,FETCH_TOOL_LIST } from '../mutation-types.js'
export  function loadToolList({dispatch},user_id){
  dispatch(LOAD_TOOL_LIST,user_id);
}
export  function fetchToolList({dispatch},token,user_id){
  return request.get(`${url_base}/tools`)
    .query({token})
    .then(checkResult)
    .then(function(data){
      if(typeof data==="object"&&data.items&&data.items.length){
        dispatch(FETCH_TOOL_LIST,data.items,user_id);
      }else{
        throw new Error('Data Error')
      }
    })
}
