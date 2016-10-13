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
    <publish-time :key="publish_key.found_time" :publish_value.sync="content.found_time" :max_time="max_time">
    </publish-time>

  </div>
  <noti v-ref:noti></noti>
</template>

<style scoped>

</style>

<script type="text/ecmascript-6">

  import PublishPicture from '../../components/publish/publish-picture.vue'
  import PublishText from '../../components/publish/publish-text.vue'
  import PublishChoose from '../../components/publish/publish-choose.vue'
  import PublishTime from '../../components/publish/publish-time.vue'
  import {token, login_state, is_login} from '../../vuex/getters.js'
  import {parseDateTime} from '../../filter/time'
  import {datetime_now} from '../../filter/time'
  import noti from '../../components/noti.vue'

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
        max_time:datetime_now(),
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
      noti
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

        if (this.is_login == false){
          return this.$router.go({
            path: '/login',
            query: {
              __ref: this.$route.path//告诉login页面要跳转回来的页面
            }
          });
        }

        var self = this;
        let token = this.token;
        var BOUNDARYPREFIX = 'nbglme';

        // 验证数据格式
        if (self.content.ext_type===2 && self.content.text.length == 0){
          self.$refs.noti.warning('描述文字不能为空哦~',{
            timeout:1500
          });
          return;
        }else if (self.content.thing.length == 0){
          self.$refs.noti.warning('说明是什么物品才能帮你快速找回哦~',{
            timeout:1500
          });
          return;
        }

        self.$refs.noti.noti('正在发布中...',{
          timeout:1500,
          bgColor:'blue'
        });

        // 为了安全，还是做一下验证
        if (message == "publish_lost") {
//           post提交，发表走起
          var formData = new FormData();
          formData.append("ext_type", self.content.ext_type);
          formData.append("text", self.content.text);
          if (self.content.file.length>=1){
            var imageCount = self.content.file.length > 4 ? 4 : self.content.file.length;
            for (var i = 0; i < imageCount; i++) {
              formData.append("file", self.content.file[i]);
            }
          }
          formData.append("thing",self.content.thing);
          if (self.content.found_time && self.content.found_time!=''){
            var found_time = parseDateTime(self.content.found_time);
            if (self.content.ext_type == 4) { // 丢失

              formData.append("lose_time", found_time);
            } else { // 捡到
              formData.append("pickeup_time",found_time);
            }
          }

          return this.$request
            .post(`${this.$api.url_base}/demand?token=${token}`)
            .send(formData)
            .then(this.$api.checkResult)
            .then(function () {
              self.$refs.noti.noti('发布成功~',{
                timeout:1500,
                bgColor:'blue',
                callback(result,vm){
                  if (this.$route.query)
                    this.$router.go('/main/demand/lost');
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
                return this.$refs.noti.warning(`${e.message}`,{
                  timeout:1500
                })
              }
            })
        }else {
          self.$refs.noti.warning('非法操作',{
            timeout:1500
          });
        }
        self.$refs.noti.noti('',{
          timeout:15,
          bgColor:'blue'
        });
      }
    }
  }
</script>
