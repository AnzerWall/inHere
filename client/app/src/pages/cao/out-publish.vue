<template>

  <div>
    <!--图文-->
    <publish-picture :publish_text.sync="content.text" :image_publish.sync="content.file"></publish-picture>

    <!--标签-->
    <publish-tag-choose :key="publish_key.tag" :tag.sync="content.lab_name" :tags="lab_items" :editable="!!1" :maxlength=35></publish-tag-choose>

  </div>

  <noti v-ref:noti></noti>
</template>

<script type="text/ecmascript-6">

  import PublishPicture from '../../components/publish/publish-picture.vue'
  import PublishTagChoose from '../../components/publish/publish-tag-choose.vue'
  import {token, login_state, is_login} from '../../vuex/getters.js'
  import noti from '../../components/noti.vue'

  export default{
    route: {
      //页面加载数据钩子(或者叫事件)
      data(){
        this.content.lab_name = this.$route.query.topic;
        var token = this.token;
        return this.$request
          .get(`${this.$api.url_base}/ask_reply/labels?token=${token}`)
          .query({'ext_type':11,'limit':100})  // 获取50条数据，类型为问答10
          .then(this.$api.checkResult)//处理code等信息，返回data
          .then((data)=> {
            //处理数据
            this.lab_items = data.items.map(function(item,index){
              item.value = item.name;
              return item;
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
                return this.$refs.noti.warning(`与服务器通讯失败`,{
                  timeout:1500
                })
              }
            } else {
              console.error(e.stack||e);
              return this.$refs.noti.warning(`网络出错啦`,{
                timeout:2000
              })
            }
            //后续显示重试按钮
          })
      }
    },
    data(){
      return{
        content:{
          ext_type: 11,
          text: "",              //"文字描述",
          file: [],              //null,
          tag_id: 0,              // 标签id 如果是自定义标签为0  其他为标签原本id 在选择标签时修改
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
    events:{
      'cao-publish': function (message) {
        var self = this;
        let token = this.token;

        // 验证数据格式
        if (self.content.text.length == 0){
          self.$refs.noti.warning('描述文字不能为空哦~',{
            timeout:1500
          });
          return;
        }else if (self.content.lab_name===''){
          self.$refs.noti.warning('调皮，标签不能为空哦~',{
            timeout:1500
          });
          return;
        }

        if (message == "cao_out_publish") {
          var formData = new FormData();
          formData.append('ext_type',self.content.ext_type);
          formData.append("content", self.content.text);
          // 图片的验证和处理
          if (self.content.file.length > 0) {
            var imageCount = self.content.file.length > 4 ? 4 : self.content.file.length;
            for (var i = 0; i < imageCount; i++) {
              formData.append("file", self.content.file[i]);
            }
          }
          formData.append('lab_name',self.content.lab_name);

          this.$refs.noti.noti('正在发布中...',{
            timeout:0,
            bgColor:'red'
          });

          return this.$request
            .post(`${this.$api.url_base}/ask_reply?token=${token}`)
            .send(formData)
            .then(this.$api.checkResult)//处理code等信息，返回data
            .then(function (res) {
              self.$refs.noti.noti('发布成功~',{
                timeout:1500,
                bgColor:'red',
                callback(result,vm){
                  window.history.back();
                }
              });
            })
            .catch(function (err) {
              self.$refs.noti.warning(err,{
                timeout:1500
              });
            })
        }else {
          self.$refs.noti.warning('非法操作',{
            timeout:1500
          });
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
      PublishTagChoose,
      noti
    }
  }
</script>
