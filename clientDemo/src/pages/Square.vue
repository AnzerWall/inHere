<template lang="jade">
    .square
        .activity-swipe
            .activity-swipe-item
                .title
                    .h1 {{{swipe.title}}}
                    .h2 {{swipe.author}}
        square-item(:color="nuo.color",:title="nuo.title",:hot="nuo.hot",:more="nuo.more",:subtitle="nuo.subtitle")
            .nuo-item(v-for="item in nuo.list")
                span.text {{item.name}}
                input.checkbox(type="checkbox",v-model="item.value")
        square-item(:color="ask.color",:title="ask.title",:hot="ask.hot",:more="ask.more",:subtitle="ask.subtitle")
            {{ask.detail}}
        square-item(:color="cao.color",:title="cao.title",:hot="cao.hot",:more="cao.more",:subtitle="cao.subtitle")
            {{cao.detail}}
        .canteen-btn 深&nbsp;夜&nbsp;食&nbsp;堂
</template>

<script>

module.exports = {
    components:{
        'square-item':require('../components/SquareItem.vue')
    },
    data:function () {
        return {
            swipe:{
                title:'小清新微电影<br>《一半》正片上映',
                author:'@影视文化协会'
            },
            nuo:{
                title:'有诺必行',
                color:'#18B803',
                subtitle:'我的目标',
                hot:[{
                    text:'论如何成为IT达人',
                    url:'#'
                },{
                    text:'学神奋斗史',
                    url:'#'
                },{
                    text:'美妆达人',
                    url:'#'
                }],
                list:[{
                    name:'去大润发购物满100块',
                    value:false
                },{
                    name:'去牌坊广场看音乐喷泉',
                    value:true
                }
                ],
                more:{
                    text:'更多',
                    url:'#'
                }
            },
            ask:{
                title:'有问必答',
                color:'#0073FF',
                subtitle:'以前办理了电信网络的怎么办？',
                hot:[{
                    text:'学校附近哪里有好吃的？',
                    url:'#'
                },{
                    text:'学生宽带如何收费？',
                    url:'#'
                },{
                    text:'怎么才能快速找到兼职？',
                    url:'#'
                }],
                detail:'本学期起我校全体学生用户的带宽统一为4M且无任何捆绑业务。上学期已完成了对大部分电信8m或20m捆绑手机用户的解绑 ...',
                more:{
                    text:'更多',
                    url:'#'
                }
            },
            cao:{
                title:'有槽必吐',
                color:'#BD00E7',
                subtitle:'你的坑爹室友做过哪些让你意想不到的事？',
                hot:[{
                    text:'你们的饭堂有什么奇葩的菜式？',
                    url:'#'
                },{
                    text:'说说那些让你睡着的老师',
                    url:'#'
                },{
                    text:'你的坑爹室友做过哪些让你意想不到 ...',
                    url:'#'
                }],
                detail:'和室友喝着喝着酒，室友就把腿放在我身上，是有什么暗示吗 急 在线等',
                more:{
                    text:'更多',
                    url:'#'
                }
            }
        }
    }
}
</script>

<style lang="sass">
@import '../style.scss';
.square {
    margin-bottom:$TABBAR_HEIGHT+10px+23px;
    .activity-swipe {
        position: relative;
        height:238px;
        overflow: hidden;
        .activity-swipe-item {
            position: relative;
            height: 100%;
            width: 100%;    
            background-image: url(http://ww1.sinaimg.cn/mw690/6f924588gw1f32g8p67w6j20qo123wjv.jpg);
            background-position: center;
            background-size:cover;
            .title {
                position: absolute;
                left:15px;
                bottom:15px;
                background: black;
                padding:10px;
                color:white;
                max-width:180px;
                .h1 {
                    font-size: 1.1em;
                    font-weight: bold;
                    margin-bottom: 2px;
                }
                .h2 {
                    font-size:0.4em;
                }
            }
        }
    }
    .nuo-item {
        margin:5px 0 10px;
        display: flex;
        .text {
            flex:1;
        }
        .checkbox {
            flex:none;
        }
    }
    .canteen-btn {
        margin:23px;
        border-radius: $BORDER_RADIUS;
        border:solid 1px $COLOR_LINE;
        text-align: center;
        padding:16px;
        color:#BCBCBC;
    }
}
</style>