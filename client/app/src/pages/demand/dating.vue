<template>
  <div>
    <!--$loadingRouteData当路由数据加载中为true，否则为false-->
    <div  v-if="!$loadingRouteData">
      <photos-wipe ref:viewer></photos-wipe>
      <demand-card v-for="item in list" :data="item" :is_detail="false" @view-image="View"></demand-card>
      <!--加载更多组件-->
      <infinite-loading :on-infinite="onLoadMore">
        <span slot="no-more">
          没有更多了...
        </span>
      </infinite-loading>
    </div>
    <!--加载动画组件：小圆点-->
    <div v-if="$loadingRouteData" class="loading-area">
      <pulse-loader color="rgb(38, 162, 255)" size="12px"></pulse-loader>
    </div>
  </div>
</template>
<!--加载动画组件的CSS-->
<style scoped>
  .loading-area {
    display: flex;
    justify-content: center;;
    margin-top: 120px;
  }
</style>
<script type="text/ecmascript-6">
  import DemandCard from 'components/demand-card/demand-card.vue';
  import PhotosWipe from 'components/photoswipe/photoswipe.vue';
  import Vue from 'vue';
  //https://github.com/greyby/vue-spinner
  import PulseLoader from 'vue-spinner/src/PulseLoader.vue'
  //https://peachscript.github.io/vue-infinite-loading
  import InfiniteLoading from 'vue-infinite-loading';
  export default{
    components: {
      DemandCard,
      PhotosWipe,
      PulseLoader,
      InfiniteLoading
    },
    methods:{
      View(index,photos){
        this.$refs.viewer.show(index,photos);
      },
      onLoadMore(){
        return this.$request
            .get("/demand")
            .query({ext_type: [6]})
            .query({offset: this.data.offset + 5, limit: this.data.limit})
            .then(this.$api.checkResult)
            .then((data)=> {
            //通知组件加载完毕
            this.$broadcast('$InfiniteLoading:loaded');
        //更新数据数组
        this.list = this.list.concat(data.items);
        this.data.offset = data.offset;
        this.data.total = data.total;
        //判断是否已经不能加载到更多的数据
        if(this.data.offset>=this.data.total){
          this.$broadcast('$InfiniteLoading:complete');
        }
      })
      }
    },
    //配置路由钩子
    route: {
      //页面加载数据钩子(或者叫事件)
      data(){
        return this.$request
          .get("/demand")//GET方法 url为/demand
          .query({ext_type:[6]})//    传递query，   url变为 /demand?ext_type=1&ext_type=2&ext_type=3 过滤信息
          .then(this.$api.checkResult)//一个辅助函数，用于处理code等信息，直接返回data
          .then(function(data){
            //处理数据，具体见vue-router文档data钩子页说明
            return {
              data:data,
              list:data.items
            }
          })
      }
    },
    data(){
      return{
        list:[],
        data:{}
      }
    }
  }
</script>
