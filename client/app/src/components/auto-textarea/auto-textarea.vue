<template>
  <div class="auto-textarea-wrapper" v-el:autotextarea>
    <textarea  rows="1" cols=""  v-model="value"  v-el:hidden-textarea class="auto-textarea-hidden" ></textarea>
    <textarea rows="1" cols=""  v-el:textarea @input="resize" @focus="resize" @click="resize" @keyup="resize"
              class="auto-textarea" v-model="value" placeholder="{{placeholder}}" @keyup.enter="clickEnter"></textarea>
  </div>
</template>up
<style scoped>

  .auto-textarea-wrapper {
    /*position: fixed;*/
    background-color: transparent;
    border: none;
    outline: none;
    bottom: 0;
    box-sizing: border-box;
    width: 100%;

    /*border: solid 1px #ccc;*/
    /*position: relative;*/
  }
  .auto-textarea-hidden {
    position: absolute;
    opacity: 0;
    right: -9999px;
  }

  .auto-textarea, .auto-textarea-hidden  {
    box-sizing: border-box;
    resize: none;
    overflow: hidden;
    overflow-y:auto;
    padding: 0;
    width: 85%;
    margin: 15px 20px;
    background-color: transparent;
    border: none;
    outline: none;

  }
  .auto-textarea{
    /*transition: all 0.45s ;*/
  }

  .auto-textarea:focus,.auto-textarea-hidden:focus {
    outline: none;
  }

</style>
<script>
  var agt = navigator.userAgent.toLowerCase();
  var is_op = (agt.indexOf("opera") != -1);
  var is_ie = (agt.indexOf("msie") != -1) && document.all && !is_op;
  export default{
    props: {
      value: {//值
        type: String,
        default: ""
      },
      maxRow: {//最大行数
        type: Number,
        default: 2
      },
      height:{
        type:Number,
        default:0
      },
      placeholder:{
        type:String,
        default:""
      }
    },
    watch:{
      value(){
        this.resize();
      }
    },
    ready(){
      this.resize();
    },
    methods: {
      resize(){
        let hiddenTextarea =this.$els.hiddenTextarea;
        let textarea =this.$els.textarea;
        let autotextarea = this.$els.autotextarea;
        let lineNum=Math.min(3,Math.floor(hiddenTextarea.scrollHeight/hiddenTextarea.offsetHeight));

        let height=hiddenTextarea.offsetHeight*lineNum;
        textarea.style.height=height+'px';

        this.height=autotextarea.offsetHeight;
       // console.log(lineNum,height);
      //  console.log(hiddenTextarea.scrollHeight,hiddenTextarea.clientHeight);
//        let text = this.value;
//        let maxRow = this.maxRow;
//        let lineNum = text.split("\n");
//        var finaleLineNum = is_ie ? 1 : 0;
//        finaleLineNum += lineNum.length;
//        this.$els.textarea.rows = Math.min(finaleLineNum, maxRow);

      },
      clickEnter(){
        console.log("enter",this.value);
        this.$emit('enter',this.value);
      }
    },
    data(){
      return {}
    }
  }
</script>
