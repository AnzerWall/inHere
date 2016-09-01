<template lang="jade">
    .tabbar
        .tabbar-tab(v-link="{path:'/main/demand',activeClass:'cur'}")
            svg-demand
        .tabbar-tab(v-link="{path:'/main/square',activeClass:'cur'}")
            svg-square
        .tabbar-tab(v-link="{path:'/main/message',activeClass:'cur'}")
            svg-message
        .tabbar-tab(v-link="{path:'/main/profile',activeClass:'cur'}")
            svg-profile
</template>

<script>
module.exports = {
    components:{
        'svg-demand':require('../svg/tabbar/Demand.vue'),
        'svg-square':require('../svg/tabbar/Square.vue'),
        'svg-message':require('../svg/tabbar/Message.vue'),
        'svg-profile':require('../svg/tabbar/Profile.vue'),
    }
}
</script>

<style lang="sass">
@import '../style.scss';

.tabbar {
    position: fixed;
    left:0;
    bottom: 0;
    width:100%;
    height:$TABBAR_HEIGHT;
    background: $COLOR_BG;
    border-top: solid 1px $COLOR_LINE;
    display: flex;
    z-index:600;
    .tabbar-tab {
        transition: all $TRANSITION_SPEED;
        flex:1;
        text-align: center;
        width:25%;
        svg {
            height:100%;
            opacity: 0.1;
        }
        &.cur {
            svg {
                opacity: 1;
            }
        }
    }
}


</style>