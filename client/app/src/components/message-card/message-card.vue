<template>
  <div>
    <!--官方通知-->
    <div class="notice-div" v-if="type==='notice'">
      <span class="n-title  bold">
         <message-vip class="photo"></message-vip>
        <!--加上span制造空隙-->
        <span>{{item.title}}</span>
        </span>
      <div class="n-message padding1">{{item.content}}</div>
    </div>
    <!--&lt;!&ndash;我的评论&ndash;&gt;-->
    <!--<div class="my-div" v-if="item.tag==='my'">-->
    <!--<div class="m-title bold " :style="{color: item.color}" v-if="item.mess==='n-ew'||item.mess==='general'">-->
    <!--<div class="photo round " v-show="item.color" :style="{'background-color': item.color}"-->
    <!--&gt;</div>-->
    <!--{{item.count}}条新评论-->
    <!--</div>-->

    <!--<div class="m-title  bold" :style="{color: item.color}" v-if="item.mess==='yet'">-->
    <!--<div class="photo round " v-show="item.color" :style="{'background-color': item.color}"-->
    <!--&gt;</div>-->
    <!--{{item.count}}人评论了你-->
    <!--</div>-->
    <!--<div class="m-message padding2">{{item.message}}</div>-->
    <!--</div>-->



    <!--我的小纸条-->
    <div class="my-div" v-if="type==='chat'">
      <!--更多小纸条-->
      <!--<div class="m-title bold " v-if="item.mess==='more'">-->
        <!--<div class="photo round  chat-color"></div>-->
        <!--({{item.count}}){{item.mainMessage}}-->
      <!--</div>-->
      <!--新的小纸条-->
      <!--<div class="m-title bold " v-if="item.mess==='n-ew'">-->
        <!--<div class="photo round  chat-color"></div>-->
        <!--{{item.mainMessage}}-->
      <!--</div>-->

      <!--阅读过的小纸条（默认）-->
      <div class="m-title bold " :class="{'chat-gray':item.unread===0}" >
        <div class="photo round  chat-color" v-if="item.unread!=0"></div>
        {{item.title}}
      </div>

      <div class="m-message padding2 chat-gray">{{item.content}}</div>
    </div>

  </div>
</template>
<style src="./message-card.css" scoped>


</style>
<script>
  import MessageVip from 'svg/main/message/MessageVip.vue'
  export default{
    data(){
      return {}
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
