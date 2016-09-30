<template>

  <div>
    <!--图文-->
    <publish-picture :publish_text.sync="content.text" :image_publish.sync="content.file"></publish-picture>

    <!--标签-->
    <publish-text :key="publish_key.tag" :publish_value.sync="content.lab_name"></publish-text>
  </div>

</template>

<script type="text/ecmascript-6">

  import PublishPicture from '../../components/publish/publish-picture.vue'
  import PublishText from '../../components/publish/publish-text.vue'
  import {token, login_state, is_login} from '../../vuex/getters.js'

  export default{
    data(){
      return{
        content:{
          ext_type: 11,
          text: "",              //"文字描述",
          file: [],              //null,
          tag_id: 0,              // 标签id 如果是自定义标签为0  其他为标签原本id 在选择标签时修改
          lab_name: "",               //标签
        },
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
    events:{
      'cao-publish': function (message) {
        var self = this;
        let token = this.token;

        // 验证数据格式
        if (self.content.text.length == 0){
          Simpop({
            mask: false,
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

        if (message == "cao_out_publish") {
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
    vuex: {
      actions: {},
      getters: {
        login_state,
        token,
        is_login
      }
    },
    components:{
      PublishPicture,
      PublishText
    }
  }
</script>
