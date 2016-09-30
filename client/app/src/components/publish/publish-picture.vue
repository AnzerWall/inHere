<template>

  <div class="publish">
    <div class="dating-content">
      <textarea class="content-text" maxlength=250 placeholder="主人，你想说些什么呢？" v-model="publish_text"></textarea>
    </div>

    <div class="add-img-wrapper" :style="imgWrapperHeight">
      <!--  multiple="multiple" -->
      <input id="fileUpload"
             v-on:change="addImage()"
             type="file"
             accept="image/jpeg, image/png, image/jpg, image/gif"
             style="display: none;">

      <!--图片-->
      <div class="add-img order2" v-for="image in images" v-show="image.hasImage" @click.stop="deletePhotos($index)" :style="{'background-image': 'url('+image.src+')'}">
      </div>
      <!--添加按钮-->
      <div class="add-img order20" v-if="has_add_image">
        <div class="dating-img">
          <image-add @click="openFile()"></image-add>
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
    padding: 18px 18px 0 18px;
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
  }
  .order2{
    order:2;
    background-repeat: no-repeat;
    background-size: cover;
    background-position: center;
    border: 1vw solid #ffffff;
    box-shadow: 2px 2px 8px #E1E1E1;
    -webkit-box-shadow:2px 2px 8px #E1E1E1;
    -moz-box-shadow:2px 2px 8px #E1E1E1;
  }
  .order20{
    order:20;
    border: 1vw solid #f1f1f1;
  }
  .dating-img{
  }
  input{
    border-style: none;
    outline: none;
    text-align:right;
    padding-right: 20px;
  }
  img{

  }
</style>

<script type="text/ecmascript-6">
  import ImageAdd from 'svg/common/ImageAdd.vue'

  export default{

    data(){
      return{
        images:[
          {hasImage:false,src:""},
          {hasImage:false,src:""},
          {hasImage:false,src:""},
          {hasImage:false,src:""}
        ],
        has_add_image:true,
        index:-1  // 记录上一次onchange事件已选择最后一张图片的下标
      }
    },
    props:{
      publish_text:{
        type:String,
        default:""
      },
      image_publish:{
        type: Array,
        default:[]
      }
    },
    methods:{
      /**
       * 照片删除
       */
      deletePhotos(index){
        var self = this;

        Simpop({
          title: '提示',
          content: '确定删除图片吗？',
          ok: function(){
            // this.items.splice(index, 1)
            self.image_publish.splice(index,1);
            // 向子组件传播事件，删除图片列表数组中的images[index]与并添加一个默认的item元素，，实现将当前图像删除，并将后面的图片向前移动一个位置
            self.images.splice(index,1);
            self.index--;
            if (self.index<4){
              self.has_add_image = true;
            }
            self.images.push({
              hasImage:false,
              src:""
            })
          },
          cancel: function(){

          }
        }).show();

      },

      /**
       *    图片上传并预览
        * @param index
       */
      openFile(){
        if (this.index < 4){
          var ofileUpload = document.getElementById("fileUpload");
          ofileUpload.click();

          // 这是个bug ，我也不知道为什么，后期要删除
          ofileUpload.click();
        }else {
          console.log("图片超过四张");
        }
      },
      addImage() {  // 添加图片到formData 并预览
        var self = this;
        if (self.index < 4){

          console.log("index="+self.index);
          var ofileUpload = document.getElementById("fileUpload");   // 上传按钮对象

          var pic_count = ofileUpload.files.length+self.index>3 ? (3-self.index) : ofileUpload.files.length;
          // 保证最多添加4张图片（index从-1开始，故和最大不超过3）

          for(var i = 0; i < pic_count; i++){
            self.image_publish.push(ofileUpload.files[i]);
            console.log("子组件self.image_publish"+self.image_publish[i]);
          }

          // 2、图片预览（单图）
          var oFReader = new FileReader();
          oFReader.onload = function (oFREvent) {
            ++self.index;
            self.images[self.index].hasImage = true;
            self.images[self.index].src = oFREvent.target.result;
            if (self.index == 3){
              self.has_add_image = false;
            }
          };

            if (ofileUpload.files.length === 0) { return; }
            var oFile = ofileUpload.files[0];
            oFReader.readAsDataURL(oFile);

          /**
           *  多图片上传预览 这个方法还有问题，，，小泽后期再改 2016.9.9 11：08   勿删
           */
          // 2、图片预览（考虑多图情况,有bug）(oimagePreview的下标从 index+1  到  index+pic_count )
//          function loadImageFile(start_index, end_index) {
//            if (ofileUpload.files.length === 0) {
//              return;
//            }else {
//              var oFile;
//              for (var tem_index = start_index; tem_index <= end_index; tem_index ++){
//                oFile = ofileUpload.files[tem_index];
//
//                var oFReader = new FileReader();
//                oFReader.readAsDataURL(oFile);
//                console.log("oFReader.readAsDataURL(oFile);");
//                oFReader.onload = function (oFREvent) {
//                  self.images[tem_index].hasImage = true;
//                  console.log(self.images[tem_index].hasImage);
//                  self.images[tem_index].src = oFREvent.target.result;
//                };
//              }
//            }
//          }
//          loadImageFile(index+1,index+pic_count);
//          index = index+pic_count;

        }else {
          console.log("图片已经超过了4张");
          return;
        }
      }
    },
    components:{
      ImageAdd
    },
    events:{

    },
    computed:{
    }
  }
</script>
