<template>
  <!--任务图文-->
  <div>
    <publish-picture :key.sync="content.text" :image_publish.sync="content.file"></publish-picture>
  </div>

  <!--任务类型-->
  <div>
    <publish-choose :key="publish_key.ext_type" :publish_value.sync="content.ext_type" :labels="type_labels"
                    v-on:change-type="changeTaskType">
    </publish-choose>
  </div>


  <!--快递-->
  <div v-if="publish_type == 0">
    <!--酬劳-->
    <div>
      <publish-number :key="publish_key.pay" :publish_value.sync="content.pay"></publish-number>
      <!--<publish-text :key="publish_key.pay" :publish_value.sync="content.pay"></publish-text>-->
    </div>
  </div>


  <!--转让-->
  <div v-if="publish_type == 1">
    <!--成色-->
    <div>
      <publish-text :key="publish_key.quality" :publish_value.sync="content.quality"></publish-text>
    </div>
    <!--购买时间-->
    <div>
      <publish-time :key="publish_key.buy_time1" :publish_value.sync="content.buy_time"></publish-time>
    </div>
    <!--原价-->
    <div>
      <publish-text :key="publish_key.original_price" :publish_value.sync="content.original_price"></publish-text>
    </div>
    <!--售价-->
    <div>
      <publish-text :key="publish_key.price" :publish_value.sync="content.price"></publish-text>
    </div>
  </div>


  <!--帮忙-->
  <div v-if="publish_type == 2">
    <!--酬劳-->
    <div>
      <publish-text :key="publish_key.pay" :publish_value.sync="content.pay"></publish-text>
    </div>
  </div>



  <!--其他-->
  <div v-if="publish_type == 3">
    <!--酬劳-->
    <div>
      <publish-text :key="publish_key.pay" :publish_value.sync="content.pay"></publish-text>
    </div>
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
  import PublishNumber from '../../components/publish/publish-number.vue'
  import {token,login_state,is_login} from '../../vuex/getters.js'

  export default{

    data(){
      return{
        content:{
          id: "",                   //28,// 记录编号
          ext_type: 1,
          text: "",                  //"文字描述",
          file: [],                 //null,
          create_time: "",           // "创建时间",
          update_time: "",          //"更新时间",
          user_id: "",              //"创建者",

          //1、快递   3、帮忙
          pay:"",             // "酬金"

          //2、转让
          quality: "",                   //"成色",
          buy_time: "",                //"购买时间",
          original_price: "",          //"原价",
          price: "",                   //"售价"

          is_end: 0,                  // 是否已结束，0未结束，1结束
        },
        publish_type:"",          // 发布任务的类型 0、兼职  1、快递  2、转让 3、帮忙  4、其他

        publish_key:{
          ext_type:"类型",

          pay:"酬金",

          quality:"成色",
          buy_time1:"购买时间",
          original_price:"原价",
          price:"售价"
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
            tag:"兼职",
            id:"expressType",
            isChecked:true,
            value:0
          },
          {
            tag:"转让",
            id:"transferType",
            isChecked:false,
            value:1
          },
          {
            tag:"帮忙",
            id:"helpType",
            isChecked:false,
            value:2
          },
          {
            tag:"其他",
            id:"otherType",
            isChecked:false,
            value:100
          }
        ],
      }
    },
    methods:{
      changeTaskType:function (type) {
        console.log("父组件接收子组件的通信");
          this.publish_type = type;
      }
    },
    components:{
      PublishPicture,
      PublishText,
      PublishChoose,
      PublishTime,
      PublishNumber
    },
    vuex: {
      actions: {
      },
      getters: {
        login_state,
        token,
        is_login
      }
    },

//    post提交，发表任务
//     route: {
//       data(){
//         let token = this.token;
//        //页面加载数据钩子(或者叫事件)
//          return this.$request
//            .post('/demand?token=${token}')
//            .set('Content-Type','application/json')
//            .send(data.content)
//            .end(function (res) {
//              console.log("lost-publish回调函数");
//              if (res.ok){
//                console.log("返回正确"+JSON.stringify(res.body));
//              }else {
//                console.log("返回错误"+res.text);
//              }
//            })
//       }
//     }
  }
</script>
