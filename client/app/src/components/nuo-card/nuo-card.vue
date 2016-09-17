<template>
  <!--<div class="nuo-card-wrapper">-->
  <div class="nuo-card" :class=" {'nuo-card-radius-all': hide_radius!==true}">
    <div @click="detail" class="nuo-card-detail">
      <div class="nuo-card-title"># {{data.title}}</div>
      <div class="nuo-card-desc">{{data.text}}</div>
      <div class="nuo-card-process" v-if="data.status===1">
        <div class="process-bar-wrapper">
          <div class="process-bar-content" :style="{width: this.process+'%'}"></div>
        </div>
      </div>
      <div class="nuo-card-process-like-hate-count" v-if="data.status===0">
        <div class="like-count">{{data.praise_count}} LIKE</div>
        <div class="hike-count"> {{data.low_count}} HATE</div>
        <div class="has-give-up" v-if="data.ha_give_up===true">曾放弃</div>
      </div>
    </div>

    <div class="nuo-car-tail-like-hate" v-if="data.status===1">
      <div class="btn-like" :style="{opacity: data.praised?1:0.5}"  @click="like">
        {{data.praise_count}} LIKE

      </div>
      <div class="btn-hate" :style="{opacity: data.is_low?1:0.5}"  @click="hate">
        {{data.low_count}} HATE
      </div>
    </div>
    <div class="nuo-car-tail-join" v-if="data.status===0" @click="join" :class=" {'nuo-card-radius-bottom': hide_radius!==true}"> 加入我的目标</div>

  </div>
  <!--</div>-->
</template>
<style scoped src="./nuo-card.scss" lang="scss">

</style>
<script type="text/ecmascript-6">

  export default{
    props: {
      hide_radius:{
        type:Boolean,
        default:false
      },
      data: {
        type: Object,
        default: ()=> {
          return {

              "id": 0,
              "title": "test",
              "text": "test",
              "create_time": 1473817466000,
              "update_time": 1473817466000,
              "target_total": 0,
              "process": 3,
              "has_give_up": false,
              "status": 0,
              "praised": false,
              "is_low": false,
              "praise_count": 233,
              "low_count": 256,
              "process_total": 10

          }
        }
      }
    },
    methods:{
      join(){

        this.$emit('join')
      },
      hate(){

        this.$emit('hate')
      },
      like(){
        this.$emit('like')
      },
      detail(){
        this.$emit('detail')
      }
    },
    computed: {
      process(){
        if (this.data.process_total == 0)return 0;
        else return Math.floor((this.data.process / this.data.process_total) * 100)
      }
    }


  }
</script>
