<template lang="jade">
    .demand-detail
        navbar
            .nav-title(@click="goBack()") 《 详细
            svg-follow(:style="{opacity:data.following?'1':'0.15'}")
        .demand-detail-content
            demand-card(:data.sync="data",:is-detail="true")
            .detail-list
                demand-detail-item(v-for="item in data.tags",:name="item.name",:value="item.value",:color="data.color")
            .comments(v-if="data.type != 'lost'")
                .comments-title(:style="{color:data.color}") {{comments.length+'条评论'}}
                comment-item(v-for="item in comments",:data.sync="item",:color="data.color")
</template>

<script>
module.exports = {
    components:{
        navbar:require('../../components/Navbar.vue'),
        svgFollow:require('../../svg/demand/Follow.vue'),
        demandCard:require('../../components/DemandCard.vue'),
        demandDetailItem:require('../../components/DemandDetailItem.vue'),
        commentItem:require('../../components/CommentItem.vue')
    },
    data(){
        return {
            id:'',
            data:{},
            comments:[{
                time:1472662020304,
                author:'',
                text:'你们快来啊',
                floor:3,
                isPoster:true
            },{
                time:1472662300304,
                author:'玲玉',
                text:'我不要',
                floor:1
            }]
        }
    },
    route: {
        data(_route) {
            var type = _route.to.params.type;
            var _lost = {
                type:'lost',
                thing:'银行卡',
                text: '寻失主。昨晚在建行网点捡到，一直找不到失主。麻烦失主看到联系我取回卡。下方留言我私聊你。',
                imgs:[{
                    src:'http://ww3.sinaimg.cn/mw690/e910bd00jw1f5exrm7glwj20qo0zkq55.jpg'
                }],
                color: '#0CC998',
                isLost:false,
                time: 1472581345635,
                tags:[{
                    name:'发布时间',
                    value:'Thu Sep 01 2016 01:14:13'
                },{
                    name:'地点',
                    value:'建行网点'
                }],
            };
            var _dating = {
                type:'dating',
                text: '有没有妹纸想一起去北岭山?',
                color: '#EC407A',
                sex:2,
                likeNum:8,
                joinNum:0,
                liked:true,
                time: 1472581345635,
                tags:[{
                    name:'人均消费',
                    value:'¥120'
                },{
                    name:'求陪同',
                    value:'求女生'
                },{
                    name:'地点',
                    value:'北岭沙摩星岭'
                },{ 
                    name:'时间',
                    value:'Thu Sep 01 2016 01:14:13'
                },{
                    name:'集中地',
                    value:'后山商业街'
                }],
            };
            var _task = {
                type:'task',
                text: '多余的小泽软妹纸一枚，谁要？',
                color: '#ffc107',
                tag: '转让',
                time: 1472581345635,
                pay: 15,
                tags:[{
                    name:'价格',
                    value:'¥12'
                },{
                    name:'成色',
                    value:'8成'
                }]
            };
            if(type == 'dating') this.data = _dating;
            else if(type == 'lost')this.data = _lost;
            else if(type == 'task')this.data = _task;
            this.data.type = type;
        }
    },
    methods:{
        goBack(){
            window.history.back();
        }
    }
}
</script>

<style lang="sass">
@import "../../style.scss";
.demand-detail {
    .navbar {
        .nav-title {
            margin-left:10px;
        }
        svg {
            margin-right: 15px;
        }
    }

    .demand-detail-content {
        margin-top:$NAVBAR_HEIGHT + $STATUSBAR_HEIGHT+10px;
        margin-bottom:$TABBAR_HEIGHT;
        .comments {
            .comments-title {
                position: relative;
                margin:15px 0 5px 30px;
                &:before {
                    content:'*';
                    position: absolute;
                    top:50%;
                    right:100%;
                    margin-right:5px;
                    transform:translateY(-50%);
                }
            }
        }
    }
}
</style>