<template>
  <div class="details">
    <div class="head" :style="{'background-image': 'url('+cover_img.src+')'}">
      <div class="content">
        <div class="back" @click="back()">《</div>
        <div class="title">{{data.title}}</div>
        <div class="change">
          <div class="user">
            <span class="font1">@{{data.user_name}}</span>
            <span class="time font1">{{data.start_time|fromNow}}</span>
          </div>

          <div class="my-base">
            <span class="place">{{data.place}}</span>
            <span class="like">{{data.praise}}<icon-like class="icon"></icon-like></span>
          </div>
        </div>
      </div>
    </div>
    <div class="d-content">
      <iframe v-el:myiframe scrolling="no" frameborder="0" @load="changeFrameHeight()" width="100%"
              src="{{data.content}}"></iframe>
    </div>
    <!--评论组件-->
    <div class="a-r-length">
      <div class="a-r-star">*</div>
      {{lists.length}}条评论
    </div>
    <div class="activity-reply">

      <comment :comments="lists" :main_color="main_color" :user_id="user_id" :number="number"></comment>
    </div>
  </div>
</template>
<style scoped>

  .head {
    padding: 0;
    margin: 0;
    position: relative;
    width: 100%;
    height: 360px;
    background-size: cover;
    background-position: center;
  }

  .back {
    margin-left: 7px;
    padding-top: 28px;
    font-size: large;
    color: white;
    font-weight: bold;
  }

  .title {
    color: white;
    font-weight: bold;
    margin-top: 108px;
    margin-left: 25px;
    margin-right: 47px;
    font-size: 30px;
    display: -webkit-box;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
    vertical-align: middle;
  }

  .change {
    padding-bottom: 25px;
    opacity: 0.7;
    background: linear-gradient(to top, rgba(0, 0, 0, .8), rgba(0, 0, 0, 0));
  }

  .user {
    margin-top: 46px;
    margin-left: 25px;
  }

  .time {
    margin-left: 6px;
  }

  .font1 {
    font-size: 14px;
    color: white;
  }

  .my-base {
    margin-left: 25px;
    margin-top: 32px;
  }

  .place {
    padding: 5px 7px 5px 7px;
    background-color: white;
    border-radius: 5px;
  }

  .like {
    color: white;
    font-weight: bold;
    float: right;
    margin-right: 28px;
  }

  .icon {
    margin-left: 2px;
  }

  .d-content {
    padding: 20px 0 20px 0;
  }

  .activity-reply {
    padding: 20px 0 20px 0;
  }

  .a-r-length {
    color: #03b719;
    margin-left: 30px;
    font-weight: bold;
    position: relative;
    margin-top: 40px;
  }

  .a-r-star {
    color: #03b719;
    padding: 0;
    margin: 0;
    position: absolute;
    right: 100%;
    margin-right: 5px;
    top: 50%;
    transform: translateY(-50%)

  }
</style>
<script type="text/javascript">

  import Comment from '../../components/comment/comment.vue'
  import IconLike from 'svg/common/comment/IconLike.vue'
  import {fromNow} from 'filter/time.js';
  export default{
    filters: {
      fromNow
    },
    methods: {
      back(){
        window.history.back()
      },
      changeFrameHeight(){
        this.$els.myiframe.height = document.documentElement.clientHeight;
      }

    },
//    评论组件数据请求
    route: {
      data(){
        var id = this.$route.params.id;
        var token = "19e7aae2d81da63d62cfa36eb69706069e7a97bb61c8901782d8c1d98765ea94";
        return this.$request
          .get('http://115.28.67.181:8080/activity/' + id)
          .query({token: token})
          .then(this.$api.checkResult)
          .then(function (data) {
            return {
              data: data,
              lists: data.comment.items,
              user_id: data.user_id,
              cover_img: data.cover_img
            }
          })
      }
    },
    data(){
      return {
        lists: [],
        user_id: '',
        data: {},
        number: 1,
        cover_img: []
      }
    },
    components: {
      IconLike,
      Comment
    },
    computed: {
      main_color: function () {
        return "#03b719"
      }
    }
  }

</script>
