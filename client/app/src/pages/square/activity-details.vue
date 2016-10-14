<template>
  <!--模板详情页-->

  <div class="details" v-if="url_type==1">
    <noti v-ref:noti></noti>
    <div class="head" :style="{'background-image': 'url('+cover_img.src+')'}" v-if="data">
      <div class="dark"></div>
      <div class="content">
        <div class="back" @click="back()">《</div>
        <div class="title">{{data.title}}</div>
        <div class="change">
          <div class="user">
            <span class="font1">@{{data.user_name}}</span>
            <span class="time font1">{{data.start_time|fromNow}}</span>
          </div>

          <div class="my-base">
            <span class="place">{{data.place}}</span>
            <span class="like">{{data.praise}}<icon-like class="icon" @click="onclickpraise(data,id,ext_type)" :style="{fill:activityColor}"></icon-like></span>
          </div>
        </div>
      </div>
    </div>
    <!--正文内容-->
    <!--加载失败图标组件-->
    <div class="activity-detail-fail" v-if="!data&&!$loadingRouteData" @click="load()">
      <fail text="加载失败,点击刷新"></fail>
    </div>

    <!--评论组件-->
    <div class="a-r-length"  v-if="data">
      <div class="a-r-star">*</div>
      {{total}}条评论
    </div>
    <!--输入组件高度自适应-->
    <div class="activity-reply"  v-if="!$loadingRouteData" :style="{marginBottom:bottomHeight+'px'}" >

      <comment v-for="list in comments" :list="list" :main_color="main_color" :user_id="user_id" :number="number"></comment>
      <!--加载更多组件-->
      <infinite-loading :on-infinite="onLoadMore" v-if="data">
        <span slot="no-more">
          没有更多了...
        </span>
      </infinite-loading>
    </div>
    <!--输入组件-->
    <div class="detail-foot" v-if="data">
      <auto-textarea :height.sync="bottomHeight" :placeholder="placeholder" :value.sync="content" @enter="submit(this.$request,content,this.data.id,this.ext_type)" ></auto-textarea>

      <!--<textarea class="detail-textarea" placeholder="世界不如人意,人生如此艰难" v-model="content" @keyup.enter="submit(this.$request,this.content,this.data.id,this.ext_type)"></textarea>-->
    </div>
  </div>


  <!--url的详情页-->

  <div v-if="url_type==0" class="url_style">
    <div class="activity-head"><span class="activity-title" @click="back()">《 {{title}}</span></div>
      <iframe v-el:myiframe scrolling="yes" frameborder="0" height="100%" width="100%"
              v-bind:src="url_content"></iframe>
  </div>

</template>
<style scoped>

  .head {
    padding: 0;
    margin: 0;
    position: relative;
    width: 100%;
    height: 360px;
    background-size: cover;
    background-position: center;
  }

  .head .dark {
    position: absolute;
    left:0;
    right:0;
    top:0;
    bottom:0;
    background: rgba(0,0,0,.2);
  }

  .head .content {
    position: relative;
  }


  .back {
    margin-left: 7px;
    padding-top: 28px;
    font-size: large;
    color: white;
    font-weight: bold;
  }

  .title {
    color: white;
    font-weight: bold;
    margin-top: 108px;
    margin-left: 25px;
    margin-right: 47px;
    font-size: 30px;
    display: -webkit-box;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
    vertical-align: middle;
  }

  .change {
    padding-bottom: 25px;
    opacity: 0.7;
    background: linear-gradient(to top, rgba(0, 0, 0, .8), rgba(0, 0, 0, 0));
  }

  .user {
    margin-top: 46px;
    margin-left: 25px;
  }

  .time {
    margin-left: 6px;
  }

  .font1 {
    font-size: 14px;
    color: white;
  }

  .my-base {
    margin-left: 25px;
    margin-top: 32px;
  }

  .place {
    padding: 5px 7px 5px 7px;
    background-color: white;
    border-radius: 5px;
  }

  .like {
    color: white;
    font-weight: bold;
    float: right;
    margin-right: 28px;
  }

  .icon {
    margin-left: 2px;
  }

  .detail-foot{
    position: fixed;
    bottom: 0;
    background: #ffffff;
    z-index: 1;
    width: 100%;
    border-top: solid 1px #cccccc;
    /*padding: 10px 0px 10px 20px;*/
  }
  .url_style {
    /*padding: 20px 0 20px 0;*/
    position:fixed;
    left:0;
    right:0;
    top:0;
    bottom:0;
    display: flex;
    flex-direction: column;
  }
  /*.url_style .d_content {*/
    /*flex:1;*/
    /*display:flex;*/
  /*}*/
  .url_style .activity-head{
    flex:none;
  }

  /*.url_style .d_content iframe {*/
    /*flex:1;*/
  /*}*/



  .activity-head {
    padding: 0;
    margin: 0;
    background-color: white;
    width: 100%;
    height: 60px;
    /*position: fixed;*/
    display: flex;
    align-items: center;
    padding-left: 10px;
    border-bottom: solid 1px rgba(213, 213, 213, .5);
    z-index: 200;
  }

  .activity-title {
    font-weight: bold;
  }


  .activity-reply {
    padding: 20px 0 0 0;
  }

  .a-r-length {
    color: #03b719;
    margin-left: 30px;
    font-weight: bold;
    position: relative;
    margin-top: 40px;
  }

  .a-r-star {
    color: #03b719;
    padding: 0;
    margin: 0;
    position: absolute;
    right: 100%;
    margin-right: 5px;
    top: 50%;
    transform: translateY(-50%)

  }
  .activity-detail-fail{
    margin-top: 0px;
  }
