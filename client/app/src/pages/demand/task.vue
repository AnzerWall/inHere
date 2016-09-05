<template>
    <div>
       <div  v-if="!$loadingRouteData">
         <demand-card v-for="item in list" :data="item" is_detail="false"></demand-card>
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
      route: {
        data(){
        return this.$request
          .get("/demand")//GET方法 url为/demand
          .query({ext_type:[1,2,3]})//    传递query，   url变为 /demand?ext_type=1&ext_type=2&ext_type=3
          .then(this.$api.checkResult)
          .then(function(data){
            return {data:data,list:data.items}
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
