<template>
    <div class="body">
      <photos-wipe v-ref:viewer></photos-wipe>
      <div class="cao-message" >
        <div class="top" @click="onClick(item.id)">
          <div class="message-text" >
            {{item.ext_data.content}}
          </div>
        </div>
        <div class="center hide-scroll" @click="onClick(item.id)">

          <div class="image-wrapper " v-if="item.ext_data.photos&& item.ext_data.photos!=0"  >
            <img class="image" v-for="photo in item.ext_data.photos" v-lazy="photo.src" @click.stop="onClickImage($index)">
            <div style="height:10px;width:15px;">&nbsp;</div>
          </div>

          <div class="image-space" v-if="item.ext_data.photos&& item.ext_data.photos.length!=0"></div>
        </div>

        <div class="bottom">
          <div class="left" @click="onClick(item.id)">
              <div class="identity" :style="{backgroundColor:main_color}">#{{item.label_name}}</div>
            <div class="dian">·</div>
            <div class="time" :style="{color:main_color}">{{item.create_time|fromNow}}</div>
          </div>
          <div class="right">
            <div class="comment" @click="onClick(item.id)">{{item.ext_data.comment_num}}</div><icon-comment-icon class="icon-comment" @click="onClick(item.id)"></icon-comment-icon>
            <div class="like" :style="{color:message_color}">{{item.ext_data.praise}}</div><icon-like-icon @click="onclickpraise(item.ext_data,item.id,item.ext_type)" :style="{fill:message_color}"></icon-like-icon>
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
  import color from '../../util/praise_color.js';



  export default{
    data(){
      return {}
    },
    components: {
      IconCommentIcon,
      IconLikeIcon,
      PhotosWipe
    },
    props:['item','main_color'],
    methods:{
      onClickImage(index){
        this.$refs.viewer.show(index,this.item.ext_data.photos);
      },
      onClick(id){
        console.log("click");
        this.$emit('on-click',id);
      },
      onclickpraise(ext_data,id,ext_type){
        console.log(ext_data,id,ext_type);
        this.$emit('onclickpraise',ext_data,id,ext_type);
      },

    },
    computed:{
      message_color(){
        return color.messageColor(this.item);
//
      }
    },
    filters:{
      fromNow
    }



  }
</script>
