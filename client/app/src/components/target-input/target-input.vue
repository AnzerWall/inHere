<template>
  <div class="target-input-wrapper">
    <div class="target-input-head">
      <span class="target-type" v-if="type===1"> {{index+1}}.数量累计型</span>
      <span class="target-type" v-if="type===2"> {{index+1}}.完成型</span>
      <span class="target-type" v-if="type===3"> {{index+1}}.特定时间型</span>
      <span class="target-type" v-if="type===4">{{index+1}}.每日签到型</span>
      <span class="target-type" v-if="type===5"> {{index+1}}.回答问题型</span>
      <div class="icon-group">
        <span @click="up" class="icon" style="transform:translateY(-1px)">
          <move-icon></move-icon>
        </span>
        <span @click="down" class="icon right-move-icon" style="transform: rotate(180deg) translateY(-2px) ">
          <move-icon></move-icon>
        </span>


      </div>

    </div>
    <div class="target-info-wrapper">
      <div class="target-info">
        <input class="target-title" v-model="title">
        <div class="textarea-wrapper">
          <textarea class="target-text" v-model="text" rows="4"></textarea>

        </div>
      </div>

      <div v-if="type===1">
        <div class="input-cell">
          <div class="input-cell-title">数量</div>
          <div class="input-cell-input"><input v-model="count"></div>
        </div>
      </div>
      <div v-if="type===2">
        <div class="input-group list-input">
          <div class="group-left">
            <div class="left-cell" v-for="item in todos" track-by="$index">
              <div class="list-circle">{{$index+1}}</div>

            </div>
          </div>
          <div class="group-right">
            <div class="right-cell" v-for="item in todos" track-by="$index">

              <div class="list-selection">
                <input v-model="item">
                <del-icon class="icon" @click="delTodo($index)"></del-icon>
              </div>
            </div>
            <div class="add-btn-cell" @click="addTodo">
              <div class="add-btn-wrapper">
                <add-icon class="icon"></add-icon>
              </div>
            </div>
          </div>

        </div>
      </div>
      <div v-if="type===3">
        <div class="optional-input input-group">
          <div class="group-left">
            <div class="left-cell " :class="{disable:!specific_month_enable}">
              <checkbox :checked.sync="specific_month_enable"></checkbox>
            </div>
            <div class="left-cell" :class="{disable:!specific_day_enable}">
              <checkbox :checked.sync="specific_day_enable"></checkbox>
            </div>
            <div class="left-cell" :class="{disable:!specific_week_day_enable}">
              <checkbox :checked.sync="specific_week_day_enable"></checkbox>
            </div>
            <div class="left-cell" :class="{disable:!hour_min_enable}">
              <checkbox :checked.sync="hour_min_enable"></checkbox>
            </div>
            <div class="left-cell" :class="{disable:!hour_max_enable}">
              <checkbox :checked.sync="hour_max_enable"></checkbox>
            </div>

          </div>
          <div class="group-right">
            <div class="right-cell" :class="{disable:!specific_month_enable}">
              <div class="input-label">月份</div>
              <div class="input-field">
                <input readonly :value="specific_month_list[specific_month-1].name"
                       @click="specific_month_menu_show=!specific_month_menu_show,specific_month_enable=true">
                <selector :show.sync="specific_month_menu_show" :tag.sync="specific_month" :tags="specific_month_list"
                          :editable="false"></selector>
              </div>
            </div>
            <div class="right-cell" :class="{disable:!specific_day_enable}">
              <div class="input-label">日期</div>
              <div class="input-field">
                <input readonly :value="specific_day_list[specific_day-1].name"
                       @click="specific_day_menu_show=!specific_day_menu_show,specific_day_enable=true">
                <selector :show.sync="specific_day_menu_show" :tag.sync="specific_day" :tags="specific_day_list"
                          :editable="false"></selector>

              </div>
            </div>
            <div class="right-cell" :class="{disable:!specific_week_day_enable}">
              <div class="input-label">星期</div>
              <div class="input-field">
                <input readonly :value="specific_week_day_list[specific_week_day-1].name"
                       @click="specific_week_day_menu_show=!specific_week_day_menu_show,specific_week_day_enable=true">

                <selector :show.sync="specific_week_day_menu_show" :tag.sync="specific_week_day"
                          :tags="specific_week_day_list" :editable="false"></selector>
              </div>
            </div>
            <div class="right-cell" :class="{disable:!hour_min_enable}">
              <div class="input-label">最早不超过几点</div>
              <div class="input-field">
                <input readonly :value="hour_min_list[hour_min-1].name"
                       @click="hour_min_menu_show=!hour_min_menu_show,hour_min_enable=true">
                <selector :show.sync="hour_min_menu_show" :tag.sync="hour_min" :tags="hour_min_list"
                          :editable="false"></selector>

              </div>
            </div>
            <div class="right-cell" :class="{disable:!hour_max_enable}">
              <div class="input-label">最晚不超过几点</div>
              <div class="input-field">
                <input readonly :value="hour_max_list[hour_max-1].name"
                       @click="hour_max_menu_show=!hour_max_menu_show,hour_max_enable=true">
                <selector :show.sync="hour_max_menu_show" :tag.sync="hour_max" :tags="hour_max_list"
                          :editable="false"></selector>

              </div>
            </div>
          </div>
        </div>

      </div>

      <div v-if="type===4">

        <div class="input-cell">
          <div class="input-cell-title">天数</div>
          <div class="input-cell-input"><input v-model="sign_in_total"></div>
        </div>
        <div class="input-group optional-input ">
          <div class="group-left">
            <div class="left-cell" :class="{disable:!sign_in_continuously}">
              <checkbox :checked.sync="sign_in_continuously"></checkbox>
            </div>
            <div class="left-cell" :class="{disable:!hour_min_enable}">
              <checkbox :checked.sync="hour_min_enable"></checkbox>
            </div>
            <div class="left-cell" :class="{disable:!hour_max_enable}">
              <checkbox :checked.sync="hour_max_enable"></checkbox>
            </div>
          </div>
          <div class="group-right">
            <div class="right-cell" :class="{disable:!sign_in_continuously}">
              <div class="input-label">是否要求连续签到</div>
              <!--<div class="input-field"><input readonly></div>-->
            </div>
            <div class="right-cell" :class="{disable:!hour_min_enable}">
              <div class="input-label">最早不超过几点</div>
              <div class="input-field">
                <input readonly :value="hour_min_list[hour_min-1].name"
                       @click="hour_min_menu_show=!hour_min_menu_show,hour_min_enable=true">
                <selector :show.sync="hour_min_menu_show" :tag.sync="hour_min" :tags="hour_min_list"
                          :editable="false"></selector>

              </div>
            </div>
            <div class="right-cell" :class="{disable:!hour_max_enable}">
              <div class="input-label">最晚不超过几点</div>
              <div class="input-field">
                <input readonly :value="hour_max_list[hour_max-1].name"
                       @click="hour_max_menu_show=!hour_max_menu_show,hour_max_enable=true">
                <selector :show.sync="hour_max_menu_show" :tag.sync="hour_max" :tags="hour_max_list"
                          :editable="false"></selector>

              </div>
            </div>
          </div>
        </div>
      </div>
      <div v-if="type===5" class="question-target">
        <div class="input-cell question-cell">
          <div class="input-cell-title">问题</div>
          <div class="input-cell-input"><input v-model="question"></div>
        </div>
        <div class="input-cell">
          <div class="input-cell-title">答案</div>
          <div class="input-cell-input"><input v-model="answer"></div>
        </div>
      </div>

    </div>

  </div>

