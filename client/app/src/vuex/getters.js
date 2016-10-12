/**
 * Created by anzer on 2016/9/18.
 */
import _ from 'lodash'
export function token(state){
  return state.user.token;
}
export function login_state(state){
  return state.user.login_state;
}
export function is_login(state){
  return state.user.is_login;
}
export function user_id(state){
  return state.user.login_state.user_id||"未知";
}

export function school(state){
  return state.user.login_state.school||"未知";
}
export function chat_list(state){
  return state.chat.chat_list;
}
export function total_chat_unread(state){

  return _.reduce(state.chat.chat_list,(sum,item)=>sum+=item.unread,0);
}
export function tool_list(state){
  return state.tool.list;
}
