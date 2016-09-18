<template>
  <div class="body">
    <!--校内-->
    <div class="school" v-if="$route.query.type==='school'" >
      <div class="top">
        <div class="left">《 有槽必吐</div>
        <div class="right">
          <div class="in">校内</div>
          <div class="out" @click="$router.go('/cao?type=public')">校外</div>
        </div>
      </div>
      <div @click="$router.go('/cao?type=school&tag=1')"> 小道消息</div>
      <div v-if="$route.query.tag==='1'">到付件案件了解到该了解爱死了几个垃圾管理</div>


    </div>



    <!--校外-->
    <div class="public" v-if="$route.query.type==='public'">
      fsdfladjflajlsjjglsjaflslf
    </div>
  </div>





    <message v-for="item in items"  :item="item" :image="item.images">
    </message>




</template>
<style>
  .xiao{
    color: red;
  }

</style>
<script>
  import Message from '../../components/suqare/message.vue'

    export default{
      data(){
        return {
          type:"school",

          items:[{
            content:'这个实训的老师太垃圾了啊啊啊啊啊啊啊啊啊啊啊啊',
            identity:'老师',
            time:'15分钟前',
            comment:'333',
            like:'231',
            images: [
              {

                src: 'http://ww4.sinaimg.cn/mw690/e910bd00jw1f7c80cfvyoj20qo0zk75i.jpg',
                w: 360,
                h: 480

              },
              {
                src: 'http://ww1.sinaimg.cn/mw690/e910bd00jw1f7c1ndknjwj20qo0zktat.jpg',
                w: 690,
                h: 920
              },
              {
                src: 'http://ww3.sinaimg.cn/mw690/e910bd00jw1f7c3j00tqij20qo0zkjts.jpg',
                w: 360,
                h: 480
              }
            ]



          }]
        }
      },
      components: {
        Message

      },
      methods:{

      },


    }
</script>
