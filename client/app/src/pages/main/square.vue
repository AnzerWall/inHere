s<template>
  <div class="square-wrapper">
    <!--动画-->
      <!--加载动画组件：小圆点-->
      <div v-if="$loadingRouteData" class="loading-area">
        <pulse-loader color="rgb(24, 184, 3)" size="12px"></pulse-loader>
      </div>
    <div class="square-container" v-if="!$loadingRouteData">
      <!--社团活动入口-->
      <div class="square-activity" :style="{'background-image': 'url('+activity.cover_img.src+')'}" @click="activityShow()">
        <div class="activity-content-bg"></div>
        <div class="activity-content">
          <div class="square-activity-tittle"><span>{{activity.title}}</span></div>
          <span>@{{activity.user_name}}&nbsp;&nbsp;&nbsp;&nbsp;</span>
          <span>{{activity.start_time |date}} ~ {{activity.end_time |date}}</span>
        </div>
      </div>

      <!--有。必。栏目-->
      <!--<div class="item">-->
        <!--<div class="square-item" :style="{'color': color(1)}">-->
          <!--<span class="square-item-tittle">* 有诺必行</span>-->
          <!--<span @click="enter(1)">进入</span>-->
        <!--</div>-->
        <!--<square-slider :topics="target.items" :square_type="1" v-on:go-to-the-topic="goTopic"></square-slider>-->
      <!--</div>-->

      <div class="item">
        <div class="square-item" :style="{'color': color(2)}">
          <span class="square-item-tittle">* 有问必答</span>
          <span>进入</span>
        </div>
        <square-slider :topics="ask_reply_items" :square_type="2" v-on:go-to-the-topic="goTopic"></square-slider>
        <div class="sub">
          <p :style="{'color': color(2)}">#{{ask_reply.best_reply.que_title}}</p>
          <p>{{ask_reply.best_reply.best_answer}}</p>
        </div>
      </div>

      <div class="item">
        <div class="square-item" :style="{'color': color(3)}">
          <span class="square-item-tittle">* 有嘈必吐</span>
          <span @click="enter(3)">进入</span>
        </div>
        <square-slider :topics="teasing_items" :square_type="3" v-on:go-to-the-topic="goTopic"></square-slider>
        <div class="sub">
          <p :style="{'color': color(3)}">#{{teasing.best_teasing.lab_name}}</p>
          <p>{{teasing.best_teasing.content}}</p>
        </div>
      </div>

      <!--<div class="canteen-btn" @click="enterCanteen">深&nbsp;夜&nbsp;食&nbsp;堂</div>-->

    </div>
  </div>
</template>
<style scoped>
  .loading-area {
    display: flex;
    justify-content: center;;
    margin-top: 180px;
  }

  .square-wrapper {
    font-size: 15px;
  }

  .square-container {
    width: 100%;
  }

  .square-activity {
    position: relative;
    width: 100%;
    height: 238px;
    background-size: cover;
    background-position: center;
  }

  .activity-content, .activity-content-bg {
    height: 47px;
    position: absolute;
    bottom: 0px;
    padding: 30px 0 15px 0;
    color: white;
  }

  .activity-content {
    padding-left: 15px;
  }

  .activity-content-bg {
    width: 100%;
    opacity: 0.75;
    background: linear-gradient(to top, rgba(0, 0, 0, 1), rgba(0, 0, 0, 0));
  }

  .square-activity-tittle {
    font-size: 1.5em;
    font-weight: bold;
    margin-bottom: 5px;

    /* 保持一行  */
    overflow : hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-line-clamp: 1;
    -webkit-box-orient: vertical;
  }

  .item {
    border-bottom: 1px solid #D5D5D5;
    padding-bottom: 15px;
  }

  .square-item {
    display: flex;
    justify-content: space-between;
    font-weight: bold;
    padding: 15px;
  }

  p {
    font-size: 0.9em;
    margin: 15px 0 0;
    /* 保持三行  */
    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-line-clamp: 3;
    -webkit-box-orient: vertical;
  }

  .sub {
    font-family: ".PingFang-SC-Default", "Helvetica Neue", Helvetica, "Microsoft Yahei", Arial, "Hiragino Sans GB", "Heiti SC", "WenQuanYi Micro Hei", sans-serif;;
    margin: 0 15px 0 30px;
  }

  .canteen-btn {
    margin: 23px;
    border-radius: 4px;
    border: solid 1px #d5d5d5;
    text-align: center;
    padding: 16px;
    color: #BCBCBC;
  }
