<template>
  <div>

  </div>

</template>
<style>

</style>
<script type="text/ecmascript-6">
  import fakeComment from '../api/fake/comment.js'
  export default{
    route: {
      data(){
        return this.$request
          .get("/demand")
          .then(this.$api.checkResult)
          .then((data)=>{
            console.log(data);
          })
          .then(()=>{
            return  this.$request
              .get("/demand/1")
              .then(this.$api.checkResult)
              .then((data)=>{
                console.log(data);
              })

          })
          .then(()=>{
            return  this.$request
              .get("/official_notice")
              .then(this.$api.checkResult)
              .then((data)=>{
                console.log(data);
              })

          });

      }
    }

  }
</script>
