<template>
  <div class="main-wrapper">
    <div class="main-content">
      <router-view ></router-view>
    </div>
    <div class="main-tabbar-wrapper">
      <div class="main-tabbar">
        <div class="main-tabbar-item"  v-link="{path:'/demand',activeClass: 'current'}">
          <demand-icon></demand-icon>
        </div>
        <div class="main-tabbar-item" v-link="{path:'/square',activeClass: 'current'}">
          <square-icon></square-icon>
        </div>
        <div class="main-tabbar-item" v-link="{path:'/message',activeClass: 'current'}">
          <message-icon></message-icon>
        </div>
        <div class="main-tabbar-item" v-link="{path:'/profile',activeClass: 'current'}">
          <profile-icon></profile-icon>
        </div>

      </div>
    </div>


  </div>
</template>
<style scoped>
  .main-wrapper{
    height: 100vh;
    background-color: #FAFAFA;
    display: flex;
    flex-direction: column;
    position: relative;

  }
  .main-content {
    overflow: auto;
    height: 100%;
    flex-shrink: 1;
    position: relative;
    display: flex;
    flex-direction: column;
  }

  .main-tabbar-item {
    flex-grow: 1;
    display: flex;
    justify-content: center;
    align-items: center;
    opacity: 0.15;

  }

  .main-tabbar-item.current {
    opacity: 0.9;
  }
  .main-tabbar-wrapper{
    flex-shrink:0;
    position: relative;
    height: 52px;
  }
  .main-tabbar-wrapper .main-tabbar {
    background-color: #FAFAFA;
    position: fixed;
    bottom: 0;
    width: 100%;
    left: 0;
    height: 52px;
    border-top: #ccc solid 1px;
    display: flex;
    z-index: 200;
  }
</style>
<script>
  import DemandIcon from 'svg/main/Demand.vue'
  import MessageIcon from 'svg/main/Message.vue'
  import ProfileIcon from 'svg/main/Profile.vue'
  import SquareIcon from 'svg/main/Square.vue'
  export default{
    components: {
      DemandIcon,
      MessageIcon,
      ProfileIcon,
      SquareIcon
    },
    data(){
      return {

      }

    }
  }
</script>
