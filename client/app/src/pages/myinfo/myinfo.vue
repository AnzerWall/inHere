<template>
    <div class="myinfo-body" v-if="!$loadingRouteData">
      <noti v-ref:noti></noti>
      <!--头部-->
      <div class="myinfo-header">
        <div class="myinfo-header-left">
          <span @click="back">《 {{data.user_id}} / 肇庆学院</span>
        </div>
        <div class="myinfo-header-right" @click="judgement(user_name,sex,phone,qq,this)">
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
          <div class="myinfo-main-list-right"><input type="text"  class="myinfo-input" v-model="user_name" value="{{data.user_name}}" @blur="checkUserName(user_name,this)"></div>
        </div>
        <!--密码-->
        <div class="myinfo-main-list">
          <div class="myinfo-main-list-left">密码</div>
          <div class="myinfo-main-list-right" @click="showChangePassword(this)">修改密码</div>
        </div>
        <!--性别-->
        <div >
          <publish-tag-choose class="main-list-choose" :key="key" :tag.sync="sex" :tags="tags" :tag_name="sex_name" :editable=1 :is-publish=false :tag-name.sync="tag"> </publish-tag-choose>
        </div>
        <!--学校-->
        <div class="myinfo-main-list">
          <div class="myinfo-main-list-left">学校</div>
          <div class="myinfo-main-list-right list-school">{{data.school}}</div>
        </div>
        <!--手机-->
        <div class="myinfo-main-list">
          <div class="myinfo-main-list-left">手机</div>
          <div class="myinfo-main-list-right"><input  type="text"  class="myinfo-input" v-model="phone" value="{{data.phone}}" @blur="checkPhone(phone,this)"></div>
        </div>
        <!--QQ-->
        <div class="myinfo-main-list">
          <div class="myinfo-main-list-left">QQ</div>
          <div class="myinfo-main-list-right"><input  type="text" class="myinfo-input" v-model="qq" value="{{data.qq}}" @blur="checkQQ(qq,this)"></div></div>
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
                  value: "1"
                },
                {
                  name:"女",
                  value:"0"
                }
              ],
              data:null,
              sex:"1",
              sex_name:"",
              user_name1:"",
              phone1:"",
              qq1:""
            }
        },
        components:{
          ConfirmIcon,
          PublishTagChoose,
          ChangePassword,
          Noti
        },
      methods:{
        showChangePassword(self) {
          this.$refs.changepassword.show({
            validator(oldPwd, newPwd1, newPwd2) {
              console.log(oldPwd, newPwd1, newPwd2);
              return newPwd1===newPwd2?null:'两个新密码不一样';


            },
            confirm(oldPwd,newPwd){
              console.log("11")
              let url=`${this.$api.url_base}/user/change_pwd`;
              console.log(url)
              return this.$request
                .post(url)
                .query({token:self.token})
                .send({old_pwd:oldPwd,new_pwd:newPwd})
                .then(this.$api.checkResult)
                .then((res)=>{
                  return true;
                })
                .catch((e)=>{
                 if(e.code==412){
                   this.$refs.noti.warning(`${e.message}`);
                 }
                  return false;
                })

            }
          })
        },
        judgement(user_name,sex,phone,qq,self){
          this.$refs.noti.confirm('你确定修改吗?',{
            callback(result){
              if(result==true){
                let url=`${this.$api.url_base}/user/change`;
                console.log("aa");
                return this.$request
                  .post(url)
                  .query({token:self.token})
                  .send(
                    {user_name:user_name,
                      sex:parseInt(sex),
                      phone:phone,
                      qq:qq
                    })
                  .then(this.$api.checkResult)
                  .then((res)=>{
                    console.log("bb")
                  })
              }
//              else if(result==false){
//                let url=`${this.$api.url_base}/user`;
////                return this.$request
////                  .get(url)
////                  .query({token:self.token})
////                  .then(this.$api.checkResult)
////                  .then((data)=>{
////                    this.data=data;
////                    this.sex=data.sex;
////                    console.log(this.data);
////                    console.log(this.sex);
////                  })
//              }
            }
          });
          return 1;

        },
        back(){
          window.history.back();
        },
//        Chinese(sex){
//          if(sex==1){
//            return "男";
//          }
//          else if(sex==0){
//            return "女"
//          }
//          else if(sex=="男"){
//            return 1;
//          }
//          else if(sex=="女"){
//            return 0;
//          }
//        }
        checkUserName(user_name,self){
          if(user_name==''){
            self.$refs.noti.warning('用户名不存在');
            return 1;
          }
          else if ( !(/[A-Za-z0-9_\u4e00-\u9fa5]{3,9}/).exec(user_name)){
            self.$refs.noti.warning('用户名是3~9位的字符哦');
            return 1;
          }
        },
        checkPhone(phone,self){
          if(!(/^1[34578]\d{9}$/).exec(phone)){
            self.$refs.noti.warning("手机号码有误，请重填")
          }
          return 1;
        },
        checkQQ(qq,self){
          if(!(/[1-9][0-9]{4,14}/).exec(qq)){
            self.$refs.noti.warning("QQ号码有误，请重填")
          }
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
              this.sex=''+data.sex;
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
