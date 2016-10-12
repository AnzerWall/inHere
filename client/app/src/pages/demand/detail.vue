<template>
  <div class="response">
    <noti v-ref:noti></noti>
    <!--头部-->
    <div class="response-header">
      <div class="head" >
        <div class="head-left" @click="back">
          <div class="head-message" >《 &nbsp;{{chinese(data && data.ext_type)}}</div>
        </div>
        <div class="head-right" >
          <div>
            <send-chat-icon :style="{fill:main_color}" class="sendchat"  ></send-chat-icon>
            <menu-icon :style="{fill:main_color}"></menu-icon>
          </div>
        </div>
      </div>
    </div>

    <div class="answer-detail-fail" v-if="!data&&!$loadingRouteData"  @click="load()">
      <!--加载失败图标组件-->
      <fail text="加载失败,点击刷新"></fail>
    </div>

    <!--下部内容-->
    <div class="content" :style="{marginBottom:bottomHeight+'px'}" v-if="data">
      <!--卡片-->
      <photos-wipe v-ref:viewer></photos-wipe>
      <header-card  :data="data" :is_detail="true" @view-image="View"></header-card>
      <!--任务信息-->
      <div class="task">
        <div class="content-center" >
          <!--快递   帮忙-->
          <div  v-if="data.ext_type===1||data.ext_type===3">
            <div class="center-all"  >
              <div class="center-left" >酬金</div>
              <div class="center-right" :style="{color:main_color}">¥{{data.ext_data.pay}}</div>
            </div>
          </div>
          <!--转让-->
          <div v-if="data.ext_type===2">
            <div class="center-all" v-if="data.ext_data.buy_time!==null">
              <div class="center-left">购买时间</div>
              <div class="center-right" :style="{color:main_color}">{{data.ext_data.buy_time|date}}</div>
            </div>
            <div class="center-all" v-if="data.ext_data.original_price!==null">
              <div class="center-left">原价</div>
              <div class="center-right" :style="{color:main_color}">¥{{data.ext_data.original_price}}</div>
            </div>
            <div class="center-all">
              <div class="center-left">售价</div>
              <div class="center-right" :style="{color:main_color}">¥{{data.ext_data.price}}</div>
            </div>
            <div class="center-all" >
              <div class="center-left">成色</div>
              <div class="center-right" :style="{color:main_color}">{{data.ext_data.quality}}</div>
            </div>
          </div>
          <!--丢失东西-->
          <div v-if="data.ext_type===4">
            <div class="center-all">
              <div class="center-left">丢失物品</div>
              <div class="center-right" :style="{color:main_color}">{{data.ext_data.thing}}</div>
            </div>
            <div class="center-all">
              <div class="center-left">丢失时间</div>
              <div class="center-right" :style="{color:main_color}">{{data.ext_data.lose_time}}</div>
            </div>
          </div>
          <!--捡到东西-->
          <div v-if="data.ext_type===5">
            <div class="center-all">
              <div class="center-left">捡到物品</div>
              <div class="center-right" :style="{color:main_color}">{{data.ext_data.thing}}</div>
            </div>
            <div class="center-all">
              <div class="center-left">捡到时间</div>
              <div class="center-right" :style="{color:main_color}">{{data.ext_data.pick_time}}</div>
            </div>
          </div>
          <!--走起-->
          <div v-if="data.ext_type===6">
            <div class="center-all">
              <div class="center-left">人均消费</div>
              <div class="center-right" :style="{color:main_color}">{{data.ext_data.per_cost}}</div>
            </div>
            <div class="center-all">
              <div class="center-left"> 求陪同</div>
              <div class="center-right" :style="{color:main_color}">{{want_sex}}</div>
            </div>
            <div class="center-all">
              <div class="center-left"> 地点</div>
              <div class="center-right" :style="{color:main_color}">{{data.ext_data.place}}</div>
            </div>
            <div class="center-all">
              <div class="center-left"> 时间</div>
              <div class="center-right" :style="{color:main_color}">{{data.ext_data.start_time|now}} WED</div>
            </div>
            <div class="center-all">
              <div class="center-left"> 集中地</div>
              <div class="center-right" :style="{color:main_color}">{{data.ext_data.gathering_place}}</div>
            </div>
          </div>
          <!--评论条数-->
          <div class="comment-number" :style="{color:main_color}" >
            <div class="dian">*</div>
            <div class="number">{{(data.comment && total)||0}}条评论</div>
          </div>
          </div>
      </div>
        <!--评论组件-->
        <comment   v-for="comment in comments" :list="comment" :user_id="user_id" :main_color="main_color" :data.sync="data" :ext_type="ext_type" >
        </comment>
      <!--加载更多组件-->
      <infinite-loading :on-infinite="onLoadMore">
        <span slot="no-more">
          没有更多了...
        </span>
      </infinite-loading>
    </div>
    <div class="detail-foot" v-if="data">
      <auto-textarea :height.sync="bottomHeight" :placeholder="placeholder" :value.sync="content" @enter="submit(this.$request,content,this.data.id,this.ext_type)" ></auto-textarea>

      <!--<textarea class="detail-textarea" placeholder="世界不如人意,人生如此艰难" v-model="content" @keyup.enter="submit(this.$request,this.content,this.data.id,this.ext_type)"></textarea>-->
    </div>


  </div>
  <!--加载动画组件：小圆点-->
  <div v-if="$loadingRouteData" class="loading-area">
    <pulse-loader color="rgb(38, 162, 255)" size="12px"></pulse-loader>
  </div>
