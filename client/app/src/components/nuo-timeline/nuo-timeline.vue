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
      <div class="nuo-timeline-type1" v-if="data.type===1">
        <div class="title">{{data.text}}</div>
        <div v-if="data.status!=0">
          <div class="text">{{data.title}}</div>
          <div class="process">目标数量:{{data.type_data.total_count}} 已完成:{{data.user_data.total_count||0}}</div>
          <div class="btn">+1</div>
        </div>
        <div v-else class="placehoder-area">
          </div>
      </div>
      <div class="nuo-timeline-type2" v-if="data.type===2">
        <div class="title">{{data.text}}</div>
        <div v-if="data.status!=0">
          <div class="text">{{data.title}}</div>
          <div class="nuo-checkbox-list" v-for="item in data.type_data.todo_list">
            <nuo-checkbox :label="item" :checked="item.completed"></nuo-checkbox>
          </div>
        </div>
        <div v-else class="placehoder-area">
        </div>
      </div>
      <div class="nuo-timeline-type3" v-if="data.type===3">
        <div class="title">{{data.text}}</div>
        <div v-if="data.status!=0">
          <div class="text">{{data.title}}</div>
          <div class="time">签到时间:3232</div>
          <div class="btn disable">完成</div>
        </div>
        <div v-else class="placehoder-area">
        </div>
      </div>
      <div class="nuo-timeline-type4" v-if="data.type===4">
        <div class="title">{{data.text}}</div>
        <div v-if="data.status!=0">
          <div class="text">{{data.title}}</div>
          <div class="status">今天未签到</div>
          <div class="btn ">签到</div>
        </div>
        <div v-else class="placehoder-area">
        </div>
      </div>
      <div class="nuo-timeline-type5" v-if="data.type===5">
        <div class="title">{{data.text}}</div>
        <div v-if="data.status!=0">
          <div class="text">{{data.title}}</div>
          <div class="question">{{data.type_data.question}}</div>
          <input type="text">
        </div>
        <div v-else class="placehoder-area">
        </div>
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
