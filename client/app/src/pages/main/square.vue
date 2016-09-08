<template>
  <div class="square-wrapper">
    <div class="square-container">
      <!--社团活动入口-->
      <div class="square-activity" :style="{'background-image': 'url('+activity.imgUrl+')'}">
        <div class="activity-content-bg"></div>
        <div class="activity-content">
          <div class="square-activity-tittle">{{activity.tittle}}</div>
          <span>@{{activity.organization}}&nbsp;&nbsp;&nbsp;&nbsp;{{activity.start_time}} ~ {{activity.end_time}}</span>
        </div>
      </div>

      <!--有。必。栏目-->
      <div>
        <div class="square-item" >
          <!--:style="color:itemColor.color1"-->
          <span class="square-item-tittle">* 有诺必行</span>
          <span>进入</span>
        </div>
        <square-slider :topics ="items[0].topic"></square-slider>
      </div>


      <div>
        <div class="square-item">
          <span class="square-item-tittle">* 有问必答</span>
          <span>进入</span>
        </div>
        <square-slider :topics="items[1].topic"></square-slider>
        <div>
          <p>{{items[1].question}}</p>
          <p>{{items[1].answer}}</p>
        </div>
      </div>

      <div>
        <div class="square-item">
          <span class="square-item-tittle">* 有嘈必吐</span>
          <span>进入</span>
        </div>
        <square-slider :topics="items[2].topic"></square-slider>
        <div>
          <p>{{items[2].question}}</p>
          <p>{{items[2].answer}}</p>
        </div>
      </div>

      <div>深 夜 食 堂</div>

    </div>
  </div>
</template>
<style scoped>

  .square-wrapper{
    font-size: 15px;
  }
  .square-container{
    width:100%;
  }
  .square-activity{
    position: relative;
    width: 100%;
    height: 238px;
    /*background-image: url("http://ww1.sinaimg.cn/mw690/6f924588gw1f32g8p67w6j20qo123wjv.jpg");*/
    background-size: cover;
    background-position: center;
  }
  .activity-content, .activity-content-bg{
    height: 47px;
    position: absolute;
    bottom: 0px;
    padding: 30px 0 15px 0;
    color: white;
  }
  .activity-content{
    padding-left: 15px;
  }
  .activity-content-bg{
    width: 100%;
    opacity: 0.75;
    background: linear-gradient(to top, rgba(0,0,0,1), rgba(0,0,0,0));
  }
  .square-activity-tittle{
    font-size: 1.5em;
    font-weight: bold;
    margin-bottom: 5px;
  }

  .square-item{
    /*width:100%;*/
    display: flex;
    justify-content: space-between;
    font-weight: bold;
    padding: 15px;
  }
</style>
<script type="text/ecmascript-6">
  import squareSlider from '../../components/square-slider/slider.vue'
