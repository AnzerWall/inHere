<template>
  <div class="nuo-create-wrapper">
    <div class="nuo-create-head">
      <!--:style="{ 'background-color': 'rgba(0,0,0,0)'}"-->
      <div class="nuo-create-head-left">《 有诺必行</div>
      <div class="nuo-create-head-right">
        <confirm-icon style="fill:white;"></confirm-icon>
      </div>
    </div>
    <div class="nuo-create-head-placeholder">

    </div>
    <div class="nuo-info">
      <input class="nuo-title">
      <div class="textarea-wrapper">
        <textarea class="nuo-text" rows="4"></textarea>

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
<script>
  import AddIcon from 'svg/nuo/nuo_add.vue'
  import Selector from 'components/publish/publish-tag-editor.vue'

  import ConfirmIcon from 'svg/common/Confirm.vue'
  import TargetInput from 'components/target-input/target-input.vue'
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
