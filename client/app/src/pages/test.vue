<template>
<div class="test">
  <div class="div1">
    <div class="tag">10000000000000000000333333333333333</div>
    <div class="time">time</div>
  </div>
  <div class="div2">
    <div class="tt">SSSSSSSSS</div>
    <div class="tt">bbbb</div>
  </div>
  </div>

</template>
<style scoped>
.test{
  display: flex;
  justify-content: space-between;
}
.tt{
  padding-left: 5px;

}
.div1{
  flex-shrink: 1;
  flex-grow: 1;
  display: flex;
  overflow: hidden;
  justify-content: flex-start;
}
  .tag{
    overflow: hidden;
    text-overflow: ellipsis;
    flex-shrink: 1;
    flex-grow:0;
    padding-left: 5px;

  }
  .time{
    flex-shrink: 0;
    flex-grow:1;
    padding-left: 5px;
  }
  .div2{

    flex-shrink: 0;
    flex-grow: 0;
    display: flex;
    /*padding-left: 120px;*/
  }


</style>
<script type="text/ecmascript-6">
  //import fakeComment from '../api/fake/comment.js'
  export default{
    data(){
      return {
        labels:[{
          name:1,
          checked:false
        },{
          name:2,
          checked:false
        },{
          name:3,
          checked:false
        }]
      }
    }
  }
</script>
