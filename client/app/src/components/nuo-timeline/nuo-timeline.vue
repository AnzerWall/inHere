<template>
  <div class="nuo-timeline-wrapper">
    <div class="nuo-timeline-content">
      <div class="nuo-timeline-timeline-wrapper">
        <div class="nuo-timeline-timeline-circle">
          {{data.index+1}}
        </div>
        <div class="nuo-timeline-timeline-line">

        </div>
      </div>
      <div class="nuo-timeline-type1" v-if="false">
        <div class="title">读四本书</div>
        <div class="text">来到了大学怎么能不读书呢</div>
        <div class="process">目标数量:10 已完成:2</div>
        <div class="btn">+1</div>
      </div>
      <div class="nuo-timeline-type2"  v-if="false">
        <div class="title">去吃遍学校里的所有美食</div>
        <div class="text">来到了大学怎么能不读书呢</div>
        <div class="nuo-checkbox-list">
          <nuo-checkbox label="第一饭堂" ></nuo-checkbox>
          <nuo-checkbox label="第二饭堂" ></nuo-checkbox>
          <nuo-checkbox label="第三饭堂" ></nuo-checkbox>
          <nuo-checkbox label="第四饭堂" ></nuo-checkbox>
        </div>

      </div>
      <div class="nuo-timeline-type3"   v-if="false" >
        <div class="title">早上六点去一次行知湖</div>
        <div class="text">我同你讲，早上6点的行知湖。。。。。。。。。好吧，我也不知道会怎么样</div>
        <div class="time">签到时间:3232</div>
        <div class="btn disable">完成</div>
        </div>
      <div class="nuo-timeline-type4"    v-if="false" >
        <div class="title">坚持跑步，坚持30无间断</div>
        <div class="text">跑跑步总是对身体是好的</div>
        <div class="status">今天未签到</div>
        <div class="btn ">签到</div>
      </div>
      <div class="nuo-timeline-type5"    >
        <div class="title">了解校史</div>
        <div class="text">你知道吗</div>
        <div class="question">白宫的原名是什么?</div>
        <input type="text">
        </div>
    </div>

  </div>
</template>
<style scoped src="./nuo-timeline.scss" lang="scss">

</style>
<script>
  import NuoCheckbox from 'components/checkbox/checkbox.vue'

  export default{
    props: {
      data: {}
    },
    data(){
      return {}
    },
    components: {
      NuoCheckbox
    }
  }
</script>
