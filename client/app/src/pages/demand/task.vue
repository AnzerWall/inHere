<template>
  <div>
    <!--$loadingRouteData当路由数据加载中为true，否则为false-->
    <photos-wipe v-ref:viewer></photos-wipe>
    <div v-if="!$loadingRouteData">
      <demand-card v-for="item in list" :data="item" :is_detail="false" @view-image="viewImage" @click="$router.go('/demand-detail/'+item.id)"></demand-card>
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
</template>
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
    methods: {
      viewImage(index, photos){
        console.log(index,photos);
        this.$refs.viewer.show(index, photos);
      },
      onLoadMore(){
        var token = "19e7aae2d81da63d62cfa36eb69706069e7a97bb61c8901782d8c1d98765ea94"
        this.$request
          .get("http://115.28.67.181:8080/demand")//GET方法 url为/demand
          .query({token: token})
          .query({ext_type: [1, 2, 3]})
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
            if(this.data.offset>=this.data.total){
              this.$broadcast('$InfiniteLoading:complete');
            }
          }))
      }
    },
    //配置路由钩子
    route: {
      //页面加载数据钩子(或者叫事件)
      data(){
        var token="19e7aae2d81da63d62cfa36eb69706069e7a97bb61c8901782d8c1d98765ea94";
        return this.$request
          .get("http://115.28.67.181:8080/demand")//GET方法 url为/demand
          .query({token:token})
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
      }
    },
    data(){
      return {
        list: [],
        data: {}
      }
    }

  }
</script>
