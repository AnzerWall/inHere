<template>
  <div v-if="!$loadingRouteData">
    <!--活动头部-->
    <div class="activity-head"><span class="activity-title" @click="back()">《 现在有{{data.total}}个活动</span></div>

    <!--活动列表-->
    <div class="activity-content" >
      <activity-card v-for="list in lists" :item="list">

      </activity-card>
      <!--加载更多组件-->
      <infinite-loading :on-infinite="onLoadMore">
        <span slot="no-more">
          没有更多了...
        </span>
      </infinite-loading>
    </div>

  </div>
  <!--加载动画组件：小圆点-->
  <div v-if="$loadingRouteData" class="loading-area">
    <pulse-loader color="rgb(38, 162, 255)" size="12px"></pulse-loader>
  </div>
</template>
<style scoped>
  .loading-area {
    display: flex;
    justify-content: center;;
    margin-top: 120px;
  }

  .activity-head {
    padding: 0;
    margin: 0;
    background-color: white;
    width: 100%;
    height: 60px;
    position: fixed;
    display: flex;
    align-items: center;
    padding-left: 10px;
    border-bottom: solid 1px rgba(213, 213, 213, .5);
    z-index: 200;
  }

  .activity-title {
    font-weight: bold;
  }

  .activity-content {
    position: relative;
    padding: 60px 10px 10px 10px;
  }

</style>
<script type="text/ecmascript-6">
  import ActivityCard from'components/activity-card/activity-card.vue'
  import {token,login_state,is_login,school,user_id} from '../../vuex/getters.js';
  import PulseLoader from 'vue-spinner/src/PulseLoader.vue'
  import InfiniteLoading from 'vue-infinite-loading';
  import Noti from 'components/noti.vue';
  import Fail from 'components/fail.vue';
  export default{
    methods: {
      back(){
        window.history.back()
      },
      onLoadMore(){
        let url = `${this.$api.url_base}/activity/`;
        return this.$request
          .get(url)//GET方法 url为/activity
          .query({token: this.token})
          .query({offset: ( this.data.offset || 0) + 5, limit: 5})
          .then(this.$api.checkResult)
          .then((data)=> {
            //通知组件加载完毕
            console.log(data);
            this.$broadcast('$InfiniteLoading:loaded');
//           //更新数据数组
            this.lists = this.lists.concat(data.items);
            this.data.offset = data.offset;
            this.data.total = data.total;
//            //判断是否已经不能加载到更多的数据
            if (this.data.offset >= this.data.total) {
              this.$broadcast('$InfiniteLoading:complete');
            }
          })
          .catch(function (e) {
            console.log(e);
          })
      }

    },
    vuex: {
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

        let url = `${this.$api.url_base}/activity/`;
        return this.$request
          .get(url)//GET方法 url为/activity
          .query({token: this.token})
          //            .query({ext_type: [1]})//    传递query，   url变为 /demand?ext_type=1&ext_type=2&ext_type=3 过滤信息
          .then(this.$api.checkResult)//一个辅助函数，用于处理code等信息，直接返回data
          .then(function (data) {

            //处理数据，具体见vue-router文档data钩子页说明
            return {
              data: data,
              url_type: data.url_type,
              lists: data.items
            }
          })
      }
    },
    data(){
      return {

        lists: []
      }
    },
    components: {
      ActivityCard,
      PulseLoader,
      InfiniteLoading,
      Noti,
      Fail

    }
  }
</script>
