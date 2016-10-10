<!--时间选择组件-->
<template>

  <div class="publish-detail">
    <div class="publish-key">{{key}}</div>
    <div class="publish-value">
        <span>{{publish_value.replace('T',' ')}}</span>
        <input type="datetime-local" v-model="publish_value">
    </div>
  </div>

</template>

<style scoped>
  span{
    float: right;
    /*height: 1em;*/
    text-align: right;
    margin-right: 20px;
  }
  input {
    float: right;
    width:70vw;
    height: 1em;
    opacity: 0;
    border-style: none;
    outline: none;
    text-align: right;
    padding-right: 20px;
  }

  .publish-detail {
    width: 100%;
    padding: 28px 0;
    font-weight: bold;
    border-bottom: 1px solid #d5d5d5;
    display: flex;
    flex-wrap: nowrap;
    justify-content: space-between;
    align-content: center;
  }

  .publish-key {
    /*width: 20vw;*/
    padding: 0 18px;
    color: gray;
    opacity: 0.85;
  }

  .publish-value {
    width:70vw;
    height: 1em;
  }
</style>
<script>
  export default{

    data(){
      return {
      }
    },
    components: {
    },
    props: {
      key: {
        type: String,
        required: true,
        default: "时间"
      },
      publish_value: {
        type: String,
        required: true,
        default: ""
      }
    },
    methods:{
    }
  }
</script>



<!--&lt;!&ndash;时间选择组件&ndash;&gt;-->
<!--<template>-->

  <!--<div class="publish-detail">-->
    <!--<div class="publish-key">{{key}}</div>-->
    <!--<div class="publish-value">-->
      <!--<vue-flatpickr class="timepicker" v-model='publish_value' :options="fpOptions"></vue-flatpickr>-->
    <!--</div>-->
  <!--</div>-->

<!--</template>-->

<!--<style scoped>-->

  <!--input{-->
    <!--border-style: none;-->
    <!--outline: none;-->
    <!--text-align:right;-->
    <!--padding-right: 20px;-->
  <!--}-->
  <!--.publish-detail {-->
    <!--width:100%;-->
    <!--padding: 28px 0;-->
    <!--font-weight: bold;-->
    <!--border-bottom: 1px solid #d5d5d5;-->
    <!--display: flex;-->
    <!--flex-wrap: nowrap;-->
    <!--justify-content: space-between;-->
    <!--align-content: center;-->
  <!--}-->
  <!--.publish-key {-->
    <!--/*width: 20vw;*/-->
    <!--padding: 0 18px;-->
    <!--color: gray;-->
    <!--opacity: 0.85;-->
  <!--}-->
  <!--.publish-value{-->
    <!--/*width:80vw;*/-->

  <!--}-->
  <!--.timepicker{-->
    <!--text-align:right;-->
  <!--}-->
<!--</style>-->
<!--<script>-->
  <!--import VueFlatpickrCss from  'vue-flatpickr/assets/flatpickr.min.css'-->
  <!--import VueFlatpickr from 'vue-flatpickr/VueFlatpickr-zh.vue'-->
  <!--export default{-->

    <!--data(){-->
      <!--return{-->
        <!--fpOptions: {-->
          <!--enableTime:true,-->
          <!--time_24hr:true,-->
<!--//          parseDate:true,-->
          <!--dataUtc:true,-->
<!--//          defaultDate:"2016-10-27T16:16:22.585Z",-->
          <!--dateFormat:"Y-m-d H:i",-->
        <!--}-->
      <!--}-->
    <!--},-->
    <!--components:{-->
      <!--VueFlatpickr-->
    <!--},-->
    <!--props:{-->
      <!--key:{-->
        <!--type:String,-->
        <!--required:true,-->
        <!--default:"时间"-->
      <!--},-->
      <!--publish_value:{-->
        <!--type:String,-->
        <!--required:true,-->
        <!--default:""-->
      <!--}-->
    <!--}-->
  <!--}-->
<!--</script>-->


