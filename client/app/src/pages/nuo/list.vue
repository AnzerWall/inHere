<template>
    <div class="nuo-list-wrapper">
      <div class="nuo-list-head">
        <div class="nuo-list-head-left">《 有诺必行</div>
        <div class="nuo-list-head-right">
          <profile-icon class="icon" style="fill: white;"></profile-icon>
          <add-icon  class="icon"  style="fill: white;" @click="createNuo"></add-icon>
        </div>

      </div>
      <div class="nuo-list-content"  v-if="!$loadingRouteData">
        <div class="nuo-card-wrapper" v-for="item in items">
          <nuo-card :data="item"  @detail="detail(item)" @join="join(item)" @like="like(item)" @hate="hate(item)"></nuo-card>
        </div>
        <infinite-loading :on-infinite="onLoadMore" v-if="has_more">
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
<script>
    import ProfileIcon from 'svg/common/Profile.vue';
    import AddIcon from 'svg/main/demand/Add.vue';
    import NuoCard from 'components/nuo-card/nuo-card.vue'
    //https://github.com/greyby/vue-spinner
    import PulseLoader from 'vue-spinner/src/PulseLoader.vue'
    //https://peachscript.github.io/vue-infinite-loading
    import InfiniteLoading from 'vue-infinite-loading';
    import _ from 'lodash'
    export default{
        components:{
          AddIcon,
          ProfileIcon,
          NuoCard,
          PulseLoader,
          InfiniteLoading

        },
        data(){
            return{

            }
        },
      //配置路由钩子
      route: {
        //页面加载数据钩子(或者叫事件)
        data(){
          let token="607f90267bd7f8dcedbf2709c60ab42471d5acbe1aff80f6751a2536487c4d74";
          let url_base=this.$api.node_api_base;

          return this.$request
            .get(`${url_base}/nuo`)//GET方法 url为/demand
            .query({token:token})//    传递query，   url变为 /demand?ext_type=1&ext_type=2&ext_type=3 过滤信息
            .then(this.$api.checkResult)//一个辅助函数，用于处理code等信息，直接返回data
            .then(function(data){
              //处理数据，具体见vue-router文档data钩子页说明
              return {
                items:data.items,
                count:data.count,
                has_more:data.has_more,
                offset:data.offset+data.items.length
              }
            })
        }
      },
      methods:{
        onLoadMore(){
          let token="607f90267bd7f8dcedbf2709c60ab42471d5acbe1aff80f6751a2536487c4d74";
          let url_base=this.$api.node_api_base;

          return this.$request
            .get(`${url_base}/nuo`)//GET方法 url为/demand
            .query({token:token})//    传递query，   url变为 /demand?ext_type=1&ext_type=2&ext_type=3 过滤信息
            .query({offset:this.offset})
            .then(this.$api.checkResult)//一个辅助函数，用于处理code等信息，直接返回data
            .then(function(data){
              //处理数据，具体见vue-router文档data钩子页说明
              return {
                items:data.items,
                count:data.count,
                has_more:data.has_more
              }
            })
        },
        detail(item){
          this.$router.go(`/nuo/${item.id}`)
        },
        createNuo(){
          this.$router.go(`/create-nuo`)
        },
        join(item){
          let token="607f90267bd7f8dcedbf2709c60ab42471d5acbe1aff80f6751a2536487c4d74";
          let url_base=this.$api.node_api_base;
          item.status=1;
          this.$request
            .post(`${url_base}/nuo/${item.id}/participator`)
            .query({token:token})
            .then(this.$api.checkResult)//一个辅助函数，用于处理code等信息，直接返回data
            .then(function(data){
              console.log("success");
            })
            .catch(function(err){
              console.err(err);
              item.status=0;
            });
        },
        like(item){

          let token="607f90267bd7f8dcedbf2709c60ab42471d5acbe1aff80f6751a2536487c4d74";
          let url_base=this.$api.node_api_base;

          if(!item.praised){
            let save_item= _.cloneDeep(item);
            item.praised=true;
            item.praise_count++;
            if(item.is_low){
              item.is_low=false;
              item.low_count--;
            }

            this.$request
              .post(`${url_base}/nuo/${item.id}/praise`)
              .query({token:token})
              .then(this.$api.checkResult)//一个辅助函数，用于处理code等信息，直接返回data
              .then(function(data){
                console.log("like success");
              })
              .catch(function(err){
                console.err(err);
                item=save_item;
              });
          }

        },
        hate(item){

          let token="607f90267bd7f8dcedbf2709c60ab42471d5acbe1aff80f6751a2536487c4d74";
          let url_base=this.$api.node_api_base;

          if(!item.is_low){
            let save_item= _.cloneDeep(item);
            item.is_low=true;
            item.low_count++;
            if(item.praised){
              item.praised=false;
              item.praise_count--;
            }

            this.$request
              .post(`${url_base}/nuo/${item.id}/low`)
              .query({token:token})
              .then(this.$api.checkResult)//一个辅助函数，用于处理code等信息，直接返回data
              .then(function(data){
                console.log("hate success");
              })
              .catch(function(err){
                console.err(err);
                item=save_item;
              });
          }
        }
      },
      data(){
        return {
          count:0,
          items:[],
          has_more:true,
          offset:0
        }
      }

    }
</script>
