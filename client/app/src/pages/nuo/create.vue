<template>
  <div class="nuo-create-wrapper">
    <div class="nuo-create-head">
      <!--:style="{ 'background-color': 'rgba(0,0,0,0)'}"-->
      <div class="nuo-create-head-left">《 有诺必行</div>
      <div class="nuo-create-head-right">
        <confirm-icon style="fill:white;" @click="createNuo"></confirm-icon>
      </div>
    </div>
    <div class="nuo-create-head-placeholder">

    </div>
    <div class="nuo-info">
      <input class="nuo-title" v-model="title">
      <div class="textarea-wrapper"  >
        <textarea class="nuo-text" rows="4" v-model="text"></textarea>

      </div>
    </div>
    <div :class="{'target-list':target_list.length!=0}">
      <target-input  v-for="item in target_list" :index="$index" :type_data.sync="item.type_data"  :type.sync="item.type" :title.sync="item.title"  :text.sync="item.text"></target-input>

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

  import ConfirmIcon from 'svg/common/Confirm.vue'
  import TargetInput from 'components/target-input/target-input.vue'
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
      createNuo(){
        for(let index in this.target_list){
          let target=this.target_list[index];
          if(!target.title||!target.text){
            console.error(`[目标${index+1}]标题或者描述不能为空`);
            return;
          }
          if(target.type===1){
              if(!_.isNumber(target.type_data.total_count)||target.type_data.total_count>10000||target.type_data.total_count<1){
                console.error(`[目标${index+1}]总数量不合法`);
                return;
              }

          }
          if(target.type===2){
            if(target.type_data.todo_list.length<1){
              console.error(`[目标${index+1}]要做的事情不能为空列表`);
              return;
            }
          }
          if(target.type===3){
            if(_.isEmpty(target.type_data)){
              console.error(`[目标${index+1}]至少需要选择中时间限制`);
              return;
            }
          }
          if(target.type===4){
            if(!_.isNumber(target.type_data.sign_in_total)||target.type_data.sign_in_total>10000||target.type_data.sign_in_total<1){
              console.error(`[目标${index+1}]签到天数不合法`);
              return;

            }
          }
          if(target.type===5){
            if(!target.type_data.question||!target.type_data.answer){
              console.error(`[目标${index+1}]问题和回答不能为空`);
              return;

            }
          }
        }

        this.$request
          .post(`${this.$api.node_api_base}/nuo`)
          .send({
            title:this.title,
            text:this.text,
            target_list:this.target_list.map((item)=>({type:item.type,title:item.title,text:item.text,...item.type_data})),

          })
          .then(this.$api.checkResult)
          .then(function(d){
            console.log(d);
          })
          .catch(function(e){
            console.log(e);
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
      }

    },
    components: {ConfirmIcon, TargetInput, AddIcon, Selector}
  }
</script>
