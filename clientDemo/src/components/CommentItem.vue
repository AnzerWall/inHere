<template lang="jade">
    .comment-item
        .title.clearfix
            .author(:style="{color:color}") 
                div(v-if="!data.isPoster") {{data.author}}
                div.poster(v-else,:style="{background:color}") 楼主
                | {{' · '+timeStr(data.time)}}
            .floor {{data.floor}}
        .text {{data.text}}
</template>

<script>
var moment = require('moment');
module.exports = {
    props:{
        data:Object,
        // author:String,
        // time:Number,
        // floor:Number,
        // text:String,
        color:String
    },
    methods:{
        timeStr:function (timestamp) {
            return moment(timestamp).format('h:mm A');
        }
    }
}
</script>

<style lang="sass">
@import '../style.scss';
.comment-item {
    padding:12px 15px 20px 0;
    margin-left:30px;
    border-bottom: solid 1px $COLOR_LINE;
    font-size:0.8em;
    .title {
        margin-bottom: 5px;
        .author {
            float:left;
            div {
                display: inline-block;
            }
            .poster {
                padding:3px 6px;
                border-radius:$BORDER_RADIUS;
                color:white;
                margin-bottom: 2px;
            }
        }
        .floor {
            float:right;
            color:#CCCCCC;
            &:before {
                content:'#';
            }
        }
    }
    .text {
        margin-right: 10px;
    }
}
</style>