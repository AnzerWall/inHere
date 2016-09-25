<template>
    <div class="body">
      <photos-wipe v-ref:viewer></photos-wipe>
      <div class="message">
        <div class="top">
          <div class="message-text" @click="onClick(item.id)">
            {{item.ext_data.content}}
          </div>
        </div>
        <div class="center hide-scroll">

          <div class="image-wrapper " v-if="item.ext_data.photos&& item.ext_data.photos!=0"  >
            <img class="image" v-for="photo in item.ext_data.photos" v-lazy="photo.src" @click.stop="onClickImage($index)">
          </div>

          <div class="image-space" v-if="item.ext_data.photos&& item.ext_data.photos.length!=0"></div>
        </div>

        <div class="bottom">
          <div class="left">
              <div class="identity" :style="{backgroundColor:main_color}">#{{item.label_name}}</div>
            <div class="dian">·</div>
            <div class="time" :style="{color:main_color}">{{item.create_time|fromNow}}</div>
          </div>
          <div class="right">
            <div class="comment">{{item.ext_data.comment_num}}</div><icon-comment-icon class="icon-comment"></icon-comment-icon>
            <div class="like">{{item.ext_data.praise}}</div><icon-like-icon></icon-like-icon>
          </div>
        </div>

      </div>
    </div>
</template>
<style src="./message.css" scoped>
</style>
<script type="text/ecmascript-6">
  import IconCommentIcon from '../../svg/common/comment/IconComment.vue';
  import IconLikeIcon from '../../svg/common/comment/IconLike.vue';
  import PhotosWipe from '../photoswipe/photoswipe.vue';
  import {fromNow} from '../../filter/time.js';



  export default{
    data(){
      return {}
    },
    components: {
      IconCommentIcon,
      IconLikeIcon,
      PhotosWipe
    },
    props:['item','main_color',],
    methods:{
      onClickImage(index){
        this.$refs.viewer.show(index,this.item.ext_data.photos);
      },
      onClick(id){
        console.log("click");
        this.$emit('on-click',id);
      }


    },
    filters:{
      fromNow
    }



  }
</script>
