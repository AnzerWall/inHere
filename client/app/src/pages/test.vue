<template>
  <div>
    该页面为练习用测试用页面，路由为/test
    <!--<button >test</button>-->
  </div>

</template>
<style>

</style>
<script type="text/ecmascript-6">

  export default{
    route: {
      data(){

        return this.$api.demand.listTask("5", {
          test: "t"
        }).then(function (...data) {
          console.log("ok",data);
        })
      }
    }
  }
</script>
