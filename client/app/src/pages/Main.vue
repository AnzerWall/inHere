<template>
  <div>
    <div class="main-content">
      <router-view></router-view>
    </div>

    <div class="main-tabbar">
      <div class="main-tabbar-item" :class="{'current':current_tab===0}" @click="nav(0)">
        <i class="fa fa-tasks" aria-hidden="true"></i>
      </div>
      <div class="main-tabbar-item" :class="{'current':current_tab===1}" @click="nav(1)">
        <i class="fa fa-th-large" aria-hidden="true"></i>
      </div>
      <div class="main-tabbar-item" :class="{'current':current_tab===2}" @click="nav(2)">
        <i class="fa fa-commenting" aria-hidden="true"></i>
      </div>
      <div class="main-tabbar-item" :class="{'current':current_tab===3}" @click="nav(3)">
        <i class="fa fa-user" aria-hidden="true"></i>
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
    font-size: 18px;
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

  export default{
    data(){
      return {
        current_tab:-1
      }

    },
    methods: {
      nav(index){
        if (this.current_tab != index) {
          this.current_tab = index;
          let router=this.$route.router;
          switch (index) {
            case 0:
              router.go("/demand");
              break;
            case 1:
              router.go("/square");
              break;
            case 2:
              router.go("/message");
              break;
            case 3:
              router.go("/profile");
              break;
          }
        }

      }
    }
  }
</script>
