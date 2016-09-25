<template>
    <div class="classify-wrapper" >
      <div class="classify-head" >
        <div class="classify-header hide-scroll" >
          <div class="hot-classify" v-for="list in lists" >
            <div class="classify-message" :style="{opacity: countOpacity($index)}" @click="onClick($index)" >
              <span :style="{color:color}"># {{list.name}}</span>
            </div>

          </div>
        </div>
      </div>
    </div>
</template>
<style>
  .classify-wrapper {
    width: 100%;
    overflow: hidden;
    height: 50px;
    padding: 0 0 10px 0px;
    position: relative;
  }

  .classify-head {
    width: 100%;
  }

  .classify-header {
    display: flex;
    flex-direction: row;
    flex-wrap: nowrap;
    overflow: auto;
    height: 70px;
    margin-top: 5px;

    padding-left: 20px;

  }

  .hide-scroll::-webkit-scrollbar,
  .hide-scroll::-webkit-scrollbar-track,
  .hide-scroll::-webkit-scrollbar-thumb {
    width: 0;
    border-width: 0;
    height: 0;
  }

  .classify-message {
    height: 20px;
    border-radius: 4px;
    padding: 8px;
    margin-right: 10px;
    font-size: 14px;
    background-color: white;
    white-space: nowrap;

  }
  /*.classify-space{*/
    /*height: 50px;*/
    /*width: 100%;*/

  /*}*/



</style>
<script type="text/ecmascript-6">

    export default{
        data(){
            return{

            }
        },
      methods: {
        onClick(index){
          console.log("click");
          this.$emit("filter-label",this.lists[index].id);

        },
        countOpacity: function (index) {
          return 1 - 0.12 * index;
        }
      },
      props:['lists','color'],
        components:{

        }
    }
</script>
