/**
 * Created by anzer on 2016/9/20.
 */
import Storage from '../../storage/storage'
import { LOAD_CHAT_DATA,PUSH_CHAT_DATA,CHANGE_CHAT_SEND_STATUS } from '../mutation-types.js'
import _ from 'lodash'
let state = {
  chat_list: [],
  send_chat_list:[],
//  fail_chat_list:[]
};
function getDataKey(item, user_id) {
  if (item.send_user == user_id) {
    return `${item.module_type}|${item.module_id}|${item.rev_user}`
  } else {
    return `${item.module_type}|${item.module_id}|${item.send_user}`
  }
}
function getChatUser(item, user_id) {
  if (item.send_user == user_id) {
    return item.rev_user;
  } else {
    return item.send_user;
  }
}
let mutations = {
  [LOAD_CHAT_DATA](state, user_id){

    if (user_id) {

      try {
        state.chat_list = JSON.parse(Storage.get(`chat_list|${user_id}`) || '[]');
        console.log("[LocalStorage] load chat data");
      } catch (e) {
        console.error(e);
      }

    }
  },
  [CHANGE_CHAT_SEND_STATUS](state,status,request_key,doc ){
    if(status===0){
      let data= _.cloneDeep(doc);
      data.id=request_key;
      data.create_time=Date.now();
      data.status=0;
      state.send_chat_list.push(data);
    }else if(status===1){
      let index= _.findIndex( state.send_chat_list,(item)=>item.id===request_key&&item.status===0);
      console.log(index);
      if(index!=-1){
        state.send_chat_list.splice(index, 1);

      }
    }else if(status===2){
      let index= _.findIndex( state.send_chat_list,(item)=>item.id===request_key&&item.status===0);
      if(index!=-1){
        state.send_chat_list[index].status=2;
        //let doc=
        //state.fail_chat_list.push(doc);
        //state.send_chat_list.$remove(index);

      }
    }else{
      console.error(CHANGE_CHAT_SEND_STATUS,arguments);
    }

  },
  [PUSH_CHAT_DATA](state, data, user_id){

    if (user_id && _.isArray(data)) {
      if (data.length > 0) {
        let list = data.filter(item=>item.rev_user == user_id || item.send_user == user_id);//过滤掉对话双方都不是自己的数据
        let group = _.groupBy(list, (item)=>getDataKey(item, user_id));//按照对话的对方以及模块类型和模块id生成key进行数据分组

        for (let k in group) {
          let v = group[k];
          if (!_.isArray(v) || v.length === 0)continue;//忽略掉空数组数据，虽然不可能出现这种数据
          let key = getDataKey(v[0], user_id);//按照对话的对方以及模块类型和模块id生成key
          let module_type = v[0].module_type;
          let module_id = v[0].module_id;
          let chat_user = getChatUser(v[0],user_id);//获取对话中的对方的user_id
          let index = _.findIndex(state.chat_list, (item)=>item.chat_user == chat_user && item.module_type == module_type && item.module_id == module_id);
          let items =(index === -1 ? {} : state.chat_list[index]).items || [];
          items=_.uniqBy(items.concat(v), "id");
          items.sort((a, b)=>( a.create_time > b.create_time));
          //let items = _.uniqBy(((chat_data[key] || {}).items || []).concat(v), "id");
          let ret = {
            module_id,
            module_type,
            chat_user,
            items: items
          };
          ret.unread = _.reduce(items, (sum, item)=>sum += item.read === 0 ? 1 : 0, 0);
          ret.title = items[0].title;
          ret.content = items[0].content;
          if (index === -1) {
            state.chat_list.push(ret);
          } else {
            state.chat_list.$set(index, ret);
          }
        }

        Storage.set(`chat_list|${user_id}`, JSON.stringify(state.chat_list));
      }
    } else if (user_id && _.isPlainObject(data)) {

      let key = getDataKey(data, user_id);
      let module_type = data.module_type;
      let module_id = data.module_id;
      let chat_user = getChatUser(data,user_id);//获取对话中的对方的user_id
      let index = _.findIndex(state.chat_list, (item)=>item.chat_user == chat_user && item.module_type == module_type && item.module_id == module_id);

      let items =state.chat_list[index];

      items=((index === -1 ? {} : items).items || []);
      items.push(data);
      items=_.uniqBy(items, "id");
      let ret = {
        module_id,
        module_type,
        chat_user,
        items: items
      };

      ret.unread = _.reduce(items, (sum, item)=>sum += item.unread === 0 ? 1 : 0, 0);
      ret.title = items[0].title;
      ret.content = items[0].content;

      if (index === -1) {
        state.chat_list.push(ret);
      } else {
        state.chat_list.$set(index, ret);
      }
      Storage.set(`chat_list|${user_id}`, JSON.stringify(state.chat_list));

    } else {
      console.error("PUSH_CHAT_DATA error")
    }
  }
};

export default {
  state,
  mutations
}
