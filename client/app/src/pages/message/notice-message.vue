<template>
    <div>
<div>
  <div class="notice-message">
      <span class="notice-title">
         <message-vip class="photo"></message-vip>
        <!--加上span制造空隙-->
        <span>{{item.title}}</span>
        </span>
    <span class="close" @click="back()"><i class="fa fa-close fa-lg"></i></span>
  </div>
    <div class="message-div">{{item.content}}</div>

</div>
    </div>
</template>
<style scoped>
  .notice-message{
    padding: 20px 20px 20px 20px;
    border-bottom: 1px solid rgba(213, 213, 213, .5);
    z-index: 2;

  }
  .notice-title{
    position: relative;
    margin: 24px;
    font-weight: bold;
  }
  .photo {
    padding: 0;
    margin: 0;
    position: absolute;
    right: 100%;
    margin-right: 8px;
    top: 50%;
    transform: translateY(-50%)

  }
.message-div {
  padding: 20px 20px 20px 20px;
  font-size: large;
  line-height: 1.5;
}
  .close{
 float: right;
  }
</style>
<script>
  import MessageVip from 'svg/main/message/MessageVip.vue'
  export default{
    methods:{
      back(){
        window.history.back()
      }
    },
    data(){
      return {
      }
    },
    props: {
      item: {
        type: Object
      },
      type: {
        type: String
      }
    },
    components: {
      MessageVip
    }
  }
</script>
