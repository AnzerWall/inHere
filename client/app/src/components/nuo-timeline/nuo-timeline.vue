<template>
  <div class="nuo-timeline-wrapper">
    <div class="nuo-timeline-content">
      <div class="nuo-timeline-timeline-wrapper">
        <div class="nuo-timeline-timeline-circle" :class="{padding:padding,doing:doing}">
          {{data.index+1}}
        </div>
        <div class="nuo-timeline-timeline-line">

        </div>
      </div>
      <div class="nuo-timeline-type1" v-if="data.type===1">
        <div class="title">{{data.text}}</div>
        <div class="text" v-if="!data.completed&&!padding">{{data.title}}</div>
        <div class="process" v-if="!data.completed&&!padding">目标数量:{{data.type_data.total_count}}
          已完成:{{data.user_data.total_count||0}}
        </div>
        <div class="btn" v-if="!data.completed" @click="count">+ 1</div>
        <div v-else class="placehoder-area" v-if="!isFinal">
        </div>
      </div>
      <div class="nuo-timeline-type2" v-if="data.type===2">
        <div class="title">{{data.text}}</div>
        <div class="text" v-if="!data.completed&&!padding">{{data.title}}</div>
        <div class="nuo-checkbox-list" v-if="!data.completed&&!padding">
          <nuo-checkbox v-for="item in data.type_data.todo_list" :label="item" :no-padding-tb="true"
                        @check="do(item)"
                        :checked="data.user_data.todo_list&&data.user_data.todo_list.indexOf(item)!=-1"></nuo-checkbox>
        </div>
        <div v-else class="placehoder-area" v-if="!isFinal">
        </div>
      </div>
      <div class="nuo-timeline-type3" v-if="data.type===3">
        <div class="title">{{data.text}}</div>
        <div class="text" v-if="!data.completed&&!padding">{{data.title}}</div>
        <div class="time" v-if="!data.completed&&!padding">{{parseTimeLimitDate(data.type_data)}}</div>
        <div class="btn " v-if="!data.completed&&!padding" :class="{disable:!canComplete}" @click="complete">完 成</div>
        <div v-else class="placehoder-area" v-if="!isFinal">
        </div>
      </div>
      <div class="nuo-timeline-type4" v-if="data.type===4">
        <div class="title">{{data.text}}</div>
        <div class="text" v-if="!data.completed&&!padding">{{data.title}}</div>
        <div class="status" v-if="!data.completed&&!padding">{{isSignInText}}</div>
        <div class="btn " v-if="!data.completed&&!padding" :class="{disable:!canSignIn}" @click="signIn">签 到</div>
        <div v-else class="placehoder-area" v-if="!isFinal">
        </div>
      </div>
      <div class="nuo-timeline-type5" v-if="data.type===5">
        <div class="title">{{data.text}}</div>
        <div class="text" v-if="!data.completed&&!padding">{{data.title}}</div>
        <div class="question" v-if="!data.completed&&!padding">{{data.type_data.question}}</div>
        <input type="text" v-if="!data.completed&&!padding" @keydown.enter="answer" v-model="answer_text">
        <div v-else class="placehoder-area" v-if="!isFinal">
        </div>
      </div>
    </div>


  </div>
</template>
<style scoped src="./nuo-timeline.scss" lang="scss">

