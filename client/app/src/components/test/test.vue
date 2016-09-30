<template>
    <div>
      <input v-model="text" :value="v">
    </div>
</template>
<style>

</style>
<script>

    export default{
        props:['text','v']
    }
</script>
