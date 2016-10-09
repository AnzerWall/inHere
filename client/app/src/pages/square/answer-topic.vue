<template>
  <div class="response"v-if="!$loadingRouteData">
    <div class="answer-topic">
      <!--头部-->
      <div class="answer-header">
        <div class="answer-header-name">
          <div class="header-top" @click="back">
            《 有问必答
          </div>
        </div>
        <div class="answer-header-label_name" >
          <div class="header-bottom">
            #{{items[0].label_name}}
          </div>
        </div>

      </div>
      <!--中部-->
      <div class="answer-topic-center">
        <answer-message v-for="item in items" :item="item" :color="color" @onclickpraise="onclickpraise"></answer-message>
      </div>
      <div class="answer-foot">
        <input class="answer-foot-message" placeholder="#{{items[0].label_name}}">
      </div>


    </div>
  </div>
  <div v-if="$loadingRouteData" class="answer-topic-loading-area">
    <pulse-loader color="rgb(38, 162, 255)" size="12px"></pulse-loader>
  </div>
</template>
<style>
  .answer-topic{
    position: relative;
    width: 100%;
  }
  .answer-header{
    width: 100%;
    color: #ffffff;

  }
  .answer-header-name{
    background-color: #0274FF;
    position: fixed;
    top: 0;
    z-index: 1;
    width: 100%;

  }
  .header-top{
    display: flex;
    height: 60px;
    align-items: center;
    margin-left: 10px;
    font-size: 16px;

  }
  .answer-header-label_name{
    background: linear-gradient(to bottom, #0274FF ,#5FA7FF );
    width: 100%;

  }
  .header-bottom{

    width: 50%;
    height: 50px;
    margin-left: 20px;
    line-height: 20px;

  }
  .answer-topic-center{
    width: 100%;
    margin-bottom: 50px;
  }

  .answer-topic-loading-area {
    display: flex;
    justify-content: center;;
    margin-top: 200px;
  }
  .answer-foot{
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
  .answer-foot-message{
    width: 70%;
    margin-left:20px;
    outline: none;
  }

</style>
<script type="text/ecmascript-6">
  import IconCommentIcon from '../../svg/common/comment/IconComment.vue';
  import IconLikeIcon from '../../svg/common/comment/IconLike.vue';
  import PhotosWipe from '../../components/photoswipe/photoswipe.vue'
  import {fromNow} from '../../filter/time.js';
  import PulseLoader from 'vue-spinner/src/PulseLoader.vue';
  import praise from '../../util/praise.js';
  import AnswerMessage from '../../components/square/answer-message.vue';
  import {token,login_state,is_login,school,user_id} from '../../vuex/getters.js';

    export default{
        data(){
            return{
              items:[]

            }
        },
      route:{
        data(){
          let url=`${this.$api.url_base}/ask_reply`;
          return this.$request
            .get(url)
            .query({token:this.token})
            .query({ext_type:12})
            .query({
              label_id:this.$route.query.label_id
            })
            .then(this.$api.checkResult)
            .then((data)=>{
              this.items=data.list.items
            })


        }

      },
        components:{
          IconCommentIcon,
          IconLikeIcon,
          PhotosWipe,
          PulseLoader,
          AnswerMessage

        },
      methods:{
        back(){
          window.history.back()
        },
        onclickpraise(ext_data,id){
          return praise.praise(ext_data,id,null,this);
        },
      },
      filters:{
        fromNow
      },
      computed: {
        color(){
          return "#246BFE"
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
    }
</script>
