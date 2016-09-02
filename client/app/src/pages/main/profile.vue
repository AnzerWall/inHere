<template>

  <!--头部-->
  <div class="head">
    <chat-icon class="chatdiv"></chat-icon>
    <div class="newmessage">你又又有{{newMessageCount}}条悄悄话 》</div>
  </div>
  <!--中部-->
  <div class="content">
    <!--官方通知-->
    <div class="noticediv" v-for="item in items">
      <div class="noticeh">
        <message-vip class="mid"></message-vip>
          <span class="ntittle mid">
            {{item.tittle}}
            </span>
      </div>
      <div class="noticemessage">{{item.message}}</div>
    </div>

    <!--我的评论-->
    <div class="mydiv" v-for="tran in trans">
      <div class="myh">
        <div class="round mid" v-if="tran.color" style="background-color: {{tran.color}}"></div>
        <div class="mtittle mid" style="color: {{tran.color}}">{{tran.count}}</div>
      </div>
      <div class="mymessage">{{tran.text}}</div>
    </div>

  </div>

</template>
<style>
  div, span {
    margin: 0;
    padding: 0;
  }

  .mid {
    display: inline-block;
  }

  .head {
    width: 100%;
    height: 60px;
    border-bottom: solid 1px #CCCCCC;
    line-height: 60px;
    position: fixed;
    background-color: white;
  }

  .content {
    padding: 60px 0 10px 0;
    z-index: 2;
  }

  .chatdiv {
    vertical-align: middle;
    margin-left: 10px;
  }

  .newmessage {
    color: white;
    float: right;
    background-color: #ffc106;
    height: 36px;
    width: 200px;
    border-radius: 50px;
    line-height: 36px;
    margin-top: 12px;
    margin-right: 14px;
    text-align: center;
  }

  .ntittle, .mtittle {

    font-weight: bold;
  }

  .noticediv, .mydiv {

    padding: 20px 20px 20px 20px;
    border-bottom: 1px solid #cccccc;
  }

  .round {
    width:12px;
    height: 12px;
    border-radius: 12px;
  }

  .noticemessage,.mymessage{
    overflow : hidden;
    text-overflow: ellipsis;
    line-height: 20px; ;
 height: 40px;
  }
</style>
<script>
  import ChatIcon from'svg/main/message/Chat.vue'
  import MessageVip from'svg/main/message/MessageVip.vue'


  export default{
    components: {
      ChatIcon,
      MessageVip,
    },
    data(){
      return {
        newMessageCount: '66',
        items: [
          {
            tittle: '肇庆学院',
            message: '【重磅| 学生宿舍第一批空调已抵达！很好！这很夏天！】'
          },
          {
            tittle: '天翼校园一卡通',
            message: '温馨提示：国庆期间天翼一卡通服务中心上班时间为2,4,6号，8号后正常上班。由于校园网上学期办理的包学期有效期到2013年9月30日，为了避免10月1日断网，建议大四需要充值校园网的尽量在国庆放假前前往一卡通服务中心的前台充值。请周知！（30元/月，尽量自备零钱哦亲）'
          }
        ],
        trans: [{
          count: '23条新评论',
          color: '#FFC107',
          text: '之前在树洞看到有转让小熊音乐会门票的微博，如果还有票请联系我，谢谢，好想去。655338'
        }, {
          count: '2人评论了你',
          color: '#2196F3',
          text: '有没有人可以帮忙拿个快递到F1？'
        }, {
          count: '1条新评论',
          text: '谁能帮小泽妹纸买个早餐？'
        }, {
          count: '23条新评论',
          text: '之前在树洞看到有转让小熊音乐会门票的微博，如果还有票请联系我，谢谢，好想去。655338'
        }, {
          count: '2条新评论',
          text: '有没有人可以帮忙拿个快递到F1？'
        }, {
          count: '1条新评论',
          text: '谁能帮小泽妹纸买个早餐？'
        }]
      }
    }
  }
</script>
