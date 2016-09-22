<template>
  <div >
    {{data}}
    <button @click="auth" >auth</button>
    <button @click="chat" >chat</button>
    <button @click="leave" >leave</button>
    <button @click="get" >get</button>
    <div v-for="item in chat_list">{{$index}}  {{item.items.length}}</div>
  </div>

</template>
<style scoped>


</style>
<script type="text/ecmascript-6">
  //import fakeComment from '../api/fake/comment.js'
  import {token,login_state,is_login,chat_list,user_id} from '../vuex/getters.js'
  import websocket from '../util/websocket_helper.js'
  import {pushUnreadChatList} from '../vuex/actions/chat-action.js'
  export default{
    sockets: {
//      chat(data){
//        console.log(data)
//      },
      unread_chat_list(data){

      },
      test(data){
        console.log( data);
      },
      chat(data){
        console.log(data)
        this.pushUnreadChatList(data, this.user_id);
      }
    },
    vuex: {
      getters: {
        login_state,
        token,
        chat_list,
        is_login,
        user_id
      },
      actions:{
        pushUnreadChatList
      }
    },
    data(){
      return {
        data:"233"
      }
    },
    methods:{
      auth(){
        console.log(this.token);
        this.$socket.emit("auth",{token:this.token})
      },
      leave(){
       // this.$socket.emit("leave")
        console.log(this.chat_list);
      },
      chat(){
        this.$socket.emit("chat",{
          token:this.token,
          request_key:"123",
          module_type:1,
          module_id:1,
          rev_user:"LLB",
          content_type:0,
          content:"测试消息"

        })
      },
      test(){
//        console.log(this.$refs);
        this.$socket.emit("test",{token:this.token})
      },
      get(){
        websocket.startRequest(this.$socket,"get_unread_chat",{token:this.token})
          .then((data)=>{
            this.pushUnreadChatList(data,this.user_id);
           // console.log('success');
            console.log(data);
        });


      }
    },
    ready(){

    }
  }
</script>
