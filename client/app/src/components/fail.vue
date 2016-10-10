<template lang="jade">
    .fail
        .fail-content
            sad-svg
            .fail-text {{text||'加载失败'}}    
</template>

<script>
import sadSvg from '../svg/common/sad.vue'
module.exports = {
    components:{
        sadSvg
    },
    props:{
        text:String
    }
}
</script>

<style lang="scss" scoped>

.fail {
    width:170px;
    height:170px;
    display: flex;
    align-items: center;
    justify-content: center;
    background: rgba(213,213,213,.5);
    border-radius: 8px;
    .fail-content {
        .fail-text {
            text-align: center;
            color: rgba(121,121,121,1);
            padding-top:20px;
        }
    }
}

</style>