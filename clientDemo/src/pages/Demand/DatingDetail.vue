<template lang="jade">
    .dating-detail
        navbar
            .nav-title(@click="goBack()") 《 走起
            svg-follow(:style="{opacity:data.following?'1':'0.15'}")
        .dating-detail-content
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
            data:{
                type:'dating',
                text: '有没有妹纸想一起去北岭山?',
                color: '#EC407A',
                sex:2,
                likeNum:8,
                joinNum:0,
                liked:true,
                time: 1472581345635
            },
            comments:[{
                time:1472662020304,
                author:'',
                text:'你们快来啊',
                floor:2,
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
.dating-detail {
    .navbar {
        .nav-title {
            margin-left:10px;
        }
        svg {
            margin-right: 15px;
        }
    }

    .dating-detail-content {
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