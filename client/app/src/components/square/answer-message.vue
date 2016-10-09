<template>
    <div>
      <div class="message" >
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
            <div v-if="item.ext_data.comment_num!==0" class="bottom-have">
              <!--评论数-->
              <div class="comment">{{item.ext_data.comment_num}}</div><icon-comment-icon class="icon-comment"></icon-comment-icon>
              <!--点赞数-->
              <div class="like" :style="{color:message_color}">{{item.ext_data.praise}}</div><icon-like-icon @click="onclickpraise(item.ext_data,item.ext_data.best_reply.id)" :style="{fill:message_color}"></icon-like-icon>
            </div>
           <div class="bottom-null" v-if="item.ext_data.comment_num==0">
             [还没回答]
           </div>
          </div>
        </div>
      </div>

    </div>
</template>
<style>
  .message-title{
    margin: 20px 20px 10px;
    font-size: 16px;
    font-weight: bold;
    word-wrap: break-word;

  }
  .message-main {
    margin: 0 20px 10px;
    font-size: 14px;
    line-height: 20px;
    word-wrap: break-word;
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
  .bottom-have{
    display: flex;
    align-items: center;
  }
</style>
<script>
  import IconCommentIcon from '../../svg/common/comment/IconComment.vue';
  import IconLikeIcon from '../../svg/common/comment/IconLike.vue';
  import {fromNow} from '../../filter/time.js';
  import color from '../../util/praise_color.js';

    export default{
        data(){
            return{

            }
        },
        components:{
          IconCommentIcon,
          IconLikeIcon,
        },
      props:['item','color'],
      filters:{
        fromNow
      },
      methods:{
        onclickpraise(ext_data,id){
          console.log(ext_data,id);
          this.$emit('onclickpraise',ext_data,id);
        },
      },
      computed:{
        message_color(){
          return color.messageColor(this.item);
//
        }
      },
    }
</script>
