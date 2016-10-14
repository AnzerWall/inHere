<template>
    <div class="url_style">
        <div class="activity-head"><span class="activity-title" @click="back()">《 {{title}}</span></div>
        <iframe v-el:myiframe scrolling="yes" frameborder="0" height="100%" width="100%" v-bind:src="url" v-if="isWebView"></iframe>

    </div>
</template>
<script>
module.exports = {
    data(){
        return {
            title:'',
            url:''
        }
    },
    props:{
      isWebView:{
        type:Boolean,
        required:false,
        default:true
      }
    },
    route:{
        data(){
            this.title = this.$route.query.title;
            this.url = this.$route.query.url;
        }
    },
    methods:{
      back(){
        window.history.back()
      },
    }
}
</script>
<style lang="scss">
.url_style {
    /*padding: 20px 0 20px 0;*/
    position: fixed;
    left: 0;
    right: 0;
    top: 0;
    bottom: 0;
    display: flex;
    flex-direction: column;
}


/*.url_style .d_content {*/


/*flex:1;*/


/*display:flex;*/


/*}*/

.url_style .activity-head {
    flex: none;
}

.activity-head {
    padding: 0;
    margin: 0;
    background-color: white;
    width: 100%;
    height: 60px;
    /*position: fixed;*/
    display: flex;
    align-items: center;
    padding-left: 10px;
    border-bottom: solid 1px rgba(213, 213, 213, .5);
    z-index: 200;
}
</style>
