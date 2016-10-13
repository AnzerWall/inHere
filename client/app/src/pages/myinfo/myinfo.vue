<template>
    <div class="myinfo-body" v-if="!$loadingRouteData">
      <noti v-ref:noti></noti>
      <!--头部-->
      <div class="myinfo-header">
        <div class="myinfo-header-left">
          <span @click="back">《 {{data.user_id}} / 肇庆学院</span>
        </div>
        <div class="myinfo-header-right" @click="judgement()">
          <confirm-icon></confirm-icon>
        </div>
      </div>
      <!--列表-->
      <div class="myinfo-main">
        <!--账号-->
        <div class="myinfo-main-list">
          <div class="myinfo-main-list-left">账号</div>
          <div class="myinfo-main-list-right list-school">{{data.user_id}}</div>
        </div>
        <!--用户名-->
        <div class="myinfo-main-list">
          <div class="myinfo-main-list-left">用户名</div>
          <div class="myinfo-main-list-right"><input type="text" value="{{data.user_name}}" class="myinfo-input"></div>
        </div>
        <!--密码-->
        <div class="myinfo-main-list">
          <div class="myinfo-main-list-left">密码</div>
          <div class="myinfo-main-list-right" @click="showChangePassword()">修改密码</div>
        </div>
        <!--性别-->
        <div >
          <publish-tag-choose class="main-list-choose" :key="key" :tag.sync="data.sex" :tags="tags" :editable="1" :isPublish="isPublish"></publish-tag-choose>
        </div>
        <!--学校-->
        <div class="myinfo-main-list">
          <div class="myinfo-main-list-left">学校</div>
          <div class="myinfo-main-list-right list-school">{{data.school}}</div>
        </div>
        <!--手机-->
        <div class="myinfo-main-list">
          <div class="myinfo-main-list-left">手机</div>
          <div class="myinfo-main-list-right"><input  type="text" value="{{data.phone}}" class="myinfo-input"></div>
        </div>
        <!--QQ-->
        <div class="myinfo-main-list">
          <div class="myinfo-main-list-left">QQ</div>
          <div class="myinfo-main-list-right"><input  type="text" value="{{data.qq}}" class="myinfo-input"></div></div>
        </div>
      <!--修改密码组件-->
      <change-password v-ref:changepassword></change-password>
      </div>


    </div>
</template>
<style>
  .myinfo-body{
    position: relative;
    font-size: 16px;
    background: #ffffff;
  }
  .myinfo-header{
    width: 100%;
    display: flex;
    justify-content: space-between;
    height: 72px;
    align-items: center;
    font-size: 18px;
    border-bottom: solid 1px #cccccc;
    background-color: #F2F2F4;
    position: fixed;
  }
  .myinfo-header-left{
    margin-left: 5px;
  }
  .myinfo-header-right{
    margin-right: 20px;
  }
  .myinfo-main{
    background: #ffffff;
    margin-top: 72px;
  }
  .myinfo-main-list{

    display:flex;
    justify-content: space-between;
    height: 72px;
    align-items: center;
    border-bottom: solid 1px #cccccc;
  }
  .myinfo-main-list-left{
    margin-left: 20px;
  }
  .myinfo-main-list-right{
    margin-right: 20px;
  }
  .list-school{
    color: #cccccc;
  }
  .main-list-choose{
    border: none;

  }
  .myinfo-input{
    text-align: right;
  }
</style>
<script type="text/ecmascript-6">
  import ConfirmIcon from 'svg/common/Confirm.vue'
  import PublishTagChoose from '../../components/publish/publish-tag-choose.vue'
  import ChangePassword from '../../components/change-password.vue'
  import {token,login_state,is_login,school,user_id} from '../../vuex/getters.js';
  import Noti from 'components/noti.vue';



    export default{
        data(){
            return{
              key:"性别",
              tags:[
                {
                  name: "男",
                  value: "男"
                },
                {
                  name:"女",
                  value:"女"
                }
              ],
              data:null,
              isPublish:"false"

            }
        },
        components:{
          ConfirmIcon,
          PublishTagChoose,
          ChangePassword,
          Noti
        },
      methods:{
        showChangePassword() {
          this.$refs.changepassword.show({
            validator(oldPwd, newPwd1, newPwd2) {
              console.log(oldPwd, newPwd1, newPwd2);
              return newPwd1===newPwd2?null:'两个新密码不一样';
            },
            confirm(oldPwd,newPwd) {
              alert(`旧密码:${oldPwd},新密码:${newPwd}`);
              return true;
            }
          })
        },
        judgement(){
          this.$refs.noti.confirm('你确定?',{
            callback(result){
              if(result=="true"){
                let url=`${this.$api.url_base}/user/change`
                this.$request
                  .post(url)
                  .query({token:this.token})
                  .query(
                    {user_name:this.data.user_name,
                      sex:this.data.sex,
                      phone:this.data.phone,
                      qq:this.data.qq
                    })
                  .then(this.$api.checkResult)
                  .then((data)=>{
                  })
              }
              else{
                let url=`${this.$api.url_base}/user`;
                this.$request
                  .get(url)
                  .query({token:this.token})
                  .then(this.$api.checkResult)
                  .then((data)=>{
                    this.data=data;
                  })
              }
            }
          });
          return 1;

        },
        back(){
          windows.history.back();
        }


      },
      route:{
        data(){
          let url=`${this.$api.url_base}/user`;
          return this.$request
            .get(url)
            .query({token:this.token})
            .then(this.$api.checkResult)
            .then((data)=>{
              this.data=data;
        })
        .catch((e)=> {
            if (e.type === 'API_ERROR') {//判断是api访问出错还是其他错，仅限被checkResult处理过。。详见checkResult。。
            if (e.code === 23333) {//根据code判断出错类型,比如未登录时候跳转啊
              return this.$refs.noti.warning(`参数验证失败`)//这里以及后边的return是为了结束函数。。。仅此而已 ，常用技巧  : )
            } else if (e.code === 401) {
              return this.$router.go({
                path: '/login',
                query: {
                  __ref: this.$route.path//告诉login页面要跳转回来的页面
                }
              });
            } else {
              return this.$refs.noti.warning(`与服务器通讯失败:${e.message}`)
            }
          } else {
            console.error(e.stack||e);
            console.log(this.$refs.noti);
            return this.$refs.noti.warning(`未知错误:${e.message}`)
          }
          //后续显示重试按钮
        })
        }
      },
      vuex: {
        getters: {
          login_state,
          token,
          is_login,
          school,
          user_id
        }
      },
    }
</script>
