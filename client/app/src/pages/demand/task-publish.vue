<template>
  <!--任务图文-->
  <div>
    <publish-picture :publish_text.sync="content.text" :image_publish.sync="content.file"></publish-picture>


    <!--任务类型-->
    <publish-choose :key="publish_key.ext_type" :publish_value.sync="content.ext_type" :labels="type_labels"
                    v-on:change-type="changeTaskType">
    </publish-choose>


    <!--快递-->
    <div v-if="publish_type == 0">
      <!--酬劳-->
      <publish-number :key="publish_key.pay" :publish_value.sync="content.pay"></publish-number>
    </div>


    <!--转让-->
    <div v-if="publish_type == 1">
      <!--售价-->
      <publish-number :key="publish_key.price" :publish_value.sync="content.price"></publish-number>
      <!--原价-->
      <publish-number :key="publish_key.original_price" :publish_value.sync="content.original_price"></publish-number>
      <!--成色-->
      <publish-tag-choose :key="publish_key.quality" :tag.sync="content.quality" :tags="lab_items" :editable="!1"></publish-tag-choose>
      <!--购买时间-->
      <publish-time :key="publish_key.buy_time1" :publish_value.sync="content.buy_time"></publish-time>

    </div>


    <!--帮忙-->
    <div v-if="publish_type == 2">
      <!--酬劳-->
      <publish-number :key="publish_key.pay" :publish_value.sync="content.pay"></publish-number>
    </div>


    <!--其他-->
    <div v-if="publish_type == 3">
      <!--酬劳-->
      <publish-number :key="publish_key.pay" :publish_value.sync="content.pay"></publish-number>
    </div>
  </div>
</template>

<style scoped>

</style>

<script type="text/ecmascript-6">

  import PublishPicture from '../../components/publish/publish-picture.vue'
  import PublishTagChoose from '../../components/publish/publish-tag-choose.vue'
  import PublishChoose from '../../components/publish/publish-choose.vue'
  import PublishTime from '../../components/publish/publish-time.vue'
  import PublishNumber from '../../components/publish/publish-number.vue'
  import {token, login_state, is_login} from '../../vuex/getters.js'
  import '../../components/publish/alert-view'

  export default{

    data(){
      return {
        content: {                 // 记录编号
          ext_type: 1,              // 1-兼职，2-转让，3-帮忙,
          text: "",                  //"文字描述",
          file: [],                 //null,

          //1、快递   3、帮忙
          pay: "",             // "酬金"

          //2、转让
          quality: "",                   //"成色",
          buy_time: "",                //"购买时间",
          original_price: "",          //"原价",
          price: "",                   //"售价"
        },
        publish_type: "",          // 发布任务的类型  1、快递  2、转让 3、帮忙
        lab_items:[
          {name:"1成",value:"1成"},
          {name:"2成",value:"2成"},
          {name:"3成",value:"3成"},
          {name:"4成",value:"4成"},
          {name:"5成",value:"5成"},
          {name:"6成",value:"6成"},
          {name:"7成",value:"7成"},
          {name:"8成",value:"8成"},
          {name:"9成",value:"9成"},
          {name:"10成",value:"10成"}
        ],
        publish_key: {
          ext_type: "类型",

          pay: "酬金",

          quality: "成色",
          buy_time1: "购买时间",
          original_price: "原价",
          price: "售价"
        },
        images: [
          {hasImage: false},
          {hasImage: false},
          {hasImage: false},
          {hasImage: false}
        ],
        has_add_image: true,

        type_labels: [
          {
            tag: "兼职",
            id: "expressType",
            isChecked: true,
            value: 1
          },
          {
            tag: "转让",
            id: "transferType",
            isChecked: false,
            value: 2
          },
          {
            tag: "帮忙",
            id: "helpType",
            isChecked: false,
            value: 3
          }
        ],
      }
    },
    methods: {
      changeTaskType: function (type) {
//        console.log("父组件接收子组件的通信");
        this.publish_type = type;
      }
    },
    components: {
      PublishPicture,
      PublishTagChoose,
      PublishChoose,
      PublishTime,
      PublishNumber
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

        // 数据是否为空和格式的校验
        if (self.content.text.length == 0) {
          Simpop({
            content: '描述文字不能为空哦~',
            time: 2000  //2秒后自动关闭
          }).show();
          return;
        } else if (self.content.ext_type===2 && self.content.price === '') {
          Simpop({
            content: '乖，把出售价格写上去~',
            time: 2000  //2秒后自动关闭
          }).show();
          return;
        }

        // 为了安全，还是做一下验证
        if (message === "publish_task") {
          // post提交，发表走起
          var formData = new FormData();
          formData.append("ext_type", self.content.ext_type);
          formData.append("text", self.content.text);
          // 图片的验证和处理
          if (self.content.file.length > 0) {
            for (var i = 0; i < self.content.file.length; i++) {
              formData.append("file", self.content.file[i]);
            }
          }
          if ((self.content.ext_type === 1 || self.content.ext_type === 3) && self.content.pay != '') { // 1、兼职、3、帮忙
            formData.append('pay', self.content.pay);
          }
          else if (self.content.ext_type === 2) { // 2、转让
            formData.append('price', self.content.price);
            if (self.content.quality != '') {// 将 '6成'转化为数字 6
              var quality = self.content.quality.replace(/成/, "");
              formData.append('quality', parseInt(quality));
            }
            if (self.content.buy_time1 != '') {
              formData.append('buy_time', Date.parse(self.content.buy_time));
            }
            if (self.content.original_price) {
              formData.append('original_price', self.content.original_price);
            }
          }

          return this.$request
            .post(`${this.$api.url_base}/demand?token=${token}`)
            .send(formData)
            .then(function (res) {
              if (res.body.code === 200){
                Simpop({
                  content: '发布成功~',
                  time: 1000  //2秒后自动关闭
                }).show(function () {
                  window.history.back();
                });
              }else {
                Simpop({
                  content: '出了点小问题~',
                  time: 1500  //1.5秒后自动关闭
                }).show();
              }
              console.log("res"+res.body.code);

            })
            .catch(function (err) {
              Simpop({
                content: err,
                time: 2000  //2秒后自动关闭
              }).show();
            })
        }
      },

    }
  }
</script>
