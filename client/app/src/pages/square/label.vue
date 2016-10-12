<template>
    <div class="label" >
      <noti v-ref:noti></noti>
      <div class="label-header">
        <!--有槽必吐-->
        <div class="label-header-cao" v-if="$route.query.ext_type==='10'">
          <span @click="back">《 有槽必吐排行榜—校内</span>
        </div>
        <div class="label-header-cao" v-if="$route.query.ext_type==='11'">
          <span @click="back">《 有槽必吐排行榜—校外</span>
        </div>
        <!--有问必答-->
        <div class="label-header-answer" v-if="$route.query.ext_type==='12'">
          <span @click="back">《 有问必答排行榜</span>
        </div>
      </div>
      <div @click="load()" v-if="!data&&!$loadingRouteData">
        <!--加载失败图标组件-->
        <fail  class="label-fail" text="加载失败,点击刷新" ></fail>
      </div>
      <div class="label-body" v-if="data&&!$loadingRouteData">
        <!--排行榜列表-->
        <div v-for="list in lists" >
          <div class="label-body-list" :style="{opacity: countOpacity($index)}" @click="onClick($route.query.ext_type,list.id)">
            <!--排行榜次序-->
            <div class="body-list-number" :style="{ 'background-color':number_color($index), 'color':num_color($index)}"> {{$index+1}}</div>
            <div class="body-list" :style="{color:list_color()}">
              <!--标签名字-->
              <div class="list-name">#{{list.name}}</div>
              <div class="list-dian">·</div>
              <!--评论数-->
              <div class="list-comment_num">{{list.size}}</div>
            </div>
          </div>
        </div>
        <infinite-loading :on-infinite="onLoadMore">
        <span slot="no-more">
          没有更多了...
        </span>
        </infinite-loading>

      </div>
    </div>
</template>
<style >

  .label{

  }
  .label-header{
    width: 100%;
    color: #ffffff;
    position: fixed;
    top:0;
    z-index: 2;
  }
  .label-header-cao{
    height: 60px;
    display: flex;
    align-items: center;
    background: linear-gradient(to bottom, #F91C88 ,#FF5FAD);
    padding-left: 15px;
  }
  .label-header-answer{
    height: 60px;
    display: flex;
    align-items: center;
    background: linear-gradient(to bottom, #0274FF ,#5FA7FF);
    padding-left: 15px ;
  }
  .label-body{
    margin-top: 60px;
  }
  .label-body-list{
    height: 60px;
    display: flex;
    justify-content: flex-start;
    align-items: center;
  }
  .body-list-number{
    height: 30px;
    width: 30px;
    /*background-color: #0274FF;*/
    border-radius:100%;
    margin-left: 20px ;
    margin-right: 15px;
    flex: none;
    line-height: 30px;
    text-align: center;
  }
  .body-list{
    height: 60px;
    display: flex;
    border-bottom: solid 1px #E3E3E3;
    align-items: center;
    overflow:hidden;
    flex-shrink: 1;
    flex-grow: 1;
    justify-content: flex-start;
  }
  .list-name{
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
    flex-shrink: 1;
    flex-grow:0;
  }
  .list-dian{
    margin: 0 7px;

  }
  .list-comment_num{
    margin-right: 20px;
  }




</style>
<script type="text/ecmascript-6">
  import {token,login_state,is_login,school,user_id} from '../../vuex/getters.js';
  import Noti from 'components/noti.vue';
  import Fail from 'components/fail.vue';
  import InfiniteLoading from 'vue-infinite-loading';

    export default{
        data(){
            return{
              lists:[],
              data:null,

            }
        },
        components:{
          Noti,
          Fail,
          InfiniteLoading

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
          let url=`${this.$api.url_base}/ask_reply/labels`;
          return this.$request
            .get(url)
            .query({token:this.token})
            .query({
              ext_type:this.$route.query.ext_type
            })
            .then(this.$api.checkResult)
            .then((data=>{
              this.data=data;
              this.lists=data.items
              }))
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
      methods:{
        back(){
          window.history.back()
        },
          countOpacity:function (index) {
            if(index<5){
              return  1-0.12*index;
            }
            else{
              return 0.4;
            }
          },
        number_color(index) {
          if(index<5){
            if(this.$route.query.ext_type==='10'||this.$route.query.ext_type==='11'){
              return "#F91C88"
            }
            else if(this.$route.query.ext_type==='12'){
              return "#0274FF"
            }
          }
          else{
            return "#ffffff"
          }

        },
        num_color(index){
          if(index<5){
            return "#ffffff"
          }
          else{
            if(this.$route.query.ext_type==='10'||this.$route.query.ext_type==='11'){
              return "#F91C88"
            }
            else if(this.$route.query.ext_type==='12'){
              return  "#0274FF"
            }
          }
        },
        list_color(){
          if(this.$route.query.ext_type==='10'||this.$route.query.ext_type==='11'){
            return "#F91C88"
          }
          else if(this.$route.query.ext_type==='12'){
            return "#0274FF"
          }
        },
        onClick(ext_type,id){
          if(ext_type==10){
            this.$router.go('/cao-topic?ext_type=10&label_id='+id)
          }
          else if(ext_type==11){
            this.$router.go('/cao-topic?ext_type=11&label_id='+id)
          }
          else if(ext_type==12)
            this.$router.go('/answer-topic?label_id='+id)

        },
        onLoadMore(){
          console.log('more');
          var token = this.token;
          this.$request
            .get(`${this.$api.url_base}/ask_reply/labels`)
            .query({token: token})
            .query({offset:( this.data.offset||0) + 5, limit: 5})
            .query({ext_type: this.$route.query.ext_type})
            .then(this.$api.checkResult)
            .then((data)=> {
              //通知组件加载完毕
              console.log(data);
              this.$broadcast('$InfiniteLoading:loaded');
//           //更新数据数组
              this.lists = this.lists.concat(data.items);
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
        //        点击重新刷新
        load(){
          return this.$request
            .get(`${this.$api.url_base}/ask_reply/labels`)
            .query({ext_type:this.$route.query.ext_type})
            .query({token:this.token})
            .then(this.$api.ckeckResult)
            .then((res)=>{

              var data =res.body.data;
              this.data=data;
              this.lists=data.items;
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
