<template>
    <div class="slider-wrapper">
      <div class="slider-header">
        <a class="hot-item" v-for="topic in topics">
          <div class="slider-topic" :style="{opacity: countOpacity($index)}"># {{topic.message}}</div>
        </a>
      </div>
    </div>
</template>
<style scoped>
    .slider-wrapper {
      overflow: hidden;
      width: 100%;
      height: 97px;
      margin: 12px 0px;
      z-index: 2;
    }
    .slider-header {
      display: flex;
      flex-direction: row;
      flex-wrap: nowrap;
      overflow: auto;
      height: 120px;
    }
    .hot-item:first-child div{
      margin-left: 30px;
    }
    .slider-topic {
      height: 73px;
      width: 120px;
      border-radius: 10px;
      color: fuchsia;
      opacity: 1;
      padding: 12px;
      margin: 0px 5px;
      background-color: white;
    }
</style>
<script>
export default{
  data(){
      return{
          msg:'hello vue'
      }
  },
  methods:{
    countOpacity:function (index) {
      return 1-0.1*index;
    }
  },
  props:{
    topics:{
      required: true
    },
//    topicColor:{
//      type: String,
//      required:true,
//      default:'blue'
//    }
  }
}
</script>
