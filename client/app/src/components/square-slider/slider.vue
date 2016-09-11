<template>
    <div class="slider-wrapper" :style="{'background-color':wrapper_color(square_type)}">
      <div class="slider-header">
        <a class="hot-item" v-for="topic in topics" @click="goToTheTopic(topic.id, square_type)">
          <div class="slider-topic" :style="{opacity: countOpacity($index), 'background-color':color(square_type), 'color':wrapper_color(square_type) }">
            <span># {{topic.title}}</span>
          </div>
        </a>
      </div>
    </div>
</template>
<style scoped>
    .slider-wrapper {
      overflow: hidden;
      width: 100%;
      height: 97px;
      padding: 0 0 12px;
    }
    .slider-header {
      display: flex;
      flex-direction: row;
      flex-wrap: nowrap;
      overflow: auto;
      height: 120px;
      overflow-y: hidden;
    }
    .hot-item:first-child div{
      margin-left: 30px;
    }
    .hot-item:last-child div{
      margin-right: 15px;
    }
    .slider-topic {
      height: 73px;
      width: 120px;
      border-radius: 4px;
      padding: 12px;
      margin: 0px 5px;
      font-weight: bold;
      text-align: start;
    }
    span{
      /* 保持三行  */
      overflow : hidden;
      text-overflow: ellipsis;
      display: -webkit-box;
      -webkit-line-clamp: 4;
      -webkit-box-orient: vertical;
    }
    .hot-item:last-child div{
      width: 73px;
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
          return ColorValue.COLOR_WHITE;
      }
    },
    wrapper_color(square_type){
      switch (square_type){
        case 1:
        case 2:
        case 3:
          return 'white';
        case 4:
          return ColorValue.COLOR_CAO_PINK;
        default:
          return 'white';

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
    // 1 有诺必行  2 有问必答  3 有嘈必吐  4 有嘈必吐详情页
    square_type:{
      type: Number,
      required:true,
      default:'1'
    }
  },
  computed:{
  }
}
</script>
