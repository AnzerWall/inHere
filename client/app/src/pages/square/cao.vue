<template>
  <div class="main">
    <div class="body" v-if="!$loadingRouteData">

      <!--校内-->
      <div class="school" v-if="$route.query.ext_type==='10'" >
        <div class="header" >
          <div class="cao-top" >
            <div class="left" @click="$router.go('/square')"><span>《 有槽必吐</span></div>
            <div class="right">
              <div class="in">校内</div>
              <div class="out" @click="$router.go('/cao?ext_type=11')"  >校外</div>
            </div>
          </div>
          <div class="space"></div>
          <classify :lists="labels" class="classify" @filter-label="filterLabel" :color="color">

          </classify>


        </div>

        <div class="message">
          <message v-for="item in items" :item="item"  :main_color="main_color" @on-click="onClick">
          </message>
        </div>

        <!--&lt;!&ndash;<div @click="$router.go('/cao?type=school&tag=1')"> 小道消息</div>&ndash;&gt;-->
        <!--&lt;!&ndash;<div v-if="$route.query.tag==='1'">到付件案件了解到该了解爱死了几个垃圾管理</div>&ndash;&gt;-->


      </div>



      <!--校外-->
      <div class="public" v-if="$route.query.ext_type==='11'">
        <div class="header" >
          <div class="cao-top" >
            <div class="left" @click="$router.go('/square')">《 有槽必吐</div>
            <div class="right">
              <div class="in1" @click="$router.go('/cao?ext_type=10')" >校内</div>
              <div class="out1" >校外</div>
            </div>
          </div>
          <div class="space1"></div>
          <slider :topics="labels" class="classify"  :square_type="4" v-on:go-to-the-topic='goToTopic'>

          </slider>


        </div>

        <div class="message">
          <message v-for="item in items" :item="item" :main_color="main_color"  @on-click="onClick">
          </message>
      </div>
      <div class="foot">
        <input  class="text" type="text" placeholder="世界不如人意,人生如此艰难">
      </div>
        </div>

    </div>
  </div>
  <!--圆点过度-->
  <div v-if="$loadingRouteData" class="cao-loading-area">
    <pulse-loader color="rgb(38, 162, 255)" size="12px"></pulse-loader>
  </div>




</template>
<style scoped>
  .main{


  }
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
  .cao-top .right .out1{
    padding: 0px 5px;
  }
  .cao-top .right .in1{
    padding: 0px 5px;
    opacity: 0.5;
  }
  .space1{
    height: 60px;
    width: 100%;
  }
  .message{
    width: 100%;
  }
  .cao-loading-area {
    display: flex;
    justify-content: center;;
    margin-top: 200px;
  }
  .classify{
    background: linear-gradient(to bottom, #F91C88 ,#FF5FAD );
  }


</style>
<script type="text/ecmascript-6">
  import Message from '../../components/square/message.vue';
  import Classify from '../../components/square/classify.vue';
  import Slider from '../../components/square-slider/slider.vue';
  import PulseLoader from 'vue-spinner/src/PulseLoader.vue';



    export default{
      data(){
        return {
          labels:[

            {
              id: 1,
              name: ""
            }
          ],
          items:[],
          ext_data:{}



//
//            topic:[
//              {id: 11,title:"小道消息"},
//              {id: 11,title:"老师"},
//              {id: 11,title:"宿舍"},
//              {id: 11,title:"官方消息"},
//              {id: 11,title:"说说那些情"},
//              {id: 11,title:"更多"}
//            ],
//
//
//              item:[
//                {id: 11,title:"你们饭堂有什么奇葩菜"},
//                {id: 11,title:"啥地方了撒娇噶是家里发生的了"},
//                {id: 11,title:"士大夫撒个垃圾死了"},
//                {id: 11,title:"是否撒娇过拉丝家乐福"},
//                {id: 11,title:"说说那些情大沙发"},
//                {id: 11,title:"更多"}
//                ],
//
//
//
//          items:[{
//            content:'这个实训的老师太垃圾了啊啊啊啊啊啊啊啊啊啊啊啊',
//            identity:'老师',
//            time:'15分钟前',
//            comment:'333',
//            like:'231',
//            images: [
//              {
//
//                src: 'http://ww4.sinaimg.cn/mw690/e910bd00jw1f7c80cfvyoj20qo0zk75i.jpg',
//                w: 360,
//                h: 480
//
//              },
//              {
//                src: 'http://ww1.sinaimg.cn/mw690/e910bd00jw1f7c1ndknjwj20qo0zktat.jpg',
//                w: 690,
//                h: 920
//              },
//              {
//                src: 'http://ww3.sinaimg.cn/mw690/e910bd00jw1f7c3j00tqij20qo0zkjts.jpg',
//                w: 360,
//                h: 480
//              },
//              {
//                src: 'http://ww4.sinaimg.cn/mw690/e910bd00jw1f7c80cfvyoj20qo0zk75i.jpg',
//                w: 360,
//                h: 480
//
//              },
//
//            ]
//
//
//
//          },
//            {
//              content:'这个实训的老师太垃圾了啊啊啊啊啊啊啊啊啊啊啊啊',
//              identity:'老师',
//              time:'15分钟前',
//              comment:'333',
//              like:'231',
//
//            },
//            {
//              content:'这个实训的老师太垃圾了啊啊啊啊啊啊啊啊啊啊啊啊',
//              identity:'老师',
//              time:'15分钟前',
//              comment:'333',
//              like:'231',
//              images: [
//                {
//
//                  src: 'http://ww4.sinaimg.cn/mw690/e910bd00jw1f7c80cfvyoj20qo0zk75i.jpg',
//                  w: 360,
//                  h: 480
//
//                },
//                {
//                  src: 'http://ww1.sinaimg.cn/mw690/e910bd00jw1f7c1ndknjwj20qo0zktat.jpg',
//                  w: 690,
//                  h: 920
//                },
//                {
//                  src: 'http://ww3.sinaimg.cn/mw690/e910bd00jw1f7c3j00tqij20qo0zkjts.jpg',
//                  w: 360,
//                  h: 480
//                },
//                {
//                  src: 'http://ww4.sinaimg.cn/mw690/e910bd00jw1f7c80cfvyoj20qo0zk75i.jpg',
//                  w: 360,
//                  h: 480
//
//                },
//
//              ]
//
//
//
//            },
//          ]
        }
      },
      components: {
        Message,
        Classify,
        Slider,
        PulseLoader


      },
      methods:{
          filterLabel(id)
          {
            if(id==0){
              this.$router.go('/label?ext_type=10');
            }
            else{
              this.$router.go('/cao-topic?ext_type=10&label_id='+id);
            }
          },
        goToTopic(id,cao_type){
          switch (cao_type){
            case 4:{
              if(id==0){
                this.$router.go('/label?ext_type=11');
              }
              else{
                this.$router.go('/cao-topic?ext_type=11&label_id='+id);
              }

            }

          }

        },
        onClick(id){
          this.$router.go('/cao-detail/'+id);
        }




      },
      computed:{
        main_color(){
          return "#F91D89"
        },
        color(){
          return "#F91D89"
        }
      },
      route:{
        data(){

          var self=this;
          return this.$request
            .get("http://115.28.67.181:8080/ask_reply?ext_type="+self.$route.query.ext_type+"&token=4121581213c1605a1db4872d7cca6eed1b41259bffd8066d9573783b07214d6f")
            .then(this.$api.checkResult)
            .then(function(data){
              self.labels=data.labels;
              self.items=data.list.items;
              self.labels.push({
                id:0,
                name:"更多"
              });




            })
        },
      },


    }
</script>
