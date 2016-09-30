<template>
      <div v-if="$loadingRouteData" class="answer-loading-area">
        <pulse-loader color="rgb(38, 162, 255)" size="12px"></pulse-loader>
      </div>
  <div class="response"v-if="!$loadingRouteData">
      <div class="answer">

        <!--头部-->
        <div class="answer-head">
          <div class="top">
            <div class="left" @click="$router.go('/square')">
              <span>《&nbsp;&nbsp;有问必答</span>
            </div>
            <div class="right">

              <prfile-icon class="prfile"></prfile-icon>
              <add-icon class="add"></add-icon>
            </div>
          </div>
          <classify :lists="labels" class="classify" :color="color" @filter-label="filterLabel">
          </classify>
        </div>
        <!--中部-->
        <div class="answer-center">
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

</template>
<style scoped>
  .answer{
    position: relative;
  }
  .answer-head{
    width: 100%;


  }
  .answer-head .top{
    display: flex;
    justify-content: space-between;
    width: 100%;
    height: 60px;
    align-items: center;
    font-weight: bold;
    color: white;
    background-color: #0274FF;
    position: fixed;
    top: 0;
    left: 0;
    z-index: 1;
  }
  .top .right{
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-right: 18px;

  }
  .top .left{
    margin-left: 15px;
  }
  .top .right .prfile{
    padding: 0px 20px;
  }
  .top .right .add{
    padding: 0px 5px;
    fill: #ffffff;
  }
  .classify{
    margin-top: 60px;
    background: linear-gradient(to bottom, #0274FF ,#5FA7FF );
  }
  .answer-center{
    width: 100%;

  }
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
  .answer-loading-area {
    display: flex;
    justify-content: center;;
    margin-top: 200px;
  }


</style>
<script type="text/ecmascript-6">
  import AddIcon from '../../svg/main/demand/Add.vue'
  import PrfileIcon from '../../svg/common/Profile.vue';
  import Classify from '../../components/square/classify.vue';
  import IconCommentIcon from '../../svg/common/comment/IconComment.vue';
  import IconLikeIcon from '../../svg/common/comment/IconLike.vue';
  import {fromNow} from '../../filter/time.js';
  import PulseLoader from 'vue-spinner/src/PulseLoader.vue';

    export default{
      data(){
        return {
//              lists:[{
//                topic:[
//                  {id: 11,title:"小道消息"},
//                  {id: 11,title:"老师"},
//                  {id: 11,title:"宿舍"},
//                  {id: 11,title:"官方消息"},
//                  {id: 11,title:"说说那些情"},
//                  {id: 11,title:"更多"}
//                ],
//              }
//              ],
//              items:[{
//                title:'大学宿舍没空调是一种怎样的感受吧?',
//                main:'这里真是热啊积分撒娇了房间爱啥感觉欧萨积分撒娇估计了价格拉我家领导说几个垃圾是',
//                identity:'宿舍',
//                time:'1分钟前',
//                comment:'233',
//                like:'344',
//                images: [
//                  {
//
//                    src: 'http://ww4.sinaimg.cn/mw690/e910bd00jw1f7c80cfvyoj20qo0zk75i.jpg',
//                    w: 360,
//                    h: 480
//
//                  },
//                  {
//                    src: 'http://ww1.sinaimg.cn/mw690/e910bd00jw1f7c1ndknjwj20qo0zktat.jpg',
//                    w: 690,
//                    h: 920
//                  },
//                  {
//                    src: 'http://ww3.sinaimg.cn/mw690/e910bd00jw1f7c3j00tqij20qo0zkjts.jpg',
//                    w: 360,
//                    h: 480
//                  },
//                  {
//                    src: 'http://ww4.sinaimg.cn/mw690/e910bd00jw1f7c80cfvyoj20qo0zk75i.jpg',
//                    w: 360,
//                    h: 480
//
//                  },
//
//                ]
//              },
//                {
//                  title:'大学宿舍没空调是一种怎样的感受吧?',
//                  main:'这里真是热啊积分撒娇了房间爱啥感觉欧萨积分撒娇估计了价格拉我家领导说几个垃圾是',
//                  identity:'宿舍',
//                  time:'1分钟前',
//                  comment:'233',
//                  like:'344'
//
//
//                },
//                {
//                  title:'大学宿舍没空调是一种怎样的感受吧?',
//                  main:'这里真是热啊积分撒娇了房间爱啥感觉欧萨积分撒娇估计了价格拉我家领导说几个垃圾是',
//                  identity:'宿舍',
//                  time:'1分钟前',
//                  comment:'233',
//                  like:'344'
//
//
//                },
//                {
//                  title:'大学宿舍没空调是一种怎样的感受吧?',
//                  main:'这里真是热啊积分撒娇了房间爱啥感觉欧萨积分撒娇估计了价格拉我家领导说几个垃圾是',
//                  identity:'宿舍',
//                  time:'1分钟前',
//                  comment:'233',
//                  like:'344'
//
//
//                }
//              ]
          labels: [

            {
              id: 1,
              name: ""
            }
          ],
          items: [],
          ext_data: {}

        }
      },
      components: {
        AddIcon,
        PrfileIcon,
        Classify,
        IconCommentIcon,
        IconLikeIcon,
        PulseLoader,


      },
      computed: {
        color(){
          return "#246BFE"
        }
      },
      route: {
        data(){
          var self = this;
          return this.$request
            .get("http://115.28.67.181:8080/ask_reply?ext_type=12&token=6eeea1f8c6181756a487b1fecadd9446958a99e2c89f2023bce0d8d79f4819cc")
            .then(this.$api.checkResult)
            .then(function (data) {
//              console.info(data)
              self.ext_type = data.list.items.ext_type;
              self.labels = data.labels;
              self.items = data.list.items;
              self.labels.push({
                id:0,
                name:"更多"
              })
            })
        }
      },


        methods: {

          filterLabel(id){
            if(id==0){
              this.$router.go('/label?ext_type=12');
            }
            else{
              this.$router.go('/answer-topic?ext_type=10&label_id='+id)
            }
          }
        },
      filters:{
        fromNow
      }

    }
</script>
