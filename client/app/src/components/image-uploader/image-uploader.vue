<template>
  <div>
    <input type="file" @change="change" v-el:image-input class="hidden-file-input">
  </div>
</template>
<style scoped>
  .hidden-file-input {
    border: none;
    background-color: transparent;
    opacity: 0;
    width: 100%;
    height: 100%;
    position: absolute;
    top: 0;
    left: 0;
    z-index: 100;
  }
</style>
<script type="text/ecmascript-6">
   const fileToImage =function (file)  {
    return new Promise((resolve, reject) => {
      const img = new Image();
      img.src = URL.createObjectURL(file);
      img.onload = () => resolve(img);
      img.onerror = reject
    })
  };
   const compress =function(file,quality = 0.8)  {
     return fileToImage(file)
       .then(img => {
        // let { quality = 0.8 } = opts;

         let canvas = document.createElement('canvas');
         let ctx = canvas.getContext('2d');
         let  width = img.width;
         let height = img.height;
         let scaleH=(height>720)?720/height:1;
         let scaleW=(width>720)?720/width:1;
         let scale=Math.min(scaleH,scaleW);
         const newHeight=height*scale;
         const newWidth=width*scale;

         canvas.width = newWidth;
         canvas.height = newHeight;

         console.info(scaleH, scaleW, scale, newHeight, newWidth, width, height);
         ctx.drawImage(img, 0, 0, newWidth,newHeight);

         return canvas.toDataURL('image/jpeg', quality)
       })
   };

  export default{
    data(){
      return {}
    },
    methods: {
      change(){
        let imageInput=this.$els.imageInput;

        if(imageInput.files&&imageInput.files.length>0){
          let file=imageInput.files[0];
          let  filter = /^(?:image\/bmp|image\/cis\-cod|image\/gif|image\/ief|image\/jpeg|image\/jpeg|image\/jpeg|image\/pipeg|image\/png|image\/svg\+xml|image\/tiff|image\/x\-cmu\-raster|image\/x\-cmx|image\/x\-icon|image\/x\-portable\-anymap|image\/x\-portable\-bitmap|image\/x\-portable\-graymap|image\/x\-portable\-pixmap|image\/x\-rgb|image\/x\-xbitmap|image\/x\-xpixmap|image\/x\-xwindowdump)$/i;
          if(filter.test(file.type)) {
            let reader = new FileReader();
            let data=reader.readAsDataURL(file);
            compress(file)
              .then(dataURL =>  {
                console.log(dataURL.toString().length);
                this.$emit("change-image",dataURL);
                imageInput.value="";
              });

          }

        }
      }
    }

  }
</script>
