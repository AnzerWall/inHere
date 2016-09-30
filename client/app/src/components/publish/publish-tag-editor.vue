<template lang="jade">
    .tag-editor-bg(v-show="show",@click.self="toggleShow()",transition="fade-up")
        .tag-editor
            input.line(placeholder="自定义标签",v-model="tmptag",@keypress.13="clickTag(tmptag)",v-if="editable")
            .lines
                .line.selector(v-for="item in tags | filterBy tmptag in 'name'",@click="clickTag(item.value)")
                    span {{item.name}}
            .line.cancel(@click="toggleShow()") 取消
</template>

<script type="text/ecmascript-6">
module.exports = {
    props:{
        show:Boolean,
        tag:{},
        editable:{
            type:Boolean,
            default:false
        },
        tags:{
            type:Array,
            default:()=>([])
        }
    },
    data(){
        return {
            tmptag:''
        }
    },
    watch:{
        show(val){
            if(val){
                var tmp = this.tags.findIndex(item=>item.value==this.tag);
                if(tmp<0)this.tmptag = this.tag;
                else this.tmptag = '';
            }
        }
    },
    methods:{
        clickTag(str){
            this.tag = str;
            this.toggleShow();
            this.$emit('select',str);
        },
        toggleShow(){
            this.show=!this.show
        }
    }
}
</script>

<style lang="scss" scoped>

.fade-up-transition {
    transition: all 0.3s;
    >div {
        transition: all 0.3s;
    }
}
.fade-up-enter,.fade-up-leave {
    opacity: 0;
    >div {
        transform: translateY(100%);
    }
}


.tag-editor-bg {
    position: fixed;
    top:0;
    left:0;
    bottom: 0;
    right:0;
    background: rgba(0,0,0,0.2);
    z-index:9000;
    .tag-editor {
        display: flex;
        flex-direction: column;
        position: absolute;
        left:0;
        bottom: 0;
        right:0;
        top:20%;
        background: #FAFAFA;
        color:black;
        input.line {
            flex:none;
            text-align: center;
            background: transparent;
        }
        .line.cancel {
            flex:none;
        }
        .lines {
            overflow-y:auto;
            flex:1;
            // display: flex;
            // flex-direction: column;
        }
        .line {
            border-bottom:solid 1px rgba(0,0,0,.05);
            width:100%;
            height:50px;
            display: flex;
            align-items: center;
            justify-content: center;
        }
    }
}
</style>
