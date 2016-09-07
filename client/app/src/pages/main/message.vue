<template>
  <div class="message-wrapper">

    <div class="message-head-wrapper">
      <div class="message-head">
        <div class="icon">
          <chat-icon></chat-icon>
        </div>
        <div class="notes">你又又又有{{newMessageCount}}条悄悄话 》</div>
      </div>
    </div>
    <div class="message-content">
      <message-card v-for="notice in notices |limitBy num" :item="notice">
      </message-card>
      <!--更多官方提示-->
      <div class="notice-more"  @click="allShow()" v-if="num<notices.length">
        <span class="notice-span">还有{{notices.length-2}}条官方通知</span>
      </div>

      <message-card v-for="item in items" :item="item">
      </message-card>
    </div>
  </div>
</template>
<style scoped>
  .message-wrapper {
    display: flex;
    height: 100%;
    width: 100%;
    flex-direction: column;
    position: relative;
    background-color:#FAFAFA;

  }

  .message-wrapper .message-head-wrapper {
    flex-shrink: 0;
    flex-grow: 0;
    height: 60px;
    width: 100%;
    position: relative;
    z-index: 1;

  }

  .message-wrapper .message-head-wrapper .message-head {
    top: 0;
    left: 0;
    height: 60px;
    width: 100%;
    border-bottom: solid 1px rgba(213,213,213,.5);
    align-items: center;
    justify-content: space-between;
    display: flex;
    position: fixed;
    background-color:#FAFAFA;
  }

  .message-wrapper .message-head-wrapper .message-head .icon {
    margin-left: 15px;
  }

  .message-wrapper .message-head-wrapper .message-head .notes {
    margin-right: 15px;
  }

  .message-wrapper .message-content {
    flex-shrink: 1;
    height: 100%;
    position: relative;
  }

  .notes {
    margin-right: 25px;
    padding: 6px 14px 6px 22px;
    border-radius: 50px;
    background: #FFC107;
    color: white;
  }

  .notice-more{
    display: flex;
    height: 40px;
    align-items:center;
    justify-content:center;
  border-bottom: solid 1px rgba(213,213,213,.5);
    background-color:#FAFAFA;
  }
  .notice-span{
    background-color:#FAFAFA;
    font-weight: bold;
    font-size: smaller;
  }
</style>
<script type="text/ecmascript-6">
  import MessageCard from 'components/message-card/message-card.vue'
  import ChatIcon from'svg/main/message/Chat.vue'

  export default{
    methods:{
      allShow(){
        this.num=this.notices.length;
      }

    },
    components: {
      ChatIcon,
      MessageCard
    },
    data(){
      return {
        num:2,
        switch:true,
        newMessageCount: '66',
        notices: [
          {
            tag: 'notice',
            title: '肇庆学院',
            message: '【重磅| 学生宿舍第一批空调已抵达！很好！这很夏天！】'
          },
          {
            tag: 'notice',
            title: '天翼校园一卡通',
            message: '温馨提示：国庆期间天翼一卡通服务中心上班时间为2,4,6号，8号后正常上班。由于校园网上学期办理的包学期有效期到2013年9月30日，为了避免10月1日断网，建议大四需要充值校园网的尽量在国庆放假前前往一卡通服务中心的前台充值。请周知！（30元/月，尽量自备零钱哦亲）'
          },
          {
            tag: 'notice',
            title: '计算机学院',
            message: '温馨提示：国庆期间断网，对大家造成的不便表示歉意。由于校园网上学期办理的包学期有效期到2013年9月30日，为了避免10月1日断网，建议大四需要充值校园网的尽量在国庆放假前前往一卡通服务中心的前台充值。请周知！（30元/月，尽量自备零钱哦亲）'
          }
        ],
          items: [
        {
            tag: 'my',
            mess: 'n-ew',
            count: '23',
            color: '#FFC107',
            message: '之前在树洞看到有转让小熊音乐会门票的微博，如果还有票请联系我，谢谢，好想去。655338'
          }, {
            tag: 'my',
            mess: 'yet',
            count: '2',
            color: '#2196F3',
            message: '有没有人可以帮忙拿个快递到F1？'
          }, {
            tag: 'my',
            mess: 'general',
            count: '1',
            message: '谁能帮小泽妹纸买个早餐？'
          }, {
            tag: 'my',
            mess: 'general',
            count: '23',
            message: '之前在树洞看到有转让小熊音乐会门票的微博，如果还有票请联系我，谢谢，好想去。655338'
          }, {
            tag: 'my',
            mess: 'general',
            count: '2',
            message: '有没有人可以帮忙拿个快递到F1？'
          }, {
            tag: 'my',
            mess: 'general',
            count: '1',
            message: '谁能帮小泽妹纸买个早餐？'
          }]
      }
    }
  }
</script>
