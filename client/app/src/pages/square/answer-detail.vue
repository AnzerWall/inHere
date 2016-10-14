<template>
  <noti v-ref:noti></noti>
  <div >
    <!--图片放大插件-->
    <photos-wipe v-ref:viewer></photos-wipe>
    <!--头部-->
    <div class="change">
      <div class="ar-head">
        <div class="ard-left"><span @click="back">《&nbsp有问必答</span></div>
        <menu class="ard-right"></menu>
      </div>
      <div class="ard-head" v-if="data&&!$loadingRouteData">
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
            <div class="ard-attention" @click="follow()" v-if="follow1==0">
              <span class="attention-font" >关&nbsp&nbsp注&nbsp&nbsp问&nbsp&nbsp题</span>
            </div>
            <div class="ard-attention"  v-if="follow1==1">
              <span class="attention-font">已&nbsp&nbsp关&nbsp&nbsp注</span>
            </div>
          </div>
        </div>

      </div>
    </div>
    <div class="answer-detail-fail" v-if="!data&&!$loadingRouteData"  @click="load()">
      <!--加载失败图标组件-->
      <fail text="加载失败,点击刷新"></fail>
    </div>

    <!--评论组件-->
    <div class="ard-reply" :style="{marginBottom:bottomHeight+'px'}" v-if="data&&!$loadingRouteData">
      <comment v-for="comment in comments" :list="comment" :main_color="main_color" :user_id="user_id" :number="number" @onclickpraise="onclickpraise"></comment>
      <infinite-loading :on-infinite="onLoadMore">
        <span slot="no-more">
          没有更多了...
        </span>
      </infinite-loading>
    </div>
    <!--评论输入-->
    <div class="answer-detail-foot">
      <auto-textarea :height.sync="bottomHeight" :placeholder="placeholder" :value.sync="content" @enter="submit(this.$request,content,this.data.id,this.data.ext_type)" ></auto-textarea>
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
    top:0px;
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
    word-wrap: break-word;
  }

  .hide {
    overflow: hidden;
    text-overflow: ellipsis;
    height: 20px;
    line-height: 20px;
    max-height: 20px;
    word-wrap: break-word;

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
     flex-direction: row;
     flex-wrap: nowrap;

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
  .answer-detail-fail{
    margin-top: 0px;
  }
</style>
<script type="text/ecmascript-6">
  import Menu from 'svg/common/Menu.vue'
  import Comment from '../../components/comment/comment.vue'
  import {fromNow} from 'filter/time.js';
  import PulseLoader from 'vue-spinner/src/PulseLoader.vue';
  import PhotosWipe from '../../components/photoswipe/photoswipe.vue';
  import post from '../../util/comment_post.js';
  import AutoTextarea from '../../components/auto-textarea/auto-textarea.vue';
  import praise from '../../util/praise.js';
  import {token,login_state,is_login,school,user_id} from '../../vuex/getters.js';
  import InfiniteLoading from 'vue-infinite-loading';
  import Noti from 'components/noti.vue';
  import Fail from 'components/fail.vue';
  export default{
    filters: {
      fromNow
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
    route: {
      data(){
        var id = this.$route.params.id;
        let url=`${this.$api.url_base}/ask_reply/`+id;
        return this.$request
          .get(url)
          .query({token:this.token})
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
              follow1:data.ext_data.follow,

            }
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
    data(){
      return {
        comments: [],
        user_id:'',
        data:null,
        number:1,
        ext_data:[],
        photos:[],
        ext_type:0,
        placeholder:'说点什么?',
        content:"",
        bottomHeight:0,
        follow1:0,
      }
    },
    components: {
      Menu,
      Comment,
      PulseLoader,
      PhotosWipe,
      AutoTextarea,
      InfiniteLoading,
      Noti,
      Fail
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
      },
      onclickpraise(ext_data,id){
        return praise.praise(ext_data,id,null,this);
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
        if (this.data.offset >=this.data.total) {
          this.$broadcast('$InfiniteLoading:complete');
        }
      })
      .catch(function (e) {
          console.log(e);
        })
      },
      load(){
        var id=this.$route.params.id;
        return this.$request
          .get(`${this.$api.url_base}/ask_reply/`+id)
          .query({ext_type:12})
          .query({token:this.token})
          .then(this.$api.ckeckResult)
          .then((res)=>{
            var data =res.body.data;
            this.data=data;
            console.log(data);
            this.ext_data=data.ext_data;
            console.log(this.ext_data);
            this.comments= data.reply_list.items;
            this.user_id=data.user_id;
            this.photos=data.ext_data.photos;
            this.ext_type=data.ext_type

          })
          .catch((e)=> {
            console.log("adadddasd")
            console.log(e)
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

      },
      follow(){
        var token = this.token;
        var id=this.$route.params.id;
        let url=`${this.$api.url_base}/ask_reply/follow/`+id;
        this.$request
          .post(url)
          .query({token:token})
          .then(this.$api.ckeckResult)
          .then((res)=>{
            this.$request
              .get(`${this.$api.url_base}/ask_reply/`+id)
              .query({token:this.token})
              .query({ext_type: 12})
              .then(this.$api.checkResult)
              .then((data)=>{
                this.follow1=data.ext_data.follow
              })

          })
      }
    }
  }
</script>
