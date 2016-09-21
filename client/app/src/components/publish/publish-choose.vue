<template>

  <div class="publish-detail">
    <div class="publish-key">{{key}}</div>
    <div class="choices">
      <div class="choices-flex-end">
        <div class="choices-wrapper">
          <div class="publish-value" v-for="label in labels">
            <input type="radio"  :id="label.id" :value="value" v-model="publish_value" :checked="label.isChecked">
            <div class="tag" :for="label.id" @click="changeBg($index)"
                 :class="{'checked':label.isChecked}">
              {{label.tag}}
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

</template>

<style scoped>

  .publish-detail {
    display: flex;
    align-items: center;
    width:100%;
    font-weight: bold;
    border-bottom: 1px solid #d5d5d5;
    display: flex;
    flex-wrap: nowrap;
    align-content: center;
  }
  .publish-key {
    width: 20vw;
    padding: 28px 0 28px 18px;
    color: gray;
    opacity: 0.85;
  }
  .choices{
    width:80vw;
    height: 19px;


    padding: 22px 0 33px 0;
    overflow: hidden;
  }
  .choices-flex-end{
    display: flex;
    flex-wrap: nowrap;
    justify-content: flex-end;
  }
  .choices-wrapper{
    padding-bottom: 40px;
    display: flex;
    flex-wrap: nowrap;
    overflow-x: auto;
  }
  .publish-value:last-child div{
    margin-right: 10px;
  }
  .publish-value{

  }
  input[type="radio"] {
  display: none;
  }
  .tag {
    text-align: center;
    font-weight: bold;
    border-radius: 4px;
    cursor: pointer;
    height: 1em;
    /*width: 3em;*/
    padding: 8px;
    margin: 0 1px;
    color: gray;

    opacity: 0.65;
  }
  .checked{
    opacity: 1;
    background-color: black;
    color: white;
  }
</style>
<script>
  export default{
    data(){
      return{
      }
    },
    props:{
      key:{
        type:String,
        required:true,
        default:"求陪同"
      },
      publish_value:{
        type:Number,
        required:true,
        default:0
      },
      labels:{
        type:Array,
        required:true,
        default:()=>([
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
        ])
      },
    },
    methods:{
      changeBg:function(index){

        for (var i=0; i < this.labels.length; i++){
          if (i==index){
            this.labels[i].isChecked=true;
          }else {
            this.labels[i].isChecked=false;
          }
        }
        console.log("changeBg,emit给父组件通信");
//        this.$emit('change-type',index);
//        this.$dispatch('change-type',index);
          this.$emit('change-type',index);
      }
    }
  }
</script>
