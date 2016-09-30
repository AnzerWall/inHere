<template>
  <div>
    <input type="text" v-el:input>

  </div>
</template>
<style >
  .flatpickr-calendar.open {
    visibility: visible;
  }
  .flatpickr-input-custom{
    border: hidden;
    background: transparent;


  }
  .flatpickr-input-custom:focus{
    outline: none;
  }
</style>
<script type="text/ecmascript-6">
  import VueFlatpickrCss from  './flatpickr/dist/flatpickr.min.css'
  import Flatpickr from './flatpickr/src/flatpickr.js'
  export default{
    props: {
      options: {
        type: Object
      }
    },
//    watch: {
//      options: {
//        handler() {
//          this.init()
//        },
//        deep: true
//      }
//    },
    methods:{
      init(){
        new Flatpickr(this.$els.input)
        this.$el.querySelectorAll('.flatpickr-input').forEach((item)=>item.className+=" flatpickr-input-custom");
      }
    },
    ready() {
      this.init();
    },
//    mounted() {
//      this.init();
//
//    },
    data(){
      return {}
    },
    components: {}
  }
</script>


<!--<template>-->
  <!--<input type="text">-->
<!--</template>-->

<!--<script>-->
  <!--import VueFlatpickrCss from  './flatpickr/dist/flatpickr.min.css'-->
  <!--import Flatpickr from './flatpickr/src/flatpickr.js'-->
  <!--export default {-->
    <!--props: {-->
      <!--options: {-->
        <!--type: Object-->
      <!--}-->
    <!--},-->
    <!--watch: {-->
      <!--options: {-->
        <!--handler() {-->
          <!--this.newFlatpickr()-->
        <!--},-->
        <!--deep: true-->
      <!--}-->
    <!--},-->
    <!--ready() {-->
      <!--new Flatpickr(this.$el, this.options)-->
    <!--},-->
    <!--mounted() {-->
      <!--new Flatpickr(this.$el, this.options)-->
    <!--}-->
  <!--}-->
<!--</script>-->
