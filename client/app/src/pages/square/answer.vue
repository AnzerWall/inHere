<template>
      <div v-if="$loadingRouteData" class="answer-loading-area">
        <pulse-loader color="rgb(38, 162, 255)" size="12px"></pulse-loader>
      </div>
  <div class="response"v-if="!$loadingRouteData">
      <div class="answer">

        <!--头部-->
        <div class="answer-head">
          <div class="top">
            <div class="left" @click="$router.go('/square')">
              <span>《&nbsp;&nbsp;有问必答</span>
            </div>
            <div class="right">

              <prfile-icon class="prfile"></prfile-icon>
              <add-icon class="add" v-link="{path:'/ask-publish'}"></add-icon>
            </div>
          </div>

          <classify :lists="labels" class="classify" :color="color" @filter-label="filterLabel">
          </classify>

        </div>
        <!--中部-->
        <div class="answer-center">

            <answer-message v-for="item in items" :item="item" :color="color" @onclickpraise="onclickpraise"></answer-message>


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


</style>
<script type="text/ecmascript-6">
  import AddIcon from '../../svg/main/demand/Add.vue'
  import PrfileIcon from '../../svg/common/Profile.vue';
  import Classify from '../../components/square/classify.vue';
  import PulseLoader from 'vue-spinner/src/PulseLoader.vue';
  import praise from '../../util/praise.js';
  import AnswerMessage from '../../components/square/answer-message.vue';
  import {token,login_state,is_login,school,user_id} from '../../vuex/getters.js';



    export default{
      data(){
        return {
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
        AnswerMessage


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
              self.ext_type = data.list.items.ext_type;
              self.labels = data.labels;
              self.items = data.list.items;
              self.labels.push({
                id:0,
                name:"更多"
              })
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
