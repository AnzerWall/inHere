<template>
  <div class="profile-wrapper">

    <div class="profile-head-wrapper">
      <div class="profile-head">
        <div class="text">
          小泽/肇庆学院
        </div>
        <div class="icon">这是右边的图标</div>
      </div>
    </div>
    <div class="profile-content">

    </div>
  </div>
</template>
<style>
  .profile-wrapper{
    display: flex;
    height: 100%;
    width: 100%;
    flex-direction: column;
  }
  .profile-wrapper .profile-head-wrapper{
    flex-shrink: 0;
    height: 60px;
    width: 100%;
    position: relative;

  }
  .profile-wrapper .profile-head-wrapper .profile-head{
    top: 0;
    left: 0;
    height: 60px;
    width: 100%;
    position: fixed;
    border-bottom: solid 1px #d5d5d5;
    align-items: center;
    justify-content: space-between;
    display: flex;
  }
  .profile-wrapper .profile-head-wrapper .profile-head .text{
    margin-left: 15px;
  }
  .profile-wrapper .profile-head-wrapper .profile-head .icon{
    margin-right: 15px;
  }
</style>
<script>

  export default{

  }
</script>




<!--<template>-->

  <!--&lt;!&ndash;头部&ndash;&gt;-->
  <!--<div class="head">-->
    <!--<chat-icon class="chatdiv"></chat-icon>-->
    <!--<div class="newprofile">你又又有{{newMessageCount}}条悄悄话 》</div>-->
  <!--</div>-->
  <!--&lt;!&ndash;中部&ndash;&gt;-->
  <!--<div class="content">-->
    <!--&lt;!&ndash;官方通知&ndash;&gt;-->
    <!--<div class="noticediv" v-for="item in items">-->
      <!--<div class="noticeh">-->
        <!--<message-vip class="mid"></message-vip>-->
          <!--<span class="ntittle mid">-->
            <!--{{item.tittle}}-->
            <!--</span>-->
      <!--</div>-->
      <!--<div class="noticemessage">{{item.message}}</div>-->
    <!--</div>-->

    <!--&lt;!&ndash;我的评论&ndash;&gt;-->
    <!--<div class="mydiv" v-for="tran in trans">-->
      <!--<div class="myh">-->
        <!--<div class="round mid" v-show="tran.color" style="background-color: {{tran.color}}"></div>-->
        <!--<div class="mtittle mid" style="color: {{tran.color}}">{{tran.count}}</div>-->
      <!--</div>-->
      <!--<div class="mymessage">{{tran.text}}</div>-->
    <!--</div>-->

  <!--</div>-->

<!--</template>-->
<!--<style>-->
  <!--div, span {-->
    <!--margin: 0;-->
    <!--padding: 0;-->
  <!--}-->

  <!--.mid {-->
    <!--display: inline-block;-->
  <!--}-->

  <!--.head {-->
    <!--width: 100%;-->
    <!--height: 60px;-->
    <!--border-bottom: solid 1px #CCCCCC;-->
    <!--line-height: 60px;-->
    <!--position: fixed;-->
    <!--background-color: white;-->
  <!--}-->

  <!--.content {-->
    <!--padding: 60px 0 10px 0;-->
    <!--z-index: 2;-->
  <!--}-->

  <!--.chatdiv {-->
    <!--vertical-align: middle;-->
    <!--margin-left: 10px;-->
  <!--}-->

  <!--.newmessage {-->
    <!--color: white;-->
    <!--float: right;-->
    <!--background-color: #ffc106;-->
    <!--height: 36px;-->
    <!--width: 200px;-->
    <!--border-radius: 50px;-->
    <!--line-height: 36px;-->
    <!--margin-top: 12px;-->
    <!--margin-right: 14px;-->
    <!--text-align: center;-->
  <!--}-->

  <!--.ntittle, .mtittle {-->

    <!--font-weight: bold;-->
  <!--}-->

  <!--.noticediv{-->

    <!--padding: 20px 20px 20px 20px;-->
    <!--border-bottom: 1px solid #cccccc;-->
  <!--}-->

  <!--.mydiv{-->

    <!--padding: 20px 20px 20px 20px;-->
    <!--border-bottom: 1px solid #cccccc;-->
  <!--}-->

  <!--.round {-->
    <!--width:12px;-->
    <!--height: 12px;-->
    <!--border-radius: 12px;-->
  <!--}-->

  <!--.noticemessage{-->
    <!--overflow : hidden;-->
    <!--text-overflow: ellipsis;-->
    <!--line-height: 20px; ;-->
 <!--height: 40px;-->
  <!--}-->

  <!--.mymessage{-->
    <!--overflow : hidden;-->
    <!--text-overflow: ellipsis;-->
    <!--line-height: 20px; ;-->
    <!--height: 40px;-->
    <!--margin-left: 15px;-->
  <!--}-->
<!--</style>-->
<!--<script>-->
  <!--import ChatIcon from'svg/main/message/Chat.vue'-->
  <!--import MessageVip from'svg/main/message/MessageVip.vue'-->


  <!--export default{-->
    <!--components: {-->
      <!--ChatIcon,-->
      <!--MessageVip,-->
    <!--},-->
    <!--data(){-->
      <!--return {-->
        <!--newMessageCount: '66',-->
        <!--items: [-->
          <!--{-->
            <!--tittle: '肇庆学院',-->
            <!--message: '【重磅| 学生宿舍第一批空调已抵达！很好！这很夏天！】'-->
          <!--},-->
          <!--{-->
            <!--tittle: '天翼校园一卡通',-->
            <!--message: '温馨提示：国庆期间天翼一卡通服务中心上班时间为2,4,6号，8号后正常上班。由于校园网上学期办理的包学期有效期到2013年9月30日，为了避免10月1日断网，建议大四需要充值校园网的尽量在国庆放假前前往一卡通服务中心的前台充值。请周知！（30元/月，尽量自备零钱哦亲）'-->
          <!--}-->
        <!--],-->
        <!--trans: [{-->
          <!--count: '23条新评论',-->
          <!--color: '#FFC107',-->
          <!--text: '之前在树洞看到有转让小熊音乐会门票的微博，如果还有票请联系我，谢谢，好想去。655338'-->
        <!--}, {-->
          <!--count: '2人评论了你',-->
          <!--color: '#2196F3',-->
          <!--text: '有没有人可以帮忙拿个快递到F1？'-->
        <!--}, {-->
          <!--count: '1条新评论',-->
          <!--text: '谁能帮小泽妹纸买个早餐？'-->
        <!--}, {-->
          <!--count: '23条新评论',-->
          <!--text: '之前在树洞看到有转让小熊音乐会门票的微博，如果还有票请联系我，谢谢，好想去。655338'-->
        <!--}, {-->
          <!--count: '2条新评论',-->
          <!--text: '有没有人可以帮忙拿个快递到F1？'-->
        <!--}, {-->
          <!--count: '1条新评论',-->
          <!--text: '谁能帮小泽妹纸买个早餐？'-->
        <!--}]-->
      <!--}-->
    <!--}-->
  <!--}-->
<!--</script>-->
