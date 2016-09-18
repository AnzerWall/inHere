<template>
<!--图文-->
  <div>
    <publish-picture :key.sync="content.text" :image_publish.sync="content.photos"></publish-picture>
  </div>

<!--详情-->
  <!--人均消费-->
  <div>
    <publish-text :key="publish_key.per_cost" :publish_value.sync="content.per_cost"></publish-text>
  </div>

  <!--陪同对象-->
  <div>
    <publish-choose :key="publish_key.want_sex" :publish_value.sync="content.want_sex" :labels="sex_labels"></publish-choose>
  </div>

  <!--地点-->
  <div>
    <publish-text :key="publish_key.place" :publish_value.sync="content.place"></publish-text>
  </div>

  <!-时间-->
  <div>
    <publish-time :key="publish_key.start_time" :publish_value.sync="content.start_time"></publish-time>
  </div>

  <!--集中地-->
  <div>
    <publish-text :key="publish_key.gathering_place" :publish_value.sync="content.gathering_place"></publish-text>
  </div>

</template>

<style scoped>

</style>

<script type="text/ecmascript-6">
  var formData = new FormData();

  import PublishPicture from '../../components/publish/publish-picture.vue'
  import PublishText from '../../components/publish/publish-text.vue'
  import PublishChoose from '../../components/publish/publish-choose.vue'
  import PublishTime from '../../components/publish/publish-time.vue'

  export default{

    data(){
      return{
      content:{
        id: "",                   //28,// 记录编号
        ext_type: 6,
        text: "",                  //"文字描述",
        photos: [],                 //null,
        create_time: "",           // "创建时间",
        update_time: "",          //"更新时间",
        user_id: "",              //"创建者",
          want_sex: 2,            // "要求性别",  0 男   1 女生   2 无所谓
          start_time: "",           //"开始时间" ,
          gathering_place: "",      // "集中地点" ,
          place: "",                //"活动地点" ,
          join_count: "",           //"参加人数" ,
          praise: "",               //"点赞数",
          per_cost: "",             //"人均消费"
        is_end: 0,                  // 是否已结束，0未结束，1结束
      },
        publish_key:{
          per_cost:"人均消费",
          want_sex:"求陪同",
          place: "地点",
          start_time:"时间",
          gathering_place: "集中地"
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
            tag:"无所谓",
            id:"wantSexBoth",
            isChecked:true,
            value:2
          },
          {
            tag:"求男生",
            id:"wantSexMale",
            isChecked:false,
            value:0
          },
          {
            tag:"求女生",
            id:"wantSexFemale",
            isChecked:false,
            value:1
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

//    post提交，发表走起
//      //页面加载数据钩子(或者叫事件)
//        return this.$request
//          .post("/dating-publish")//GET方法 url为/dating-publish
//          .set('Content-Type','application/json')
//          .send(data.content)
//          .end(function (res) {
//            console.log("dating-publish回调函数");
//            if (res.ok){
//              console.log("返回正确"+JSON.stringify(res.body));
//            }else {
//              console.log("返回错误"+res.text);
//            }
//          })
  }
</script>
