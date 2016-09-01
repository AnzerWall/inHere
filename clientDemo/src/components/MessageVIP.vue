<template lang="jade">
    .message-item-vip
        .message-title 
            svg-message-vip
            span {{title}}
        .message-text {{text}}
</template>

<script>
module.exports = {
    components:{
        'svg-message-vip':require('../svg/MessageVip.vue')
    },
    props:{
        title:String,
        text:String
    }
}
</script>

<style lang="sass">
@import '../style.scss';

.message-item-vip {
    background: $COLOR_BG_DARKER;
    border-bottom: solid 1px $COLOR_LINE;
    padding:18px 22px;
    .message-title {
        font-weight: bold;
        * {
            vertical-align: middle;
        }
        span {
            margin-left:8px;
        }
    }
    .message-text {
        font-size:0.9em;
        margin-top:10px;
        line-height: 1.5em;
        max-height: 3em;
        overflow: hidden;
    }
}
</style>