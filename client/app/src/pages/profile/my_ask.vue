<template>
  <div class="url_style">
    <div class="activity-head"><span class="activity-title" @click="back()">《 {{title}}</span></div>
    <div>
      <!--$loadingRouteData当路由数据加载中为true，否则为false-->
      <photos-wipe v-ref:viewer></photos-wipe>
      <div v-if="!$loadingRouteData">
        <demand-card v-for="item in list" :data="item" :is_detail="false" @view-image="viewImage"
                     @click="$router.go('/demand-detail/'+item.id)"></demand-card>
        <infinite-loading :on-infinite="onLoadMore">
          <span slot="no-more">
            没有更多了...
          </span>
        </infinite-loading>
      </div>
      <div v-if="$loadingRouteData" class="loading-area">
        <pulse-loader color="rgb(38, 162, 255)" size="12px"></pulse-loader>
      </div>
    </div>
  </div>
</template>
<style lang="scss">
  .url_style {
    /*padding: 20px 0 20px 0;*/
    position: fixed;
    left: 0;
    right: 0;
    top: 0;
    bottom: 0;
    display: flex;
    flex-direction: column;
  }

  .loading-area {
    display: flex;
    justify-content: center;;
    margin-top: 120px;
  }

  .url_style .activity-head {
    flex: none;
  }

  .activity-head {
    padding: 0;
    margin: 0;
    background-color: white;
    width: 100%;
    height: 60px;
    /*position: fixed;*/
    display: flex;
    align-items: center;
    padding-left: 10px;
    border-bottom: solid 1px rgba(213, 213, 213, .5);
    z-index: 200;
  }
</style>
<script type="text/ecmascript-6">
  import DemandCard from 'components/demand-card/demand-card.vue'
  import PhotosWipe from 'components/photoswipe/photoswipe.vue'
  import {token, login_state, is_login} from '../../vuex/getters.js'
  import Vue from 'vue'
  import PulseLoader from 'vue-spinner/src/PulseLoader.vue'
  import InfiniteLoading from 'vue-infinite-loading'
  export default{
    components: {
      DemandCard,
      PhotosWipe,
      PulseLoader,
      InfiniteLoading
    },
    methods: {
      back(){
        window.history.back()
      },
      viewImage(index, photos){
        console.log(index, photos);
        this.$refs.viewer.show(index, photos);
      },
      onLoadMore(){
        var token = this.token;
        this.$request
          .get(`${this.$api.url_base}/ask_reply`)//GET方法 url为/demand
          .query({token: token})
          .query({ext_type: 12})
          .query({creator:this.user_id})
          .query({offset: this.data.offset + 5, limit: this.data.limit})
          .then(this.$api.checkResult)
          .then((data=> {
            //通知组件加载完毕
            this.$broadcast('$InfiniteLoading:loaded');
            //更新数据数组
            this.list = this.list.concat(data.items);
            this.data.offset = data.offset;
            this.data.total = data.total;
            //判断是否已经不能加载到更多的数据
            if (this.data.offset >= this.data.total) {
              this.$broadcast('$InfiniteLoading:complete');
            }
          }))
      }
    },
    //配置路由钩子
    route: {
      //页面加载数据钩子(或者叫事件)
      data(){

        this.title = this.$route.query.title;
        this.url = this.$route.query.url;
        var token = this.token;

        return this.$request
          .get(`${this.$api.url_base}/demand`)//GET方法 url为/demand
          .query({token: token})
          .query({ext_type: [1, 2, 3]})//    传递query，   url变为 /demand?ext_type=1&ext_type=2&ext_type=3 过滤信息
          .query({offset: 0, limit: 5})
          .then(this.$api.checkResult)//一个辅助函数，用于处理code等信息，直接返回data
          .then(function (data) {
            //处理数据，具体见vue-router文档data钩子页说明
            return {
              data: data,
              list: data.items
            }
          })
          .catch((e)=> {
            if (e.type === 'API_ERROR') {//判断是api访问出错还是其他错，仅限被checkResult处理过。。详见checkResult。。
              if (e.code === 23333) {//根据code判断出错类型,比如未登录时候跳转啊
                return this.$refs.noti.warning(`参数验证失败`, {
                  timeout: 1500
                })//这里以及后边的return是为了结束函数。。。仅此而已 ，常用技巧  : )
              } else if (e.code === 401) {
                return this.$router.go({
                  path: '/login',
                  query: {
                    __ref: this.$route.path//告诉login页面要跳转回来的页面
                  }
                });
              } else {
                return this.$refs.noti.warning(`与服务器通讯失败:${e.message}`, {
                  timeout: 1500
                })
              }
            } else {
              console.error(e.stack || e);
              return this.$refs.noti.warning(`${e.message}`, {
                timeout: 1500
              })
            }
          })
      }
    },
    data(){
      return {
        title: '',
        url: '',
        list: [],
        data: {}
      }
    },
    props: {
      isWebView: {
        type: Boolean,
        required: false,
        default: true
      }
    },
    vuex: {
      actions: {},
      getters: {
        login_state,
        token,
        is_login
      }
    }
  }
</script>

