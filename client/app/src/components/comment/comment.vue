<template>

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
    </div>

  </div>
</template>
<style src="./comment.css" scoped>


</style>
<script>
  import {humanized,fromNow} from 'filter/time.js';
  import helper from '../../util/demand_helper.js';
  import detail from '../../pages/demand/detail.vue'

  export default{
    filters:{
      humanized,
      fromNow
    },

    props:

      ['comments','main_color','user_id'],



    data(){

      return {}
    },



  }

</script>
