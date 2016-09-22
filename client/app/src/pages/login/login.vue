<template>
  <div class="login-wrapper">
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
    <div class="register-hint">还没有账号?</div>
  </div>
</template>
<style src="./login.scss" scoped lang="scss"></style>


<script type="text/ecmascript-6">
  import {login} from '../../vuex/actions/user-action.js';
  import {token,login_state,is_login} from '../../vuex/getters.js'
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
          console.log(this.username, this.password);
          this.login(this.username, this.password)
            .then(()=> {

              //console.log(this.$route.query.__ref);
              if(this.$route.query.__ref){
                this.$router.go(this.$route.query.__ref);
              }
            })
            .catch((e)=> {
              console.log(e.message)
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
    components: {}
  }
</script>
