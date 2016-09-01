<template>
  <div class="demand-card-wrapper" :style="wrapper_style">
    <div class="demand-card-head" :style="{color:main_color}" v-if="['found','lost','dating'].indexOf(tag)!=-1">
      <div v-if="tag==='found'" class="tag">{{chinese(tag)}}</div>
      <div v-if="tag==='lost'" class="tag">{{chinese(tag)}}</div>
      <div v-if="tag==='lost'||tag==='found'" class="tag">{{data.thing}}</div>
      <div v-if="tag==='dating'" class="tag">{{want_sex}}</div>
      <div v-if="tag==='dating'" class="tag">{{humanized(data.time)}}</div>

    </div>
    <div class="demand-card-content">
      <div class="text">{{data.text}}</div>
      <div class="image-wrapper hide-scroll"  v-if="data.images&& data.images.length!=0">
        <img  class="image" v-for="item in data.images" v-lazy="item.src">
      </div>
      <div class="image-space" v-if="data.images&&data.images.length!=0">

      </div>

    </div>
    <div class="demand-card-tail">
      <div class="normal" v-if="['express','sell','help','dating'].indexOf(tag)!=-1">
        <div class="left">
          <div class="time"v-if="['express','sell','help'].indexOf(tag)!=-1">{{humanized(create_time)}}</div>
          <div class="tag"v-if="['express','sell','help'].indexOf(tag)!=-1">{{'# '+chinese(tag)}}</div>
          <div class="tag"v-if="tag==='dating'">参加人数: {{data.join_num}}人</div>

        </div>
        <div class="right">
          <div class="pay" v-if="['express','sell'].indexOf(tag)!=-1">{{'¥'+data.pay}}</div>
          <div class="like-text" v-if="tag==='dating'">{{data.like_num}}</div>
          <pay-icon v-if="['express','sell'].indexOf(tag)!=-1" class="icon"></pay-icon>
          <like-icon v-if="tag==='dating'&&!data.liked" class=" like-icon"></like-icon>
          <liked-icon v-if="tag==='dating'&&data.liked" class="like-icon"></liked-icon>
        </div>
      </div>

      <div class="btn" v-if="['lost','found'].indexOf(tag)!=-1">
          联系TA
      </div>
    </div>
  </div>
</template>
<style src="./demand-card.scss" lang="scss" scoped>

</style>
<script type="text/ecmascript-6">
  import moment from 'moment'
  import PayIcon from 'svg/main/demand/Pay.vue';
  import LikeIcon from 'svg/main/demand/Like.vue';
  import LikedIcon from 'svg/main/demand/Liked.vue';
  import AddIcon from 'svg/main/demand/Add.vue';

  const COLOR_DEMAND_BLUE = "#2397f3";
  const COLOR_DEMAND_PINK = "#EC407A";
  const COLOR_DEMAND_ORANGE = "#FFC107";
  const COLOR_DEMAND_AOI = "#0CC998";
  const COLOR_DEMAND_GREEN = "#04C830";

  export default{
    components: {
      PayIcon,
      LikeIcon,
      LikedIcon,
      AddIcon
    },
    props: {
      tag: {
        type: String,
        required: true,
        default: "express"
      },
      data: {
        type: Object,
        required: true,
        default: ()=>({})
      },
      create_time: {
        type: Number,
        required: true,
        default: Date.now
      }
    },
    data(){
      return {}
    },
    methods: {
      humanized(time){
        return moment(time).format('h:mm A');
      },
      chinese(tag){
        switch (tag) {
          case 'express':
            return "快递";
          case 'sell':
            return "转让";
          case 'help':
            return "帮忙";
          case 'found':
            return "捡到";
          case 'lost':
            return "丢失";
          case 'dating':
            return "约";
          default:
            return "其他"
        }
      }
    },
    computed: {
      main_color(){
        let tag = this.tag;
        let data=this.data;
        if (tag === "express")return COLOR_DEMAND_BLUE;
        else if (tag === "sell")return COLOR_DEMAND_ORANGE;
        else if (tag === "help")return COLOR_DEMAND_PINK;
        else if (tag === "lost")return COLOR_DEMAND_GREEN;
        else if (tag === "found")return COLOR_DEMAND_BLUE;
        else if (tag === "dating") {
          if (data.want_sex === 1)return COLOR_DEMAND_BLUE;
          else if (data.want_sex === 2)return COLOR_DEMAND_PINK;
          else return COLOR_DEMAND_ORANGE;
        }
      },
      want_sex(){
        let sex=this.data.want_sex;
        switch(sex){
          case 1:return '求男生';
          case 2:return '求女生';
        }
        return '性别不限'
      },
      wrapper_style(){
        let ret = {
          "background-color":this.main_color
        };

        let data = this.data;

        return ret;
      }
    }
  }
</script>
