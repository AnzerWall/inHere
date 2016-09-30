<template>
  <div>
    <!--图片放大插件-->
    <photos-wipe v-ref:viewer></photos-wipe>
    <!--头部-->
    <div class="change">
      <div class="ar-head">
        <div class="ard-left"><span @click="back">《&nbsp有问必答</span></div>
        <menu class="ard-right"></menu>
      </div>
      <div class="ard-head">
        <div class="ard-ask">
          <div class="ard-tt">
            <div class="ard-tag hide">#{{data.label_name}}</div>
            <div class="ard-time"><span class="ard-spot">·</span>{{data.create_time|fromNow}}</div>
          </div>
          <div class="ard-data">
            <div class="ard-title ">{{ext_data.title}}</div>
            <div class="ard-content">{{ext_data.content}}</div>
            <div class="ard-image hide-scroll">
              <div class="ard-image-wrapper " v-if="photos&& photos!=0"  >
                <img class="ard-data-image" v-for="photo in photos" v-lazy="photo.src" @click.stop="onClickImage($index)">
              </div>
              <div class="ard-image-space" v-if="photos&& photos.length!=0"></div>
            </div>
            <div class="ard-attention"><span class="attention-font">关&nbsp&nbsp注&nbsp&nbsp问&nbsp&nbsp题</span></div>
          </div>
        </div>

      </div>
    </div>
    <!--评论组件-->
    <div class="ard-reply" :style="{marginBottom:bottomHeight+'px'}">
      <comment :comments="comments" :main_color="main_color" :user_id="user_id" :number="number"></comment>
    </div>
    <!--评论输入-->
    <div class="answer-detail-foot">
      <auto-textarea :height.sync="bottomHeight" :placeholder="placeholder" :value.sync="content" @enter="submit(this.$request,content,this.data.id,this.ext_type)" ></auto-textarea>
      <!--<input  class="text" type="text" placeholder="世界不如人意,人生如此艰难">-->
    </div>
  </div>
  <div v-if="$loadingRouteData" class="answer-detail-loading-area">
    <pulse-loader color="rgb(38, 162, 255)" size="12px"></pulse-loader>
  </div>
</template>
<style scoped>
  .ar-head {
    margin: 0;
    padding: 0;
    position: fixed;
    width: 100%;
    height: 60px;
    background-color: #0274FF;
    z-index: 200;
    display: flex;
    justify-content: space-between;
  }

  .ard-left {
    width: 50%;
    margin-top: 20px;
    margin-left: 6px;
    font-weight: bold;
    color: white;
  }

  .ard-right {
    width: 20px;
    height: 20px;
    fill: white;
    margin-top: 20px;
    margin-right: 20px;
  }

  .ard-head {
    position: relative;
    overflow: auto;
  }

  .ard-ask {
    margin-top: 60px;
    background-color: #167fff;
    padding: 5px 0px 20px ;
    z-index: 1;
  }

  .ard-tt {
    display: flex;
    white-space: nowrap;
    margin: 0 20px;
  }

  .ard-tag {
    padding: 5px 10px 5px 8px;
    border-radius: 5px;
    background-color: white;
    color: #167fff;
    font-weight: bold;

  }

  .ard-time {
    height: 30px;
    line-height: 30px;
    max-height: 30px;
    width: 50%;
    font-weight: bold;
    color: white;
  }

  .ard-data {
    width: 100%;
    margin-top: 10px;


  }

  .ard-title {
    font-weight: bold;
    color: white;
    margin: 0 20px;
  }

  .hide {
    overflow: hidden;
    text-overflow: ellipsis;
    height: 20px;
    line-height: 20px;
    max-height: 20px;

  }

  .ard-content {

    color: white;
    margin: 10px 20px 0;
  }

  .ard-attention {
    font-weight: bold;
    margin: 25px auto 0px;
    width: 90%;
    height: 45px;
    border-radius: 5px;
    display: flex;
    align-items: center;
    justify-content: center;
    background-color: rgba(0, 0, 0, .2);
  }

  .attention-font {
    color: white;
  }

  .ard-ask {
    background: linear-gradient(to bottom, #0274FF, #5FA7FF);
  }

  .ard-end{
    padding-left: 20px;
    margin: 0;
    position: fixed;
    bottom: 0;
    width: 100%;
    border-top:solid 1px #ececec;
    border-left: 0;
    z-index: 1;
    background: #ffffff;

  }

  .ard-spot{
    margin-left: 8px;
    margin-right: 8px;
  }

  .answer-detail-loading-area {
    display: flex;
    justify-content: center;;
    margin-top: 200px;
  }
   .ard-image{
    display: flex;
    /*padding: 0 10px ;*/
    overflow: auto;
    width: 100%;position: relative;

  }
   .ard-image-wrapper{
    display: flex;
    flex-direction: row;
    flex-wrap: nowrap;
    position: absolute;
    left: 0;
    padding: 0 25px 0 15px;
  }
  .ard-image-space {
    height: 130px;
    width:100%;
    padding-bottom: 15px;
  }
  .ard-data-image {
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
  .answer-detail-foot{
    position: fixed;
    bottom: 0;
    background: #ffffff;
    z-index: 1;
    width: 100%;
    /*padding: 10px 0px 10px 20px;*/
    border-top: solid 1px #cccccc;
  }
</style>
<script>
  import Menu from 'svg/common/Menu.vue'
  import Comment from '../../components/comment/comment.vue'
  import {fromNow} from 'filter/time.js';
  import PulseLoader from 'vue-spinner/src/PulseLoader.vue';
  import PhotosWipe from '../../components/photoswipe/photoswipe.vue';
  import post from '../../util/comment_post.js';
  import AutoTextarea from '../../components/auto-textarea/auto-textarea.vue'
  export default{
    filters: {
      fromNow
    },
    route: {
      data(){
        var id = this.$route.params.id;
        var token = "4cc763b9496fda23941fbaa4af298fc3b32ed8f3f44f19f2585bc4f0ac51e103";
        return this.$request
          .get('http://115.28.67.181:8080/ask_reply/' + id)
          .query({token: token})
          .query({ext_type: 12})
          .then(this.$api.checkResult)
          .then(function (data) {
            return {
              data: data,
              ext_data:data.ext_data,
              comments: data.reply_list.items,
              user_id: data.user_id,
              photos:data.ext_data.photos,
              ext_type:data.ext_type,

            }
          })
      }
    },
    data(){
      return {
        comments: [],
        user_id:'',
        data:{},
        number:1,
        ext_data:[],
        photos:[],
        ext_type:0,
        placeholder:'说点什么?',
        content:"",
        bottomHeight:0,
      }
    },
    components: {
      Menu,
      Comment,
      PulseLoader,
      PhotosWipe,
      AutoTextarea
    },
    computed: {
      main_color: function () {
        return "#0274FF"
      }
    },
    methods:{
      back(){
        window.history.back()
      },
      onClickImage(index){
        this.$refs.viewer.show(index,this.photos);
      },
      submit(request,content,id,ext_type){
        console.log(content);
        return post.post(request,content,id,ext_type,this);
      }
    }
  }
</script>
