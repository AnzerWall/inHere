<template lang="jade">
    .tag-editor-bg(v-show="show",@click.self="toggleShow()",transition="fade-up")
        noti(v-ref:noti)
        .tag-editor
            input.line(@keypress="chkLength",placeholder="自定义标签",v-model="tmptag",@keypress.13="clickTag({name:tmptag,value:tmptag})",v-if="editable")
            .lines
                .line.selector(v-for="item in tags | filterBy tmptag in 'name'",@click="clickTag(item)")
                    span {{item.name}}
            .line.cancel(@click="toggleShow()") 取消
        //- .warning-popup(v-show="warningInfo",transition="fade-down") {{warningInfo}}
</template>

<script>
import noti from '../noti.vue'
module.exports = {
    components: {
        noti
    },
    props: {
        show: {
            type:Boolean,
            default:false
        },
        tag: {},
        tagName: {},
        editable: {
            type: Boolean,
            default: false
        },
        tags: {
            type: Array,
            default: () => ([])
        },
        maxlength: {
            type: Number,
            default: 6
        }
    },
    data() {
        return {
            tmptag: '',
            warningInfo: ''
        }
    },
    ready(){
      var tmp = this.tags.find(item=>item.value==this.tag);
      if(tmp)this.tagName = tmp.name;
    },
    watch: {
        show(val) {
            if (val) {
                var tmp = this.tags.findIndex(item => item.value == this.tag);
                if (tmp < 0) this.tmptag = this.tag;
                else this.tmptag = '';
            }
        },
        tmptag(val, oldVal) {
            this.chkLength();
        }
    },
    methods: {
        hideMe(){
            this.toggleShow();
        },
        showMe(){
            this.toggleShow(true);
        },
        clickTag(item) {
            if (!this.chkLength()) return;
            this.tag = item.value;
            this.tagName=item.name;
            this.toggleShow();
            this.$emit('select', item.value);
        },
        toggleShow(t) {
            this.show = t!=undefined?t:!this.show
        },
        chkLength() {
            if (!this.editable) return true;
            if (this.maxlength && this.tmptag.length > this.maxlength) {
                this.$refs.noti.warning(`标签长度不得超过${this.maxlength}字`, {
                        needBg:false,
                        topMost:false
                    })
                    // this.warningInfo = `标签长度不得超过${this.maxlength}字`;
                return false;
            } else {
                this.$refs.noti.hide();
                // this.warningInfo = '';
                return true;
            }
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

.fade-down-transition {
  transition: all 0.3s;
}
.fade-down-enter,.fade-down-leave {
  transform: translateY(-100%);
}


.tag-editor-bg {
    position: fixed;
    top:0;
    left:0;
    bottom: 0;
    right:0;
    background: rgba(0,0,0,0.2);
    z-index:9000;
    .warning-popup {
      position: absolute;;
      left:0;
      top:0;
      right:0;
      text-align: center;
      line-height:60px;
      background: #ff0051;
      color:white;
      height:60px;
    }
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
