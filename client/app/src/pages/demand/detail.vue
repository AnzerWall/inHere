<template>
  <div class="response"v-if="!$loadingRouteData">
    <!--头部-->
    <div class="response-header">
      <div class="head-detail" >
        <div class="head-left" @click="back">
          <div class="head-message" >《 &nbsp;{{chinese(data.ext_type)}}</div>
        </div>
        <div class="head-right" >
          <div>
            <send-chat-icon :style="{fill:main_color}" class="sendchat"  ></send-chat-icon>
            <menu-icon :style="{fill:main_color}"></menu-icon>
          </div>
        </div>
      </div>
    </div>

    <!--下部内容-->
    <div class="content" >
      <!--卡片-->
      <photos-wipe v-ref:viewer></photos-wipe>
      <header-card  :data="data" :is_detail="true" @view-image="View"></header-card>
      <!--任务信息-->
      <div class="task">
        <div class="content-center" >
          <!--快递   帮忙-->
          <div  v-if="ext_type===1||ext_type===3">
            <div class="center-all"  >
              <div class="center-left" >酬金</div>
              <div class="center-right" :style="{color:main_color}">¥{{data.ext_data.pay}}</div>
            </div>
          </div>
          <!--转让-->
          <div v-if="data.ext_type===2">
            <div class="center-all">
              <div class="center-left">价格</div>
              <div class="center-right" :style="{color:main_color}">¥{{data.ext_data.price}}</div>
            </div>
            <div class="center-all" >
              <div class="center-left">成色</div>
              <div class="center-right" :style="{color:main_color}">{{data.ext_data.quality}} 成</div>
            </div>
          </div>
          <!--丢失东西-->
          <div v-if="data.ext_type===4">
            <div class="center-all">
              <div class="center-left">丢失物品</div>
              <div class="center-right" :style="{color:main_color}">{{data.ext_data.thing}}</div>
            </div>
            <div class="center-all">
              <div class="center-left">丢失时间</div>
              <div class="center-right" :style="{color:main_color}">{{data.ext_data.lose_time}}</div>
            </div>
          </div>
          <!--捡到东西-->
          <div v-if="data.ext_type===5">
            <div class="center-all">
              <div class="center-left">捡到物品</div>
              <div class="center-right" :style="{color:main_color}">{{data.ext_data.thing}}</div>
            </div>
            <div class="center-all">
              <div class="center-left">捡到时间</div>
              <div class="center-right" :style="{color:main_color}">{{data.ext_data.pick_time}}</div>
            </div>
          </div>
          <!--走起-->
          <div v-if="data.ext_type===6">
            <div class="center-all">
              <div class="center-left">人均消费</div>
              <div class="center-right" :style="{color:main_color}">{{data.ext_data.per_cost}}</div>
            </div>
            <div class="center-all">
              <div class="center-left"> 求陪同</div>
              <div class="center-right" :style="{color:main_color}">{{want_sex}}</div>
            </div>
            <div class="center-all">
              <div class="center-left"> 地点</div>
              <div class="center-right" :style="{color:main_color}">{{data.ext_data.place}}</div>
            </div>
            <div class="center-all">
              <div class="center-left"> 时间</div>
              <div class="center-right" :style="{color:main_color}">{{data.ext_data.start_time|now}} WED</div>
            </div>
            <div class="center-all">
              <div class="center-left"> 集中地</div>
              <div class="center-right" :style="{color:main_color}">{{data.ext_data.gathering_place}}</div>
            </div>
          </div>
          <!--评论条数-->
          <div class="comment-number" :style="{color:main_color}">
            <div class="dian">*</div>
            <div class="number">{{data.comment.total}}条评论</div>
          </div>
          </div>
      </div>
      <!--评论组件-->
      <comment class="message-content" :comments="comment" :user_id="user_id" :main_color="main_color">
      </comment>


    </div>

  </div>
  <!--加载动画组件：小圆点-->
  <div v-if="$loadingRouteData" class="loading-area">
    <pulse-loader color="rgb(38, 162, 255)" size="12px"></pulse-loader>
  </div>
