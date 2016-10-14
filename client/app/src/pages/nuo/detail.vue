<template>
  <div class="nuo-detail-wrapper">
    <div class="nuo-detail-head">
      <!--:style="{ 'background-color': 'rgba(0,0,0,0)'}"-->
      <div class="nuo-detail-head-left" @click="back">《 有诺必行</div>
      <div class="nuo-detail-head-right">
        <menu-icon style="fill:white;"></menu-icon>
      </div>
    </div>
    <div class="nuo-detail-head-placeholder">

    </div>
    <div class="nuo-detail-content">
      <div v-if="!$loadingRouteData">
        <nuo-card :hide_radius="true" :data="data" @like="like(data)" @hate="hate(data)" @join="join(data)">

        </nuo-card>
        <div class="nuo-detail-timeline" v-if="data.status!=0">
          <nuo-timeline v-for="target in data.target_list"
                        :data="target"
                        :padding="$index!==0&&! data.target_list[$index-1].completed"
                        :doing="($index===0|| data.target_list[$index-1].completed)&&!data.target_list[$index].completed"
                        :final="$index===data.target_list[$index].length-1"
                        :nuo_id="data.id"
                        @action="action(target)"></nuo-timeline>

        </div>
      </div>


      <div v-if="$loadingRouteData" class="loading-area">
        <pulse-loader color="#35ca6e" size="12px"></pulse-loader>
      </div>
    </div>

  </div>
</template>
<style scoped src="./detail.scss" lang="scss">

</style>

<script type="text/ecmascript-6">
  import MenuIcon from 'svg/common/Menu.vue'
  import NuoCard from 'components/nuo-card/nuo-card.vue'
  import NuoTimeline from 'components/nuo-timeline/nuo-timeline.vue'
  import PulseLoader from 'vue-spinner/src/PulseLoader.vue'
  import {token, login_state, is_login} from '../../vuex/getters.js'

  export default{
    vuex: {
      getters: {
        token
      }
    },
    components: {
      MenuIcon,
      NuoCard,
      PulseLoader,
      NuoTimeline

    },

    route: {
      data(){
        return this.load();
      }
    },
    methods: {

      action(target){
        this.load();
      },

      load(){
        let id = this.$route.params.id;
        let token = this.token;
        let url_base = this.$api.node_api_base;

        return this.$request
          .get(`${url_base}/nuo/${id}`)//GET方法 url为/demand
          .query({token: token})//    传递query，   url变为 /demand?ext_type=1&ext_type=2&ext_type=3 过滤信息
          .then(this.$api.checkResult)//一个辅助函数，用于处理code等信息，直接返回data
          .then( (data)=> {
            //处理数据，具体见vue-router文档data钩子页说明
//            console.log(data);

            this.data = data || {};
            this.target_list = this.data.target_list || [];
          })

      },
      back(){
        window.history.back();
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
    ,
    data()
    {
      return {
        data: {},
        target_list: []
      }
    }
  }
</script>