</style>
<script type="text/ecmascript-6">

  import Comment from '../../components/comment/comment.vue'
  import IconLike from 'svg/common/comment/IconLike.vue'
  import {fromNow} from 'filter/time.js';
  import {token,login_state,is_login,school,user_id} from '../../vuex/getters.js';
  import post from '../../util/comment_post.js';
  import AutoTextarea from '../../components/auto-textarea/auto-textarea.vue';
  import InfiniteLoading from 'vue-infinite-loading';
  import Noti from 'components/noti.vue';
  import Fail from 'components/fail.vue';
  import praise from '../../util/praise.js';
  import color from '../../util/praise_color.js';
  export default{
    filters: {
      fromNow
    },
    methods: {
      back(){
        window.history.back()
      },
      onLoadMore(){
        let url = `${this.$api.url_base}/comments`;
        return this.$request
          .get(url)//GET方法 url为/activity
          .query({ext_type:this.ext_type})
          .query({item_id:this.id})
          .query({token: this.token})
          .query({offset: ( this.data.offset || 0) + 5, limit: 5})
          .then(this.$api.checkResult)
          .then((data)=> {
            //通知组件加载完毕
            console.log(data);
            this.$broadcast('$InfiniteLoading:loaded');
//           //更新数据数组
            this.comments= this.comments.concat(data.items);
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
      submit(request,content,id,ext_type){
        console.log(content);
        return post.post(request,content,id,ext_type,this);
      },
      load(){
        var id=this.$route.params.id;
        return this.$request
          .get(`${this.$api.url_base}/activity/`+id)
          .query({token:this.token})
          .then(this.$api.ckeckResult)
          .then((res)=>{
            var data =res.body.data;
            this.data=data;
            console.log(data);
            this.id=data.id;
            this.comments=data.comment.itmes;
            this.user_id=data.user_id;
            this.cover_img=data.cover_img;
            this.ext_type=data.ext_type;
            this.total=data.comment.total
//
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
      onclickpraise(ext_data,id,ext_type){
        return praise.praise(ext_data, id, ext_type, this);
      },
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
        let url=`${this.$api.url_base}/activity/`+id;
        this.url_type = this.$route.query.url_type;
        this.url_content = this.$route.query.url_content;
        this.title = this.$route.query.title;
        console.log(this.$route.query);
        if(this.url_type)
          return this.$request
            .get(url)
            .query({token: this.token})
            .then(this.$api.checkResult)
            .then(function (data) {
              return {
                data: data,
                id:data.id,
                comments: data.comment.items,
                user_id: data.user_id,
                cover_img: data.cover_img,
                ext_type:data.ext_type,
                total:data.comment.total
              }
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
        else {
//          this.url_content = this.$route.query.url_content;
//          console.log(this.$route.query)
        }
      }
    },
    data(){
      return {
        id:0,
        ext_type:0,
        total:0,
        comments:[],
        lists: [],
        user_id: '',
        data: null,
        number: 1,
        cover_img: [],
        url_content:'',
        url_type:1,
        title:'',
        content: "",
        placeholder:"世界不如人意,人生如此艰难",
        bottomHeight:0
      }
    },
    components: {
      IconLike,
      Comment,
      AutoTextarea,
      InfiniteLoading,
      Noti,
      Fail
    },
    computed: {
      main_color: function () {
        return "#03b719"
      },
      activityColor(){
        return color.activityColor(this.data);
      }
    }

  }

</script>
