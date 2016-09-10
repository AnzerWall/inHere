<template>
  <div class="square-wrapper">
    <div class="square-container">
      <!--社团活动入口-->
      <div class="square-activity" :style="{'background-image': 'url('+activity.cover_img+')'}">
        <div class="activity-content-bg"></div>
        <div class="activity-content">
          <div class="square-activity-tittle">{{activity.tittle}}</div>
          <span>@{{activity.user_name}}&nbsp;&nbsp;&nbsp;&nbsp;{{activity.start_time}} ~ {{activity.end_time}}</span>
        </div>
      </div>

      <!--有。必。栏目-->
      <div>
        <div class="square-item" :style="{'color': color(1)}">
          <!--:style="color:itemColor.color1"-->
          <span class="square-item-tittle">* 有诺必行</span>
          <span>进入</span>
        </div>
        <square-slider :topics ="items[0].topic" :square_type="1"></square-slider>
      </div>


      <div>
        <div class="square-item" :style="{'color': color(2)}">
          <span class="square-item-tittle">* 有问必答</span>
          <span>进入</span>
        </div>
        <square-slider :topics="items[1].topic" :square_type="2"></square-slider>
        <div class="sub">
          <p :style="{'color': color(2)}">#{{items[1].question}}</p>
          <p>{{items[1].answer}}</p>
        </div>
      </div>

      <div>
        <div class="square-item" :style="{'color': color(3)}">
          <span class="square-item-tittle">* 有嘈必吐</span>
          <span>进入</span>
        </div>
        <square-slider :topics="items[2].topic" :square_type="3"></square-slider>
        <div class="sub">
          <p :style="{'color': color(3)}">#{{items[2].question}}</p>
          <p>{{items[2].answer}}</p>
        </div>
      </div>

      <div class="canteen-btn" @click="enterCanteen">深&nbsp;夜&nbsp;食&nbsp;堂</div>

    </div>
  </div>
</template>
<style scoped>

  .square-wrapper{
    font-size: 15px;
  }
  .square-container{
    width:100%;
  }
  .square-activity{
    position: relative;
    width: 100%;
    height: 238px;
    /*background-image: url("http://ww1.sinaimg.cn/mw690/6f924588gw1f32g8p67w6j20qo123wjv.jpg");*/
    background-size: cover;
    background-position: center;
  }
  .activity-content, .activity-content-bg{
    height: 47px;
    position: absolute;
    bottom: 0px;
    padding: 30px 0 15px 0;
    color: white;
  }
  .activity-content{
    padding-left: 15px;
  }
  .activity-content-bg{
    width: 100%;
    opacity: 0.75;
    background: linear-gradient(to top, rgba(0,0,0,1), rgba(0,0,0,0));
  }
  .square-activity-tittle{
    font-size: 1.5em;
    font-weight: bold;
    margin-bottom: 5px;
  }

  .square-item{
    /*width:100%;*/
    display: flex;
    justify-content: space-between;
    font-weight: bold;
    padding: 15px;
  }
  p{
    font-size: 0.9em;
    margin: 15px 0 7px;
    /* 保持三行  */
    overflow : hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-line-clamp: 3;
    -webkit-box-orient: vertical;
  }
  .sub{
    font-family: ".PingFang-SC-Default", "Helvetica Neue", Helvetica, "Microsoft Yahei",Arial, "Hiragino Sans GB", "Heiti SC", "WenQuanYi Micro Hei", sans-serif;;
    margin: 0 15px 0 30px;
  }
  .canteen-btn {
    margin: 23px;
    border-radius: 4px;
    border: solid 1px #d5d5d5;
    text-align: center;
    padding: 16px;
    color: #BCBCBC;
  }
</style>
<script type="text/ecmascript-6">
  import SquareSlider from '../../components/square-slider/slider.vue'
  import ColorValue from '../../util/color_constant.js'

export default{
  data:function (){
    return{
      activity:{
        cover_img:'http://ww1.sinaimg.cn/mw690/6f924588gw1f32g8p67w6j20qo123wjv.jpg',
        tittle:"小清新电影《一半》正在上映",
        user_name:"影视文化协会",
        start_time:"2016-7-12",
        end_time:"2016-8-31"
      },
      items:[
        {
          topic:[
            {title:"说说那些年做过的最尴尬的事情"},
            {title:"你们饭堂有什么奇葩的菜式"},
            {title:"说说那些年做过的最尴尬的事情"},
            {title:"你们饭堂有什么奇葩的菜式"},
            {title:"说说那些年做过的最尴尬的事情"},
            {title:"其他"}
          ]
        },
        {
          topic:[
            {title:"说说那些年做过的最尴尬的事情"},
            {title:"你们饭堂有什么奇葩的菜式"},
            {title:"说说那些年做过的最尴尬的事情"},
            {title:"你们饭堂有什么奇葩的菜式"},
            {title:"说说那些年做过的最尴尬的事情"},
            {title:"其他"}
          ],
          question:"你们的饭堂有什么奇葩的菜式?",
          answer:"本学期起我校全体学生用户的带宽统一为4M且无任何捆绑业务。上学期已完成了对大部分电信8m或20m捆绑手机用户的解绑 ..."
        },
        {
          topic:[
            {title:"说说那些年做过的最尴尬的事情"},
            {title:"你们饭堂有什么奇葩的菜式"},
            {title:"说说那些年做过的最尴尬的事情"},
            {title:"你们饭堂有什么奇葩的菜式"},
            {title:"说说那些年做过的最尴尬的事情"},
            {title:"其他"}
          ],
          question:"你的坑爹室友做过哪些让你意想不到的事情？",
          answer:"本学期起我校全体学生用户的带宽统一为4M且无任何捆绑业务。上学期已完成了对大部分电信8m或20m捆绑手机用户的解绑 ..."
        }
      ]
    }
  },
  methods:{
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
    enterCanteen(){
      var date =new Date();
      var hour=date.getHours();
      if (hour>=0 && hour<7){
        //    跳转到深夜餐厅 待完成

      }else {
        alert("深夜餐厅只在深夜12点到凌晨6点开放喔~");
      }
    }
  },
  components:{
    SquareSlider
  }
}
</script>
