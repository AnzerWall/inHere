<template>
  <div v-if="!$loadingRouteData">
    <div class="body">
      <div class="content-comment">
        <div class="comment-title" v-if="list.user_id!=user_id">
          <div class="title-left " :style="{color:main_color}">
            <div class="name">{{list.name}}</div>
            <div class="dian"> ·</div>
            <div class="time">{{list.create_time|fromNow}}</div>
          </div>
          <div class="title-right">#{{list.floor}}</div>
        </div>
        <!--楼主-->
        <div class="comment-title" v-if="list.user_id===user_id">
          <div class="title-left" :style="{color:main_color}">
            <div class="name1" :style="{backgroundColor:main_color}">楼主</div>
            <div class="dian"> ·</div>
            <div class="time">{{list.create_time|fromNow}}</div>
          </div>
          <div class="title-right">#{{list.floor}}</div>
        </div>
        <div class="comment-message">{{list.content}}</div>
        <div class="like" v-if="number===1" :style="{color:comment_color}">
          {{list.praise}}
          <icon-like-icon class="like-icon" @click="onclickpraise(list,list.id,list.ext_type)"
                          :style="{fill:comment_color}"></icon-like-icon>
        </div>
      </div>
    </div>


  </div>
</template>
<style src="./comment.css" scoped>


</style>
<script type="text/ecmascript-6">
  import {humanized,fromNow} from '../../filter/time.js';
  import IconLikeIcon from '../../svg/common/comment/IconLike.vue';
//  import InfiniteLoading from 'vue-infinite-loading';
  import commentColor from '../../util/praise_color.js';
//  import {token,login_state,is_login,school,user_id} from '../../vuex/getters.js'

  export default{
//    vuex: {
//      actions: {},
//      getters: {
//        login_state,
//        token,
//        is_login,
//        school,
//        user_id
//      }
//    },
    filters: {
      humanized,
      fromNow
    },
    components: {
      IconLikeIcon,

    },

    props: ['list', 'main_color', 'user_id', 'number', 'data', 'ext_type'],
    data(){

      return {}
    },
    methods: {
      onclickpraise(ext_data, id, ext_type){
        console.log(ext_data, id, ext_type);
        this.$emit('onclickpraise', ext_data, id, ext_type);
      }
    },
    computed: {
      comment_color(){
        return commentColor.commentColor(this.list)
      }

    }


  }

</script>
