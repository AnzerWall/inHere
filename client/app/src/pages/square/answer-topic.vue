<template>
  <div class="response"v-if="!$loadingRouteData">
    <div class="answer-topic">
      <!--头部-->
      <div class="answer-header">
        <div class="answer-header-name">
          <div class="header-top" @click="back">
            《 有问必答
          </div>
        </div>
        <div class="answer-header-label_name" >
          <div class="header-bottom">
            #{{items[0].label_name}}
          </div>
        </div>

      </div>
      <!--中部-->
      <div class="answer-topic-center">
        <div class="message" v-for="item in items">
          <!--问题标题-->
          <div class="message-title" @click="$router.go('/answer-detail/'+item.id)">
            {{item.ext_data.title}}
          </div>
          <!--最火回答-->
          <div class="message-main" v-if="item.ext_data.best_reply!=undefined" @click="$router.go('/answer-detail/'+item.id)">
            {{item.ext_data.best_reply.best_answer}}
          </div>
          <div class="message-bottom">
            <div class="bottom-left">
              <!--标签名-->
              <div class="identity" :style="{backgroundColor:color}">#{{item.label_name}}</div>
              <div class="dian">·</div>
              <!--发布时间-->
              <div class="time" :style="{color:color}">{{item.create_time|fromNow}}</div>
            </div>
            <div class="bottom-right">
              <!--评论数-->
              <div class="comment">{{item.ext_data.comment_num}}</div><icon-comment-icon class="icon-comment"></icon-comment-icon>
              <!--点赞数-->
              <div class="like">{{item.ext_data.praise}}</div><icon-like-icon></icon-like-icon>

            </div>
          </div>
        </div>
      </div>


    </div>
  </div>
  <div v-if="$loadingRouteData" class="answer-topic-loading-area">
    <pulse-loader color="rgb(38, 162, 255)" size="12px"></pulse-loader>
  </div>
</template>
<style>
  .answer-topic{
    position: relative;
    width: 100%;
  }
  .answer-header{
    width: 100%;
    color: #ffffff;

  }
  .answer-header-name{
    background-color: #0274FF;
    position: fixed;
    top: 0;
    z-index: 1;
    width: 100%;

  }
  .header-top{
    display: flex;
    height: 60px;
    align-items: center;
    margin-left: 10px;
    font-size: 16px;

  }
  .answer-header-label_name{
    background: linear-gradient(to bottom, #0274FF ,#5FA7FF );
    width: 100%;

  }
  .header-bottom{

    width: 50%;
    height: 50px;
    margin-left: 20px;
    line-height: 20px;

  }
  .answer-topic-center{
    width: 100%;
  }
  .message-title{
    margin: 20px 20px 10px;
    font-size: 16px;
    font-weight: bold;

  }
  .message-main{
    margin: 0 20px 10px;
    font-size: 14px;
    line-height: 20px;
  }
  .message-photo {
    display: flex;
    /*padding: 0 10px ;*/
    overflow: auto;
    width: 100%;
    position: relative;


  }

  .message-photo .image-wrapper{
    display: flex;
    flex-direction: row;
    flex-wrap: nowrap;
    position: absolute;
    left: 0;
    padding: 0 25px 0 15px;


  }


  .message-photo .image-space {
    height: 130px;
    width:100%;
    padding-bottom: 15px;

  }
  .message-photo .image {
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


  .message-bottom{
    width: 100%;
    display: flex;
    justify-content: space-between;
    font-size: 14px;
    margin-top: 10px;
    margin-bottom: 15px;
    padding-bottom: 20px;
    border-bottom:solid 1px #cccccc ;
  }
  .message-bottom .bottom-left{
    display: flex;
    align-items: center;
    margin-left: 20px;
    overflow:hidden;
    flex-shrink: 1;
    flex-grow: 1;
    justify-content: flex-start;
    margin-right: 15px;
  }
  .message-bottom .bottom-left .identity{
    color: #ffffff;
    padding: 3px 7px;
    border-radius: 5px;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
    flex-shrink: 1;
    flex-grow:0;
  }
  .message-bottom .bottom-left .dian{
    padding: 3px 5px;
    flex-shrink: 0;
    flex-grow:0;
  }
  .message-bottom .bottom-left .time{
    flex-shrink: 0;
    flex-grow:1;
  }
  .message-bottom .bottom-right{
    flex: none;
    color: #cccccc;
    margin-right: 20px;
    display: flex;
    align-items: center;
  }
  .message-bottom .bottom-right .comment{
    margin-top: 6px;
  }
  .message-bottom .bottom-right .like{
    margin-top: 6px;
  }
  .message-bottom .bottom-right .icon-comment{
    margin-right: 15px;
  }
  .answer-topic-loading-area {
    display: flex;
    justify-content: center;;
    margin-top: 200px;
  }

</style>
<script type="text/ecmascript-6">
  import IconCommentIcon from '../../svg/common/comment/IconComment.vue';
  import IconLikeIcon from '../../svg/common/comment/IconLike.vue';
  import PhotosWipe from '../../components/photoswipe/photoswipe.vue'
  import {fromNow} from '../../filter/time.js';
  import PulseLoader from 'vue-spinner/src/PulseLoader.vue';

    export default{
        data(){
            return{

            }
        },
      route:{
        data(){
          var token="4121581213c1605a1db4872d7cca6eed1b41259bffd8066d9573783b07214d6f";
          return this.$request
            .get("http://115.28.67.181:8080/ask_reply/?ext_type=12")
            .query({token:token})
            .query({
              label_id:this.$route.query.label_id
            })
            .then(this.$api.checkResult)
            .then((data)=>{
              this.items=data.list.items
            })


        }

      },
        components:{
          IconCommentIcon,
          IconLikeIcon,
          PhotosWipe,
          PulseLoader

        },
      methods:{
        back(){
          window.history.back()
        }
      },
      filters:{
        fromNow
      },
      computed: {
        color(){
          return "#246BFE"
        }
      },
    }
</script>
