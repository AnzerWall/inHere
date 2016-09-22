/**
 * Created by anzer on 2016/9/20.
 */
/**
 * Created by anzer on 2016/9/18.
 */
import request from '../../api/base.js'
import { PUSH_CHAT_DATA,CHANGE_CHAT_SEND_STATUS } from '../mutation-types.js'

import _ from 'lodash'

export  function pushUnreadChatList({dispatch},list,user_id){

    dispatch(PUSH_CHAT_DATA,list,user_id);

 // dispatch(PUSH_MANY_CHAT_DATA,list);
}
export  function recordSendingMessage({dispatch},doc,request_key){

  dispatch(CHANGE_CHAT_SEND_STATUS,0,request_key,doc);

  // dispatch(PUSH_MANY_CHAT_DATA,list);
}
export  function failSendingMessage({dispatch},request_key){

  dispatch(CHANGE_CHAT_SEND_STATUS,2,request_key);

  // dispatch(PUSH_MANY_CHAT_DATA,list);
}
export  function successSendingMessage({dispatch},request_key){

  dispatch(CHANGE_CHAT_SEND_STATUS,1,request_key);

  // dispatch(PUSH_MANY_CHAT_DATA,list);
}
