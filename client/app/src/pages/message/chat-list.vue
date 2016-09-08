<template>
  <div>
    <!--小纸条头部-->
    <div class="chat-head"><span class="chat-font">《 {{newChat}}条新小纸条</span></div>

    <!--小纸条主体-->
    <div class="chat-content">
      <message-card v-for="chat in chats" :item="chat">
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
  export default{
    components: {
      MessageCard
    },
    data(){
      return {
        newChat: '3',
        chats: [
          {
            messType:'chat',
            mess: 'more',
            count: '2',
            mainMessage:'这个东西不能再降价了',
            message: '之前在树洞看到有转让小熊音乐会门票的微博，如果还有票请联系我，谢谢，好想去。655338'
          }, {

            mess: 'n-ew',
            messType:'chat',
            mainMessage:'你好，请问你什么时候过去',
            message: '明天的活动，风雨无阻'
          }, {

            mess: 'general',
            messType:'chat',
            mainMessage:'好的，麻烦了',
            message: '谁能帮小泽妹纸买个早餐？'
          }, {

            mess: 'general',
            messType:'chat',
            mainMessage:'好的，麻烦了',
            message: '之前在树洞看到有转让小熊音乐会门票的微博，如果还有票请联系我，谢谢，好想去。655338'
          }, {
            mess: 'general',
            messType:'chat',
            mainMessage:'好的，麻烦了',
            message: '有没有人可以帮忙拿个快递到F1？'
          }, {

            mess: 'general',
            messType:'chat',
            mainMessage:'好的，麻烦了',
            message: '谁能帮小泽妹纸买个早餐？'
          }]
      }
    }
  }
</script>
