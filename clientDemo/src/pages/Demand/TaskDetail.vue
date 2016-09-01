<template lang="jade">
    .task-detail
        navbar
            .nav-title(@click="goBack()") 《 任务
            svg-follow(:style="{opacity:data.following?'1':'0.15'}")
        .task-detail-content
            demand-card(:data.sync="data",:is-detail="true")
            .detail-list
                demand-detail-item(v-for="item in tags",:name="item.name",:value="item.value",:color="data.color")
            .comments
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
            tags:[{
                name:'价格',
                value:'¥12'
            },{
                name:'成色',
                value:'8成'
            }],
            data:{
                type:'task',
                text: '多余的小泽软妹纸一枚，谁要？',
                color: '#ffc107',
                tag: '转让',
                time: 1472581345635,
                pay: 15
            },
            comments:[{
                time:1472662320304,
                author:'玛利亚',
                text:'这个妹纸好漂亮，如果我有她一半漂亮就好了～～ 😍😍😍😍',
                floor:3
            },{
                time:1472662300304,
                author:'玲玉',
                text:'我不要',
                floor:2
            },{
                time:1472662020304,
                author:'',
                text:'不可以压价！！',
                floor:1,
                isPoster:true
            }]
        }
    },
    route: {
        data(_route) {
            var id = _route.to.params.id;
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
.task-detail {
    .navbar {
        .nav-title {
            margin-left:10px;
        }
        svg {
            margin-right: 15px;
        }
    }

    .task-detail-content {
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