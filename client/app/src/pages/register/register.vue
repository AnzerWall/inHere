<template>
  <div class="login-wrapper">
    <div class="title">
      <span class="logo">在这</span>
      <span class="text">注册</span>
    </div>
    <div class="login-input-field-wrapper">
      <div class="login-input-field">
        <div class="login-input-field-username">
          <span class="input-title">账&nbsp;&nbsp;号</span>
          <input v-model="username" placeholder="请输入6~16位字符的账号" @blur="checkUserName()">
        </div>
        <div class="login-input-field-password">
          <span class="input-title">密&nbsp;&nbsp;码</span>
          <input type="password" placeholder="请输入6~16位字符的密码" v-model="password" @blur="checkPsw()">
        </div>
        <div class="login-input-field-password">
          <span class="input-title">学&nbsp;&nbsp;校</span>
          <div @click="clickTag()"><input class="school_text" readonly :value="tag || '选择学校'"></div>
          <tag-editor v-ref:tag-editor :tags.sync="tags" :tag-name.sync="tag" :tag.sync="id" :editable="!!1" :show.sync="tagShow"></tag-editor>
        </div>
        <div>

        </div>

      </div>
      <div class="btn " @click="onRegister">注&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;册</div>
    </div>
    <div class="login-hint" v-link="{path:'/login'}">已有账号?直接登录</div>
  </div>

  <noti v-ref:noti></noti>
</template>

<style src="./register.scss" scoped lang="scss"></style>

<script>
  import TagEditor from '../../components/publish/publish-tag-editor.vue'
  import noti from '../../components/noti.vue'
  export default{
    route: {
      //页面加载数据钩子(或者叫事件)
      data(){
        var token = this.token;
        return this.$request
          .get(`${this.$api.url_base}/schools`)
          .then(this.$api.checkResult)//处理code等信息，返回data
          .then((data)=> {
            //处理数据
            this.tags = data.items.map(function (item, index) {
              item.value = item.id;
              return item;
            });
          })
          .catch((e)=> {
            if (e.type === 'API_ERROR') {//判断是api访问出错还是其他错，仅限被checkResult处理过。。详见checkResult。。
              if (e.code === 23333) {//根据code判断出错类型,比如未登录时候跳转啊
                return this.$refs.noti.warning(`参数验证失败`, {
                  timeout: 1500
                })//这里以及后边的return是为了结束函数。。。仅此而已 ，常用技巧  : )
              } else if (e.code === 401) {
                return this.$router.go({
                  path: '/login',
                  query: {
                    __ref: this.$route.path//告诉login页面要跳转回来的页面
                  }
                });
              } else {
                return this.$refs.noti.warning(`与服务器通讯失败`, {
                  timeout: 1500
                })
              }
            } else {
              console.error(e.stack || e);
              return this.$refs.noti.warning(`网络出错啦`, {
                timeout: 2000
              })
            }
            //后续显示重试按钮
          })
      }
    },
    data(){
      return {
        username:"",
        password:"",
        tag:"",
        id:"",
        tags:[],
        tagShow:false
      }

    },
    methods: {
      checkUserName(){
        var user_id = this.username;
        var self = this;
        if (user_id == ''){
          self.$refs.noti.warning("请填写账号",{
            timeout:2000
          });
          return 1;
        }else if ( !(/^\w{6,16}$/).exec(user_id)){
          self.$refs.noti.warning('账号是6~16位的字符串哦',{
            timeout:2000
          });
          return 1;
        }
        return this.$request
          .get(`${this.$api.url_base}/check/${user_id}`)
          .then(this.$api.checkResult)//处理code等信息，返回data
          .then(function (date) {
            if (date.is_exists == true){
               self.$refs.noti.warning('账号已经存在了，换一个吧~',{
                timeout:2200,
                callback(){
                  self.username = '';
                  return 1;
                }
              });
              return 1;
            } else {
              return 0;
            }
          })
          .catch(function () {
            return 1;
          })
      },
      checkPsw(){
        var password = this.password;
        var self = this;
        if (password == ''){
          self.$refs.noti.warning("请填写密码",{
            timeout:2000
          });
          return 1;
        }else if ( !(/^\w{6,16}$/).exec(password)){
          self.$refs.noti.warning('密码是6~16位的字符串哦',{
            timeout:2000
          });
          return 1;
        }else {
          return 0;
        }
      },
      onRegister(){
        var user_id = this.username;
        var passwd = this.password;
        var school_id = this.id;
        var self = this;
        if (user_id == ''){
          self.$refs.noti.warning("请填写用户名~",{
            timeout:2000
          });
          return 1;
        }else if ( !(/^\w{6,16}$/).exec(user_id)){
          self.$refs.noti.warning('用户名是6~16位的字符串哦',{
            timeout:2000
          });
          return 1;
        }

        if( this.checkPsw() ){
          return;
        }
        if (this.id == ''){
          this.$refs.noti.warning("请选择学校",{
            timeout:1500
          });
          return;
        }

        this.$refs.noti.noti('正在注册中...',{
          timeout:0,
          bgColor:'blue'
        });

        return this.$request
        .post(`${this.$api.url_base}/logup`)
          .send({"user_id":user_id,"passwd":passwd, "school_id":school_id})
//          .then(this.$api.checkResult)
          .then(function (res) {
            if (res.body.code === 200){
              self.$refs.noti.noti('注册成功~',{
                timeout:1500,
                bgColor:'blue',
                callback(result,vm){
                  window.history.back();
                }
              });
            }else{
              this.$refs.noti.warning(JSON.stringify(res.body),{
                timeout:1500,
                bgColor:'red'
              });
            }

          })
      },
      clickTag() {
        this.$refs.tagEditor.showMe();
      }
    },
    components: {
      TagEditor,
      noti
    },
    computed: {}
  }
</script>
