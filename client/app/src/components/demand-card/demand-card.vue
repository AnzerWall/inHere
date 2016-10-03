<template>
<div class="demand-card-wrapper">
  <div class="demand-card" :style="wrapper_style">
    <div class="demand-card-head" :style="{color:main_color}" v-if="[TYPE_LOST,TYPE_FOUND,TYPE_DATING].indexOf(data.ext_type)!=-1&&!is_detail">
      <div v-if="data.ext_type===TYPE_LOST||data.ext_type===TYPE_FOUND" class="tag">{{chinese(data.ext_type)}}</div>
      <div v-if="data.ext_type===TYPE_LOST||data.ext_type===TYPE_FOUND" class="tag">{{data.ext_data.thing}}</div>
      <div v-if="data.ext_type===TYPE_DATING" class="tag">{{want_sex}}</div>
      <div v-if="data.ext_type===TYPE_DATING" class="tag">{{data.create_time|humanized}}</div>

    </div>
    <div class="demand-card-content">
      <div class="text">{{data.text}}</div>
      <div class="image-wrapper hide-scroll" v-if="data.photos&& data.photos.length!=0">
        <img class="image" v-for="item in data.photos" v-lazy="item.src" @click.stop="onClickImage($index)">
      </div>
      <div class="image-space" v-if="data.photos&&data.photos.length!=0">

      </div>

    </div>
    <div class="demand-card-tail">
      <div class="normal" v-if="[TYPE_EXPRESS,TYPE_SELL,TYPE_HELP,TYPE_DATING].indexOf(data.ext_type)!=-1">
        <div class="left">
          <div class="time" v-if="[TYPE_EXPRESS,TYPE_SELL,TYPE_HELP].indexOf(data.ext_type)!=-1">{{data.create_time|humanized}}</div>
          <div class="tag" v-if="[TYPE_EXPRESS,TYPE_SELL,TYPE_HELP].indexOf(data.ext_type)!=-1">{{'# '+chinese(data.ext_type)}}</div>
          <!--<div class="tag" v-if="data.ext_type==='dating'">参加人数: {{data.join_num}}人</div>-->

        </div>
        <div class="right">
          <div class="pay" v-if="TYPE_EXPRESS===data.ext_type&&!is_detail">{{'¥ '+data.ext_data.pay}}</div>
          <div class="pay" v-if="TYPE_SELL===data.ext_type&&!is_detail">{{'¥ '+data.ext_data.price}}</div>
          <div class="like-text" v-if="data.ext_type===TYPE_DATING">{{data.praise}}</div>
          <pay-icon v-if="[TYPE_EXPRESS,TYPE_SELL].indexOf(data.ext_type)!=-1&&!is_detail" class="icon"></pay-icon>
          <like-icon v-if="data.ext_type===TYPE_DATING&&!data.praised" class=" like-icon"></like-icon>
          <liked-icon v-if="data.ext_type===TYPE_DATING&&data.praised" class="like-icon"></liked-icon>
        </div>
      </div>

      <div class="btn" v-if="[TYPE_LOST,TYPE_FOUND].indexOf(data.ext_type)!=-1&&!is_detail">
        联系TA
      </div>
    </div>
  </div>
</div>





</template>
<style src="./demand-card.scss" lang="scss" scoped></style>

<script type="text/ecmascript-6">
  //时间处理库
  import moment from 'moment'
  //导入图标组件
  import PayIcon from 'svg/main/demand/Pay.vue';
  import LikeIcon from 'svg/main/demand/Like.vue';
  import LikedIcon from 'svg/main/demand/Liked.vue';
  import AddIcon from 'svg/main/demand/Add.vue';
  import {humanized,fromNow} from 'filter/time.js'
  import helper from '../../util/demand_helper.js'
  //定义主题颜色
//  const COLOR_DEMAND_BLUE = "#2397f3";
//  const COLOR_DEMAND_PINK = "#EC407A";
//  const COLOR_DEMAND_ORANGE = "#FFC107";
//  const COLOR_DEMAND_AOI = "#0CC998";
//  const COLOR_DEMAND_GREEN = "#04C830";
//  const COLOR_DEMAND_DISABLED="#cccccc";
  export default{
    filters:{
      humanized
    },
    components: {
      PayIcon,
      LikeIcon,
      LikedIcon,
      AddIcon
    },
    props: {
      is_detail:{
        type:Boolean,
        required:true,
        default:false
      },
      data: {
        type: Object,
        required: true,
        default: ()=>({
          id: 1,
          ext_type: 1,
          ext_data: {		//私有属性
            pay: 0//酬金
          },
          user_id:0,
          praise: 0,
          file: [],
          is_end: 0,
          text: "内容",
          create_time: Date.now(),
          update_time: Date.now()
        })
      }
    },
    data(){
      return {
        ...helper.Type
      }
    },
    methods: {
      onClickImage(index){
        if(this.data.photos&&this.data.photos.length)
        this.$emit("view-image",index,this.data.photos);
      },
      chinese(type){
        switch (type) {
          case this.TYPE_EXPRESS:
            return "快递";
          case this.TYPE_SELL:
            return "转让";
          case this.TYPE_HELP:
            return "帮忙";
          case this.TYPE_FOUND:
            return "捡到";
          case this.TYPE_LOST:
            return "丢失";
          case this.TYPE_DATING:
            return "约";
          default:
            return "其他"
        }
      }
    },
    computed: {
      main_color(){

        return helper.getMainColor(this.data)
      },
      want_sex(){
        let sex = this.data.ext_data.want_sex;
        switch (sex) {
          case 1:
            return '求男生';
          case 2:
            return '求女生';
        }
        return '性别不限'
      },
      wrapper_style(){
        let ret = {
          "background-color": this.main_color
        };



        return ret;
      }
    }
  }
</script>
