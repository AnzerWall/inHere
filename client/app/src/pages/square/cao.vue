<template>
  <div class="main">
    <noti v-ref:noti></noti>

    <div class="body" >

      <!--校内-->
      <div class="school">
        <div class="header" >
          <div class="cao-top" >
            <div class="left" @click="$router.go('/main/square')"><span>《 有槽必吐</span></div>
            <div class="right">
              <div class="in">校内</div>
              <div class="out" @click="$router.go('/cao-out')">校外</div>
            </div>
          </div>
          <div class="space"></div>
          <classify :lists="labels" class="classify" @filter-label="filterLabel" :color="color" v-if="data" >

          </classify>


        </div>
        <div @click="load()">
          <!--加载失败图标组件-->
          <fail v-ref:fail class="cao-fail" v-if="!data&&!$loadingRouteData" text="加载失败,点击刷新" >
          </fail>
        </div>
        <div class="message" v-if="data&&!$loadingRouteData">
          <!--message组件-->
          <message v-for="item in items" :item.sync="item"  :main_color="main_color" @on-click="onClick" @onclickpraise="onclickpraise" >
          </message>
          <!--无限加载组件-->
          <infinite-loading :on-infinite="onLoadMore" v-if="!$loadingRouteData">
        <span slot="no-more">
          没有更多了...
        </span>
          </infinite-loading>
        </div>
      </div>
      <div class="foot" @click="goToPublish">
        <input  class="text" type="text" placeholder="世界不如人意,人生如此艰难" readonly>
      </div>

    </div>
  </div>
  <!--圆点过度-->
  <div v-if="$loadingRouteData" class="cao-loading-area">
    <pulse-loader color="rgb(38, 162, 255)" size="12px"></pulse-loader>
  </div>




</template>
<style scoped>

  .body{
    font-size: 16px;
    position: relative;
  }
  .header{
    width: 100%;
    z-index: 1;

  }
  .cao-top{
    background-color:#F91C88 ;
    position: fixed;
    top: 0px;
    height: 60px;
    width: 100%;
    /*background:#FA298F;*/
    display:flex;
    justify-content: space-between;
    align-items: center;
    font-weight: bold;
    color: white;
    z-index: 13



  }
  .cao-top .right{
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-right: 18px;

  }
  .cao-top .left{
    margin-left: 15px;
  }
  .cao-top .right .in{
    padding: 0px 5px;
  }
  .cao-top .right .out{
    padding: 0px 5px;
    opacity: 0.5;
  }
  .space{
    width: 100%;
    height: 60px;
  }

  .message{
    width: 100%;
    margin-bottom: 50px;
  }
  .cao-loading-area {
    display: flex;
    justify-content: center;;
    margin-top: 200px;
  }
  .classify{
    background: linear-gradient(to bottom, #F91C88 ,#FF5FAD );
  }
  .foot{
    width: 100%;
    position: fixed;
    bottom: 0;
    background: #ffffff;
    border-top: solid 1px #cccccc;
    z-index: 1;
  }
  .text{
    margin: 15px 20px;
    width: 100%;
  }
  .cao-fail{
    margin-top: 300px ;
  }



</style>
<script type="text/ecmascript-6">
  import Message from '../../components/square/message.vue';
  import Classify from '../../components/square/classify.vue';
  import PulseLoader from 'vue-spinner/src/PulseLoader.vue';
  import praise from '../../util/praise.js';
  import {token,login_state,is_login,school,user_id} from '../../vuex/getters.js';
  import InfiniteLoading from 'vue-infinite-loading';
  import Noti from 'components/noti.vue';
  import Fail from 'components/fail.vue';


    export default{
      data(){
        return {
          labels:[],
          items:[],
          ext_data:{},
          data:null
        }
      },
      components: {
        Message,
        Classify,
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
      methods:{
        onLoadMore(){
          console.log('more');
          var token = this.token;
          var id=this.$route.params.id;
          this.$request
            .get(`${this.$api.url_base}/ask_reply`)
            .query({token: token})
            .query({offset:( this.data.offset||0) + 5, limit: 5})
            .query({ext_type: 10})
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
          goToPublish(){
              this.$router.go('/cao-publish/in');
          },
          filterLabel(id)
          {
            if(id==0){
              this.$router.go('/label?ext_type=10');
            }
            else{
              this.$router.go('/cao-topic?ext_type=10&label_id='+id);
            }
          },

        onClick(id){
          this.$router.go('/cao-detail/'+id);
        },
        onclickpraise(ext_data,id,ext_type){
          return praise.praise(ext_data, id, ext_type, this);
        },
        clean(labels,items){
          console.log(labels,items);
          return labels==null&&items==null;

        },
//        点击重新刷新
        load(){
          return this.$request
            .get(`${this.$api.url_base}/ask_reply`)
            .query({ext_type:10})
            .query({token:this.token})
            .then(this.$api.ckeckResult)
            .then((res)=>{

              var data =res.body.data;
              this.data=data;
              this.labels=data.labels;
              this.items=data.list.items;
              this.labels.push({
                id:0,
                name:"更多"
              });
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
      computed:{
        main_color(){
          return "#F91D89"
        },
        color(){
          return "#F91D89"
        },
      },
      route:{
        data(){

          var self=this;

          let url=`${this.$api.url_base}/ask_reply`
          return this.$request
            .get(url)
            .query({ext_type:10})
            .query({token:this.token})
            .then(this.$api.checkResult)
            .then(function(data){
              self.data=data;
              self.labels=data.labels;
              self.items=data.list.items;
              self.labels.push({
                id:0,
                name:"更多"
              });
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


    }
</script>
