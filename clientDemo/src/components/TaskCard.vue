<template lang="jade">
    .task-card(:style="{background:data.color}")
        .task-card-content
            .text {{data.text}}
            .imgs.hide-scroll(v-if="data.imgs")
                img(v-for="item in data.imgs",:src="item.src")
            .imgs-space(v-if="data.imgs")
        .task-card-description
            .other-left
                .time {{timeStr(data.time)}}
                .tag {{'#'+data.tag}}
            .pay-right(v-if="data.pay") 
                span {{'¥'+data.pay}}
                svg-pay
</template>

<script>
var moment = require('moment');
module.exports = {
    props:{
        data:Object
        // color:String,
        // text:String,
        // imgs:Array,
        // pay:Number,
        // time:Number,
        // tag:String
    },
    components:{
        'svg-pay':require('../svg/Pay.vue')
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
.task-card {
    border-radius: $BORDER_RADIUS;
    color:white;
    margin: 10px;
    .task-card-content {
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
    .task-card-description {
        padding:0 15px;
        height: 35px;
        line-height:35px;
        background: rgba(0,0,0,.1);
        font-size: 0.8em;
        .other-left {
            float:left;
            .time {
                display: inline-block;
                margin-right:15px;
            }
            .tag {
                display: inline-block;
                font-weight: bold;
            }
        }
        .pay-right {
            float:right;
            svg {
                margin-left:5px;
                fill:white;
                vertical-align: middle;
            }
        }
    }
}
</style>