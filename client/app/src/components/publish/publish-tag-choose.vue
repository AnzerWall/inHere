<template>

  <div class="publish-detail">
    <div class="publish-key">{{key}}</div>
    <div class="publish-value">
        <span @click="tagShow = !tagShow"><input :value="tag || '选择标签'"></span>
        <tag-editor :show.sync="tagShow" :tag.sync="tag" :tags.sync="tags" :editable="editable"></tag-editor>
    </div>
  </div>

</template>

<style scoped>
  input{
    width: 100%;
    border-style: none;
    outline: none;
    text-align:right;
  }
  .publish-detail {
    width:100%;
    font-weight: bold;
    border-bottom: 1px solid #d5d5d5;
    display: flex;
    flex-wrap: nowrap;
    justify-content: space-between;
    align-content: center;
  }
  .publish-key {
    padding: 28px 18px;
    color: gray;
    opacity: 0.85;
  }
  .publish-value{
    overflow-x: auto;
    width:70vw;
    padding: 28px 18px 28px 0;
    text-align:right;
  }
  span{
    width: 100%;
    overflow-x: hidden;
  }
</style>
<script>
  import TagEditor from './publish-tag-editor.vue'
  export default{
    data(){
      return{
        tagShow:false
      }
    },
    components:{
      TagEditor
    },
    props:{
      key:{
        type:String,
        required:true,
        default:"# 标签"
      },
      tag:{
        type:String,
        required:true,
        default:""
      },
      tags:{
        type:Array,
        required:true,
        default:()=>([
          {
            name: "饭堂",
            value: "饭堂"
          },
          {
            name: "阿姨",
            value: "阿姨"
          },
          {
            name: "老师",
            value: "老师"
          },
          {
            name: "实训",
            value:"实训"
          }
        ])
      },
      editable:{
        type:Boolean,
        required:true,
        default:true
      }
    }
  }
</script>

