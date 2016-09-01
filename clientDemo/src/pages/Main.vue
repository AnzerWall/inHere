<template lang="jade">
    .main
        router-view
        tabbar
</template>

<script>
module.exports = {
    components:{
        tabbar:require('../components/Tabbar.vue'),
    }
}
</script>

<style lang="sass">
@import '../style.scss';

</style>