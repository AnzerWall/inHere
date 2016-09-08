<template>

    <div>
      <!--导航栏头部-->
      <div class="publish-header">

        <div class="publish-nav publish-nav-back" @click="back">《 发布</div>

        <div class="publish-nav-wrapper">
          <div class="publish-nav" v-link="{path:'/demand-publish/task',activeClass:'hight'}">任务</div>
          <div class="publish-nav" v-link="{path:'/demand-publish/lost',activeClass:'hight'}">失物</div>
          <div class="publish-nav" v-link="{path:'/demand-publish/dating',activeClass:'hight'}">走起</div>
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
<style>
  .publish-header{
    display: flex;
    justify-content: space-between;
    flex-wrap: nowrap;
    width: 100%;
    height: 60px;
    background-color: white;
    border-bottom: 1px solid #d5d5d5;
    position: fixed;
    z-index: 2000;
    align-items: center;
  }
  .publish-nav-wrapper{
    display: flex;
    flex-wrap: nowrap;
    opacity: 0.15;
    justify-content: space-between;
  }
  .publish-nav{
    font-weight: bold;
    padding: 0 10px;
  }
  .publish-nav-back{
    padding-left: 20px;
  }
  .confirm-icon{
    padding-right: 20px;
  }
  .hight{
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
