<template>
  <div class="response"v-if="!$loadingRouteData">
    <div class="cao-detail">
      <div class="cao-header">
        <div class="header-cao">
          <div class="header-top" >
            <span @click="back">《 有槽必吐</span>
          </div>
        </div>

        <div class="header-label_name">
          <div class="header-bottom">
            #{{items[0].label_name}}
          </div>
        </div>

      </div>
      <div class="cao-center">
        <message class="center-message" v-for="item in items" :item="item" :main_color="main_color" @on-click="onClick">
        </message>
      </div>
      <div class="cao-foot">
        <input class="foot-message" placeholder="#{{items[0].label_name}}">
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


</style>
<script>
  import Message from '../../components/square/message.vue';
  import PulseLoader from 'vue-spinner/src/PulseLoader.vue';

    export default{
        data(){
            return{
//              item:{
//                label_name:'你的坑爹舍友做过哪些让你意想不到的事情'
//              }
              items:[],
            }
        },
        components:{
          Message,
          PulseLoader

        },
      route:{
        data(){

          var self=this;
          var token="4121581213c1605a1db4872d7cca6eed1b41259bffd8066d9573783b07214d6f";
          console.log(this.$route.query.label_id);
          return this.$request
            .get("http://115.28.67.181:8080/ask_reply")
            .query({token:token})
            .query({
              ext_type:this.$route.query.ext_type,
              label_id:this.$route.query.label_id
            })
            .then(this.$api.checkResult)
            .then(function(data){
              console.log(data);
              self.items=data.list.items;
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
        }

      },
    }
</script>
