<template>
  <div class="square-wrapper">
    <!--动画-->
      <!--加载动画组件：小圆点-->
      <div v-if="$loadingRouteData" class="loading-area">
        <pulse-loader color="rgb(24, 184, 3)" size="12px"></pulse-loader>
      </div>
    <div class="square-container" v-if="!$loadingRouteData">
      <!--社团活动入口-->
      <div class="square-activity" :style="{'background-image': 'url('+activity.cover_img.src+')'}">
        <div class="activity-content-bg"></div>
        <div class="activity-content">
          <div class="square-activity-tittle">{{activity.title}}</div>
          <span>@{{activity.user_name}}&nbsp;&nbsp;&nbsp;&nbsp;{{activity.start_time | now}} ~ {{activity.end_time | now}}</span>
        </div>
      </div>

      <!--有。必。栏目-->
      <div class="item">
        <div class="square-item" :style="{'color': color(1)}">
          <span class="square-item-tittle">* 有诺必行</span>
          <span @click="enter(1)">进入</span>
        </div>
        <square-slider :topics="target.items" :square_type="1"></square-slider>
      </div>

      <div class="item">
        <div class="square-item" :style="{'color': color(2)}">
          <span class="square-item-tittle">* 有问必答</span>
          <span @click="enter(2)">进入</span>
        </div>
        <square-slider :topics="ask_reply.items" :square_type="2"></square-slider>
        <div class="sub">
          <p :style="{'color': color(2)}">#{{ask_reply.best_reply.title}}</p>
          <p>{{ask_reply.best_reply.best_answer}}</p>
        </div>
      </div>

      <div class="item">
        <div class="square-item" :style="{'color': color(3)}">
          <span class="square-item-tittle">* 有嘈必吐</span>
          <span @click="enter(3)">进入</span>
        </div>
        <square-slider :topics="teasing.items" :square_type="3"></square-slider>
        <div class="sub">
          <p :style="{'color': color(3)}">#{{teasing.best_reply.title}}</p>
          <p>{{teasing.best_reply.best_answer}}</p>
        </div>
      </div>

      <div class="canteen-btn" @click="enterCanteen">深&nbsp;夜&nbsp;食&nbsp;堂</div>

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
  import {now} from '../../filter/time'

  export default{
    //配置路由钩子
    route: {
      //页面加载数据钩子(或者叫事件)
      data(){
        return this.$request
          .get("/square")//GET方法 url为/demand
          .then(this.$api.checkResult)//一个辅助函数，用于处理code等信息，直接返回data
          .then(function (data) {
            //处理数据，具体见vue-router文档data钩子页说明
            return {
              activity: data.activity,
              target: data.target_list,
              ask_reply: data.ask_reply,
              teasing: data.teasing
            }
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
    methods: {
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

        } else {
          alert("深夜餐厅只在深夜12点到凌晨6点开放喔~");
        }
      },
      enter(index){
        alert("进入的栏目为" + index);
      }
    },
    components: {
      PulseLoader,
      SquareSlider
    },
    filters: {
      now
    },
  }
</script>
