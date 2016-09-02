<template>
    <div class="message-wrapper">

      <div class="message-head-wrapper">
        <div class="message-head">
          <div class="icon">
            <chat-icon></chat-icon>
          </div>
          <div class="notes">这是右边</div>
        </div>
      </div>
      <div class="message-content">

      </div>
    </div>
</template>
<style scoped>
  .message-wrapper{
    display: flex;
    height: 100%;
    width: 100%;
    flex-direction: column;
  }
  .message-wrapper .message-head-wrapper{
      flex-shrink: 0;
      height: 60px;
      width: 100%;
      position: relative;

  }
  .message-wrapper .message-head-wrapper .message-head{
    top: 0;
    left: 0;
    height: 60px;
    width: 100%;
    border-bottom: solid 1px #d5d5d5;
    align-items: center;
    justify-content: space-between;
    display: flex;
  }
  .message-wrapper .message-head-wrapper .message-head .icon{
    margin-left: 15px;
  }
  .message-wrapper .message-head-wrapper .message-head .notes{
    margin-right: 15px;
  }
</style>
<script>
  import ChatIcon from'svg/main/message/Chat.vue'

    export default{
        components:{
          'chat-icon':ChatIcon
        }
    }
</script>
