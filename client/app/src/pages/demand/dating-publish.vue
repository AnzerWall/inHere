<template>
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

    <!--开始时间       :max_time="min_time_start"    -->
    <publish-time :key="publish_key.start_time" :publish_value.sync="content.start_time" :min_time="min_time_start" ></publish-time>

    <!--结束时间-->
    <publish-time :key="publish_key.end_time" :publish_value.sync="content.end_time" :min_time="content.start_time"></publish-time>

    <!--集中地-->
    <publish-text :key="publish_key.gathering_place" :publish_value.sync="content.gathering_place">
    </publish-text>

    <!--集中时间-->
    <publish-time :key="publish_key.gathering_time" :publish_value.sync="content.gathering_time"
                  :min_time="content.start_time" :max_time="content.end_time">
    </publish-time>

  </div>
  <noti v-ref:noti></noti>
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
  import {parseDateTime} from '../../filter/time'
  import {datetime_now} from '../../filter/time'
  import noti from '../../components/noti.vue'
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
        min_time_start:datetime_now(),
//        max_time_end:"",
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
      noti
    },
    events: {
      'publish-demand': function (message) {
        var self = this;
        let token = this.token;

        // 数据是否为空和格式的校验
        if (self.text.length == 0) {
          this.$refs.noti.warning('描述文字不能为空哦~',{
            timeout:1500
          });
          return;
        }else if( self.content.per_cost && !(/^[1-9]\d*(\.\d{0,2})?|0$/).exec(self.content.per_cost) ){
          this.$refs.noti.warning('调皮，人均消费是不小于零的数字哟~',{
            timeout:1500
          });
          return;
        }
        else if (self.content.start_time===''){
          this.$refs.noti.warning('活动开始时间不能为空哦~',{
            timeout:1500
          });
          return;
        }else if (self.content.end_time===''){
          this.$refs.noti.warning('活动结束时间不能为空哦~',{
            timeout:1500
          });
          return;
        }

        // 为了安全，还是做一下验证
        if (message === "publish_dating") {

          self.$refs.noti.noti('正在发布中...',{
            timeout:1500,
            bgColor:'blue'
          });

          // post提交，发表走起
          var formData = new FormData();
          formData.append("ext_type", self.content.ext_type);
          formData.append("text", self.text);
          // 图片的验证和处理
          if (self.content.file.length > 0) {
            var imageCount = self.content.file.length > 4 ? 4 : self.content.file.length;
            for (var i = 0; i < imageCount; i++) {
              formData.append("file", self.content.file[i]);
            }
          }
          if (self.content.place!=''){
            formData.append("place", self.content.place);
          }
          formData.append("want_sex", self.content.want_sex);
          time = parseDateTime(self.content.start_time);
          formData.append("start_time", time);
          time = parseDateTime(self.content.end_time);
          formData.append("end_time", time);
          if (self.content.per_cost){
            formData.append("per_cost", self.content.per_cost);
          }
          if (self.content.gathering_time!=''){
            var time = parseDateTime(self.content.gathering_time);
            formData.append("gathering_time", time);
          }
          if (self.content.gathering_place) {
            formData.append("gathering_place", self.content.gathering_place);
          }
          return this.$request
            .post(`${this.$api.url_base}/demand?token=${token}`)
            .send(formData)
            .then(this.$api.checkResult)
            .then(function () {
              self.$refs.noti.warning('发布成功~',{
                timeout:1500,
                bgColor:'blue',
                callback(result,vm){
                  window.history.back();
                }
              });
            })
            .catch((e)=> {
              if (e.type === 'API_ERROR') {//判断是api访问出错还是其他错，仅限被checkResult处理过。。详见checkResult。。
                if (e.code === 23333) {//根据code判断出错类型,比如未登录时候跳转啊
                  return this.$refs.noti.warning(`参数验证失败`,{
                    timeout:1500
                  })//这里以及后边的return是为了结束函数。。。仅此而已 ，常用技巧  : )
                } else if (e.code === 401) {
                  return this.$router.go({
                    path: '/login',
                    query: {
                      __ref: this.$route.path//告诉login页面要跳转回来的页面
                    }
                  });
                } else {
                  return this.$refs.noti.warning(`与服务器通讯失败:${e.message}`,{
                    timeout:1500
                  })
                }
              } else {
                console.error(e.stack||e);
                return this.$refs.noti.warning(`未知错误:${e.message}`,{
                  timeout:1500
                })
              }
            })
        }
      },

    }

  }
</script>
