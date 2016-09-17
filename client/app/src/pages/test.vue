<template>
  <div >
      <div v-for="item in labels">
   <input type="checkbox" v-model="item.checked">
        <span :class="{test:item.checked}">     {{item.checked}}</span>
      </div>
  </div>

</template>
<style scoped>
.test{
  color: #0BB20C;
}

</style>
<script type="text/ecmascript-6">
  //import fakeComment from '../api/fake/comment.js'
  export default{
    data(){
      return {
        labels:[{
          name:1,
          checked:false
        },{
          name:2,
          checked:false
        },{
          name:3,
          checked:false
        }]
      }
    }
  }
</script>