</template>
<style scoped>
  .response{
    display: flex;
    flex-direction: column;
  }
  .response-header{
    flex-shrink: 0;
    z-index: 50;
  }
  .head{
    height: 60px;
    width: 100%;
    display: flex;
    justify-content: space-between;
    align-items: center;
    font-size: 16px;
    border-bottom: solid 1px #c9c9c9;
    position: fixed;
    background-color: white;
  }
  .head .head-left{
    font-weight: bold;
    padding-left: 10px;
  }
  .head .head-right{
    padding-right: 20px ;
  }
  .head .head-right .sendchat{
    padding-right: 20px;
  }
  .content{
    flex-shrink: 1;
    margin-top: 60px ;
    /*margin-bottom: 48px;*/

  }
  .content .task{
    font-weight: bold;
    width: 100%;

  }
  .content .content-center{
    margin: 0 10px;
  }
  .content .content-center .center-all{

    display: flex;
    justify-content: space-between;
    align-items: center;
    width: 100%;
    border-bottom:solid 1px #c9c9c9 ;
    height: 60px;
  }
  .content-center .center-all .center-left{
    margin-left: 20px;
  }
  .content-center .center-all .center-right{
    margin-right: 25px;
  }
  .content .comment-number{
    display: flex;
    height: 50px;
    align-items: center;
    position: relative;
  }
  .comment-number .dian{
    position: absolute;
    top: 20px;
    left: 10px;

  }
  .comment-number .number{
    margin-left: 20px;
  }
  .loading-area {
    display: flex;
    justify-content: center;;
    margin-top: 200px;
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
  /*.detail-comment{*/
    /*margin-bottom: 50px;*/
  /*}*/


</style>
<script type="text/ecmascript-6">
  import Comment from 'components/comment/comment.vue';
  import  moment from 'moment';
  import HeaderCard from 'components/demand-card/demand-card.vue';
  import PhotosWipe from 'components/photoswipe/photoswipe.vue';
  import SendChatIcon from 'svg/common/SendChat.vue';
  import MenuIcon from 'svg/common/Menu.vue';
  import helper from '../../util/demand_helper.js';
  import {now,date}from 'filter/time.js';
  import PulseLoader from 'vue-spinner/src/PulseLoader.vue';
  import post from '../../util/comment_post.js';
  import AutoTextarea from '../../components/auto-textarea/auto-textarea.vue';
  import {token,login_state,is_login,school,user_id} from '../../vuex/getters.js';
  import InfiniteLoading from 'vue-infinite-loading';
  import Noti from 'components/noti.vue';
  import Fail from 'components/fail.vue';


  export default{
    components: {
      Comment,
      HeaderCard,
      PhotosWipe,
      SendChatIcon,
      MenuIcon,
      PulseLoader,
      AutoTextarea,
      InfiniteLoading,
      Noti,
      Fail
    },


    methods: {
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
      View(index, photos){
        this.$refs.viewer.show(index, photos);
      },
      back(){
        window.history.back();
      },
      chinese(type){
        switch (type) {
          case this.TYPE_EXPRESS:
            return "快递";
          case this.TYPE_SELL:
            return "转让";
          case this.TYPE_HELP:
            return "帮忙";
          case this.TYPE_FOUND:
            return "捡到";
          case this.TYPE_LOST:
            return "丢失";
          case this.TYPE_DATING:
            return "约";
          default:
            return "其他"
        }
      },
      submit(request,content,id,ext_type){
        console.log(content);
        return post.post(request,content,id,ext_type,this);

      },
      load(){
        var id=this.$route.params.id;
        return this.$request
          .get(`${this.$api.url_base}/demand/`+id)
          .query({token:this.token})
          .then(this.$api.ckeckResult)
          .then((res)=>{

            var data =res.body.data;
            this.data=data;
            this.comments=data.comment.items;
            this.user_id=data.user_id;
            this.ext_type=data.ext_type;
            this.total=data.comment.total;
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
        var self=this
        var id=this.$route.params.id;
        let url=`${this.$api.url_base}/demand/`+id;
        return this.$request
        .get(url)
          .query({token:this.token})
          .then(this.$api.checkResult)
          .then((data=>{
          //  console.log(data);
            this.data=data;
            this.comments=data.comment.items;
            this.user_id=data.user_id;
            this.ext_type=data.ext_type;
            this.total=data.comment.total;
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
              console.log(self.$refs.noti);
              return this.$refs.noti.warning(`未知错误:${e.message}`)
            }
            //后续显示重试按钮
          })
      },

    },



    data: function () {

      return {

        data:null,
        comments:[],
        user_id:"",
        ext_type:1,
        TYPE_EXPRESS:1,
        TYPE_SELL:2,
        TYPE_HELP:3,
        TYPE_FOUND:5,
        TYPE_LOST:4,
        TYPE_DATING:6,
        content: "",
        placeholder:"世界不如人意,人生如此艰难",
        bottomHeight:0,
        total:0



      }
    },
    computed: {
      main_color(){
        if(!this.data)return null;
        return helper.getMainColor(this.data)
      },
      want_sex(){
        let sex = this.data.ext_data.want_sex;
        switch (sex) {
          case 1:
            return '求男生';
          case 2:
            return '求女生';
        }
        return '性别不限'
      },

    },
    filters:{
      now,
      date
    },

  }
</script>
