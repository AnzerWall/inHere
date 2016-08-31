<template lang="jade">
    .dating
        demand-card(v-for="item in data",:data.sync="item")
</template>

<script>
module.exports = {
    components: {
        'demand-card': require('../../components/DemandCard.vue')
    },
    data: function() {
        return {
            data: [{
                type:'dating',
                text: '有没有妹纸想一起去北岭山?',
                color: '#EC407A',
                sex:2,
                likeNum:8,
                joinNum:3,
                liked:true,
                time: 1472581345635
            },{
                type:'dating',
                text: '有没有男生想一起去打工的?',
                color: '#2196F3',
                sex:1,
                likeNum:0,
                joinNum:2,
                liked:false,
                time: 1472581335635
            },{
                type:'dating',
                text: '求帮忙搬宿舍？',
                color: '#FFC107',
                sex:0,
                likeNum:1,
                joinNum:8,
                liked:false,
                time: 1472581135635
            },{
                type:'dating',
                text: '下课去后山',
                color: '#FFC107',
                sex:0,
                likeNum:1,
                joinNum:8,
                liked:false,
                time: 1472581135635
            }]
        }
    }
}
</script>


<style lang="sass">
@import '../../style.scss';
.dating {

}
</style>