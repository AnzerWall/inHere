<template lang="jade">
    .feature(:style="{color:color}")
        .icon(:style="{fill:color,transform:'translateY(-20%)'}")
            slot
        .name {{name}}
</template>

<script>
module.exports = {
    props:{
        name:String,
        color:String
    }
}
</script>

<style lang="sass">
@import '../style.scss';

.feature {
    width:112px;
    height:112px;
    text-align: center;
    display: flex;
    align-items: center;
    justify-content: center;
    border-radius: $BORDER_RADIUS;
    border:solid 1px $COLOR_LINE;
    position: relative;
    margin:5px;
    .name {
        position: absolute;
        left:50%;
        bottom:5px;
        font-size:0.8em;
        white-space: nowrap;
        transform: translateX(-50%);
    }

}
</style>