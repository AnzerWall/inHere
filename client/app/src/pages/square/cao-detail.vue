<template>
  <div>
    <noti v-ref:noti></noti>
    <div v-if="!$loadingRouteData">
      <!--点击图片放大组件-->
      <photos-wipe v-ref:viewer></photos-wipe>
      <div class="cao-detail">
        <div class="cao-detail-head">
          <div class="head-top">
            <div class="head-top-left"><span @click="back">《 有槽必吐</span></div>
            <div class="head-top-right"><menu-icon></menu-icon></div>
          </div>
          <div class="head-label" v-if="data">
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
          <!--加载失败图标组件-->
          <fail v-ref:noti class="cao-detail-fail" v-if="!data"></fail>
        </div>
        <div  class="cao-detail-center" :style="{marginBottom:bottomHeight+'px'}">

          <!--评论组件-->
          <comment v-for="comment in comments" :list="comment" :number="number" :user_id="user_id" :main_color="main_color" @onclickpraise="onclickpraise"></comment>
          <!--加载更多组件-->
          <infinite-loading :on-infinite="onLoadMore">
        <span slot="no-more">
          没有更多了...
        </span>
          </infinite-loading>
        </div>
        <div class="cao-detail-foot">
          <!--输入框组件-->
          <auto-textarea :height.sync="bottomHeight" :placeholder="placeholder" :value.sync="content" @enter="submit(this.$request,content,this.data.id,this.ext_type)" ></auto-textarea>
          <!--<input  class="text" type="text" placeholder="世界不如人意,人生如此艰难">-->
        </div>
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
    word-wrap: break-word;
  }
  .head-image{
    position: relative;
    display: flex;
    overflow: auto;
    overflow-y:hidden ;
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
  .cao-detail-foot{
    position: fixed;
    bottom: 0;
    background: #ffffff;
    z-index: 1;
    width: 100%;
    /*padding: 10px 0px 10px 20px;*/
    border-top: solid 1px #cccccc;
  }
  .cao-detail-fail{
    margin-top: 300px;
  }

</style>
<script type="text/ecmascript-6">
  import MenuIcon from '../../svg/common/Menu.vue';
  import {fromNow} from '../../filter/time.js';
  import Comment from '../../components/comment/comment.vue';
  import PhotosWipe from '../../components/photoswipe/photoswipe.vue';
  import PulseLoader from 'vue-spinner/src/PulseLoader.vue';
  import post from '../../util/comment_post.js';
  import AutoTextarea from '../../components/auto-textarea/auto-textarea.vue';
  import praise from '../../util/praise.js';
  import {token,login_state,is_login,school,user_id} from '../../vuex/getters.js';
  import InfiniteLoading from 'vue-infinite-loading';
  import Noti from 'components/noti.vue';
  import Fail from 'components/fail.vue';

    export default{
        data(){
          return{
            data:null,
            number:1,
            user_id:"",
            ext_data:{},
            comments:[],
            images:[],
            placeholder:'说点什么?',
            content:"",
            bottomHeight:0,
            ext_type:0,

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
      route:{
        data(){
          var id=this.$route.params.id;
          let url=`${this.$api.url_base}/ask_reply/`+id;
          return this.$request
            .get(url)
            .query({token:this.token})
            .then(this.$api.checkResult)
            .then((data=>{
              console.log(data);
              this.data=data;
              this.ext_data=data.ext_data;
              this.images=data.ext_data.photos;
              this.comments=data.reply_list.items;
              this.user_id=data.user_id;
              this.ext_type=data.ext_type;
              }))
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
        components: {
          MenuIcon,
          Comment,
          PhotosWipe,
          PulseLoader,
          AutoTextarea,
          InfiniteLoading,
          Noti,
          Fail
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
        },
        submit(request,content,id,ext_type){
          console.log(content);
          return post.post(request,content,id,ext_type,this);
        },
        onclickpraise(ext_data,id,ext_type){
          return praise.praise(ext_data,id,ext_type,this);
        },
        onLoadMore(){
          console.log('more');
          var token = this.token;
          var id=this.$route.params.id;
          this.$request
            .get(`${this.$api.url_base}/comments`)
            .query({token: token})
            .query({offset:( this.data.offset||0) + 5, limit: 5})
            .query({ext_type: this.ext_type})
            .query({item_id: id})
            .then(this.$api.checkResult)
            .then((data)=> {
              //通知组件加载完毕
              console.log(data);
              this.$broadcast('$InfiniteLoading:loaded');
//           //更新数据数组
              this.comments = this.comments.concat(data.items);
              this.data.offset = data.offset;
              this.data.total = data.total;
//            //判断是否已经不能加载到更多的数据
              if (this.data.offset >= this.data.total) {
                this.$broadcast('$InfiniteLoading:complete');
              }
            })
            .catch(function (e) {
              console.log(e);
            })
        },

      }
    }
</script>
