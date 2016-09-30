<template>

  <div>

    <!--图文-->
    <publish-picture :publish_text.sync="content.text" :image_publish.sync="content.file">
    </publish-picture>

    <!--丢失类型  -->
    <publish-choose :key="publish_key.ext_type" :labels="type_labels"
                    :publish_value.sync="content.ext_type"  v-on:change-type='changeLostType'>
    </publish-choose>

    <!--物品-->
    <publish-text :key="publish_key.thing" :publish_value.sync="content.thing">
    </publish-text>

    <!--捡到、丢失时间-->
    <publish-time :key="publish_key.found_time" :publish_value.sync="content.found_time" :max_date.sync=today>
    </publish-time>

  </div>

</template>

<style scoped>

</style>

<script type="text/ecmascript-6">

  import PublishPicture from '../../components/publish/publish-picture.vue'
  import PublishText from '../../components/publish/publish-text.vue'
  import PublishChoose from '../../components/publish/publish-choose.vue'
  import PublishTime from '../../components/publish/publish-time.vue'
  import {token, login_state, is_login} from '../../vuex/getters.js'
  import '../../components/publish/alert-view'

  export default{

    data(){
      return {
        content: {
          ext_type: 5,
          text: "",                  //"文字描述",
          file: [],                 //null,
          thing: "",               //"东西名称",
          found_time: "",               //"丢失/捡到时间",
        },
        publish_key: {
          ext_type: "类型",
          thing: "什么物品",
          found_time: "捡到时间"
        },
        all_tag:[
          {text:"说说那些年在校园里面让你最尴尬的事情~"},
          {text:"你的舍友做过的最奇葩的事情是什么？"},
          {text:"你对微微一下很倾城里面的肖奈有什么看法？"}
        ],
        images: [
          {hasImage: false},
          {hasImage: false},
          {hasImage: false},
          {hasImage: false}
        ],
        has_add_image: true,

        type_labels: [
          {
            tag: "捡到",
            id: "foundType",
            isChecked: true,
            value: 5
          },
          {
            tag: "丢失",
            id: "lostType",
            isChecked: false,
            value: 4
          }
        ],
      }
    },
    methods: {
      changeLostType(index){
        if (index == 0){
          this.publish_key.found_time = "捡到时间";
        }else {
          this.publish_key.found_time = "丢失时间";
        }
      }
    },
    components: {
      PublishPicture,
      PublishText,
      PublishChoose,
      PublishTime,
    },
    vuex: {
      actions: {},
      getters: {
        login_state,
        token,
        is_login
      }
    },
    events: {
      'publish-demand': function (message) {
        var self = this;
        let token = this.token;
        var BOUNDARYPREFIX = 'nbglme';

        // 验证数据格式
        if (self.content.ext_type===2 && self.content.text.length == 0){
          Simpop({
            content: '描述文字不能为空哦~',
            time: 2000  //2秒后自动关闭
          }).show();
          return;
        }else if (self.content.thing.length == 0){
          Simpop({
            content: '说明是什么物品才能帮你快速找回哦~',
            time: 2000  //2秒后自动关闭
          }).show();
          return;
        }

        // 为了安全，还是做一下验证
        if (message == "publish_lost") {
//           post提交，发表走起
          var formData = new FormData();
          formData.append("ext_type", self.content.ext_type);
          formData.append("text", self.content.text);
          if (self.content.file.length>=1){
            for (var i = 0; i<self.content.file.length; i++){
              formData.append("file", self.content.file[i]);
            }
          }
          formData.append("thing",self.content.thing);
          if (self.content.found_time && self.content.found_time!=''){
            if (self.content.ext_type == 4) { // 丢失
              formData.append("lose_time", Date.parse(self.content.found_time));
            } else { // 捡到
              formData.append("pickeup_time",Date.parse(self.content.found_time));
            }
          }

          return this.$request
            .post(`${this.$api.url_base}/demand?token=${token}`)
            .send(formData)
            .then(function (res) {
              if (res.body.code === 200){
                Simpop({
                  mask: false,
                  content: '发布成功~',
                  time: 1000  //1秒后自动关闭
                }).show(function () {
                  window.history.back();
                });
              }else {
                Simpop({
                  content: '出了点小问题~',
                  time: 1000  //1秒后自动关闭
                }).show();
              }

            })
            .catch(function (err) {
              Simpop({
                content: err,
                time: 2000  //2秒后自动关闭
              }).show();
            })

        }else {
         alert("非法操作");
        }
      }
    }
  }
</script>
