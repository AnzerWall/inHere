<template>
  <div>
    <div class="main-content">
      <router-view></router-view>
    </div>

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
</template>
<style scoped>
  .main-content {
    padding-bottom: 53px;
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

  .main-tabbar {
    position: fixed;
    bottom: 0;
    width: 100%;
    left: 0;
    height: 52px;
    border-top: #ccc solid 1px;
    display: flex;

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
