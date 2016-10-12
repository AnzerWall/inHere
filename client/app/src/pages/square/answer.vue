<template>
      <div v-if="$loadingRouteData" class="answer-loading-area">
        <pulse-loader color="rgb(38, 162, 255)" size="12px"></pulse-loader>
      </div>
  <div class="response">
    <noti v-ref:noti></noti>
      <div class="answer" >

        <!--头部-->
        <div class="answer-head">
          <div class="top">
            <div class="left" @click="$router.go('/main/square')">
              <span>《&nbsp;&nbsp;有问必答</span>
            </div>
            <div class="right">

              <prfile-icon class="prfile"></prfile-icon>
              <add-icon class="add" v-link="{path:'/ask-publish'}"></add-icon>
            </div>
          </div>

          <classify :lists="labels" class="classify" :color="color" @filter-label="filterLabel" v-if="data&&!$loadingRouteData" >
          </classify>

        </div>
        <!--加载失败图标组件-->
        <div class="answer-fail" v-if="!data&&!$loadingRouteData" @click="load()">
          <fail text="加载失败,点击刷新"></fail>
        </div>
        <!--中部-->
        <div class="answer-center" v-if="data&&!$loadingRouteData">

          <answer-message v-for="item in items" :item="item" :color="color" @onclickpraise="onclickpraise">
          </answer-message>
          <infinite-loading :on-infinite="onLoadMore">
        <span slot="no-more">
          没有更多了...
        </span>
          </infinite-loading>

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

  .answer-loading-area {
    display: flex;
    justify-content: center;;
    margin-top: 200px;
  }
  .answer-fail{
    margin-top: 300px;
  }


</style>
<script type="text/ecmascript-6">
  import AddIcon from '../../svg/main/demand/Add.vue'
  import PrfileIcon from '../../svg/common/Profile.vue';
  import Classify from '../../components/square/classify.vue';
  import PulseLoader from 'vue-spinner/src/PulseLoader.vue';
  import praise from '../../util/praise.js';
  import AnswerMessage from '../../components/square/answer-message.vue';
  import {token,login_state,is_login,school,user_id} from '../../vuex/getters.js';
  import InfiniteLoading from 'vue-infinite-loading';
  import Noti from 'components/noti.vue';
  import Fail from 'components/fail.vue';



    export default{
      data(){
        return {
          data:null,
          labels: [

            {
              id: 1,
              name: ""
            }
          ],
          items: [],


        }
      },
      components: {
        AddIcon,
        PrfileIcon,
        Classify,

        PulseLoader,
        AnswerMessage,
        InfiniteLoading,
        Noti,
        Fail


      },
      computed: {
        color(){
          return "#246BFE"
        },

      },
      route: {
        data(){
          var self = this;
          let url=`${this.$api.url_base}/ask_reply`;
          return this.$request
            .get(url)
            .query({token:this.token})
            .query({ext_type:12})
            .then(this.$api.checkResult)
            .then(function (data) {
              console.info(data)
              self.data=data;
              self.labels = data.labels;
              self.items = data.list.items;
              self.labels.push({
                id:0,
                name:"更多"
              })
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
        }
      },


        methods: {

          filterLabel(id){
            if(id==0){
              this.$router.go('/label?ext_type=12');
            }
            else{
              this.$router.go('/answer-topic?ext_type=12&label_id='+id)
            }
          },
          onclickpraise(ext_data,id){
            console.log(id);
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
          //        点击重新刷新
          load(){
            return this.$request
              .get(`${this.$api.url_base}/ask_reply`)
              .query({ext_type:12})
              .query({token:this.token})
              .then(this.$api.ckeckResult)
              .then((res)=>{

                var data =res.body.data;
                this.data=data;
                this.labels = data.labels;
                this.items = data.list.items;
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
        actions: {

        },
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
