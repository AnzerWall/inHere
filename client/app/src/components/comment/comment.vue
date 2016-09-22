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

  </div>
</template>
<style src="./comment.css" scoped>


</style>
<script>
  import {humanized,fromNow} from '../../filter/time.js';
  import IconLikeIcon from '../../svg/common/comment/IconLike.vue';

  export default{
    filters:{
      humanized,
      fromNow
    },
    components:{
      IconLikeIcon

    },

    props:

      ['comments','main_color','user_id','number'],
    data(){

      return {
      }
    },



  }

</script>
