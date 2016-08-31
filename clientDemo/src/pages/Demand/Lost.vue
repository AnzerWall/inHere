<template lang="jade">
    .lost
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
                type:'lost',
                thing:'杯子',
                text: '我不见了一只米奇图案的杯子',
                color: '#2196F3',
                imgs: [{
                    src: 'http://ww3.sinaimg.cn/mw690/e910bd00jw1f6m9tthnccj20iw0b20sr.jpg'
                }],
                isLost:true,
                time: 1472581345635
            },{
                type:'lost',
                thing:'银行卡',
                text: '寻失主。昨晚在建行网点捡到，一直找不到失主。麻烦失主看到联系我取回卡。下方留言我私聊你。',
                imgs:[{
                    src:'http://ww3.sinaimg.cn/mw690/e910bd00jw1f5exrm7glwj20qo0zkq55.jpg'
                }],
                color: '#0CC998',
                isLost:false,
                time: 1472581345635
            },{
                type:'lost',
                thing:'苹果',
                text: '灰姑娘今天捡到了一个有毒的苹果',
                color: '#0CC998',
                isLost:false,
                time: 1472581335635
            },{
                type:'lost',
                thing:'杯子',
                text: '我不见了一只米奇图案的杯子',
                color: '#2196F3',
                isLost:true,
                time: 1472581345635
            },{
                type:'lost',
                thing:'苹果',
                text: '灰姑娘今天捡到了一个有毒的苹果',
                color: '#0CC998',
                isLost:false,
                time: 1472581335635
            }]
        }
    }
}
</script>


<style lang="sass">
@import '../../style.scss';
.lost {

}
</style>