<template>
  <div class="nuo-create-wrapper">
    <div class="nuo-create-head">
      <!--:style="{ 'background-color': 'rgba(0,0,0,0)'}"-->
      <div class="nuo-create-head-left">《 有诺必行</div>
      <div class="nuo-create-head-right">
        <confirm-icon style="fill:white;"></confirm-icon>
      </div>
    </div>
    <div class="nuo-create-head-placeholder">

    </div>
    <target-input></target-input>

  </div>
</template>
<style scoped src="./create.scss" lang="scss">

</style>
<script>
  import ConfirmIcon from 'svg/common/Confirm.vue'
  import TargetInput from 'components/target-input/target-input.vue'
  export default{
    data(){
      return {}
    },
    components: {ConfirmIcon,TargetInput}
  }
</script>
