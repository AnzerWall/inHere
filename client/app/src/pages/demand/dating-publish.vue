<template>
    <div class="publish">


      <div class="dating-content">
        <textarea class="content-text" placeholder="主人，你有什么需要呢？"></textarea>
      </div>

      <div class="add-img-wrapper" :style="imgWrapperHeight">
        <input id="fileUpload"  type="file" style="display: none;">
        <div class="add-img" v-for="item in images" v-if="images[$index].hasImage" :style="imgHeight">
          <div class="dating-img">
            <image-add  @click="addImage($index)"></image-add>

          </div>

        </div>

      </div>

      <div class="dating-detail" v-for="item in items">
        <div class="dating-key">{{item.datingKey}}</div>
        <div class="dating-value"><input type="text"></div>
      </div>
    </div>

  <div class="test">

  </div>
</template>

<style>
  .publish{
    margin: 0;
    font-size: 15px;
  }
  .dating-content{
    padding: 18px;
  }
  textarea{
    width: 100%;
    height: 120px;
    border-style: none;
    resize:none;
    outline: none;
  }
  .add-img-wrapper {
    padding: 2vw;
    display: flex;
    flex-wrap: nowrap;
    border-bottom: 1px solid #d5d5d5;
  }
  .add-img{
    width:18vw;
    height: 18vw;
    background-color: white;
    display: flex;
    align-items: center;
    justify-content: center;
    margin: 2vw;
    border: 1vw solid #f1f1f1;
  }
  .dating-img{
  }
  input{
    border-style: none;
    outline: none;
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
</style>

<script>

  import ImageAdd from 'svg/common/ImageAdd.vue'

  export default{

    data(){
        return{
          items:[
            {datingKey:"人均消费"},
            {datingKey:"求陪同"},
            {datingKey:"地点"},
            {datingKey:"时间"},
            {datingKey:"集中地"}
          ],
          images:[
            {hasImage:true},
            {hasImage:false},
            {hasImage:false},
            {hasImage:false}
          ]
        }
    },
    methods:{
      addImage(index) {


        console.log("me");



        var fileBtn1 = document.getElementById("fileUpload");
        console.log(fileBtn1);

        fileBtn1.click();
        fileBtn1.click();
//        fileBtn1.click();
       //this.$nextTick(()=>{ this.$els.fileInput.click()});
       //

       //
       // fileBtn1.click();
//        this.$nextTick(()=>{
//          fileBtn1.click();
//        });
//
//        window.tmp1 = this.$els.fileInput;
//        window.tmp2 = fileBtn1;
//
//        console.dir(   fileBtn1);
//        console.dir(    this.$els.fileInput);
//        console.log( this.$els.fileInput===  fileBtn1);

//      setTimeout(function () {
//        window.tmp1.click();
//      },2000);


      //  var tm = this.$els.fileInput;
       // console.log(tm);
        //tm.click();
//       this.$els.fileInput.click();
  //      添加图片
//        new FormData (form? : HTMLFormElement);

  //      如果数量不超过4张，添加ImageAdd组件
//        if (index<=2){
//          this.images[index+1].hasImage = true;
//        }
      }
    },
    components:{
      ImageAdd
    },
    computed:{
    }
  }
</script>
