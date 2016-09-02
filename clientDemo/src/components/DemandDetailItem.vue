<template lang="jade">
    .demand-detail-item
        .name {{name}}
        .value(:style="{color:color}") {{value}}
</template>

<script>
module.exports = {
    props:{
        name:String,
        value:String,
        color:String
    }
}
</script>

<style lang="sass">
@import '../style.scss';
.demand-detail-item {
    line-height: 60px;
    font-weight: bold;
    margin:0 15px;
    border-bottom: solid 1px $COLOR_LINE;
    display: flex;
    justify-content: space-between;
    .name {
        margin-left:15px;
        // float:left;
    }
    .value {
        margin-right:15px;
        // float:right;
    }
}
</style>