/**
 * Created by anzer on 2016/10/12.
 */
/**
 * Created by anzer on 2016/9/18.
 */


import {LOAD_TOOL_LIST,FETCH_TOOL_LIST} from '../mutation-types.js'
import Storage from '../../storage/storage'
// 该模块的初始状态
let state = {
  list:[]
};

// 相关的 mutations
let mutations = {
  [LOAD_TOOL_LIST](state,user_id){
    if (user_id) {
      try {
        state.list = JSON.parse(Storage.get(`tool_list|${user_id}`) || '[]');
        console.log("[LocalStorage] load tool list data");
      } catch (e) {
        console.error(e);
      }

    }
  },
  [FETCH_TOOL_LIST](state,list,user_id){
    state.list=list||[];
    if (user_id) {
      Storage.set(`tool_list|${user_id}`, JSON.stringify(state.list));
    }
  }
};

export default {
  state,
  mutations
}
