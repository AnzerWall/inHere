<template>
  <div class="nuo-create-wrapper">
    <noti v-ref:noti></noti>

    <div class="nuo-create-head">
      <!--:style="{ 'background-color': 'rgba(0,0,0,0)'}"-->
      <div class="nuo-create-head-left" @click="back">《 有诺必行</div>
      <div class="nuo-create-head-right">
        <confirm-icon style="fill:white;" @click="createNuo"></confirm-icon>
      </div>
    </div>
    <div class="nuo-create-head-placeholder">

    </div>
    <div class="nuo-info">
      <input class="nuo-title" v-model="title" placeholder="目标标题">
      <div class="textarea-wrapper"  >
        <textarea class="nuo-text" rows="4" v-model="text" placeholder="目标描述"></textarea>

      </div>
    </div>
    <div :class="{'target-list':target_list.length!=0}">
      <target-input  v-for="item in target_list" :index="$index" :type_data.sync="item.type_data"  :type.sync="item.type" :title.sync="item.title"  :text.sync="item.text"
                     @up="up($index)"
                     @down="down($index)"
                     @del="remove($index)"></target-input>

    </div>
    <selector :show.sync="add_menu_show" @select="addTarget" :tags="target_type_list" :editable="false"></selector>
    <div class="add-btn" @click="showAddMenu">
      <add-icon></add-icon>
    </div>
  </div>
</template>
<style scoped src="./create.scss" lang="scss">

</style>
<script type="text/ecmascript-6">
  import AddIcon from 'svg/nuo/nuo_add.vue'
  import Selector from 'components/publish/publish-tag-editor.vue'
  import Noti from 'components/noti.vue'

  import ConfirmIcon from 'svg/common/Confirm.vue'
  import TargetInput from 'components/target-input/target-input.vue'
  import {token, login_state, is_login} from '../../vuex/getters.js'

  import _ from 'lodash'
  export default{
    data(){
      return {
        add_menu_show: false,
        title: "",
        text: "",
        target_list: [],
        target_type_list: [{
          name: "数量累计型",
          value: 1
        }, {
          name: "完成型",
          value: 2
        }, {
          name: "特定时间型",
          value: 3
        }, {
          name: "每日签到型",
          value: 4
        }, {
          name: "回答问题型",
          value: 5
        }]
      }
    },
    methods: {
      back(){
        window.history.back();
      },
      createNuo(){
        for(let index in this.target_list){
          let target=this.target_list[index];
          if(!target.title||!target.text){
            this.$refs.noti.warning(`[目标${index+1}]标题或者描述不能为空`,{timeout:1000});
            return;
          }
          if(target.type===1){
              if(!_.isNumber(target.type_data.total_count)||target.type_data.total_count>10000||target.type_data.total_count<1){
                this.$refs.noti.warning(`[目标${index+1}]总数量不合法`,{timeout:1000});
                return;
              }
          }
          if(target.type===2){
            if(target.type_data.todo_list.length<1){
              this.$refs.noti.warning(`[目标${index+1}]要做的事情不能为空列表`,{timeout:1000});
              return;
            }
          }
          if(target.type===3){
            if(_.isEmpty(target.type_data)){
              this.$refs.noti.warning(`[目标${index+1}]至少需要选择中时间限制`,{timeout:1000});
              return;
            }
          }
          if(target.type===4){
            if(!_.isNumber(target.type_data.sign_in_total)||target.type_data.sign_in_total>10000||target.type_data.sign_in_total<1){
              this.$refs.noti.warning(`[目标${index+1}]签到天数不合法`,{timeout:1000});
              return;

            }
          }
          if(target.type===5){
            if(!target.type_data.question||!target.type_data.answer){
              this.$refs.noti.warning(`[目标${index+1}]问题和回答不能为空`,{timeout:1000});
              return;

            }
          }
        }

        this.$request
          .post(`${this.$api.node_api_base}/nuo`)
          .query({token:this.token})
          .send({
            title:this.title,
            text:this.text,
            target_list:this.target_list.map((item)=>({type:item.type,title:item.title,text:item.text,...item.type_data})),

          })
          .then(this.$api.checkResult)
          .then(()=>{
            window.history.back();
          })
          .catch(function(e){
            if (e.type === 'API_ERROR') {//判断是api访问出错还是其他错，仅限被checkResult处理过。。详见checkResult。。
              if (e.code === 23333) {//根据code判断出错类型,比如未登录时候跳转啊
                return this.$refs.noti.warning(`参数验证失败`,{timeout:1000});//这里以及后边的return是为了结束函数。。。仅此而已 ，常用技巧  : )
              } else if (e.code === 401) {
                return this.$router.go({
                  path: '/login',
                  query: {
                    __ref: this.$route.path//告诉login页面要跳转回来的页面
                  }
                });
              } else {
                return this.$refs.noti.warning(`与服务器通讯失败:${e.message}`,{timeout:1000})
              }
            } else {
              console.error(e.stack||e);
              return this.$refs.noti.warning(`未知错误:${e.message}`,{timeout:1000})
            }
          })
      },
      showAddMenu(){
        this.add_menu_show = !this.add_menu_show;
      },
      addTarget(type){
        this.target_list.push({
          title: "",
          text: "",
          type: type,
          type_data: {}
        });
      },
      remove(index){
        this.target_list.splice(index,1);
      },
      up(index){
        console.log('up',index,this.target_list.length);

        if(index!=0){
          let a= this.target_list[index];
          let b= this.target_list[index-1];
          this.target_list.$set(index,b);
          this.target_list.$set(index-1,a);

        }

      },
      down(index){
        console.log('down',index,this.target_list.length);
        if(index!= this.target_list.length-1){
          let a= this.target_list[index];
          let b= this.target_list[index+1];
          this.target_list.$set(index,b);
          this.target_list.$set(index+1,a);

        }




      }

    },
    components: {ConfirmIcon, TargetInput, AddIcon, Selector,Noti},
    vuex: {
      getters: {
        token
      }
    }
  }
</script>
