<template>

  <div>
    <!--导航栏头部-->
    <div class="publish-header">
      <div class="publish-nav publish-nav-back" @click="back">《 有问必答</div>

      <div class="publish-nav confirm-icon" @click="askPublish">
        <confirm-icon style="fill:white"></confirm-icon>
      </div>

    </div>

    <!--内容页面-->
    <div>
      <!--占位-->
      <div class="publish-content-header"></div>

      <!--标题-->
      <div class="ask-title">
        <textarea v-model="content.title" placeholder="写下你的问题吧~"></textarea>
      </div>

      <!--图文-->
      <publish-picture :publish_text.sync="content.text" :image_publish.sync="content.file"></publish-picture>

      <!--标签-->
      <publish-tag-choose :key="publish_key.tag" :tag.sync="content.lab_name" :tags="lab_items" :editable="!!1"></publish-tag-choose>

    </div>
  </div>
</template>
<style scoped>
  .publish-header {
    display: flex;
    justify-content: space-between;
    flex-wrap: nowrap;
    width: 100%;
    height: 60px;
    border-bottom: 1px solid rgba(213, 213, 213, .5);
    padding-top: 20px;
    position: fixed;
    z-index: 2000;
    align-items: center;
    color: white;
    background: linear-gradient(to bottom, #0274FF ,#5FA7FF);
  }

  .publish-nav {
    font-weight: bold;
    padding: 0 10px;
  }

  .publish-nav-back {
    /*padding-left: 20px;*/
  }

  .confirm-icon {
    padding-right: 20px;
  }

  .publish-content-header {
    height: 80px;
  }

  .ask-title {
    padding: 20px;
    border-bottom: 1px solid #d5d5d5;
  }

  textarea {
    width: 100%;
    height: 2em;
    font-weight: bold;
    border-style: none;
    resize: none;
    outline: none;
  }
</style>
<script>
  import ConfirmIcon from 'svg/common/Confirm.vue'
  import PublishPicture from '../../components/publish/publish-picture.vue'
  import PublishTagChoose from '../../components/publish/publish-tag-choose.vue'
  import {token, login_state, is_login} from '../../vuex/getters.js'
  import '../../components/publish/alert-view'

  export default{
    route: {
      //页面加载数据钩子(或者叫事件)
      data(){
        return this.$request
          .get(`${this.$api.url_base}/ask_reply/labels?token=${token}`)
          .query({'ext_type':12,'limit':100})  // 获取50条数据，类型为问答10
          .then(this.$api.checkResult)//处理code等信息，返回data
          .then((data)=> {
            //处理数据
            this.lab_items = data.items.map(function(item,index){
              item.value = item.name;
              return item;
            });
          })
      }
    },
    data(){
      return {
        content: {
          ext_type: 12,
          title: "",
          text: "",                  //"文字描述",
          file: [],                 //null,
          lab_name: "" ,              //标签
        },
        lab_items:[],
        publish_key: {
          tag: "# 标签"
        },
        images: [
          {hasImage: false},
          {hasImage: false},
          {hasImage: false},
          {hasImage: false}
        ],
        has_add_image: true,
      }
    },
    methods: {
      back(){
        window.history.back()
      },
      askPublish: function () {
        var self = this;
        let token = this.token;

        // 验证数据格式
        if (self.content.title.length === 0) {
          Simpop({
            mask: false,
            content: '问题不能为空哦~',
            time: 2000  //2秒后自动关闭
          }).show();
          return;
        } else if (self.content.lab_name === '') {
          Simpop({
            mask: false,
            content: '调皮，标签不能为空哦~',
            time: 2000  //2秒后自动关闭
          }).show();
          return;
        }

        var formData = new FormData();
        formData.append("ext_type", self.content.ext_type);
        formData.append('title', self.content.title);
        if (self.content.text.length > 0) {
          formData.append("content", self.content.text);
        }
        // 图片的验证和处理
        if (self.content.file.length > 0) {
          for (var i = 0; i < self.content.file.length; i++) {
            formData.append("file", self.content.file[i]);
          }
        }
        formData.append('lab_name', self.content.lab_name);

        return this.$request
          .post(`${this.$api.url_base}/ask_reply?token=${token}`)
          .send(formData)
          .then(function (res) {
            if (res.status === 200) {
              Simpop({
                mask: false,
                content: '发布成功~',
                time: 1000  //1秒后自动关闭
              }).show(function () {
                window.history.back();
              });
            }
          })
          .catch(function (err) {
            Simpop({
              content: err,
              time: 1500
            }).show();
          })
      }
    },
    vuex: {
      actions: {},
      getters: {
        login_state,
        token,
        is_login
      }
    },
    components: {
      ConfirmIcon,
      PublishPicture,
      PublishTagChoose
    },
  }
</script>
