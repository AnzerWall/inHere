<template>
  <div class="nuo-list-wrapper">
    <noti v-ref:noti></noti>
    <div class="nuo-list-head">
      <div class="nuo-list-head-left">《 有诺必行</div>
      <div class="nuo-list-head-right">
        <profile-icon class="icon" style="fill: white;"></profile-icon>
        <add-icon class="icon" style="fill: white;" @click="createNuo"></add-icon>
      </div>

    </div>
    <div class="nuo-list-content" v-if="!$loadingRouteData">
      <div class="nuo-card-wrapper" v-for="item in items">
        <nuo-card :data="item" @detail="detail(item)" @join="join(item)" @like="like(item)"
                  @hate="hate(item)"></nuo-card>
      </div>
      <infinite-loading :on-infinite="onLoadMore" >
        <span slot="no-more">
          没有更多了...
        </span>
      </infinite-loading>
    </div>
    <div v-if="$loadingRouteData" class="loading-area">
      <pulse-loader color="#35ca6e" size="12px"></pulse-loader>
    </div>
  </div>
</template>
<style scoped src="./list.scss" lang="scss">

</style>
<script type="text/ecmascript-6">
  import ProfileIcon from 'svg/common/Profile.vue';
  import AddIcon from 'svg/main/demand/Add.vue';
  import NuoCard from 'components/nuo-card/nuo-card.vue'
  //https://github.com/greyby/vue-spinner
  import PulseLoader from 'vue-spinner/src/PulseLoader.vue'
  //https://peachscript.github.io/vue-infinite-loading
  import InfiniteLoading from 'vue-infinite-loading';
  import _ from 'lodash'
  import {token, login_state, is_login} from '../../vuex/getters.js'

  import Noti from 'components/noti.vue'
  export default{
    components: {
      AddIcon,
      ProfileIcon,
      NuoCard,
      PulseLoader,
      InfiniteLoading,
      Noti

    },
    data(){
      return {
        items: [],
        offset: 0,
        count: 0
      }
    },
    vuex: {
      getters: {
        token
      }
    },
    //配置路由钩子
    route: {
      //页面加载数据钩子(或者叫事件)
      data(){
        let mine = this.$route.query.mine === 1;//过滤我的有诺必行

        return this.load(0, mine);
      }
    },
    methods: {
      load(offset, mine = 0,isLoadMore=false){
        let token = this.token;
        let url_base = this.$api.node_api_base;
        return this.$request
          .get(`${url_base}/nuo`)//获取有诺必行列表
          .query({token: token})//  传递token
          .query({offset, limit: 2})//传递获取数据范围，这里limit取8
          .query({mine:mine===0?0:1})//是否过滤自己的有诺必行
          .then(this.$api.checkResult)//一个辅助函数，如果成功，则then被调用，如果catch被调用，则显示重试按钮
          .then( (data)=> {
            //成功，对数据进行处理
            this.items=this.items.concat(data.items || []);//加入有诺必行数据列表
            _.uniqWith(this.items, (a, b)=> {//对数据进行按id去重
              return a.id === b.id;
            });
            this.count = data.count || 0;//更新总数量
            this.offset = data.offset + data.items.length;//更新偏移量
            if(isLoadMore)
              this.$broadcast('$InfiniteLoading:loaded');//通知结束load

            if (isLoadMore&&data.has_next === false)
              this.$broadcast('$InfiniteLoading:complete');//判断是否没有更多，通知没有更多了
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
              return this.$refs.noti.warning(`未知错误:${e.message}`)
            }
            //后续显示重试按钮
          })
      },
      onLoadMore(){
        console.log('more');
        let mine = this.$route.query.mine === 1;//过滤我的有诺必行

        return this.load(this.offset, mine,true);

      },
      detail(item){
        this.$router.go(`/nuo/${item.id}`)
      },
      createNuo(){
        this.$router.go(`/create-nuo`)
      },
      join(item){
        let token = "607f90267bd7f8dcedbf2709c60ab42471d5acbe1aff80f6751a2536487c4d74";
        let url_base = this.$api.node_api_base;
        item.status = 1;
        this.$request
          .post(`${url_base}/nuo/${item.id}/participator`)
          .query({token: token})
          .then(this.$api.checkResult)//一个辅助函数，用于处理code等信息，直接返回data
          .then(function (data) {
            console.log("success");
          })
          .catch(function (err) {
            console.err(err);
            item.status = 0;
          });
      },
      like(item){

        let token = "607f90267bd7f8dcedbf2709c60ab42471d5acbe1aff80f6751a2536487c4d74";
        let url_base = this.$api.node_api_base;

        if (!item.praised) {
          let save_item = _.cloneDeep(item);
          item.praised = true;
          item.praise_count++;
          if (item.is_low) {
            item.is_low = false;
            item.low_count--;
          }

          this.$request
            .post(`${url_base}/nuo/${item.id}/praise`)
            .query({token: token})
            .then(this.$api.checkResult)//一个辅助函数，用于处理code等信息，直接返回data
            .then(function (data) {
              console.log("like success");
            })
            .catch(function (err) {
              console.err(err);
              item = save_item;
            });
        }

      },
      hate(item){

        let token = "607f90267bd7f8dcedbf2709c60ab42471d5acbe1aff80f6751a2536487c4d74";
        let url_base = this.$api.node_api_base;

        if (!item.is_low) {
          let save_item = _.cloneDeep(item);
          item.is_low = true;
          item.low_count++;
          if (item.praised) {
            item.praised = false;
            item.praise_count--;
          }

          this.$request
            .post(`${url_base}/nuo/${item.id}/low`)
            .query({token: token})
            .then(this.$api.checkResult)//一个辅助函数，用于处理code等信息，直接返回data
            .then(function (data) {
              console.log("hate success");
            })
            .catch(function (err) {
              console.err(err);
              item = save_item;
            });
        }
      }
    }

  }
</script>
