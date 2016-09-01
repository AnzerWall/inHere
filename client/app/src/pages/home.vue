<template>
  <div id="navTop">
    <label>任务</label>
  </div>
  <img src="../assets/logo.png">
  <div id="navBottom">
    <div class="navBottomLi">
      <img src="../assets/logo.png" />
      <img src="../assets/logo.png" />
      <img src="../assets/logo.png" />
      <img src="../assets/logo.png" />
    </div>
  </div>
</template>
<style scoped>
  #navBottom{
    position: absolute;
    bottom: 0;
    height: 45px;
    width: 100%;
  }
  .navBottomLi{
    display: -webkit-flex; /* Safari */
    -webkit-justify-content: space-around; /* Safari 6.1+ */
    display: flex;
    justify-content: space-around;
  }
  .navBottomLi img{
    flex-direction: row;
    height: 38px;
  }

</style>
<script type="text/ecmascript-6">
  export default{


    data:function () {
      return{

      }
    }
  }
</script>
