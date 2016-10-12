/**
 * Created by anzer on 2016/10/12.
 */
import request from '../../api/base.js'
import {java_api_base as url_base,checkResult} from '../../api/base.js'
import {LOAD_TOOL_LIST } from '../mutation-types.js'
export  function loadToolList({dispatch},token){
  return request.get(`${url_base}/tools`)
    .query({token})
    .then(checkResult)
    .then(function(data){
      if(typeof data==="object"&&data.items&&data.items.length){
        dispatch(LOAD_TOOL_LIST,data.items);
      }else{
        throw new Error('Data Error')
      }
    })
}
