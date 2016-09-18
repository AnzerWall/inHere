<template>
  <div class="publish">

    <div class="dating-content">
      <textarea class="content-text" placeholder="主人，你有什么需要呢？" v-model="publish_text"></textarea>
    </div>

    <div class="add-img-wrapper" :style="imgWrapperHeight">
      <!--  multiple="multiple" -->
      <input id="fileUpload"  type="file"  accept="image/jpeg, image/png, image/jpg, image/gif" style="display: none;">

      <div id="upload"></div>

      <!--图片-->
      <div class="add-img order2" v-for="item in images" v-if="images[$index].hasImage">
        <img class="image"/>
      </div>
      <!--添加按钮-->
      <div class="add-img order20" v-if="has_add_image">
        <div class="dating-img">
          <image-add  @click="addImage($index)"></image-add>
        </div>
      </div>

    </div>

  </div>
</template>

<style scoped>
  .publish{
    margin: 0;
    font-size: 15px;
  }
  .dating-content{
    padding: 18px;
  }
  textarea{
    width: 100%;
    height: 150px;
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
  .image{
    width: 100%;
    height: 100%;
  }
  .order2{
    order:2;
  }
  .order20{
    order:20;
  }
  .dating-img{
  }
  input{
    border-style: none;
    outline: none;
    text-align:right;
    padding-right: 20px;
  }
</style>

<script type="text/ecmascript-6">
  var formData = new FormData();

  import ImageAdd from 'svg/common/ImageAdd.vue'

  export default{

    data(){
      return{
        images:[
          {hasImage:false},
          {hasImage:false},
          {hasImage:false},
          {hasImage:false}
        ],
        has_add_image:true
      }
    },
    props:{
      publish_text:{
        type:String,
        default:""
      },
      image_publish:{
        type: Array,
        default:""
      }
    },
    methods:{
      /**
       *    图片上传并预览 这个方法还有问题，，，小泽后期再改 2016.9.9 11：08
        * @param index
       */
      addImage(index) {
        var fileBtn1 = document.getElementById("fileUpload");
        console.log(fileBtn1);
        fileBtn1.click();
        fileBtn1.click();

        //      添加图片
        var oImage = document.querySelector("#fileUpload");

        for (var i = 0,file; file = oImage[i]; i++){
          formData.append(file.name, file);
          console.log(i);
        }


//        图片预览
        if (typeof FileReader != 'undefined'){

          var reader = new FileReader();
          reader.onload = function (event) {
            var image = new Image();
            image.src = event.target.result;
            image.width = 100;
            if ( document.body.appendChild(image) ){
              console.log("预览成功了");

              //      如果数量不超过4张，添加ImageAdd组件
              if (index<=2){
                this.images[index+1].hasImage = true;
              }
            }else {
              console.log("预览失败了");
            }

            reader.readAsDataURL(document.getElementById('upload').files[0]);
          }
        }else {
          console.log("不支持FileReader");
        }


      },
      sendData(){
        // 检查是否支持FormData
        if(window.FormData) {
          var formData = new FormData();
          // 建立一个upload表单项，值为上传的文件
          formData.append('upload', document.getElementById('upload').files[0]);
          var xhr = new XMLHttpRequest();
          xhr.open('POST', $(this).attr('action'));
          // 定义上传完成后的回调函数
          xhr.onload = function () {
            if (xhr.status === 200) {
              console.log('上传成功');
            } else {
              console.log('出错了');
            }
          };
          xhr.send(formData);
        }
      }
    },
    components:{
      ImageAdd
    },
    computed:{
    }
  }
</script>
