<template>
  <div class="admin-wrapper">
    <div class="admin-nav">
      <n3-nav theme="default" >
        <n3-nav-item :active="$route.name==='demand_list'">
          <a v-link="'/main/demand'">任务</a>
        </n3-nav-item>
        <n3-nav-item>
          <a v-link="{path:'/main/demand',activeClass:'active'}">活动</a>
        </n3-nav-item>
        <n3-nav-item>
          <a>有诺必行</a>
        </n3-nav-item>
        <n3-nav-item>
          <a>有槽必吐</a>
        </n3-nav-item>
        <n3-nav-item>
          <a>有问必答</a>
        </n3-nav-item>
        <n3-nav-item>
          <a>官方通知</a>
        </n3-nav-item>
        <n3-nav-item>
          <a>工具</a>
        </n3-nav-item>
        <n3-nav-item>
          <a>评论</a>
        </n3-nav-item>

      </n3-nav>
    </div>
    <div class="content">
      <router-view></router-view>
    </div>
</div>


</template>
<style>
  .admin-wrapper{
    display: flex;
    flex-direction: column;
    align-items: center;

  }
  .content{
    padding: 15px;
    margin-top: 15px;
    width: 92%!important;
    min-height: 300px;

    background: #ffffff;
    border-radius: 5px;
    box-shadow: 1px 1px 10px #ddd;
  }
  .admin-nav {
    width: 100%;
    padding: 0 50px;
    background-color: white;
  }
</style>
<script>

  export default{}
</script>
