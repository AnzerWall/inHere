<template lang="jade">
    .demand-card(v-if="data",:style="{background:data.color,marginBottom:isDetail?'0':null}",@click="clickCard")
        .demand-card-content
            .demand-card-tags(v-if="!isDetail && data.type=='lost'",:style="{color:data.color}")
                .tag {{data.isLost?'失物':'拾获'}}
                .tag {{data.thing}}
                .tag {{timeStr(data.time)}}
            .demand-card-tags(v-if="!isDetail && data.type=='dating'",:style="{color:data.color}")
                .tag {{sexStr(data.sex)}}
                .tag {{data.address}}
                .tag {{timeStr(data.time)}}
            .text {{data.text}}

            .imgs.hide-scroll(v-if="data.imgs")
                img(v-for="item in data.imgs",:src="item.src",@click.stop="clickImg($index,data.imgs)")
            .imgs-space(v-if="data.imgs")
                div(@click.stop="")
                    photo-swipe(v-ref:viewer)
        
        .demand-card-bottom(v-if="data.type=='task'")
            .left
                .time {{timeStr(data.time)}}
                .tag {{'#'+data.tag}}
            .right(v-if="data.pay && !isDetail") 
                span {{'¥'+data.pay}}
                svg-pay
        .demand-card-bottom.btn(v-if="data.type=='lost'&& !isDetail") 联&nbsp;系&nbsp;TA
        .demand-card-bottom(v-if="data.type=='dating'")
            .left 参加人数：{{data.joinNum}}
            .right
                span {{data.likeNum}}
                svg-liked.like(v-if="data.liked",@click.stop="clickLike")
                svg-like.like(v-else,@click.stop="clickLike")
</template>

<script>
var moment = require('moment');
module.exports = {
    props:{
        isDetail:Boolean,
        data:Object
    },
    components:{
        'photo-swipe':require('./photoswipe.vue'),
        'svg-pay':require('../svg/demand/Pay.vue'),
        'svg-like':require('../svg/demand/Like.vue'),
        'svg-liked':require('../svg/demand/Liked.vue')
    },
    methods:{
        timeStr(timestamp) {
            return moment(timestamp).format('MM-DD h:mm A');
        },
        sexStr(th){
            switch(th){
                case 1:return '求男生';
                case 2:return '求女生';
            }
            return '性别不限'
        },
        clickImg(index,list){
            this.$refs.viewer.show(index,list);
            return true;
        },
        clickCard(){
            this.$router.go('/demand/detail/'+this.data.type)
        },
        clickLike(){
            this.data.liked = !this.data.liked;
        }
    }
}
</script>

<style lang="sass">
@import '../style.scss';
.demand-card {
    border-radius: $BORDER_RADIUS;
    color:white;
    margin: 10px;
    .demand-card-tags {
        margin-bottom: 5px;
        .tag {
            display: inline-block;
            margin-right:5px;
            background:white;
            padding:5px 8px;
            line-height: 1em;
            font-size:0.9em;
            height:1em;
            border-radius: $BORDER_RADIUS;
        }
    }
    .demand-card-content {
        padding:15px 15px 30px;
        .imgs-space {
            height:200px;
            width:100%;
        }
        .imgs {
            position: absolute;
            width: 100%;
            padding-bottom: 10px;
            left: 0;
            white-space: nowrap;
            overflow-x: auto;
            margin-top:10px;
            img {
                border: solid 3px white;
                box-shadow: 0 4px 8px rgba(0,0,0,.2);
                margin-right:10px;
                height:190px;
                &:first-child {
                    margin-left:25px;
                }
                &:last-child {
                    margin-right:25px;
                }
            }
        }
    }
    .demand-card-bottom {
        padding:0 15px;
        height: 35px;
        line-height:35px;
        background: rgba(0,0,0,.1);
        font-size: 0.8em;
        display: flex;
        justify-content: space-between;
        &.btn {
            display: block;
            height:55px;
            line-height: 55px;
            text-align: center;
            font-weight: bold;
        }
        .left {
            // float:left;
            .time {
                display: inline-block;
                margin-right:15px;
            }
            .tag {
                display: inline-block;
                font-weight: bold;
            }
        }
        .right {
            // float:right;
            svg {
                margin-left:5px;
                fill:white;
                vertical-align: middle;
                &.like {
                    transform: translateY(-7px);
                }
            }
        }
    }
}
</style>