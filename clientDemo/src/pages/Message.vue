<template lang="jade">
    .message
        navbar(darker)
            svg-chat
            .chat-entrance {{chatStatus+' 》'}}
        .message-list
            a(v-for="item in messagevip",href="#")
                message-vip(:title="item.title",:text="item.text")
            a(v-for="item in message",href="#")
                message(:title="item.title",:color="item.color",:text="item.text")
</template>

<script>
module.exports = {
    components: {
        'navbar': require('../components/Navbar.vue'),
        'svg-chat': require('../svg/Chat.vue'),
        'message-vip':require('../components/MessageVip.vue'),
        'message':require('../components/Message.vue')
    },
    data: function() {
        return {
            chatStatus: '你又又又有66条悄悄话',
            messagevip:[{
                title:'肇庆学院',
                text:'【重磅| 学生宿舍第一批空调已运抵！可以！这很夏天！】'
            },{
                title:'天翼校园一卡通',
                text:'温馨提示：国庆期间天翼一卡通服务中心上班时间为2,4,6号，8号后正常上班。由于校园网上学期办理的包学期有效期到2013年9月30日，为了避免10月1日断网，建议大四需要充值校园网的尽量在国庆放假前前往一卡通服务中心的前台充值。请周知！（30元/月，尽量自备零钱哦亲）'
            }],
            message:[{
                title:'23条新评论',
                color:'#FFC107',
                text:'之前在树洞看到有转让小熊音乐会门票的微博，如果还有票请联系我，谢谢，好想去。655338'
            },{
                title:'2人评论了你',
                color:'#2196F3',
                text:'有没有人可以帮忙拿个快递到F1？'
            },{
                title:'1条新评论',
                text:'谁能帮小泽妹纸我买个早餐？'
            },{
                title:'23条新评论',
                text:'之前在树洞看到有转让小熊音乐会门票的微博，如果还有票请联系我，谢谢，好想去。655338'
            },{
                title:'2人评论了你',
                text:'有没有人可以帮忙拿个快递到F1？'
            },{
                title:'1条新评论',
                text:'谁能帮小泽妹纸我买个早餐？'
            }]
        }
    }
}
</script>


<style lang="sass">
@import '../style.scss';

.message {
    .navbar {
        svg {
            margin-left:15px;
        }
        .chat-entrance {
            margin-right:15px;
            padding:6px 14px 6px 22px;
            border-radius: 50px;
            background: #FFC107;
            color:white;
        }
    }
    .message-list {
        margin-top:$NAVBAR_HEIGHT + $STATUSBAR_HEIGHT;
        margin-bottom:$TABBAR_HEIGHT+18px;
    }
}
</style>