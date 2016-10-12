/**
 * Created by anzer on 2016/10/12.
 */
import request from '../../api/base.js'
import {node_api_base as url_base,checkResult} from '../../api/base.js'
import {FETCH_NOTICE_LIST,LOAD_NOTICE_LIST } from '../mutation-types.js'
export  function loadNotice({dispatch},user_id){
  dispatch(LOAD_NOTICE_LIST,user_id);
}
export  function fetchNotice({dispatch},token,user_id){
  let url = `${url_base}/notice`;
  return request
    .get(url)
    .query({token})
    .then(checkResult)
    .then((data)=> {
      if(typeof data==="object"&&data.items&&data.items.length){
        dispatch(FETCH_NOTICE_LIST,data.items,user_id);
      }else{
        throw new Error('Data Error')
      }

    });
}
