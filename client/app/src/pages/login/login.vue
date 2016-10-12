<template>
  <div class="login-wrapper">
    <noti v-ref:noti></noti>

    <div class="title">
      <span class="logo">在这</span>
      <span class="text">登录</span>
    </div>
    <div class="login-input-field-wrapper">
      <div class="login-input-field">
        <div class="login-input-field-username">
          <span class="input-title">用户名</span>
          <input v-model="username">
        </div>
        <div class="login-input-field-password">
          <span class="input-title">密&nbsp;&nbsp;码</span>
          <input type="password" v-model="password">
        </div>

      </div>
      <div class="btn " @click="onLogin">登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录</div>
    </div>
    <div class="register-hint" v-link="{path:'/register'}">还没有账号?</div>
  </div>
</template>
<style src="./login.scss" scoped lang="scss"></style>


<script type="text/ecmascript-6">
  import {login} from '../../vuex/actions/user-action.js';
  import {token,login_state,is_login} from '../../vuex/getters.js'
  import Noti from 'components/noti.vue'

  export default{
    data(){

      return {
        username: "",
        password: ""
      }
    },
//    route:{
//      data(){
//
//      }
//    },
    methods: {
      onLogin(){
//        console.dir(this);
        if (this.username && this.password) {
//          console.log(this.username, this.password);
          this.login(this.username, this.password)
//            .then(this.$api.checkResult)
            .then(()=> {
              //console.log(this.$route.query.__ref);
              this.$dispatch('init');
              if(this.$route.query.__ref){
                this.$router.go(this.$route.query.__ref);
              }else {
                this.$router.go('/main/demand/task');
              }
            })
            .catch((e)=> {
              if (e.type === 'API_ERROR') {
                if (e.code === 23333) {
                  return this.$refs.noti.warning(`参数验证失败`,{timeout:1000})
                }else {
                  return this.$refs.noti.warning(`与服务器通讯失败:${e.message}`,{timeout:1000})
                }
              } else {
                console.error(e.stack||e);
                return this.$refs.noti.warning(`未知错误:${e.message}`,{timeout:1000})
              }
            });
        }
      }
    },
    vuex: {
      actions: {
        login
      },
      getters: {
        login_state,
        token,
        is_login
      }
    },
    components: {Noti}
  }
</script>