</style>
<script type="text/ecmascript-6">
  import PulseLoader from 'vue-spinner/src/PulseLoader.vue'
  import SquareSlider from '../../components/square-slider/slider.vue'
  import ColorValue from '../../util/color_constant.js'
  import {date} from '../../filter/time.js'
  import {token,login_state,is_login,school,user_id} from '../../vuex/getters.js'

  export default{
    vuex: {
      actions: {

      },
      getters: {
        login_state,
        token,
        is_login,
        school,
        user_id
      }
    },
    //配置路由钩子
    route: {
      //页面加载数据钩子(或者叫事件)
      data(){
        let url=`${this.$api.url_base}/square`;
        return this.$request
          .get(url)
          .query({token:this.token})
          .then(this.$api.checkResult)//处理code等信息，返回data
          .then((data)=> {
            console.log(data);
            //处理数据
            this.activity=data.activity;
//            this.target=data.target_list;
            this.ask_reply=data.ask_reply;
            this.teasing=data.teasing;

//            this.target.items.push({
//              id: -1,
//              title: "更多"
//            });
//            this.ask_reply.items.push({
//              id: -1,
//              title: "更多"
//            });
//            this.teasing.items.push({
//              id: -1,
//              title: "更多"
//            });
          })
      }
    },
    data: function () {
      return {
        target: {},
        ask_reply: {},
        teasing: {},
        best_reply: {},
        activity: {}
      }
    },
    computed:{
      ask_reply_items(){
        let ret=[];
        if(this.ask_reply.items&&this.ask_reply.items.length){
           ret= this.ask_reply.items.map((item)=>{
            return {
              title:item.que_title,
              id:item.que_id
            }
          });
        ret.push({
            id: -1,
            title: "更多"
          })
        }
        return ret;
      },
      teasing_items(){
        let ret=[];
        if(this.teasing.items&&this.teasing.items.length){
           ret= this.teasing.items.map((item)=>{
            return {
              title:item.lab_name,
              id:item.lab_id
            }
          });
          ret.push({
            id: -1,
            title: "更多"
          })
        }
        return ret;
      }
    },

    methods: {
      activityShow(){
        this.$router.go('/activity');
      },
      color(square_type){
        switch (square_type) {
          case 1:
            return ColorValue.COLOR_SQUARE_NUO;
          case 2:
            return ColorValue.COLOR_SQUARE_WEN;
          case 3:
            return ColorValue.COLOR_SQUARE_CAO;
          default:
            return ColorValue.COLOR_SQUARE_WEN;
        }
      },
      enterCanteen(){
        var date = new Date();
        var hour = date.getHours();
        if (hour >= 0 && hour < 7) {
          //    跳转到深夜餐厅 待完成
          alert("其实，这个功能我们还没做，嘻嘻~");
        } else {
          alert("深夜餐厅只在深夜12点到凌晨6点开放喔~");
        }
      },
      enter(index){
        switch (index){
          case 1:{
            alert("进入的栏目为" + index);
            return;
          }
          case 2:{
            this.$router.go('/answer');
            return;
          }
          case 3:{
            this.$router.go('/cao?ext_type=10');
            return;
          }

        }
      },
      goTopic(id, topic_type){
        // 跳转到对应的栏目
        // topic_type：1 有诺必行  2 有问必答  3 有嘈必吐
        // id : 对应栏目下的话题id
        switch (topic_type){
          case 1:{
            alert("接收子组件的通信goTopic"+"栏目"+topic_type +"  id"+id);
            return;
          }
          case 2:{
            if (id>0){
              this.$router.go(`/answer-detail/${id}`);
            }else { // 跳转到其他
              this.$router.go('/answer');
            }
            return;
          }
          case 3:{
            if (id>0){
              this.$router.go(`/cao-detail/${id}`);
            } else {
              this.$router.go('/cao?ext_type=10');
            }
            return;
          }
        }
      }
    },
    components: {
      PulseLoader,
      SquareSlider
    },
    filters: {
      date
    }
  }
</script>
