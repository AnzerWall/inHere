<template>

    <div>
      <!--导航栏头部-->
      <div class="publish-header">

        <div class="publish-nav publish-nav-back" @click="back">《 发布</div>

        <div class="publish-nav-wrapper">
          <div class="publish-nav navbar_item" v-link="{path:'/demand-publish/task',activeClass:'high', replace: true}">任务</div>
          <div class="publish-nav navbar_item" v-link="{path:'/demand-publish/lost',activeClass:'high', replace: true}">失物</div>
          <div class="publish-nav navbar_item" v-link="{path:'/demand-publish/dating',activeClass:'high', replace: true}">走起</div>
        </div>

        <div class="publish-nav confirm-icon">
          <confirm-icon></confirm-icon>
        </div>

      </div>

      <!--内容页面-->
      <div>
        <div class="publish-content-header"></div>
        <router-view></router-view>
      </div>
    </div>
</template>
<style scoped>
  .publish-header{
    display: flex;
    justify-content: space-between;
    flex-wrap: nowrap;
    width: 100%;
    height: 60px;
    border-bottom: 1px solid rgba(213,213,213,.5);
    position: fixed;
    z-index: 2000;
    align-items: center;
    background-color: #FAFAFA;
  }
  .publish-nav-wrapper{
    display: flex;
    flex-wrap: nowrap;

    justify-content: space-between;
  }
  .publish-nav{
    font-weight: bold;
    padding: 0 10px;
  }
  .navbar_item{
    opacity: 0.15;
  }
  .publish-nav-back{
    /*padding-left: 20px;*/
  }
  .confirm-icon{
    padding-right: 20px;
  }
  .high{
    opacity: 1;
  }
  .publish-content-header{
    height: 60px;
  }
</style>
<script>
  import ConfirmIcon from 'svg/common/Confirm.vue'

  export default{
      data(){
          return{
          }
      },
      methods:{
        back(){
          window.history.back()
        }
      },
      components:{
        ConfirmIcon
      }
  }
</script>
