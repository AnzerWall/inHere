<template>
    <div>
      <!--$loadingRouteData当路由数据加载中为true，否则为false-->
       <div  v-if="!$loadingRouteData">
         <demand-card v-for="item in list" :data="item" :is_detail="false"></demand-card>
       </div>
      <div  v-if="$loadingRouteData" style="color: #888;text-align: center;margin: 100px">
        加载中...
      </div>
      </div>
</template>
<style>

</style>
<script>
  import DemandCard from 'components/demand-card/demand-card.vue';

    export default{
      components:{
        DemandCard
      },
      //配置路由钩子
      route: {
        //页面加载数据钩子(或者叫事件)
        data(){
        return this.$request
          .get("/demand")//GET方法 url为/demand
          .query({ext_type:[4,5,6]})//    传递query，   url变为 /demand?ext_type=1&ext_type=2&ext_type=3 过滤信息
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
