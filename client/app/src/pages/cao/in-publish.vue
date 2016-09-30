<template>

  <div>
    <!--图文-->
    <publish-picture :publish_text.sync="content.text" :image_publish.sync="content.file">
    </publish-picture>

    <!--标签-->
    <publish-tag-choose :key="publish_key.tag" :tag.sync="content.lab_name" :tags="lab_items" :editable="!!1"></publish-tag-choose>
  </div>

</template>


<script type="text/ecmascript-6">

  import PublishPicture from '../../components/publish/publish-picture.vue'
  import PublishTagChoose from '../../components/publish/publish-tag-choose.vue'
  import {token, login_state, is_login} from '../../vuex/getters.js'

  export default{
    route: {
      //页面加载数据钩子(或者叫事件)
      data(){
        return this.$request
          .get(`${this.$api.url_base}/ask_reply/labels?token=${token}`)
          .query({'ext_type':10,'limit':100})  // 获取50条数据，类型为问答10
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
      return{
        content:{
          ext_type: 10,
          text: "",                  //"文字描述",
          file: [],                 //null,
          lab_name: "",               //标签
        },
        lab_items:[],
        publish_key:{
          tag:"# 标签"
        },
        images:[
          {hasImage:false},
          {hasImage:false},
          {hasImage:false},
          {hasImage:false}
        ],
        has_add_image:true,
      }
    },
    methods:{
    },
    vuex: {
      actions: {},
      getters: {
        login_state,
        token,
        is_login
      }
    },
    events:{
      'cao-publish': function (message) {
        var self = this;
        let token = this.token;

        // 验证数据格式
        if (self.content.text.length == 0){
          Simpop({
            content: '描述文字不能为空哦~',
            time: 2000  //2秒后自动关闭
          }).show();
          return;
        }else if (self.content.lab_name===''){
          Simpop({
            mask: false,
            content: '调皮，自定义标签不能为空哦~',
            time: 2000  //2秒后自动关闭
          }).show();
          return;
        }

        if (message == "cao_in_publish") {
          var formData = new FormData();
          formData.append('ext_type',self.content.ext_type);
          formData.append("content", self.content.text);
          // 图片的验证和处理
          if (self.content.file.length > 0) {
            for (var i = 0; i < self.content.file.length; i++) {
              formData.append("file", self.content.file[i]);
            }
          }
          formData.append('lab_name',self.content.lab_name);

          return this.$request
            .post(`${this.$api.url_base}/ask_reply?token=${token}`)
            .send(formData)
            .then(function (res) {
              if (res.status===200){
                Simpop({
                  mask: false,
                  content: '发布成功~',
                  time: 1000
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
        }else {
          alert("非法操作");
        }
      }
    },
    components:{
      PublishPicture,
      PublishTagChoose
    }
  }
</script>