</style>
<script type="text/ecmascript-6">
  import NuoCheckbox from 'components/checkbox/checkbox.vue'
  import moment from 'moment'
  import {token, login_state, is_login} from '../../vuex/getters.js'

  export default{
    vuex: {
      getters: {
        token
      }
    },
    props: {

      data: {
        type: Object
      },
      doing: {
        type: Boolean,
        default: false
      },
      padding: {
        type: Boolean,
        default: false
      },
      isFinal: {
        type: Boolean,
        default: false
      },
      nuo_id: {}
    },
    data(){
      return {
        doingAction: false,
        answer_text:""
      }
    },
    methods: {
      errorHandle(e){
        this.doingAction=false;
        console.error(e.stack||e);
        if (e.code === 401) {
          return this.$router.go({
            path: '/login',
            query: {
              __ref: this.$route.path//告诉login页面要跳转回来的页面
            }
          });
        }
      },
      do(item){
        let token = this.token;
        let url_base = this.$api.node_api_base;
        this.doingAction = true;
        this.$request
          .post(`${url_base}/nuo/${this.nuo_id}/target/${this.data.index}/do`)
          .query({token: token})
          .send({todo:item})
          .then(this.$api.checkResult)
          .then(()=> {
            this.doingAction=false;
//              console.log('ok');
            this.$emit('action')

          })
          .catch(this.errorHandle)
      },
      count(){
        if (!this.doingAction) {
          let token = this.token;
          let url_base = this.$api.node_api_base;
          this.doingAction = true;
          this.$request
            .post(`${url_base}/nuo/${this.nuo_id}/target/${this.data.index}/count`)
            .query({token: token})
            .then(this.$api.checkResult)
            .then(()=> {
              this.doingAction=false;
              this.$emit('action')
            })
            .catch(this.errorHandle)
        }
      },
      complete(){
        if (!this.doingAction&&this.canComplete) {
          let token = this.token;
          let url_base = this.$api.node_api_base;
          this.doingAction = true;
          this.$request
            .post(`${url_base}/nuo/${this.nuo_id}/target/${this.data.index}/complete`)
            .query({token: token})
            .then(this.$api.checkResult)
            .then(()=> {
              this.doingAction=false;
              this.$emit('action')
            })
            .catch(this.errorHandle)
        }
      },
      signIn(){
        if (!this.doingAction&&this.canSignIn) {
          let token = this.token;
          let url_base = this.$api.node_api_base;
          this.doingAction = true;
          this.$request
            .post(`${url_base}/nuo/${this.nuo_id}/target/${this.data.index}/sign-in`)
            .query({token: token})
            .then(this.$api.checkResult)
            .then(()=> {
              this.doingAction=false;
              this.$emit('action')
            })
            .catch(this.errorHandle)
        }
      },
      answer(){
        let answer=this.answer_text;
        if (!this.doingAction) {
          let token = this.token;
          let url_base = this.$api.node_api_base;
          this.doingAction = true;
          this.$request
            .post(`${url_base}/nuo/${this.nuo_id}/target/${this.data.index}/answer`)
            .query({token: token})
            .send({answer})
            .then(this.$api.checkResult)
            .then(()=> {
              this.doingAction=false;
              this.$emit('action')
              this.answer_text="";
            })
            .catch(e=>{
              this.doingAction=false;
              console.error(e.stack||e);
              if (e.code === 401) {
                return this.$router.go({
                  path: '/login',
                  query: {
                    __ref: this.$route.path//告诉login页面要跳转回来的页面
                  }
                });
              }
              this.answer_text="";
            })
        }
      },
      parseTimeLimitDate(data){
        let list1 = [];
        if (data.specific_month != 0)list1.push(data.specific_month + '月份');
        if (data.specific_day != 0)list1.push(data.specific_day + '号');

        let list2 = [];
        if (data.specific_week_day != 0)list2.push('星期' + data.specific_week_day);
        if (data.hour_min != 0)list2.push(`最早${data.hour_min}时`);
        if (data.hour_max != 0)list2.push(`最晚${data.hour_max}时`);
        return [list1.join('的'), list2.join('')].join(' ')

      },


    },
    computed:{
      isSignInText(){
        let target=this.data;

        let now = moment('2016-10-7 12:40','YYYY-MM-DD HH:mm');
        if (target.user_data.last_sign_in_time !== undefined) {
          let last_time = moment(target.user_data.last_sign_in_time);
          let days_diff = Math.abs(moment([now.year(), now.month(), now.date()]).diff([last_time.year(), last_time.month(), last_time.date()], 'days'));
          //console.log(days_diff,hour_diff,now.year(),now.month(),now.date(),last_time.year(),last_time.month(),last_time.date());
          let hour_diff=Math.abs(now.diff(last_time,'hour'));
          if(target.type_data.sign_in_continuously===true){
            if(hour_diff>24){
              return  '已断签';
            }

          }
          if (days_diff < 1)
            return "今天已签到";
        }
        return '今天未签到'
      },
      canSignIn(){
        let target=this.data;

        let now = moment('2016-10-7 12:40','YYYY-MM-DD HH:mm');
        console.log(now);
        let hour = now.hour();
        if (target.type_data.hour_min !== 0) {
          if (hour < target.type_data.hour_min) {
            return false;
          }
        }
        if (target.type_data.hour_max !== 0) {
          if (hour > target.type_data.hour_max) {
            return false;

          }
        }
        if (target.user_data.last_sign_in_time !== undefined) {
          let last_time = moment(target.user_data.last_sign_in_time);
          let hour_diff = Math.abs(now.diff(last_time, 'hour'));
          let days_diff = Math.abs(moment([now.year(), now.month(), now.date()]).diff([last_time.year(), last_time.month(), last_time.date()], 'days'));
          console.log(last_time,days_diff,hour_diff,now.year(),now.month(),now.date(),last_time.year(),last_time.month(),last_time.date());
          if (days_diff < 1)
            return false;
          if (target.type_data.sign_in_continuously === true) {
            if (hour_diff > 24) {
              return false;

            }

          }
        }
        return true;
        //cons
      },
      canComplete(){
        let target=this.data;
        let now = moment();
//        console.log(target,now.month(),now.hour(),now.date(),now.weekday());


        let hour = now.hour();
        if (target.type_data.hour_min !== 0) {
          if (hour < target.type_data.hour_min) {
//            console.log(1);
            return false;
          }
        }
        if (target.type_data.hour_max !== 0) {
          if (hour > target.type_data.hour_max) {
//            console.log(2);

            return false;
          }
        }
        if (target.type_data.specific_month !== 0) {
          if (now.month() != target.type_data.specific_month) {
//            console.log/(3);

            return false;
          }
        }
        if (target.type_data.specific_day !== 0) {
          if (now.date() != target.type_data.specific_day) {
//            console.log(4);

            return false;
          }
        }
        if (target.type_data.specific_week_day !== 0) {
          if (now.weekday() + 1 != target.type_data.specific_week_day) {
//            console.log(5);

            return false;
          }
        }
        return true;
      }
    },
    components: {
      NuoCheckbox
    }
  }
</script>
