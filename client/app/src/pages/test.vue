<template>
<div class="test">
    <h3 @click="tagShow = !tagShow">{{tag || '选择标签'}}</h3>
    <tag-editor :show.sync="tagShow" :tag.sync="tag" :tags.sync="tags" :editable="!!1"></tag-editor>
</div>
</template>

<script>
import tagEditor from '../components/publish/publish-tag-editor.vue'
module.exports = {
    components:{
        tagEditor
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