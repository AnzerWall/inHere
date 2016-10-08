<template lang="jade">
    .warning-bg(v-show="wshow",transition="fade",:class="{darkbg:needBg,topmost:topMost}",@click.self="clickBg")
        .warning-popup(v-show="wshow",transition="fade-down",:class="bgColor") 
            .warning-text {{warningInfo}}
            .warning-btn(v-if="['noti','warning'].indexOf(type)<0",@click="clickBack(true)") 确认
            .warning-btn(v-if="['confirm'].indexOf(type)>=0",@click="clickBack(false)") 取消
</template>

<script>
/*

配置：
options:{
    needBg: 是否需要背景, (默认为:true)
    bgColor: 通知背景色 ('red'/'blue'/'green'/'yellow')(默认白色),
    timeout: 自动消失事件 (默认为:0,0则不自动消失),
    topMost: 是否置顶 (true/false)
    callback: 按钮会掉函数 (返回:true/false,通知的vm)
}

事件：
noti(内容,配置) //白底+黑字+无按钮
confirm(内容,配置) //白底+黑字+确认+取消
alert(内容,配置) //白底+黑字+确认
warning(内容,配置) //红底+白字+确认

例子：
<noti v-ref:noti></noti>

this.$refs.noti.confirm('你确定?',{
    needBg:false,
    bgColor:'green',
    callback:function(result,vm){
        console.log('结果为'+result);
    }
})

*/

var timer;
module.exports = {
    data(){
        return {
            topMost:true,
            warningInfo:'',
            wshow:false,
            type:'',
            bgColor:'',
            timeout:0,
            needBg:0,
            callback:function(){}
        }
    },
    destoryed(){
        if(timer)clearTimeout(timer);
    },
    methods:{
        clickBg(){
            if(this.needBg)this.show();
        },
        hide(){
            this.show();
        },
        show(text,options){
            options = options || {};
            if(timer)clearTimeout(timer);
            if(!text){
                this.wshow = false;
                return;
            }
            setTimeout(_=>{
                this.warningInfo = text;
                if(options.bgColor!=undefined)
                    this.bgColor = options.bgColor;
                else this.bgColor = '';
                if(options.needBg!=undefined)
                    this.needBg = options.needBg;
                else this.needBg = true;
                if(options.timeout!=undefined)
                    this.timeout = options.timeout;
                else this.timeout = 0;
                if(options.type!=undefined)
                    this.type=options.type;
                else this.type = '';
                if(options.callback!=undefined)
                    this.callback=options.callback;
                else this.callback = function(){};
                if(options.topMost!=undefined)
                    this.topMost = options.topMost;
                else this.topMost = true;
                this.wshow = true;
                if(this.timeout){
                    timer = setTimeout(_=>{
                        this.wshow=false;
                    },this.timeout);
                }
            },300);
        },
        noti(text,options){//no btn
            options = options || {};
            options.type = 'noti';
            this.show(text,options);
        },
        confirm(text,options){//yes no
            options = options || {};
            options.type = 'confirm';
            this.show(text,options);
        },
        alert(text,options){//yes
            options = options || {};
            options.type = 'alert';
            this.show(text,options);
        },
        warning(text,options){//yes
            options = options || {};
            options.type = 'warning';
            options.bgColor = 'red';
            this.show(text,options);
        },
        clickBack(t){
            this.hide();
            this.callback(t,this);
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

.warning-bg {
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
    
    .warning-popup {
      position: absolute;;
      left:0;
      top:0;
      right:0;
      text-align: center;
      background: white;
      color:black;
      .warning-text {
        padding: 22px;
        word-break: break-all;
      }
      .warning-btn {

        line-height:60px;
        margin: 4px 4px;
        border-radius: 4px;
        line-height: 50px;
        background: rgba(0,0,0,.2);
        color:white;
      }
      &.red {
        background: #D50101;
        color:white;
      }
      &.yellow {
        background: #FFC107;
        color:white;
      }


      &.green {
        background: #05BC1D;
        color:white;
      }

      &.blue {
        background: #038BD4;
        color:white;
      }
    }
}

</style>