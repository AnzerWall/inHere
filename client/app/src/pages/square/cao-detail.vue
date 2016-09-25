<template>
  <div v-if="!$loadingRouteData">
    <!--点击图片放大组件-->
    <photos-wipe v-ref:viewer></photos-wipe>
    <div class="cao-detail">
      <div class="cao-detail-head">
        <div class="head-top">
          <div class="head-top-left"><span @click="back">《 有槽必吐</span></div>
          <div class="head-top-right"><menu-icon></menu-icon></div>
        </div>
        <div class="head-label">
          <div class="head-label-top">
            <!--标签名-->
            <div class="label-top-name">#{{data.label_name}}</div>
            <div class="label-top-dian">·</div>
            <!--发表时间-->
            <div class="label-top-time">{{data.create_time|fromNow}}</div>
          </div>
          <div class="head-label-bottom">
            <!--标签内容-->
            {{ext_data.content}}
          </div>
          <!--图片-->
          <div class="head-image hide-scroll">
            <div class="image-wrapper " v-if="images&& images.length!=0"  >
              <img class="image" v-for="image in images" v-lazy="image.src" @click.stop="onClickImage($index)">
            </div>
            <div class="image-space" v-if="images&& images.length!=0"></div>
          </div>

        </div>

      </div>
      <div  class="cao-detail-center">
        <comment :comments="comments" :number="number" :user_id="user_id" :main_color="main_color"></comment>
      </div>
      <div class="cao-detail-foot"></div>
      <div class="detail-foot">
        <input  class="text" type="text" placeholder="世界不如人意,人生如此艰难">
      </div>
    </div>

  </div>
  <!--圆点过度-->
  <div v-if="$loadingRouteData" class="cao-detail-loading-area">
    <pulse-loader color="rgb(38, 162, 255)" size="12px"></pulse-loader>
  </div>
</template>
<style>
  .cao-detail{
    font-size: 16px;
  }
  .cao-detail-head{
    width: 100%;
    color: #ffffff;
    position: relative;
  }
  .head-top{
    width: 100%;
    height: 60px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    background-color: #F91C88;
    position: fixed;
    top: 0;
    z-index: 1;
  }
  .head-top-left{
    margin-left: 15px;
  }
  .head-top-right{
    margin-right: 20px;
    fill: #ffffff;
  }
  .head-label{
    font-size: 14px;
    padding-top: 3px;
    padding-bottom: 15px;
    background: linear-gradient(to bottom, #F91C88 ,#FF5FAD );
    margin-top: 60px;
  }
  .head-label-top{
    display: flex;
    margin-left: 20px;
    overflow: hidden;
    flex-shrink: 1;
    flex-grow: 1;
    justify-content: flex-start;
  }
  .head-label-top .label-top-name{
    color: #F91C88;
    background: #ffffff;
    padding: 3px 7px;
    border-radius: 5px;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
    flex-shrink: 1;
    flex-grow:0;
  }
  .head-label-top .label-top-dian{
    padding: 3px 5px;
    flex-shrink: 0;
    flex-grow:0;
  }
  .head-label-top .label-top-time{
    padding: 3px 0;
    flex-shrink: 0;
    flex-grow: 1;
    padding-right: 20px;
  }
  .head-label-bottom{
    margin: 10px 20px 0px;
  }
  .head-image{
    position: relative;
    display: flex;
    overflow: auto;
    width: 100%;
  }
  .head-image .image-wrapper{
    display: flex;
    flex-direction: row;
    flex-wrap: nowrap;
    position: absolute;
    left: 0;
    padding: 0 25px 0 15px;
  }
  .image-space {
    height: 130px;
    width:100%;
    padding-bottom: 15px;

  }
  .image {
    border: solid 3px white;
    box-shadow: 0px 2px 16px rgba(0, 0, 0, .2);
    margin-left: 10px;
    margin-bottom: 5px;
    margin-top: 5px;
    height: 130px;

  }
  .hide-scroll::-webkit-scrollbar,
  .hide-scroll::-webkit-scrollbar-track,
  .hide-scroll::-webkit-scrollbar-thumb {
    width: 0;
    border-width: 0;
    height: 0;
  }
  .cao-detail-center{
    margin-bottom: 50px;
  }
  .detail-foot{
    width: 100%;
    position: fixed;
    bottom: 0px;
    border-top:solid 1px #cccccc ;
  }
  .text{
    width: 100%;
    height: 50px;
    outline: none;
    padding-left: 15px;

  }

  input::-webkit-input-placeholder {

    color:#cccccc;
  }
  input::-moz-placeholder {
    color:#cccccc;
  }
  .cao-detail-loading-area {
    display: flex;
    justify-content: center;;
    margin-top: 200px;
  }

</style>
<script type="text/ecmascript-6">
  import MenuIcon from '../../svg/common/Menu.vue';
  import {fromNow} from '../../filter/time.js';
  import Comment from '../../components/comment/comment.vue';
  import PhotosWipe from '../../components/photoswipe/photoswipe.vue';
  import PulseLoader from 'vue-spinner/src/PulseLoader.vue';

    export default{
        data(){
          return{
            data:{},
            number:1,
            user_id:"",
            ext_data:{},
            comments:[],
            images:[]

          }

        },
      route:{
        data(){
          var id=this.$route.params.id;
          var token='4121581213c1605a1db4872d7cca6eed1b41259bffd8066d9573783b07214d6f'
          return this.$request
            .get('http://115.28.67.181:8080/ask_reply/'+id)
            .query({token:token})
            .then(this.$api.checkResult)
            .then((data=>{
              console.log(data);
              this.data=data;
              this.ext_data=data.ext_data;
              this.images=data.ext_data.photos;
              this.comments=data.reply_list.items;
              this.user_id=data.user_id;
              }))
        }
      },
        components: {
          MenuIcon,
          Comment,
          PhotosWipe,
          PulseLoader
        },
      filters:{
        fromNow
      },
      computed:{
        main_color(){
          return "#F91D89"
        }
      },
      methods:{
        onClickImage(index){
          this.$refs.viewer.show(index,this.images)
        },
        back(){
          window.history.back()
        }
      }
    }
</script>
