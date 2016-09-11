<template>
  <div class="demand-wrapper">
    <div class="demand-head-wrapper">
      <div class="demand-head">
        <!--v-link见 vue-router文档，有中文-->
        <div class="demand-head-navbar">
          <span class="navbar-item" v-link="{path:'/demand/task',activeClass:'current'}">任务</span>
          <span class="navbar-item" v-link="{path:'/demand/lost',activeClass:'current'}">失物</span>
          <span class="navbar-item" v-link="{path:'/demand/dating',activeClass:'current'}">走起</span>
        </div>
        <div class="demand-head-icon" v-link="{path:'/demand-publish/task'}">
          <add-icon></add-icon>
        </div>


      </div>
    </div>
    <div class="demand-content">
      <router-view></router-view>
    </div>

  </div>
</template>
<style scoped>
  .demand-wrapper {
    display: flex;
    height: 100%;
    width: 100%;
    background-color: #FAFAFA;
    flex-direction: column;
    position: relative;
  }

  .demand-wrapper .demand-content {
    flex-shrink: 1;

    flex-grow: 1;
    width: 100%;
    overflow: auto;
    height: 100%;
  }

  .demand-wrapper .demand-head-wrapper {
    flex-shrink: 0;
    flex-grow: 0;
    height: 60px;
    width: 100%;
    position: relative;
    background-color: #FAFAFA;

  }

  .demand-wrapper .demand-head-wrapper .demand-head {
    top: 0;
    left: 0;
    height: 60px;
    width: 100%;
    background-color: #FAFAFA;
    border-bottom: solid 1px rgba(213,213,213,.5);
    align-items: center;
    justify-content: space-between;
    display: flex;
    position: fixed;
  }
  .demand-wrapper .demand-head-wrapper .demand-head  .demand-head-navbar .navbar-item:first-child{
    margin-left: 10px;
  }
  .demand-wrapper .demand-head-wrapper .demand-head  .demand-head-navbar .navbar-item{
    opacity: 0.15;
    padding: 0 10px;

  }
  .demand-wrapper .demand-head-wrapper .demand-head  .demand-head-navbar .navbar-item.current{
    opacity: 1;
  }
  .demand-wrapper .demand-head-wrapper .demand-head  .demand-head-icon{
    padding: 10px;
    margin-right: 10px;
  }

</style>
<script type="text/ecmascript-6">
  import AddIcon from 'svg/main/demand/Add.vue'
  export default{
    components: {
      AddIcon
    }
  }
  //  import DemandCard from 'components/demand-card/demand-card.vue'
  //  import PhotoSwipe from 'components/photoswipe/photoswipe.vue'
  //  export default{
  //    components: {
  //      'demand-card':DemandCard,
  //      "photo-swipe":PhotoSwipe
  //    },
  //    methods:{
  //      onViewImage(index,list){//绑定点击图片事件
  //        //   this.$refs.viewer是图片浏览组件的应用，调用其中show方法
  //        this.$refs.viewer.show(index,list);
  //      },
  //      onCardTag(data){
  //        this.$router.go('/demand/'+data.id);
  //      }
  //    },
  //    computed:{
  //      debug_list(){
  //        //调试用，过滤掉部分tag
  //        return this.list.filter((item)=>(item.tag==='sell'));
  //      }
  //    },
  //    ready(){
  //      console.log(this.$api);
  //    },
  //    data:function(){
  //      return {
  //        list: [
  //          {
  //            tag: 'express',
  //            create_time: 1472581345635,
  //            data: {
  //              id:1,
  //              text: '有没有人可以帮忙拿个快递到F1?',
  //              pay: 15,
  //              images: []
  //            }
  //          },
  //          {
  //            tag: 'sell',
  //            create_time: 1472581045635,
  //            data: {
  //              id:2,
  //              text: '出一条全新的牛仔a字裙，49元，适合平时穿SM码数的美眉，想买的评论',
  //              pay: 49,
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
  //                }
  //              ]
  //            }
  //          },
  //          {
  //            id:2,
  //            tag: 'help',
  //            create_time: 1472581045635,
  //            data: {
  //              text: '谁能帮小泽妹纸我打个饭？',
  //              images:[]
  //            }
  //          },
  //          {
  //            id:2,
  //            tag: 'lost',
  //            create_time: 1472581045635,
  //            data: {
  //              thing:'银行卡',
  //              text: '寻失主。昨晚在建行网点捡到，一直找不到失主。麻烦失主看到联系我取回卡。下方留言我私聊你。',
  //              images:[{
  //                src:'http://ww3.sinaimg.cn/mw690/e910bd00jw1f5exrm7glwj20qo0zkq55.jpg',
  //                w: 360,
  //                h: 480
  //              }]
  //            }
  //          },
  //          {
  //            tag: 'found',
  //            create_time: 1472581045635,
  //            data: {
  //              thing:'苹果',
  //              text: '灰姑娘今天捡到了一个有毒的苹果',
  //              images:[]
  //            }
  //          },
  //          {
  //            tag: 'dating',
  //            create_time: 1472581045635,
  //            data: {
  //              text: '有没有妹纸想一起去北岭山?',
  //              want_sex:2,
  //              like_num:8,
  //              join_num:3,
  //              liked:false,
  //              time:1472581045635
  //            }
  //          },
  //          {
  //            tag: 'dating',
  //            create_time: 1472581045635,
  //            data: {
  //              text: '有没有男生想一起去打工的?',
  //              want_sex:1,
  //              like_num:1,
  //              liked:true,
  //              join_num:5,
  //              time:1472581045635
  //            }
  //          },
  //          {
  //            tag: 'dating',
  //            create_time: 1472581045635,
  //            data: {
  //              text: '打波?',
  //              want_sex:0,
  //              liked:false,
  //              like_num:10,
  //              join_num:10,
  //              time:1472581045635
  //            }
  //          }
  //        ]
  //      }
  //    }
  //  }
</script>
