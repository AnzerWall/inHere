<template>
  <div class="response">
    <noti v-ref:noti></noti>
    <div class="answer-topic" v-if="!$loadingRouteData">
      <!--头部-->
      <div class="answer-header">
        <div class="answer-header-name">
          <div class="header-top" @click="back">
            《 有问必答
          </div>
        </div>
        <div class="answer-header-label_name" >
          <div class="header-bottom" v-if="data">
            #{{items[0].label_name}}
          </div>
        </div>

      </div>
      <!--加载失败图标组件-->
      <fail v-ref:noti class="cao-topic-fail" v-if="!data"></fail>
      <!--中部-->

      <div class="answer-topic-center">
        <answer-message v-for="item in items" :item="item" :color="color" @onclickpraise="onclickpraise"></answer-message>
        <infinite-loading :on-infinite="onLoadMore">
        <span slot="no-more">
          没有更多了...
        </span>
        </infinite-loading>
      </div>
      <div class="answer-foot">
        <input class="answer-foot-message" placeholder="#{{items[0].label_name}}">
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
    margin-bottom: 50px;
  }

  .answer-topic-loading-area {
    display: flex;
    justify-content: center;;
    margin-top: 200px;
  }
  .answer-foot{
    width: 100%;
    position: fixed;
    bottom: 0;
    height: 50px;
    border-top: solid 1px #cccccc;
    background-color: #ffffff;
    z-index: 1;
    display: flex;
    align-items: center;
  }
  .answer-foot-message{
    width: 70%;
    margin-left:20px;
    outline: none;
  }
  .cao-topic-fail{
    margin-top: 200px;
  }

</style>
<script type="text/ecmascript-6">
  import IconCommentIcon from '../../svg/common/comment/IconComment.vue';
  import IconLikeIcon from '../../svg/common/comment/IconLike.vue';
  import PhotosWipe from '../../components/photoswipe/photoswipe.vue'
  import {fromNow} from '../../filter/time.js';
  import PulseLoader from 'vue-spinner/src/PulseLoader.vue';
  import praise from '../../util/praise.js';
  import AnswerMessage from '../../components/square/answer-message.vue';
  import {token,login_state,is_login,school,user_id} from '../../vuex/getters.js';
  import InfiniteLoading from 'vue-infinite-loading';
  import Noti from 'components/noti.vue';
  import Fail from 'components/fail.vue';

    export default{
        data(){
            return{
              items:[],
              data:null

            }

        },
      route:{
        data(){
          let url=`${this.$api.url_base}/ask_reply`;
          return this.$request
            .get(url)
            .query({token:this.token})
            .query({ext_type:12})
            .query({
              label_id:this.$route.query.label_id
            })
            .then(this.$api.checkResult)
            .then((data)=>{
              this.items=data.list.items;
              this.data=data;
            })
            .catch((e)=> {
              if (e.type === 'API_ERROR') {//判断是api访问出错还是其他错，仅限被checkResult处理过。。详见checkResult。。
                if (e.code === 23333) {//根据code判断出错类型,比如未登录时候跳转啊
                  return this.$refs.noti.warning(`参数验证失败`)//这里以及后边的return是为了结束函数。。。仅此而已 ，常用技巧  : )
                } else if (e.code === 401) {
                  return this.$router.go({
                    path: '/login',
                    query: {
                      __ref: this.$route.path//告诉login页面要跳转回来的页面
                    }
                  });
                } else {
                  return this.$refs.noti.warning(`与服务器通讯失败:${e.message}`)
                }
              } else {
                console.error(e.stack||e);
                console.log(this.$refs.noti);
                return this.$refs.noti.warning(`未知错误:${e.message}`)
              }
              //后续显示重试按钮
            })


        }

      },
        components:{
          IconCommentIcon,
          IconLikeIcon,
          PhotosWipe,
          PulseLoader,
          AnswerMessage,
          InfiniteLoading,
          Noti,
          Fail

        },
      methods:{
        back(){
          window.history.back()
        },
        onclickpraise(ext_data,id){
          return praise.praise(ext_data,id,null,this);
        },
        onLoadMore(){
          console.log('more');
          var token = this.token;
          this.$request
            .get(`${this.$api.url_base}/ask_reply`)
            .query({token: token})
            .query({offset:( this.data.offset||0) + 5, limit: 5})
            .query({ext_type: 12})
            .then(this.$api.checkResult)
            .then((data)=> {
              //通知组件加载完毕
              console.log(data);
              this.$broadcast('$InfiniteLoading:loaded');
//           //更新数据数组
              this.items = this.items.concat(data.list.items);
              this.data.offset = data.list.offset;
              this.data.total = data.list.total;
//            //判断是否已经不能加载到更多的数据
              if (this.data.offset >= this.data.total) {
                this.$broadcast('$InfiniteLoading:complete');
              }
            })
            .catch(function (e) {
              console.log(e);
            })
        },
      },
      filters:{
        fromNow
      },
      computed: {
        color(){
          return "#246BFE"
        }
      },
      vuex: {
        getters: {
          login_state,
          token,
          is_login,
          school,
          user_id
        }
      },
    }
</script>