export default{
  data:function (){
    return{
//      itemColor:{
//        color1:"green",
//        color2:"blue",
//        color3:"purple"
//      },
      activity:{
        imgUrl:'http://ww1.sinaimg.cn/mw690/6f924588gw1f32g8p67w6j20qo123wjv.jpg',
        tittle:"小清新电影《一半》正在上映",
        organization:"影视文化协会",
        start_time:"2016-7-12",
        end_time:"2016-8-31"
      },
      items:[
        {
          topic:[
            {message:"说说那些年做过的最尴尬的事情"},
            {message:"你们饭堂有什么奇葩的菜式"},
            {message:"说说那些年做过的最尴尬的事情"},
            {message:"你们饭堂有什么奇葩的菜式"},
            {message:"说说那些年做过的最尴尬的事情"},
            {message:"你们饭堂有什么奇葩的菜式"},
            {message:"说说那些年做过的最尴尬的事情"},
            {message:"你们饭堂有什么奇葩的菜式"}
          ]
        },
        {
          topic:[
            {message:"说说那些年做过的最尴尬的事情"},
            {message:"你们饭堂有什么奇葩的菜式"},
            {message:"说说那些年做过的最尴尬的事情"},
            {message:"你们饭堂有什么奇葩的菜式"},
            {message:"说说那些年做过的最尴尬的事情"},
            {message:"你们饭堂有什么奇葩的菜式"},
            {message:"说说那些年做过的最尴尬的事情"},
            {message:"你们饭堂有什么奇葩的菜式"}
          ],
          question:"你们的饭堂有什么奇葩的菜式?",
          answer:"本学期起我校全体学生用户的带宽统一为4M且无任何捆绑业务。上学期已完成了对大部分电信8m或20m捆绑手机用户的解绑 ..."
        },
        {
          topic:[
            {message:"说说那些年做过的最尴尬的事情"},
            {message:"你们饭堂有什么奇葩的菜式"},
            {message:"说说那些年做过的最尴尬的事情"},
            {message:"你们饭堂有什么奇葩的菜式"},
            {message:"说说那些年做过的最尴尬的事情"},
            {message:"你们饭堂有什么奇葩的菜式"},
            {message:"说说那些年做过的最尴尬的事情"},
            {message:"你们饭堂有什么奇葩的菜式"}
          ],
          question:"你的坑爹室友做过哪些让你意想不到的事情？",
          answer:"本学期起我校全体学生用户的带宽统一为4M且无任何捆绑业务。上学期已完成了对大部分电信8m或20m捆绑手机用户的解绑 ..."
        }
      ]
    }
  },
  components:{
    squareSlider
  }
}
</script>




<!--<template>-->
  <!--<div id="body">-->
    <!--<div class="nav">-->
      <!--<div class="statusBar">-->
        <!--<div class="status"></div>-->
        <!--&lt;!&ndash;<router-view></router-view>&ndash;&gt;-->

        <!--&lt;!&ndash;顶部小纸条&ndash;&gt;-->
        <!--<div id="chatDiv">-->
          <!--&lt;!&ndash;ChatIcon&ndash;&gt;-->
          <!--<chat-icon></chat-icon>-->
          <!--<div class="newMessage">你又又有{{newMessageCount}}条悄悄话》</div>-->
        <!--</div>-->
      <!--</div>-->
    <!--</div>-->
    <!--<div class="contentDiv">-->
      <!--&lt;!&ndash;公告消息&ndash;&gt;-->
      <!--<div v-for="item in items" class="noticeDiv">-->
        <!--&lt;!&ndash;MessageVipIcon&ndash;&gt;-->
        <!--<div class="padding">-->
          <!--<message-vip-icon></message-vip-icon>-->
          <!--<span><b>{{item.tittle}}</b></span>-->
        <!--</div>-->
        <!--<div class="noticeMessage">{{item.message}}</div>-->
      <!--</div>-->

      <!--&lt;!&ndash;我的评论&ndash;&gt;-->
      <!--<div>-->
        <!--<div v-for="comment in comments" class="commentDiv">-->
          <!--<div class="padding" :style="{color: comment.color}">-->
            <!--<div class="circular" :style="{backgroundColor: comment.color}"></div>-->

            <!--<span class="padding"><b>{{comment.count}}条新评论</b></span>-->
          <!--</div>-->
          <!--<div class="noticeMessage commentText">{{comment.text}}</div>-->
        <!--</div>-->
      <!--</div>-->
    <!--</div>-->
  <!--</div>-->
