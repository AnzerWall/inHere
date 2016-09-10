<template>
    <div class="slider-wrapper">
      <div class="slider-header">
        <a class="hot-item" v-for="topic in topics" @click="goToTheTopic(topic.id, square_type)">
          <div class="slider-topic" :style="{opacity: countOpacity($index), 'background-color':color(square_type) }"># {{topic.title}}</div>
        </a>
      </div>
    </div>
</template>
<style scoped>
    .slider-wrapper {
      overflow: hidden;
      width: 100%;
      height: 97px;
      margin: 0 0 12px;
      /*z-index: 2;*/
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
      border-radius: 4px;
      color: white;
      padding: 12px;
      margin: 0px 5px;
    }
  .hot-item:last-child div{
    width: 90px;
  }
</style>
<script>
  import ColorValue from '../../util/color_constant.js'

export default{
  data(){
      return{
      }
  },
  methods:{
    countOpacity:function (index) {
      return 1-0.12*index;
    },
    color(square_type){
      switch (square_type){
        case 1:
          return ColorValue.COLOR_SQUARE_NUO;
        case 2:
          return ColorValue.COLOR_SQUARE_WEN;
        case 3:
          return ColorValue.COLOR_SQUARE_CAO;
        default:
          return ColorValue.COLOR_SQUARE_WEN;
      }
    },
    goToTheTopic(id, square_type){
      // 根据 square_type类型  和 id 拼接url ，点击跳转到相应的话题详情页面

    }
  },
  props:{
    topics:{
      type:Array,
      required: true,
      default: ()=>([
          {
            id: 1, //清单编号
            title: "清单标题"
          },
          {
            id: 2, //清单编号
            title: "清单标题"
          },
          {
            id: 3, //清单编号
            title: "清单标题"
          },
          {
            id: 4, //清单编号
            title: "清单标题"
          }
      ])
    },
    // 1 有诺必行  2 有问必答  3 有嘈必吐
    square_type:{
      type: Number,
      required:true,
      default:'1'
    }
  }
}
</script>
