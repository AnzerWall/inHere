<template lang="jade">
    .task
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
                type:'task',
                text: '有没有人可以帮忙拿个快递到F1?',
                color: '#2196F3',
                tag: '快递',
                time: 1472581345635,
                pay: 15
            }, {
                type:'task',
                text: '出一条全新的牛仔a字裙，49元，适合平时穿SM码数的美眉，想买的评论',
                color: '#FFC107',
                tag: '转让',
                imgs: [{
                    src: 'http://ww4.sinaimg.cn/mw690/e910bd00jw1f7c80cfvyoj20qo0zk75i.jpg'
                }, {
                    src: 'http://ww1.sinaimg.cn/mw690/e910bd00jw1f7c1ndknjwj20qo0zktat.jpg'
                }, {
                    src: 'http://ww3.sinaimg.cn/mw690/e910bd00jw1f7c3j00tqij20qo0zkjts.jpg'
                }],
                time: 1472581045635,
                pay: 49
            }, {
                type:'task',
                text: '谁能帮小泽妹纸我打个饭？',
                color: '#EC407A',
                tag: '帮忙',
                time: 1472580045635
            },{
                type:'task',
                text: '有没有人可以帮忙拿个快递到F1?',
                color: '#2196F3',
                tag: '快递',
                time: 1472581345635,
                pay: 15
            }, {
                type:'task',
                text: '出一条全新的牛仔a字裙，49元，适合平时穿SM码数的美眉，想买的评论',
                color: '#FFC107',
                tag: '转让',
                imgs: [{
                    src: 'http://ww4.sinaimg.cn/mw690/e910bd00jw1f7c80cfvyoj20qo0zk75i.jpg'
                }, {
                    src: 'http://ww1.sinaimg.cn/mw690/e910bd00jw1f7c1ndknjwj20qo0zktat.jpg'
                }, {
                    src: 'http://ww3.sinaimg.cn/mw690/e910bd00jw1f7c3j00tqij20qo0zkjts.jpg'
                }],
                time: 1472581045635,
                pay: 49
            }, {
                type:'task',
                text: '谁能帮小泽妹纸我打个饭？',
                color: '#EC407A',
                tag: '帮忙',
                time: 1472580045635
            }]
        }
    }
}
</script>


<style lang="sass">
@import '../../style.scss';
.task {

}
</style>