<!--</template>-->
<!--<style scoped>-->
  <!--.nav{-->
    <!--position: fixed;-->
    <!--left: 0;-->
    <!--top:0;-->
    <!--width: 100%;-->
  <!--}-->
  <!--.status{-->
    <!--height: 20px;-->
    <!--background-color: #cccccc;-->
  <!--}-->
  <!--#chatDiv{-->
    <!--background-color: #f6f6f6;-->
    <!--border-bottom: 1px solid #cccccc;-->
    <!--position: fixed;-->
    <!--width: 100%;-->
    <!--height: 40px;-->
    <!--padding: 10px 20px 10px 20px;-->
    <!--display: flex;-->
    <!--justify-content: space-between;-->
    <!--align-items: center;-->
    <!-- -webkit-justify-content: space-between;-->
    <!-- -webkit-align-items: center;-->
  <!--}-->
  <!--.newMessage{-->
    <!--margin-right: 25px;-->
    <!--padding: 6px 14px 6px 22px;-->
    <!--border-radius: 50px;-->
    <!--background: #FFC107;-->
    <!--color: white;-->
  <!--}-->
  <!--.contentDiv{-->
    <!--padding: 80px 0 10px 0;-->
    <!--z-index: 2;-->
  <!--}-->
  <!--.noticeDiv, .commentDiv{-->
    <!--padding: 20px 20px 20px 20px;-->
    <!--border-bottom: 1px solid #cccccc;-->
  <!--}-->
  <!--.padding{-->
    <!--padding-bottom: 6px;-->
  <!--}-->
  <!--.noticeDiv{-->
    <!--background-color: #f6f6f6;-->
  <!--}-->
  <!--.commentDiv{-->
    <!--background-color: white;-->
  <!--}-->
  <!--/*保持两行*/-->
  <!--.noticeMessage{-->
    <!--overflow : hidden;-->
    <!--text-overflow: ellipsis;-->
    <!--display: -webkit-box;-->
    <!-- -webkit-line-clamp: 2;-->
    <!-- -webkit-box-orient: vertical;-->
  <!--}-->
  <!--.commentText{-->
    <!--padding-left: 20px;-->
  <!--}-->
  <!--.circular{-->
    <!--display: inline-block;-->
    <!--width: 12px;-->
    <!--height: 12px;-->
    <!--border-radius: 6px;-->
    <!--/*background-color: #4E4E4E;*/-->
  <!--}-->
<!--</style>-->
<!--<script>-->

  <!--import ChatIcon from 'svg/main/message/Chat.vue'-->
  <!--import MessageVipIcon from 'svg/main/message/MessageVip.vue'-->

  <!--export default{-->
    <!--components:{-->
      <!--ChatIcon,-->
      <!--MessageVipIcon-->
    <!--},-->
    <!--data(){-->
      <!--return{-->
        <!--newMessageCount:'52',-->
        <!--items:[-->
          <!--{-->
            <!--tittle:'肇庆学院',-->
            <!--message:'【重磅| 学生宿舍第一批空调已抵达！很好！这很夏天！】'-->
          <!--},-->
          <!--{-->
            <!--tittle:'天翼校园一卡通',-->
            <!--message:'温馨提示：国庆期间天翼一卡通服务中心上班时间为2,4,6号，8号后正常上班。由于校园网上学期办理的包学期有效期到2013年9月30日，为了避免10月1日断网，建议大四需要充值校园网的尽量在国庆放假前前往一卡通服务中心的前台充值。请周知！（30元/月，尽量自备零钱哦亲）'-->
          <!--}-->
        <!--],-->
        <!--comments:[{-->
          <!--count:'23',-->
          <!--color:'#FFC107',-->
          <!--text:'之前在树洞看到有转让小熊音乐会门票的微博，如果还有票请联系我，谢谢，好想去。655338'-->
        <!--},{-->
          <!--count:'2',-->
          <!--color:'#2196F3',-->
          <!--text:'有没有人可以帮忙拿个快递到F1？'-->
        <!--},{-->
          <!--count:'1',-->
          <!--text:'谁能帮小泽妹纸买个早餐？'-->
        <!--},{-->
          <!--count:'23',-->
          <!--text:'之前在树洞看到有转让小熊音乐会门票的微博，如果还有票请联系我，谢谢，好想去。655338'-->
        <!--},{-->
          <!--count:'2',-->
          <!--text:'有没有人可以帮忙拿个快递到F1？'-->
        <!--},{-->
          <!--count:'1',-->
          <!--text:'谁能帮小泽妹纸买个早餐？'-->
        <!--}]-->
      <!--}-->
    <!--}-->
  <!--}-->
<!--</script>-->
