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
                "name": "饭堂"
              },
              {
                "name": "阿姨"
              },
              {
                "name": "老师"
              },
              {
                "name": "实训"
              },
              {
                "name": "饭堂2"
              },
              {
                "name": "阿2姨"
              },
              {
                "name": "老师2"
              },
              {
                "name": "实训2"
              },
              {
                "name": "饭堂3"
              },
              {
                "name": "阿姨3"
              },
              {
                "name": "老师3"
              },
              {
                "name": "实训3"
              },
              {
                "name": "饭堂4"
              },
              {
                "name": "阿姨4"
              },
              {
                "name": "老师4"
              },
              {
                "name": "实训4"
              }
            ],
            tagShow:false
        }
    }
}
</script>