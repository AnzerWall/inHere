<template>

  <div class="dating-detail">
    <div class="dating-key">{{key}}</div>
    <div class="dating-value"><input type="text" v-model="msg"></div>
  </div>

</template>

<style scoped>
  input{
    border-style: none;
    outline: none;
    text-align:right;
    padding-right: 20px;
  }
  .dating-detail{
    width:100%;
    padding: 28px 0;
    font-weight: bold;
    border-bottom: 1px solid #d5d5d5;
    display: flex;
    flex-wrap: nowrap;
    justify-content: space-between;
    align-content: center;
  }
  .dating-key{
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
        default:"备注"
      },
      msg:{
        type:String,
        default:""
      }
    }
  }
</script>
