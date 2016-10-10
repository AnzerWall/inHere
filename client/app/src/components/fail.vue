<template lang="jade">
    .fail-bg
        .fail
            sad-svg
            .fail-text {{text||'加载失败'}}    
</template>

<script>
import sadSvg from '../svg/common/Sad.vue'
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

.fail-bg {
    position: absolute;
    top:0;
    left:0;
    right:0;
    bottom: 0;
    display: flex;
    align-items: center;
    justify-content: center;
}

.fail {
    padding:10px;
    width:150px;
    height:150px;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    background: rgba(213,213,213,.5);
    border-radius: 8px;
        .fail-text {
            text-align: center;
            color: rgba(121,121,121,1);
            padding-top:20px;
        }
}

</style>