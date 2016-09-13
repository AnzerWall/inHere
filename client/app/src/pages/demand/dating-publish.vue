<template>
<!--图文-->
  <div>
    <publish-picture :imagePublish></publish-picture>
  </div>

<!--详情-->
  <!--人均消费-->
  <div>
    <publish-text :key="items[0].datingKey" :msg.sync="per_cost"></publish-text>
  </div>

  <!--陪同对象-->
  <div class="dating-detail">
    <div class="dating-key">{{items[1].datingKey}}</div>
    <div class="dating-value">
      <div class="choice" v-for="target in targets" @click="showHightLight($index)">{{target.sex}}</div>
    </div>
  </div>

  <!--地点-->
  <div>
    <publish-text :key="items[2].datingKey" :msg.sync="place"></publish-text>
  </div>

  <!-时间-->
  <div class="dating-detail">
    <div class="dating-key">{{items[3].datingKey}}</div>
    <div class="dating-value"><input type="text" v-model="msg" :value="start_time"></div>
  </div>

  <!--集中地-->
  <div>
    <publish-text :key="items[4].datingKey" :msg.sync="gathering_place"></publish-text>
  </div>

</template>

<style scoped>
  input{
    border-style: none;
    outline: none;
    text-align:right;
    padding-right: 20px;
  }
  .dating-detail{
    width:100%;
    padding: 28px 0;
    font-weight: bold;
    border-bottom: 1px solid #d5d5d5;
    display: flex;
    flex-wrap: nowrap;
    justify-content: space-between;
    align-content: center;
  }
  .dating-key{
    padding: 0 18px;
    color: gray;
    opacity: 0.85;
  }
  .choice{
    padding: 0 8px;
  }
  .dating-value{
    display: flex;
    flex-wrap: nowrap;
    justify-content: flex-end;
  }
</style>

<script type="text/ecmascript-6">
  var formData = new FormData();

  import PublishPicture from '../../components/publish/publish-picture.vue'
  import PublishText from '../../components/publish/publish-text.vue'

  export default{

    data(){
      return{
        data:{
          target:0
        },
        items:[
          {datingKey:"人均消费"},
          {datingKey:"求陪同"},
          {datingKey:"地点"},
          {datingKey:"时间"},
          {datingKey:"集中地"}
        ],
        images:[
          {hasImage:false},
          {hasImage:false},
          {hasImage:false},
          {hasImage:false}
        ],
        hasAddImage:true,
        targets:[
          {sex:"无所谓"},
          {sex:"求女生"},
          {sex:"求男生"}
        ],
        per_cost:"¥",
        want_sex:"",
        place:"",
        start_time:"",
        gathering_place:""
      }
    },
    methods:{
      showHightLight(index){
        var oDatingValue = document.getElementsByClassName("dating-value");
        for (var i=0; i < oDatingValue.length; i ++) {
          if (i == index){
            oDatingValue[i].backgroundColor = "black";
            oDatingValue[i].color = "white";
          }else {
            oDatingValue[i].backgroundColor = "white";
            oDatingValue[i].color = "black";
          }
        }
      }
    },
    components:{
      PublishPicture,
      PublishText
    },
    routed:{

    }
  }
</script>
