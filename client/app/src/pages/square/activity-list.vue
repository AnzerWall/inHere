<template>
    <div v-if="!$loadingRouteData">
      <!--活动头部-->
      <div class="activity-head" ><span class="activity-title" @click="back()">《 现在有{{lists.length}}个活动</span></div>

      <!--活动列表-->
      <div class="activity-content">
        <activity-card v-for="list in lists" :item="list">

        </activity-card>
      </div>
    </div>
</template>
<style scoped>
.activity-head{
  padding: 0;
  margin: 0;
  background-color: white;
  width: 100%;
  height: 60px;
  position: fixed;
  display: flex;
  align-items:center;
  padding-left: 10px;
border-bottom: solid 1px  rgba(213,213,213,.5);
  z-index: 200;
}
.activity-title{
  font-weight: bold;
}
.activity-content{
position: relative;
  padding: 60px 10px 10px 10px;
}

</style>
<script>
  import ActivityCard from'components/activity-card/activity-card.vue'
    export default{
      methods:{
        back(){
          window.history.back()
        }
      },
      //配置路由钩子
      route: {
        //页面加载数据钩子(或者叫事件)
        data(){

          var token="4cc763b9496fda23941fbaa4af298fc3b32ed8f3f44f19f2585bc4f0ac51e103";
          return this.$request
            .get("http://115.28.67.181:8080/activity")//GET方法 url为/demand
            .query({token:token})
//            .query({ext_type: [1]})//    传递query，   url变为 /demand?ext_type=1&ext_type=2&ext_type=3 过滤信息
            .query({limit: 10})
            .then(this.$api.checkResult)//一个辅助函数，用于处理code等信息，直接返回data
            .then(function (data) {

              //处理数据，具体见vue-router文档data钩子页说明
              return {

                lists: data.items
              }
            })
        }
      },
        data(){
            return{
              lists:[]
//              amount:'5',
//              items:[
//                {
//                  id:1,
//                  cover_img: "static/img/Bitmap.png",
//                  title: "小清新微电影《一半》正在上映",
//                  user_name:　"影视文化协会",
//                  start_time: "2016-7-12",
//                  end_time: "2016-8-31",
//                  address:'学生活动中心',
//                  like:'88'
//                },
//                {
//                  id:2,
//                  cover_img: "static/img/Bitmap2.png",
//                  title: "活动标题",
//                  user_name:　"组织名称",
//                  start_time: "开始时间",
//                  end_time: "结束时间",
//                  address:'学生活动中心',
//                  like:'88'
//                },
//                {
//                  id:3,
//                  cover_img: "static/img/Bitmap3.png",
//                  title: "活动标题",
//                  user_name:　"组织名称",
//                  start_time: "开始时间",
//                  end_time: "结束时间",
//
//                  like:'88'
//                },
//                {
//                  id:4,
//                  cover_img: "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3930349856,855999982&fm=21&gp=0.jpg",
//                  title: "活动标题",
//                  user_name:　"组织名称",
//                  start_time: "开始时间",
//                  end_time: "结束时间",
//
//                  like:'88'
//                },
//                {
//                  id:5,
//                  tag:'ad',
//                  cover_img: "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=4149927889,705838494&fm=21&gp=0.jpg",
//                  title: "活动标题",
//                  user_name:　"组织名称",
//                  start_time: "开始时间",
//                  end_time: "结束时间",
//
//                  like:'88'
//                }

//              ]

            }
        },
        components:{
          ActivityCard

        }
    }
</script>
