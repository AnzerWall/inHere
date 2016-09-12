<template>
  <div class="p">
      <div style=" align-self: flex-start">
        12313
      </div>
    <div style=" align-self: flex-start">
      12313
    </div>
    <div style=" align-self: flex-end">
      12313
    </div>
    <div style=" align-self: flex-start">
      12313
    </div>
    <div style=" align-self: flex-end">
      12313
    </div>
    <div style=" align-self: flex-start">
      12313
    </div>
  </div>

</template>
<style scoped>
.p{
  display: flex;
  flex-direction: column;
}
  .p .s1{
    align-self: flex-start;
  }
.p .s2{
  align-self: flex-end;
}
</style>
<script type="text/ecmascript-6">
  import fakeComment from '../api/fake/comment.js'
  export default{
//    route: {
//      data(){
//        return this.$request
//          .get("/demand")
//          .then(this.$api.checkResult)
//          .then((data)=>{
//            console.log(data);
//          })
//          .then(()=>{
//            return  this.$request
//              .get("/demand/1")
//              .then(this.$api.checkResult)
//              .then((data)=>{
//                console.log(data);
//              })
//
//          })
//          .then(()=>{
//            return  this.$request
//              .get("/official_notice")
//              .then(this.$api.checkResult)
//              .then((data)=>{
//                console.log(data);
//              })
//
//          })
//          .then(()=>{
//            return  this.$request
//              .get("/note_notice")
//              .then(this.$api.checkResult)
//              .then((data)=>{
//                console.log(data);
//              })
//
//          })
//          .then(()=>{
//            return  this.$request
//              .get("/module_notice")
//              .then(this.$api.checkResult)
//              .then((data)=>{
//                console.log(data);
//              })
//
//          });
//
//      }
//    }

  }
</script>
