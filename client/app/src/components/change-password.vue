<template lang="jade">
    .panel-bg(v-show="wshow",transition="fade",class="darkbg topmost",@click.self="clickBg")
        .panel-popup(v-show="wshow",transition="fade-down",:class="bgColor")
            .panel-text {{title||'修改密码'}}
            input.panel-input(type="password",v-model="oldPwd",placeholder="原密码")
            input.panel-input(type="password",v-model="newPwd1",placeholder="新密码")
            input.panel-input(type="password",v-model="newPwd2",placeholder="重复一次新密码")
            .panel-btn(@click="clickConfirm") 确认
            .panel-btn(@click="hide") 取消    
        noti(v-ref:noti)
</template>

<script>
import noti from './noti.vue'
module.exports = {
    components:{
        noti
    },
    props:{
        title:String
    },
    data(){
        return {
            oldPwd:'',
            newPwd1:'',
            newPwd2:'',
            wshow:false,
            validator:function(){return null;},//return 错误内容
            confirm:function(){return true;}//return 是否返回
        }
    },
    methods:{
        clickBg(){
            this.hide();
        },
        show(config){
            config = config || {};
            this.validator = config.validator || function(){return null;};
            this.confirm = config.confirm || function(){return true;};
            this.oldPwd = '';
            this.newPwd1 = '';
            this.newPwd2 = '';
            this.wshow=true;
        },
        hide(){
            this.wshow=false;
            this.$refs.noti.hide();
        },
        clickConfirm(){
            var chk = this.validator(this.oldPwd,this.newPwd1,this.newPwd2);
            if(chk){
                this.$refs.noti.warning(chk,{
                    timeout:3000
                })
            }
            else {
                this.confirm(this.oldPwd,this.newPwd1,shouldHide=>{
                    // if(shouldHide)
                    this.oldPwd = '';
                    this.newPwd1 = '';
                    this.newPwd2 = '';
                    this.hide();
                });
                // var tmp = this.confirm(this.oldPwd,this.newPwd1);
                // if(tmp)this.hide();
            }
        }
    }
}
</script>

<style lang="scss" scoped>


.fade-transition {
  transition: all 0.3s;
}
.fade-enter,.fade-leave {
    opacity: 0;
}

.fade-down-transition {
  transition: transform 0.3s;
}
.fade-down-enter,.fade-down-leave {
  transform: translateY(-100%);
}


.panel-bg {
    position: fixed;
    top:0;
    left:0;
    bottom: 0;
    right:0;
    // z-index:9000;
    &.darkbg {
        background: rgba(0,0,0,0.2);
    }
    &.topmost {
        z-index:9000;
    }

    .panel-popup {
      position: absolute;;
      left:0;
      top:0;
      right:0;
      text-align: center;
      background: white;
      color:black;
      display: flex;
      flex-direction: column;
      .panel-text {
        padding: 22px;
        word-break: break-all;
      }
      .panel-input {
        display: block;
        line-height: 50px;
        border-radius: 4px;
        border: solid 1px rgba(0,0,0,.2);
        color:black;
        padding:0 10px;
        margin:4px;
        &:focus {
            border-color:black;
        }
      }
      .panel-btn {
        // line-height:60px;
        margin: 4px 4px;
        border-radius: 4px;
        line-height: 50px;
        background: rgba(0,0,0,.2);
        color:white;
      }
    }
}

</style>