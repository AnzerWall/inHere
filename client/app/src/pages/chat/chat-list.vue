<template>
  <div>
    <!--小纸条头部-->
    <div class="chat-head" @click="back"><span class="chat-font">《 {{total_chat_unread}}条新小纸条</span></div>

    <!--小纸条主体-->
    <div class="chat-content">
      <message-card v-for="item in chat_list" :item="item" type="chat" @click="goChatPage(item)">
      </message-card>
    </div>
  </div>


</template>
<style scoped>
.chat-head{
  width: 100%;
  height: 60px;
  background-color: #FFC107;
  display: flex;
  align-items:center;
}

  .chat-font{
    font-weight: bold;
    color: white;
    margin-left: 3px;
  }

  .chat-content{
    flex-shrink: 1;
    height: 100%;
    position: relative;
  }

</style>
<script>
  import MessageCard from 'components/message-card/message-card.vue'
  import {chat_list,total_chat_unread} from '../../vuex/getters.js'

  export default{
    components: {
      MessageCard
    },
    methods:{
      back(){
        window.history.back();
      },
      goChatPage(item){

        this.$router.go({
          path:"/chat",
          query:{
            module_id:item.module_id,
            module_type:item.module_type,
            chat_user:item.chat_user
          }
        })
      }
    },
    vuex:{
      getters:{
        chat_list,
        total_chat_unread
      }
    },
    data(){
      return {

      }
    }
  }
</script>
