<template lang="jade">
    .demand
        .demand-content
            router-view
        navbar
            .nav-tabs
                .nav-tab(v-link="{path:'/main/demand/task',activeClass:'cur'}") 任务
                .nav-tab(v-link="{path:'/main/demand/lost',activeClass:'cur'}") 失物
                .nav-tab(v-link="{path:'/main/demand/dating',activeClass:'cur'}") 走起
            .nav-add-btn 
                svg-add
</template>

<script>
module.exports = {
    components:{
        'navbar':require('../components/Navbar.vue'),
        'svg-add':require('../svg/demand/Add.vue')
    }
}
</script>

<style lang="sass">
@import '../style.scss';

.demand {
    .navbar {
        .nav-tabs {
            .nav-tab {
                font-size: 1.1em;
                font-weight: bold;
                display: inline-block;
                padding:0 10px;
                opacity: 0.15;
                &:first-child {
                    margin-left:10px;
                }
                &.cur {
                    opacity: 1;
                }
            }
        }
        .nav-add-btn {
            padding:10px;
            margin-right:10px;
        }
    }
    .demand-content {
        margin-top:$NAVBAR_HEIGHT+$STATUSBAR_HEIGHT+10px;
        margin-bottom:$TABBAR_HEIGHT+10px;
    }
}

</style>