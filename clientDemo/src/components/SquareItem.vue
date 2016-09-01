<template lang="jade">
    .square-item
        .square-item-title(:style="{color:color}") {{'* '+title}}
        .square-item-hot.hide-scroll(v-if="hot")
            a.hot-item(v-for="item in hot",:href="item.url")
                div(:style="{background:color,opacity:(1-$index*0.2)}") {{'# '+item.text}}
            a.hot-item.more(v-if="more",:href="more.url")
                div(:style="{background:color,opacity:(1-hot.length*0.2)}") {{more.text}}
        .square-item-detail(v-if="subtitle")
            .detail-title(:style="{color:color}") {{'# '+subtitle}}
            .detail-content
                slot
</template>

<script>
module.exports = {
    props:{
        color:String,
        title:String,
        subtitle:String,
        more:Object,
        hot:Array,
    }
}
</script>

<style lang="sass">
@import '../style.scss';
.square-item {
    padding-bottom: 15px;
    border-bottom: solid 1px $COLOR_LINE;
    .square-item-title {
        font-weight: bold;
        margin:15px 15px 10px;
    }
    .square-item-hot {
        margin-top:10px;
        white-space: nowrap;
        overflow-x: auto;
        .hot-item {
            &:first-child div {
                margin-left:15px + 15px;
            }
            &:last-child div {
                margin-right:15px;
            }
            div {
                display: inline-block;
                font-weight: bold;
                color:white;
                width:120px;
                height:73px;
                padding:12px;
                margin:0 5px;
                border-radius: $BORDER_RADIUS;
                white-space: normal;
                vertical-align: text-top;
            }
            &.more {
                div {
                    width:73px;
                }
            }
        }
    }
    .square-item-detail {
        margin-left:15px + 15px;
        margin-right:15px;
        .detail-title {
            font-size:0.9em;
            margin:15px 0 7px ;
        }
        .detail-content {
            color:#4E4E4E;
        }
    }

}
</style>