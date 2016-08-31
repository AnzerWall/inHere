<template lang="jade">
    .tabbar
        .tabbar-tab(@click="jumpTo('/demand')",:class="{cur:tabSelected=='/demand'}")
            svg-demand
        .tabbar-tab(@click="jumpTo('/square')",:class="{cur:tabSelected=='/square'}")
            svg-square
        .tabbar-tab(@click="jumpTo('/message')",:class="{cur:tabSelected=='/message'}")
            svg-message
        .tabbar-tab(@click="jumpTo('/profile')",:class="{cur:tabSelected=='/profile'}")
            svg-profile
</template>

<script>
module.exports = {
    components:{
        'svg-demand':require('../svg/Demand.vue'),
        'svg-square':require('../svg/Square.vue'),
        'svg-message':require('../svg/Message.vue'),
        'svg-profile':require('../svg/Profile.vue'),
    },
    data:function(){
        return {
                tabSelected:'/demand'
            };
    },
    methods:{
        jumpTo:function (url) {
            this.$router.go(url);
            var tmp = this.$route.path;
            var last = url;
            console.log(last);
            this.tabSelected = last;
        }
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