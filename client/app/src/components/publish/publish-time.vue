<!--时间选择组件-->
<template>

  <div class="publish-detail">
    <div class="publish-key">{{key}}</div>
    <div class="publish-value"><input type="text" v-model="publish_value"></div>
  </div>

</template>

<style scoped>
  input{
    border-style: none;
    outline: none;
    text-align:right;
    padding-right: 20px;
  }
  .publish-detail {
    width:100%;
    padding: 28px 0;
    font-weight: bold;
    border-bottom: 1px solid #d5d5d5;
    display: flex;
    flex-wrap: nowrap;
    justify-content: space-between;
    align-content: center;
  }
  .publish-key {
    padding: 0 18px;
    color: gray;
    opacity: 0.85;
  }
</style>
<script>
  export default{
    data(){
      return{
      }
    },
    components:{
    },
    props:{
      key:{
        type:String,
        required:true,
        default:"时间"
      },
      publish_value:{
        type:String,
        required:true,
        default:""
      }
    }
  }
</script>

