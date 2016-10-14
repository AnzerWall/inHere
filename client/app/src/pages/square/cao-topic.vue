<template>
  <div class="response">
    <noti v-ref:noti></noti>
    <div class="cao-detail"  >
      <div class="cao-header" >
        <div class="header-cao">
          <div class="header-top" >
            <span @click="back">《 有槽必吐</span>
          </div>
        </div>

        <div class="header-label_name" v-if="data&&!$loadingRouteData">
          <div class="header-bottom">
            #{{items[0].label_name}}
          </div>
        </div>

      </div>
      <div v-if="!data&&!$loadingRouteData" @click="load()" class="cao-topic-fail" >
        <!--加载失败图标组件-->
        <fail  text="加载失败,点击刷新"></fail>
      </div>

      <div class="cao-center" v-if="data&&!$loadingRouteData">
        <message class="center-message" v-for="item in items" :item="item" :main_color="main_color" @on-click="onClick" @onclickpraise="onclickpraise">
        </message>
        <infinite-loading :on-infinite="onLoadMore">
        <span slot="no-more">
          没有更多了...
        </span>
        </infinite-loading>
      </div>
      <div class="cao-foot" @click="goToCaoPublish(items[0].label_name)">
        <input class="foot-message" placeholder="#{{items[0].label_name}}" disabled>
      </div>

    </div>
    </div>
  <div v-if="$loadingRouteData" class="cao-topic-loading-area">
    <pulse-loader color="rgb(38, 162, 255)" size="12px"></pulse-loader>
  </div>
</template>
<style>
  .cao-detail{
    position: relative;

  }
  .cao-header{
    width: 100%;
    color: #ffffff;

  }
  .header-cao{
    width: 100%;
    background-color: #F91C88;
    position: fixed;
    left:0;
    top: 0;
    z-index: 1;
  }
  .header-top{
   width: 100%;

    display: flex;
    height: 60px;
    align-items: center;
    font-size: 16px;

  }
  .header-label_name{
    width: 100%;
    background: linear-gradient(to bottom, #F91C88 ,#FF5FAD );
  }
  .header-bottom{
    margin-top: 59px;
    width: 100%;
    height: 50px;
    margin-left: 20px;
    line-height: 20px;

  }
  .cao-center{
    margin-bottom: 50px;
  }
  .cao-foot{
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
  .foot-message{
    width: 70%;
    margin-left:20px;
    outline: none;
  }
  input::-webkit-input-placeholder {
    color:#cccccc;
  }
  input::-moz-placeholder {
    color:#cccccc;
  }
  .cao-topic-loading-area {
    display: flex;
    justify-content: center;;
    margin-top: 200px;
  }
  .cao-topic-fail{
    margin-top: 300px;
  }


</style>
<script type="text/ecmascript-6">
  import Message from '../../components/square/message.vue';
  import PulseLoader from 'vue-spinner/src/PulseLoader.vue';
  import praise from '../../util/praise.js';
  import {token,login_state,is_login,school,user_id} from '../../vuex/getters.js';
  import InfiniteLoading from 'vue-infinite-loading';
  import Noti from 'components/noti.vue';
  import Fail from 'components/fail.vue';

    export default{
        data(){
            return{
//              item:{
//                label_name:'你的坑爹舍友做过哪些让你意想不到的事情'
//              }
              items:[],
              data:null
            }
        },
        components:{
          Message,
          PulseLoader,
          InfiniteLoading,
          Noti,
          Fail

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

          var self=this;
          let url=`${this.$api.url_base}/ask_reply`;
          return this.$request
            .get(url)
            .query({token:this.token})
            .query({
              ext_type:this.$route.query.ext_type,
              label_id:this.$route.query.label_id
            })
            .then(this.$api.checkResult)
            .then(function(data){
              console.log(data);
              self.data=data;
              self.items=data.list.items;
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
                console.log(self.$refs.noti);
                return this.$refs.noti.warning(`未知错误:${e.message}`)
              }
              //后续显示重试按钮
            })
        },
      },
      computed:{
        main_color(){
          return "#F91D89"
        }
      },
      methods:{
        back(){
          window.history.back();
        },
        onClick(id){
          this.$router.go('/cao-detail/'+id);
        },
        onclickpraise(ext_data,id,ext_type){
          return praise.praise(ext_data,id,ext_type,this);
        },
        onLoadMore(){
          console.log('more');
          var token = this.token;
          var id=this.$route.params.id;
          this.$request
            .get(`${this.$api.url_base}/ask_reply`)
            .query({token: token})
            .query({offset:( this.data.offset||0) + 5, limit: 5})
            .query({ext_type: this.$route.query.ext_type,
              label_id:this.$route.query.label_id})
            .query({item_id: id})
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

        },
        goToCaoPublish(topic){
          // 这里应该传递一个话题给发布页并显示在发布页的标签上 ，先这样吧
          if (this.$route.query.ext_type == 10){
            this.$router.go(`/cao-publish/in?topic=${topic}`);
          } else {
            this.$router.go(`/cao-publish/out?topic=${topic}`);
          }
        },
        //        点击重新刷新
        load(){
          return this.$request
            .get(`${this.$api.url_base}/ask_reply`)
            .query({ext_type:this.$route.query.ext_type,
              label_id:this.$route.query.label_id})
            .query({token:this.token})
            .then(this.$api.ckeckResult)
            .then((res)=>{

              var data =res.body.data;
              this.data=data;
              this.items=data.list.items;
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

    }
</script>
