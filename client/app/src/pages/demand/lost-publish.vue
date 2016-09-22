<template>
  <!--图文-->
  <div>
    <publish-picture :key.sync="content.text" :image_publish.sync="content.file"></publish-picture>
  </div>

  <!--丢失类型-->
  <div>
    <publish-choose :key="publish_key.ext_type" :publish_value.sync="content.ext_type" :labels="type_labels"></publish-choose>
  </div>

  <!--物品-->
  <div>
    <publish-text :key="publish_key.thing" :publish_value.sync="content.thing"></publish-text>
  </div>

  <!--捡到时间-->
  <div>
    <publish-time :key="publish_key.lost_time" :publish_value.sync="content.start_time"></publish-time>
  </div>

  <!--test  后期删除  标签选择-->
  <!--<div>-->
    <!--<publish-tag-selector :key="标签" :publish_value.sync="content.tag"></publish-tag-selector>-->
  <!--</div>-->

</template>

<style scoped>

</style>

<script type="text/ecmascript-6">
  var formData = new FormData();

  import PublishPicture from '../../components/publish/publish-picture.vue'
  import PublishText from '../../components/publish/publish-text.vue'
  import PublishChoose from '../../components/publish/publish-choose.vue'
  import PublishTime from '../../components/publish/publish-time.vue'
//  import PublishTagSelector from '../../components/publish/publish-tag-selector.vue'

  export default{

    data(){
      return{
        content:{
          id: "",                   //28,// 记录编号
          ext_type: 6,
          text: "",                  //"文字描述",
          file: [],                 //null,
          create_time: "",           // "创建时间",
          update_time: "",          //"更新时间",
          user_id: "",              //"创建者",
          thing: "",               //"东西名称",
          lose_time: "",               //"丢失时间",
          is_end: 0,                  // 是否已结束，0未结束，1结束

          tag:"",
        },
        publish_key:{
          ext_type:"类型",
          thing:"什么物品",
          lose_time:"捡到时间"
        },
        images:[
          {hasImage:false},
          {hasImage:false},
          {hasImage:false},
          {hasImage:false}
        ],
        has_add_image:true,

        type_labels:[
          {
            tag:"捡到",
            id:"foundType",
            isChecked:true,
            value:5
          },
          {
            tag:"丢失",
            id:"lostType",
            isChecked:false,
            value:4
          }
        ],
      }
    },
    methods:{
    },
    components:{
      PublishPicture,
      PublishText,
      PublishChoose,
      PublishTime
    },

//    post提交，发表失物
//      //页面加载数据钩子(或者叫事件)
//        return this.$request
//          .post("/lost-publish")//GET方法 url为/dating-publish
//          .set('Content-Type','application/json')
//          .send(data.content)
//          .end(function (res) {
//            console.log("lost-publish回调函数");
//            if (res.ok){
//              console.log("返回正确"+JSON.stringify(res.body));
//            }else {
//              console.log("返回错误"+res.text);
//            }
//          })
  }
</script>
