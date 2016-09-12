<template>
    <div class="body">
      <div class="message">
        <div class="top">
          <div class="text">
            {{item.content}}
          </div>
        </div>
        <div class="center">
          <div class="image-wrapper hide-scroll" v-if="item.images&& item.images.length!=0">
            <img class="image" v-for="item in item.images" v-lazy="item.src" @click.stop="onClickImage($index)">
          </div>
        </div>
        <div class="bottom">
          <div class="left">
            <div class="identity">#{{item.identity}}</div>
            <div class="dian">·</div>
            <div class="time">{{item.time}}</div>
          </div>
          <div class="right">
            <div class="comment">{{item.comment}}<icon-comment-icon></icon-comment-icon> </div>
            <div class="like">{{item.like}}<icon-like-icon></icon-like-icon></div>

          </div>
        </div>

      </div>
    </div>
</template>
<style src="./message.css" scoped>




</style>
<script>
  import IconCommentIcon from '../../svg/common/comment/IconComment.vue';
  import IconLikeIcon from '../../svg/common/comment/IconLike.vue';


  export default{
    data(){
      return {}
    },
    components: {
      IconCommentIcon,
      IconLikeIcon
    },
    props:['item'],
    methods:{
      onClickImage(index){
        this.$emit("view-image",index,this.item.image);
      },
    }


  }
</script>
