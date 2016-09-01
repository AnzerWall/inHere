<template lang="jade">
    .profile
        navbar
            .title {{user.username+' / '+user.school}}
            svg-setting.setting
        .profile-content
            .features
                feature(name="我的目标",color="#7300FF")
                    svg-wodemubiao
                feature(name="我的回答",color="#09CE88")
                    svg-wodehuida
                feature(name="期末成绩",color="#4F8794")
                    svg-qimochengji
                feature(name="四六级成绩",color="#FFA600")
                    svg-siliujichengji
                feature(name="饭卡记录",color="#FF3A3A")
                    svg-fankajilu
                feature(name="水费查询",color="#0098FF")
                    svg-shuifeichaxun
                feature(name="快递查询",color="#16A82E")
                    svg-kuaidichaxun
            .timeline
                .timeline-title 我的过去
                .timeline-list
                    timeline-item(v-for="item in timeline",:time="item.time")
                        demand-card(:data="item.task")

</template>

<script>
module.exports = {
    components: {
        'navbar': require('../components/Navbar.vue'),
        'feature': require('../components/Feature.vue'),
        'demand-card': require('../components/DemandCard.vue'),
        'svg-setting':require('../svg/Setting.vue'),
        'svg-qimochengji':require('../svg/features/Qimochengji.vue'),
        'svg-siliujichengji':require('../svg/features/Siliujichengji.vue'),
        'svg-fankajilu':require('../svg/features/Fankajilu.vue'),
        'svg-shuifeichaxun':require('../svg/features/Shuifeichaxun.vue'),
        'svg-kuaidichaxun':require('../svg/features/Kuaidichaxun.vue'),
        'svg-wodehuida':require('../svg/features/Wodehuida.vue'),
        'svg-wodemubiao':require('../svg/features/Wodemubiao.vue'),
        'timeline-item': require('../components/TimelineItem.vue'),
    },
    data:function () {
        return {
            user:{
                username:'小泽',
                school:'肇庆学院'
            },
            timeline:[{
                time:1472581345635,
                task:{
                    type:'task',
                    text: '有没有人可以帮忙拿个快递到F1?',
                    color: '#2196F3',
                    tag: '快递',
                    time: 1472581345635,
                    pay: 15
                }
            },{
                time:1472581045635,
                task:{
                    type:'task',
                    text: '出一条全新的牛仔a字裙，49元，适合平时穿SM码数的美眉，想买的评论',
                    color: '#FFC107',
                    tag: '转让',
                    imgs: [{
                        src: 'http://ww4.sinaimg.cn/mw690/e910bd00jw1f7c80cfvyoj20qo0zk75i.jpg'
                    }, {
                        src: 'http://ww1.sinaimg.cn/mw690/e910bd00jw1f7c1ndknjwj20qo0zktat.jpg'
                    }, {
                        src: 'http://ww3.sinaimg.cn/mw690/e910bd00jw1f7c3j00tqij20qo0zkjts.jpg'
                    }],
                    time: 1472581045635,
                    pay: 49
                }
            }, {
                time:1472580045635,
                task:{
                    type:'task',
                    text: '谁能帮小泽妹纸我打个饭？',
                    color: '#EC407A',
                    tag: '帮忙',
                    time: 1472580045635
                }
            }]
        }
    }
}
</script>

<style lang="sass">
@import '../style.scss';
.profile {
    .navbar {
        .title {
            margin-left:15px;
        }
        .setting {
            margin-right:15px;
        }
    }
    .profile-content {
        margin-top:$NAVBAR_HEIGHT + $STATUSBAR_HEIGHT;
        margin-bottom:$TABBAR_HEIGHT;
        .features {
            padding-top:5px;
            text-align: left;
            display: flex;
            flex-flow: row wrap;
        }
        .timeline {
            margin-top:35px;
            .timeline-title {
                margin-bottom: 15px;
                margin-left:20px;
                color:#797979;
            }
        }
    }
}
</style>