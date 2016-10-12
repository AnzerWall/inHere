<template>
  <div class="aw-checkbox-wrapper" @click="check" :class="{'no-padding-tp':noPaddingTb}">
    <div class="aw-checkbox-circle"></div>
    <div class="aw-checkbox-circle-checked" v-show="checked" ></div>
    <div class="aw-checkbox-text" v-show="label.length!=0">{{label}}</div>
  </div>
</template>
<style scoped>
  .aw-checkbox-wrapper {
      display: flex;
      flex-direction: row;
      align-items: center;
    /*justify-content: center;*/
    padding: 10px;;
    position: relative;
  }
  .aw-checkbox-wrapper.no-padding-tp{
    padding-top: 0;
    padding-bottom: 8px;

  }
  .aw-checkbox-wrapper .aw-checkbox-circle {
    width: 20px;
    height: 20px;
    border: solid 2px #25d367;
    border-radius: 20px;
  }
  .aw-checkbox-wrapper .aw-checkbox-circle-checked{
    border-bottom: solid 3px #25d367;
    border-left: solid 3px #25d367;
    transform:rotate(-45deg);
    position: absolute;
    left:14px;
    top: 16px;
    width: 12px;
    height: 6px;

  }
  .aw-checkbox-wrapper.no-padding-tp .aw-checkbox-circle-checked{
    top: 6px;
  }
  .aw-checkbox-wrapper .aw-checkbox-text {
    padding-left: 15px;
    height: 24px;
    line-height: 24px;
    color: #25d367;
    font-weight: bold;
    font-size: 1.00rem;

  }
</style>
<script>

  export default{
    props:{
      checked:{
        type:Boolean,
        default:false
      },
      label:{
        type:String,
        default:""
      },
      noPaddingTb:{
        type:Boolean,
        default:false
      }
    },
    data(){
      return {}
    },
    components: {},
    methods:{
      check(){
        this.checked=!this.checked;
        this.$emit('check');
      }
    }
  }
</script>
