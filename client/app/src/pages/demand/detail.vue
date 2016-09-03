<template>
        <div>
          详情页面 路由/demand-detail/:id <br>
          当前id值为{{id}}
          <br>
          <a v-link="'/demand/task'">返回</a>
        </div>
</template>
<style scoped>

</style>
<script  type="text/ecmascript-6">

    export default{
      data:function(){
        return {
          id:this.$route.params.id
        }
      }

    }
</script>
