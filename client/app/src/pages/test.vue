<template>
    <div class="test">
        <div @click="clickTag()">{{tag || '选择标签'}}</div>
        <button @click="showChangePassword()">修改密码</button>
        <button @click="showMenu()">弹出菜单</button>
        <br>
        <button @click="showNoti('alert')">alert</button>
        <button @click="showNoti('confirm')">confirm</button>
        <button @click="showNoti('warning')">warning</button>
        <button @click="showNoti('noti')">noti</button>
        <tag-editor v-ref:tag-editor :tags.sync="tags" :tag.sync="tag" :editable="true" :show.sync="tagShow"></tag-editor>
        <noti v-ref:noti></noti>
        <!-- <fail text="玲玉不让你看"></fail> -->
        <change-password v-ref:changepassword></change-password>
        <menu v-ref:menu></menu>
    </div>
</template>
<script>
import fail from '../components/fail.vue'
import tagEditor from '../components/publish/publish-tag-editor.vue'
import menu from '../components/menu.vue'
import noti from '../components/noti.vue'
import changePassword from '../components/change-password.vue'
module.exports = {
    components: {
        tagEditor,
        noti,
        fail,
        changePassword,
        menu
    },
    methods: {
        showMenu(){
            this.$refs.menu.show({
                btns:[{
                    title:'按钮1',
                    event:function(){
                        console.log('event1')
                        return true;
                    }
                },{
                    title:'按钮2',
                    event:function(){
                        console.log('event2')
                        return false;
                    }
                },{
                    title:'按钮3',
                    event:function(){
                        console.log('event3')
                        return true;
                    }
                }]
            })
        },
        showChangePassword() {
            this.$refs.changepassword.show({
                validator(oldPwd, newPwd1, newPwd2) {
                    console.log(oldPwd, newPwd1, newPwd2);
                    return newPwd1===newPwd2?null:'两个新密码不一样';
                },
                confirm(oldPwd,newPwd) {
                    alert(`旧密码:${oldPwd},新密码:${newPwd}`);
                    return true;
                }
            })
        },
        showNoti(type) {
            var that = this;
            switch (type) {
                case 'alert':
                    this.$refs.noti.alert('不行啊', {
                        bgColor: 'green',
                        callback(result, vm) {
                            console.log('alert', result, vm);
                        }
                    });
                    break;
                case 'confirm':
                    this.$refs.noti.confirm('CONFIRM！你确认要打小泽？', {
                        callback(result, vm) {
                            console.log('confirm', result);
                            if (result) vm.alert('打死他!');
                            else vm.warning('不打不行!', {
                                timeout: 2000
                            });
                        }
                    });
                    break;
                case 'warning':
                    this.$refs.noti.warning('WARNING!不可以这样的！', {
                        callback(result, vm) {
                            console.log('warning', result, vm);
                        }
                    });
                    break;
                case 'noti':
                    this.$refs.noti.noti('NOTI!!!!23333333333333sdagasdgasddsagasdgsagssadgsadgasdgasdgs时代嘎上的嘎说');
                    break;
            }
        },
        clickTag() {
            this.$refs.tagEditor.showMe();
        }
    },
    data() {
        return {
            tag: '',
            tags: [{
                "name": "饭堂",
                "value": 123
            }, {
                "name": "阿姨",
                "value": 321
            }, {
                "name": "老师",
                "value": 333
            }, {
                "name": "实训",
                "value": 111
            }, {
                "name": "饭堂2",
                "value": 222
            }, {
                "name": "阿2姨",
                "value": 1221213
            }, {
                "name": "老师2",
                "value": 123
            }, {
                "name": "实训2",
                "value": 123
            }, {
                "name": "饭堂3",
                "value": 123
            }, {
                "name": "阿姨3",
                "value": 123
            }, {
                "name": "老师3",
                "value": 123
            }, {
                "name": "实训3",
                "value": 123
            }, {
                "name": "饭堂4",
                "value": 123
            }, {
                "name": "阿姨4",
                "value": 123
            }, {
                "name": "老师4",
                "value": 123
            }, {
                "name": "实训4",
                "value": 123
            }],
            tagShow: false
        }
    }
}
</script>
