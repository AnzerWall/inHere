<template>
  <div>
    <div v-if="$loadingRouteData ">
        加载中...
    </div>
    <div v-if="!$loadingRouteData ">
      {{detail}}
    </div>
  </div>

</template>
<style>

</style>
<script type="text/ecmascript-6">
  import fakeComment from '../api/fake/comment.js'
  export default{
    route: {
      data(){
//        //访问路由对象，获取路由id
//        var id=this.$route.params.id;
//
//        return this.$api
//          .get("/demand/"+id)
//          .then(function (res) {
//              return Promise.resolve({ detail: res.body.data })
//           })


      }
    },
    data(){
      return{
        detail:{}
      }
    }
  }
</script>
