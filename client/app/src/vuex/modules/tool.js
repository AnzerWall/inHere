/**
 * Created by anzer on 2016/10/12.
 */
/**
 * Created by anzer on 2016/9/18.
 */


import {LOAD_TOOL_LIST} from '../mutation-types.js'
import Storage from '../../storage/storage'
// 该模块的初始状态
let state = {
  list:[]
};

// 相关的 mutations
let mutations = {
  [LOAD_TOOL_LIST](state,list){
    state.list=list||[];
  }
};

export default {
  state,
  mutations
}