</template>
<style scoped src="./target-input.scss" lang="scss">

</style>
<script type="text/ecmascript-6">
  import DelIcon from 'svg/nuo/nuo_del.vue'
  import AddIcon from 'svg/nuo/nuo_add.vue'
  import MoveIcon from 'svg/nuo/nuo_move.vue'
  import Checkbox from '../checkbox/checkbox.vue'
  import Selector from 'components/publish/publish-tag-editor.vue'
  import _ from 'lodash'
  export default{
    methods: {
      addTodo(){
        this.todos.push("");
      },
      delTodo(index){
        //  console.log(index);
        this.todos.splice(index, 1);
      },
      up(){
        this.$emit('up')
      },
      down(){
        this.$emit('down');
      }

    },
    ready(){
      switch (this.type) {
        case 1:
          this.type_data.total_count = this.count;
          break;
        case 2:
          this.type_data.todo_list = this.todos;
          break;
        case 3:
          this.type_data.specific_month=0;
          this.type_data.specific_day=0;

          this.type_data.specific_week_day=0;

          this.type_data.hour_min=0;
          this.type_data.hour_max=0;



          break;
        case 4:
          this.type_data.sign_in_total = this.sign_in_total;
          this.type_data.sign_in_continuously = this.sign_in_continuously;
          this.type_data.hour_min=0;
          this.type_data.hour_max=0;

          break;
        case 5:
          this.type_data.question = this.question;
          this.type_data.answer = this.answer;

          break;
      }
    },
    props: {
      index: {
        type: Number,
        default: 0
      },
      type: {
        type: Number,
        default: 4
      },
      title: {
        type: String,
        default: ""
      },
      text: {
        type: String,
        default: ""
      },
      type_data: {
        type: Object,
        default: ()=>({})
      }
    },
    data(){
      return {

        specific_month_enable: false,
        specific_month_list: _.range(1, 13).map(item=>({name: item + '月', value: item})),
        specific_month: 1,
        specific_month_menu_show: false,


        specific_day_enable: false,
        specific_day_list: _.range(1, 32).map(item=>({name: item + '号', value: item})),
        specific_day: 1,
        specific_day_menu_show: false,

        specific_week_day_enable: false,
        specific_week_day_list: _.range(1, 8).map(item=>({
          name: "星期" + ['', '一', '二', '三', '四', '五', '六', '日'][item],
          value: item
        })),
        specific_week_day: 1,
        specific_week_day_menu_show: false,


        hour_min_enable: false,
        hour_min_list: _.range(1, 25).map(item=>({name: item + '点后', value: item})),
        hour_min: 1,
        hour_min_menu_show: false,


        hour_max_enable: false,
        hour_max_list: _.range(1, 25).map(item=>({name: item + '点前', value: item})),
        hour_max: 1,
        hour_max_menu_show: false,

        count: 3,
        todos: ["吃饭睡觉打豆豆"],

        sign_in_total: 3,
        sign_in_continuously: true,
        question: "",
        answer: ""
      }
    },
    watch: {
      sign_in_continuously(){
        this.type_data.sign_in_continuously = this.sign_in_continuously;
      },
      sign_in_total(){
        this.type_data.sign_in_total = _.toNumber(this.sign_in_total);
      },
      question(){
        this.type_data.question = this.question;
      },
      answer(){
        this.type_data.answer = this.answer;

      },
      count(){
        this.type_data.total_count = _.toNumber(this.count);
      },
      todos(){
        this.type_data.todo_list = this.todos;
      },
      specific_month_enable(){
        if (this.specific_month_enable) {
          this.type_data.specific_month = this.specific_month;
        } else {
          this.type_data.specific_month=0;
        }
      },
      specific_month(){
        this.type_data.specific_month = this.specific_month;
      },
      specific_day_enable(){
        if (this.specific_day_enable) {
          this.type_data.specific_day = this.specific_day;
        } else {
           this.type_data.specific_day=0;
        }
      },
      specific_day(){
        this.type_data.specific_day = this.specific_day;

      },
      specific_week_day_enable(){
        if (this.specific_week_day_enable) {
          this.type_data.specific_week_day = this.specific_week_day;
        } else {
           this.type_data.specific_week_day=0;
        }
      },
      specific_week_day(){
        this.type_data.specific_week_day = this.specific_week_day;
      },
      hour_min_enable(){
        if (this.hour_min_enable) {
          this.type_data.hour_min = this.hour_min;
        } else {
           this.type_data.hour_min=0;
        }
      },
      hour_min(){
        this.type_data.hour_min = this.hour_min;

      },
      hour_max_enable(){
        if (this.hour_max_enable) {
          this.type_data.hour_max = this.hour_max;
        } else {
           this.type_data.hour_max=0;
        }
      },
      hour_max(){
        this.type_data.hour_max = this.hour_max;

      }
    },
    components: {DelIcon, AddIcon, Checkbox, Selector, MoveIcon}
  }
</script>
