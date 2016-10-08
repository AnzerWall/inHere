<template>
<div class="test">
  <div @click="clickTag()">{{tag || '选择标签'}}</div>
  <button @click="showNoti('alert')">alert</button>
  <button @click="showNoti('confirm')">confirm</button>
  <button @click="showNoti('warning')">warning</button>
  <button @click="showNoti('noti')">noti</button>
  <tag-editor v-ref:tag-editor :tags.sync="tags" :tag.sync="tag" :editable="true" :show.sync="tagShow"></tag-editor>
  <!-- <button @click="clickBtn()">按钮</button> -->
  <noti v-ref:noti></noti>
</div>
</template>

<script>
import tagEditor from '../components/publish/publish-tag-editor.vue'
import noti from '../components/noti.vue'
module.exports = {
    components:{
        tagEditor,noti
    },
    methods:{
      showNoti(type){
        switch(type){
          case 'alert':
          this.$refs.noti.alert('不行啊',{
            callback(result,noti){
              console.log('alert',result,noti);
            }
          });
          break;
          case 'confirm':
           this.$refs.noti.confirm('CONFIRM！你确认要打小泽？',{
            callback(result,noti){
              console.log('confirm',result,noti);
            }
          });
          break;
          case 'warning':
          this.$refs.noti.warning('WARNING!不可以这样的！',{
            callback(result,noti){
              console.log('warning',result,noti);
            }
          });
          break;
          case 'noti':
          this.$refs.noti.noti('NOTI!!!!');
          break;
        }
      },
      clickTag(){
        this.$refs.tagEditor.showMe();
      }
    },
    data(){
        return {
            tag:'',
            tags:[
              {
                "name": "饭堂",
                "value":123
              },
              {
                "name": "阿姨",
                "value":321
              },
              {
                "name": "老师",
                "value":333
              },
              {
                "name": "实训",
                "value":111
              },
              {
                "name": "饭堂2",
                "value":222
              },
              {
                "name": "阿2姨",
                "value":1221213
              },
              {
                "name": "老师2",
                "value":123
              },
              {
                "name": "实训2",
                "value":123
              },
              {
                "name": "饭堂3",
                "value":123
              },
              {
                "name": "阿姨3",
                "value":123
              },
              {
                "name": "老师3",
                "value":123
              },
              {
                "name": "实训3",
                "value":123
              },
              {
                "name": "饭堂4",
                "value":123
              },
              {
                "name": "阿姨4",
                "value":123
              },
              {
                "name": "老师4",
                "value":123
              },
              {
                "name": "实训4",
                "value":123
              }
            ],
            tagShow:false
        }
    }
}
</script>
