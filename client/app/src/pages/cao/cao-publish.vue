<template>

  <div>
    <!--导航栏头部-->
    <div class="publish-header">
      <div class="publish-nav publish-nav-back" @click="back">《 有嘈必吐</div>

      <div class="publish-nav-wrapper">
        <div class="publish-nav navbar_item" v-link="{path:'/cao-publish/in',activeClass:'high', replace: true}">校内</div>
        <div class="publish-nav navbar_item" v-link="{path:'/cao-publish/out',activeClass:'high', replace: true}">校外</div>
      </div>

      <div class="publish-nav confirm-icon" @click="caoPublish">
        <confirm-icon style="fill:white"></confirm-icon>
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
    /*padding-top: 20px;*/
    position: fixed;
    z-index: 2000;
    align-items: center;
    color: white;
    background: linear-gradient(to bottom ,#F91C88 ,#FF5FAD);
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
    opacity: 0.5;
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
      },
      caoPublish(){
        this.$broadcast('cao-publish',this.$route.name);
      }
    },
    components:{
      ConfirmIcon
    }
  }
</script>
