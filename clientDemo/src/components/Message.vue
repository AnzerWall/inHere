<template lang="jade">
    .message-item(:style="{opacity:new?'1':'0.3'}")
        .message-title(:style="{color:color}")
            .point(:style="{background:color}")
            span {{title}}
        .message-text {{text}}
</template>

<script>
module.exports = {
    props:{
        new:Boolean,
        title:String,
        text:String,
        color:String
    }
}
</script>

<style lang="sass">
@import '../style.scss';

.message-item {
    border-bottom: solid 1px $COLOR_LINE;
    padding:18px 22px;
    .message-title {
        margin-left:20px;
        position: relative;
        font-weight: bold;
        .point {
            position: absolute;
            top:50%;
            left:-20px;
            width:12px;
            height:12px;
            transform:translateY(-50%);
            border-radius: 50%;
        }
    }
    .message-text {
        margin-left:20px;
        font-size:0.9em;
        margin-top:2px;
        line-height: 1.5em;
        max-height: 3em;
        overflow: hidden;
    }
}
</style>