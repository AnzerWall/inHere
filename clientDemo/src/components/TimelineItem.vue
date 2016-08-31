<template lang="jade">
    .timeline-item
        .timeline-item-time {{timeStr(time)}}
        .timeline-item-content
            slot
</template>

<script>
var moment = require('moment');
module.exports = {
    props:{
        time:Number,
    },
    methods:{
        timeStr:function (timestamp) {
            return moment(timestamp).format('YYYY-MM-DD h:mm A');
        }
    }
}
</script>

<style lang="sass">
@import '../style.scss';
.timeline-item {
    .timeline-item-time {
        position: relative;
        margin-left:50px;
        color:#FFC000;
        font-size:0.9em;
        &:before {
            content:'';
            position: absolute;
            margin-right:19px;
            border:solid 3px #FFC000;
            background: $COLOR_BG;
            width:12px;
            height:12px;
            right:100%;
            border-radius: 50%;
            transform: translateX(50%);
        }
    }
    .timeline-item-content {
        position: relative;
        margin-left:40px;
        padding:5px 0 15px 0;
        &:before {
            content:'';
            position: absolute;
            margin-right:8px;
            width:2px;
            height:100%;
            top:0;
            right:100%;
            background: #FFC000;
        }
    }
}
</style>