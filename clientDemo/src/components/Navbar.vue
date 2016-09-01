<template lang="jade">
    .navbar(:class="{darker:darker}")
        slot
</template>

<script>
module.exports = {
    name:'navbar',
    props:{
        darker:Boolean
    }
}
</script>

<style lang="sass">
@import '../style.scss';

.navbar {
    position: fixed;
    left:0;
    top:0;
    width: 100%;
    height:$NAVBAR_HEIGHT;
    padding-top:$STATUSBAR_HEIGHT;
    background: $COLOR_BG;
    border-bottom: solid 1px $COLOR_LINE;
    display: flex;
    align-items: center;
    justify-content: space-between;
    z-index:600;
    &.darker {
        background: $COLOR_BG_DARKER;
    }
    &:before {
        display: block;
        content: " ";
        height:$STATUSBAR_HEIGHT;
        top: 0;
        background: rgba(0, 0, 0, 0.2);
        position: fixed;
        left: 0;
        width: 100%;
    }
}
</style>