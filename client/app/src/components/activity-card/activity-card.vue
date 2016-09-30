<template>
    <div>
<div class="b-card"  :style="{'background-image': 'url('+item.cover_img.src+')'}"@click="$router.go('/activity/'+item.id)">
  <div class="b-activity-content-bg">
  <div class="b-title">{{item.title}}</div>
  <div class="b-user">
    <span class="ad " v-if="item.ext_type==='9'">广告</span>
    <span class="b-name font-b">@{{item.user_name}}</span>
    <span class="b-time font-b">{{item.start_time|fromNow}}～{{item.end_time|fromNow}}</span>
  </div>
  </div>
  <div class="b-base">
    <span class="b-address"v-if="item.place">{{item.place}}</span>
    <span></span>
    <span class="b-like font-b">{{item.praise}}<icon-like class="icon"></icon-like></span>

  </div>
</div>
    </div>
</template>
<style scoped>
  .icon{
    margin-left: 2px;
  }
.b-card{
  margin-top: 10px;
  position: relative;
  height: 197px;
  background-size: cover;
  background-position: center;
  border-radius: 8px;
}
  .b-title{
    font-weight: bold;
    font-size: 1.1em;
    color: white;
  }
.font-b{
  font-size: small;
  color: white;
  font-weight: bold;
}
.b-activity-content-bg {
padding: 15px 15px 40px 15px ;
  border-radius: 8px;
  opacity: 0.7;
  background: linear-gradient(to bottom, rgba(0, 0, 0,.8), rgba(0, 0, 0, 0));
}
.b-user{
  margin-top: 5px;
}
.ad{
  padding:2px 4px 2px 4px;
  border-radius: 4px;
  background-color: white;
  font-size: x-small;
  color: black;
  font-weight: bold;
}
  .b-base{
    margin-top: 65px;
display: flex;
    justify-content:space-between;
  }

  .b-like{
    margin-right: 18px;
  }
  .b-address{
    font-size: small;
    color: black;
    padding: 4px 8px 4px 8px;
border-radius: 4px;
    background-color: white;
    margin-left: 13px;
    font-size: small;
  }
</style>
<script type="text/ecmascript-6">
  import {fromNow} from 'filter/time.js';
import IconLike from 'svg/common/comment/IconLike.vue'
    export default{
      filters:{
        fromNow
      },
        data(){
            return{

            }
        },
     props: ['item'],
//       item: {
//              type: Object,
//              required: true,
//              default: ()=>({
//                id: 1,
//                cover_img: "resources/activity/1-max.jpg",
//                title: "活动标题",
//                user_name:　"组织名称",
//                start_time: "开始时间",
//                end_time: "结束时间",
//                praise: 0,
//            place: "地方",
//            url: "http://ip:port/activity/23"
//
//        })
//}


        components:{
          IconLike

        }
    }
</script>
