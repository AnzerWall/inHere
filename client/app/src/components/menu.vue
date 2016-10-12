<template lang="jade">
.menu-bg(v-show="mshow",transition="fade",@click.self="hide()")
    .menu(v-show="mshow",transition="fade-down")
        .menu-title(@click="hide()",:style="{opacity:0.5}") {{title}}
        .menu-item(v-for="item in data",@click="clickMenu(item)") {{item.title}}
</template>

<script>
/*
配置：

{
    title:'菜单标题',
    btns:[{
        title:'按钮1',
        event:function(){
            console.log('xxx');
            return true; // true:点击后隐藏菜单 ， false:不隐藏菜单
        }
    }]
}
*/
module.exports = {
    data(){
        return {
            title:'',
            data:[],
            mshow:false,
        }
    },
    methods:{
        clickMenu(item){
            var tmp = item.event || function(){return true;};
            if(tmp())this.hide();
        },
        show(config){
            config = config || {};
            config.title = config.title || '关闭';
            config.btns = config.btns || [];
            this.title = config.title;
            this.data = config.btns;
            this.mshow = true;
        },
        hide(){
            this.mshow = false;
        }
    }
}
</script>
<style lang="scss" scoped>
.fade-transition {
    transition: all 0.3s;
}

.fade-enter,
.fade-leave {
    opacity: 0;
}

.fade-down-transition {
    transition: transform 0.3s;
}

.fade-down-enter,
.fade-down-leave {
    transform: translateY(-100%);
}

.menu-bg {
    position: fixed;
    top: 0;
    left: 0;
    bottom: 0;
    right: 0;
    background: rgba(0, 0, 0, 0.2);
    text-align: center;
    z-index: 8000;
    .menu {
        background: white;
        color: black;
        display: flex;
        flex-direction: column;
        .menu-title {
            padding: 22px;
        }
        .menu-item {
            margin: 4px 4px;
            border-radius: 4px;
            line-height: 50px;
            background: rgba(0, 0, 0, .2);
            color: white;
        }
    }
}
</style>