</template>
<style scoped>
  .response{
    display: flex;
    flex-direction: column;
  }
  .response-header{
    flex-shrink: 0;
    z-index: 50;
  }
  .head-detail{
    height: 60px;
    width: 100%;
    display: flex;
    justify-content: space-between;
    align-items: center;
    font-size: 16px;
    border-bottom: solid 1px #c9c9c9;
    position: fixed;
    background-color: white;
  }
  .head-detail .head-left{
    font-weight: bold;
    padding-left: 10px;
  }
  .head-detail .head-right{
    padding-right: 20px ;
  }
  .head-detail .head-right .sendchat{
    padding-right: 20px;
  }
  .content{
    flex-shrink: 1;
    margin-top: 60px ;

  }
  .content .task{
    font-weight: bold;
    width: 100%;

  }
  .content .content-center{
    margin: 0 10px;
  }
  .content .content-center .center-all{

    display: flex;
    justify-content: space-between;
    align-items: center;
    width: 100%;
    border-bottom:solid 1px #c9c9c9 ;
    height: 60px;
  }
  .content-center .center-all .center-left{
    margin-left: 20px;
  }
  .content-center .center-all .center-right{
    margin-right: 25px;
  }
  .content .comment-number{
    display: flex;
    height: 50px;
    align-items: center;
    position: relative;
  }
  .comment-number .dian{
    position: absolute;
    top: 20px;
    left: 10px;

  }
  .comment-number .number{
    margin-left: 20px;
  }
  .loading-area {
    display: flex;
    justify-content: center;;
    margin-top: 200px;
  }

</style>
<script type="text/ecmascript-6">
  import Comment from 'components/comment/comment.vue';
  import  moment from 'moment';
  import HeaderCard from 'components/demand-card/demand-card.vue';
  import PhotosWipe from 'components/photoswipe/photoswipe.vue';
  import SendChatIcon from 'svg/common/SendChat.vue';
  import MenuIcon from 'svg/common/Menu.vue';
  import helper from '../../util/demand_helper.js';
  import {now} from 'filter/time.js';
  import PulseLoader from 'vue-spinner/src/PulseLoader.vue';
  export default{
    components: {
      Comment,
      HeaderCard,
      PhotosWipe,
      SendChatIcon,
      MenuIcon,
      PulseLoader
    },


    methods: {
      View(index, photos){
        this.$refs.viewer.show(index, photos);
      },
      back(){
        window.history.back();
      },
      chinese(type){
        switch (type) {
          case this.TYPE_EXPRESS:
            return "快递";
          case this.TYPE_SELL:
            return "转让";
          case this.TYPE_HELP:
            return "帮忙";
          case this.TYPE_FOUND:
            return "捡到";
          case this.TYPE_LOST:
            return "丢失";
          case this.TYPE_DATING:
            return "约";
          default:
            return "其他"
        }
      }
    },
    route:{
      data(){
        var id=this.$route.params.id;
        var self=this;
        return this.$request
        .get("/demand/"+id)
          .then(this.$api.checkResult)
          .then(function(data){
          //  console.log(data);

            self.data=data;
            self.comment=data.comment.items;
            self.user_id=data.user_id;
            self.ext_type=data.ext_type;



          })
      }
    },



    data: function () {

      return {

        data:{},
        comment:[],
        user_id:"",
        ext_type:1,
        TYPE_EXPRESS:1,
        TYPE_SELL:2,
        TYPE_HELP:3,
        TYPE_FOUND:5,
        TYPE_LOST:4,
        TYPE_DATING:6
      }
    },
    computed: {
      main_color(){

        return helper.getMainColor(this.data)
      },
      want_sex(){
        let sex = this.data.ext_data.want_sex;
        switch (sex) {
          case 1:
            return '求男生';
          case 2:
            return '求女生';
        }
        return '性别不限'
      },

    },
    filters:{
      now
    },

  }
</script>
