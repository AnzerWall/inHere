<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<style>
  body {
    user-select: none;
    font-size: 15px;
    font-family: ".PingFang-SC-Regular", "Helvetica Neue", Helvetica, Arial, "Microsoft Yahei", "Hiragino Sans GB", "Heiti SC", "WenQuanYi Micro Hei", sans-serif;
    /*font-family: -apple-system, "Helvetica Neue", "Arial", "PingFang SC", "Hiragino Sans GB", "STHeiti", "Microsoft YaHei", "Microsoft JhengHei", "Source Han Sans SC", "Noto Sans CJK SC", "Source Han Sans CN", "Noto Sans SC", "Source Han Sans TC", "Noto Sans CJK TC", "WenQuanYi Micro Hei", SimSun, sans-serif;*/
  }

  *::-webkit-scrollbar,
  *::-webkit-scrollbar-track,
  *::-webkit-scrollbar-thumb {
    width: 0;
    border-width: 0;
    height: 0;
  }

  input {
    outline:0;
    border: none;
  }

  /*html,body,#app{*/
  /*height: 100%;*/
  /*}*/
</style>
<script>

  import store from './vuex/store.js'
  import {loadLocalLoginState} from './vuex/actions/user-action.js'

  import {user_id} from './vuex/getters.js'
  import websocket from './util/websocket_helper.js'
  import {pushUnreadChatList} from './vuex/actions/chat-action.js'
  export default{
    store,
    sockets: {
      chat(data){
        console.log(data)
        this.pushUnreadChatList(data,this.user_id);
      },
      connect () {
        console.log('websocket connected')
      },
      end_request(data){
        websocket.endRequest(data.request_key,data.data);
      }
    },
    vuex:{
      actions:{
        loadLocalLoginState,
        pushUnreadChatList
      },
      getters:{
        user_id
      }
    },
    ready(){
      this.loadLocalLoginState();
    }
  }


</script>
