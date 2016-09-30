<template>

  <div class="publish-detail">
    <div class="publish-key">{{key}}</div>
    <div class="publish-value"><input type="number" pattern="\d*" v-model="publish_value"></div>
  </div>

</template>

<style scoped>
  input{
    width: 100%;
    border-style: none;
    outline: none;
    text-align:right;
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
    padding: 0 0 0 18px;
    color: gray;
    opacity: 0.85;
  }
  .publish-value{
    width:68vw;
    padding-right: 18px;
  }
</style>
<script type="text/ecmascript-6">
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
        default:"备注"
      },
      publish_value:{
        type:String,
        required:true,
        default:""
      }
    }
  }
</script>
