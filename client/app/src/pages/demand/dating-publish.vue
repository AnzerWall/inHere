<template>
  <photos-wipe v-ref:imageviewer></photos-wipe>
  <div>
    <!--图文-->
    <publish-picture :publish_text.sync="text" :image_publish.sync="content.file">
    </publish-picture>

    <!--详情-->
    <!--人均消费-->
    <publish-number :key="publish_key.per_cost" :publish_value.sync="content.per_cost">

    </publish-number>

    <!--陪同对象-->
    <publish-choose :key="publish_key.want_sex" :publish_value.sync="content.want_sex"
                    :labels="sex_labels">
    </publish-choose>


    <!--地点-->
    <publish-text :key="publish_key.place" :publish_value.sync="content.place">

    </publish-text>

    <!--开始时间-->

    <!--<div style="border:1px solid black; width: 100%;height: 100px ">-->
      <!--<input class=flatpickr data-date-format="d-m-Y">-->
    <!--</div>-->
      <publish-time :key="publish_key.start_time" :publish_value.sync="content.start_time"
                    :min_date.sync=today>

      </publish-time>

    <!--结束时间-->
      <publish-time :key="publish_key.end_time" :publish_value.sync="content.end_time"
                    :min_date.sync="content.end_time"></publish-time>

    <!--集中地-->
    <publish-text :key="publish_key.gathering_place" :publish_value.sync="content.gathering_place">
    </publish-text>

    <!--集中时间-->
    <publish-time :key="publish_key.gathering_time" :publish_value.sync="content.gathering_time"
                  :min_date.sync=today :max_date.sync="content.start_time">
    </publish-time>

  </div>
</template>

<style scoped>

</style>

<script type="text/ecmascript-6">
  import {token, is_login, login_state} from '../../vuex/getters.js'
  import PublishPicture from '../../components/publish/publish-picture.vue'
  import PublishText from '../../components/publish/publish-text.vue'
  import PublishChoose from '../../components/publish/publish-choose.vue'
  import PublishTime from '../../components/publish/publish-time.vue'
  import PublishNumber from '../../components/publish/publish-number.vue'
  import PhotosWipe from 'components/photoswipe/photoswipe.vue'
  import '../../components/publish/alert-view'
  export default{

    data(){
      return {
        text: "",                  //"文字描述",  **必须
        content: {
          ext_type: 6,               //类型         **必须

          file: [],                 // 图片文件
          place: "",                //"活动地点" ,
          want_sex: 2,            // "要求性别",            0 女生  1 男   2 无所谓 默认2
          start_time: "",           // 周六   "开始时间" ,   **必须
          end_time: "",              // 结束时间     **必须
          per_cost: "",             //"人均消费"
          gathering_place: "",      // "集中地点",
          gathering_time: "",        // 集中时间
        },
        publish_key: {
          per_cost: "人均消费",
          want_sex: "求陪同",
          place: "地点",
          start_time: "开始时间",
          end_time: "结束时间",
          gathering_place: "集中地",
          gathering_time: "集中时间",
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
            tag: "无所谓",
            id: "wantSexBoth",
            isChecked: true,
            value: 2
          },
          {
            tag: "求女生",
            id: "wantSexFemale",
            isChecked: false,
            value: 0
          },
          {
            tag: "求男生",
            id: "wantSexMale",
            isChecked: false,
            value: 1
          }
        ],
      }
    },
    methods: {
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
      PublishPicture,
      PublishText,
      PublishChoose,
      PublishTime,
      PublishNumber,
      PhotosWipe
    },
    events: {
      'publish-demand': function (message) {
        var self = this;
        let token = this.token;

        // 数据是否为空和格式的校验
        if (self.text.length == 0) {
          Simpop({
            content: '描述文字不能为空哦~',
            time: 2000  //2秒后自动关闭
          }).show();
          return;
        }else if( self.content.per_cost && !(/^[1-9]\d*(\.\d{0,2})?|0$/).exec(self.content.per_cost) ){
          Simpop({
            content: '调皮，人均消费是不小于零的数字哟~',
            time: 2000  //2秒后自动关闭
          }).show();
          return;
        }
        else if (self.content.start_time===''){
          Simpop({
            content: '开始时间不能为空哦~',
            time: 2000  //2秒后自动关闭
          }).show();
          return;
        }else if (self.content.end_time===''){
          Simpop({
            content: '活动结束时间不能为空哦~',
            time: 2000  //2秒后自动关闭
          }).show();
          return;
        }

        // 为了安全，还是做一下验证
        if (message === "publish_dating") {
          // post提交，发表走起
          var formData = new FormData();
          formData.append("ext_type", self.content.ext_type);
          formData.append("text", self.text);
          // 图片的验证和处理
          if (self.content.file.length > 0) {
            for (var i = 0; i < self.content.file.length; i++) {
              formData.append("file", self.content.file[i]);
            }
          }
          if (self.content.place!=''){
            formData.append("place", self.content.place);
          }
          formData.append("want_sex", self.content.want_sex);
          formData.append("start_time", Date.parse(self.content.start_time));
          formData.append("end_time", Date.parse(self.content.end_time));
          if (self.content.per_cost){
            formData.append("per_cost", self.content.per_cost);
          }
          if (self.content.gathering_time!=''){
            formData.append("gathering_time", Date.parse(self.content.gathering_time));
          }
          if (self.content.gathering_place) {
            formData.append("gathering_place", self.content.gathering_place);
          }
//          function (res) {
//            if (res.body.code === 200){
//              Simpop({
//                mask: false,
//                content: '发布成功~',
//                time: 1000
//              }).show(function () {
//                window.history.back();
//              });
//            }else {
//              Simpop({
//                content: '出了点小问题~',
//                time: 1500
//              }).show();
//            }
//            console.log("res"+res.body.code);
//
//          })
          return this.$request
            .post(`${this.$api.url_base}/demand?token=${token}`)
            .send(formData)
            .then(this.$api.checkResult)
            .catch(function (err) {
              console.log(err);
              Simpop({
                content: '发布失败~',
                time: 2000  //2秒后自动关闭
              }).show();
            })
        }
      },

    }

  }
</script>
