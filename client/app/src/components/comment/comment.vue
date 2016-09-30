<template>
  <div v-if="!$loadingRouteData">
  <div class="body">
    <div v-for="list in comments" class="content-comment">
      <div class="comment-title" v-if="list.user_id!=user_id">
        <div class="title-left " :style="{color:main_color}">
          <div class="name" >{{list.name}}</div>
          <div class="dian"> ·</div>
          <div class="time">{{list.create_time|fromNow}}</div>
        </div>
        <div class="title-right">#{{list.floor}}</div>
      </div>
      <!--楼主-->
      <div class="comment-title" v-if="list.user_id===user_id">
        <div class="title-left" :style="{color:main_color}">
          <div class="name1" :style="{backgroundColor:main_color}">楼主</div>
          <div class="dian"> · </div>
          <div class="time">{{list.create_time|fromNow}}</div>
        </div>
        <div class="title-right">#{{list.floor}}</div>
      </div>
      <div class="comment-message">{{list.content}}</div>
      <div class="like" v-if="number===1">
        {{list.praise}}<icon-like-icon class="like-icon"></icon-like-icon>
      </div>
    </div>
</div>
    <!--<infinite-loading :on-infinite="onLoadMore">-->
        <!--<span slot="no-more">-->
          <!--没有更多了...-->
        <!--</span>-->
    <!--</infinite-loading>-->

  </div>
</template>
<style src="./comment.css" scoped>


</style>
<script type="text/ecmascript-6">
  import {humanized,fromNow} from '../../filter/time.js';
  import IconLikeIcon from '../../svg/common/comment/IconLike.vue';
  import InfiniteLoading from 'vue-infinite-loading';

  export default{
    filters:{
      humanized,
      fromNow
    },
    components:{
      IconLikeIcon,
      InfiniteLoading

    },

    props:
      ['comments','main_color','user_id','number','data','ext_type'],
    data(){

      return {
      }
    },
    methods:{
//      onLoadMore(){
//        var token = "4121581213c1605a1db4872d7cca6eed1b41259bffd8066d9573783b07214d6f";
//        this.$request
//            .get("http://115.28.67.181:8080/comments")
//            .query({token:token})
//            .query({offset: this.data.offset + 5, limit: this.data.limit})
//            .query({ext_type: this.ext_type})
//            .query({item_id: this.data.id})
//            .then(this.$api.checkResult)
//            .then((data)=> {
//            //通知组件加载完毕
//            this.$broadcast('$InfiniteLoading:loaded');
//        //更新数据数组
//        this.list = this.list.concat(data.items);
//        this.data.offset = data.offset;
//        this.data.total=data.total;
//        //判断是否已经不能加载到更多的数据
//        if(this.data.offset>=this.data.total){
//          this.$broadcast('$InfiniteLoading:complete');
//        }
//      })
//      .catch (function(e){
//          console.log(e);
//        })
//      },
    }



  }

</script>
