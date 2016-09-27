<template>
    <div class="label">
      <div class="label-header">
        <!--有槽必吐-->
        <div class="label-header-cao" v-if="$route.query.ext_type==='10'">
          <span @click="back">《 有槽必吐排行榜—校内</span>
        </div>
        <div class="label-header-cao" v-if="$route.query.ext_type==='11'">
          <span @click="back">《 有槽必吐排行榜—校外</span>
        </div>
        <!--有问必答-->
        <div class="label-header-answer" v-if="$route.query.ext_type==='12'">
          <span @click="back">《 有问必答排行榜</span>
        </div>
      </div>
      <div class="label-body" >
        <!--排行榜列表-->
        <div v-for="list in lists">
          <div class="label-body-list" :style="{opacity: countOpacity($index)}" @click="onClick($route.query.ext_type,list.id)">
            <!--排行榜次序-->
            <div class="body-list-number" :style="{ 'background-color':number_color($index), 'color':num_color($index)}"> {{$index+1}}</div>
            <div class="body-list" :style="{color:list_color()}">
              <!--标签名字-->
              <div class="list-name">#{{list.name}}</div>
              <div class="list-dian">·</div>
              <!--评论数-->
              <div class="list-comment_num">{{list.size}}</div>
            </div>
          </div>
        </div>

      </div>

    </div>
</template>
<style >
  
  .label{

  }
  .label-header{
    width: 100%;
    color: #ffffff;
    position: fixed;
    top:0;
    z-index: 2;
  }
  .label-header-cao{
    height: 60px;
    display: flex;
    align-items: center;
    background: linear-gradient(to bottom, #F91C88 ,#FF5FAD);
    padding-left: 15px;
  }
  .label-header-answer{
    height: 60px;
    display: flex;
    align-items: center;
    background: linear-gradient(to bottom, #0274FF ,#5FA7FF);
    padding-left: 15px ;
  }
  .label-body{
    margin-top: 60px;
  }
  .label-body-list{
    height: 60px;
    display: flex;
    justify-content: flex-start;
    align-items: center;
  }
  .body-list-number{
    height: 30px;
    width: 30px;
    /*background-color: #0274FF;*/
    border-radius:100%;
    margin-left: 20px ;
    margin-right: 15px;
    flex: none;
    line-height: 30px;
    text-align: center;
  }
  .body-list{
    height: 60px;
    display: flex;
    border-bottom: solid 1px #E3E3E3;
    align-items: center;
    overflow:hidden;
    flex-shrink: 1;
    flex-grow: 1;
    justify-content: flex-start;
  }
  .list-name{
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
    flex-shrink: 1;
    flex-grow:0;
  }
  .list-dian{
    margin: 0 7px;

  }
  .list-comment_num{
    margin-right: 20px;
  }



</style>
<script type="text/ecmascript-6">

    export default{
        data(){
            return{
              lists:[]

            }
        },
        components:{

        },
      route:{
        data(){
          var token="4121581213c1605a1db4872d7cca6eed1b41259bffd8066d9573783b07214d6f";
          return this.$request
            .get('http://115.28.67.181:8080/ask_reply/labels')
            .query({token:token})
            .query({
              ext_type:this.$route.query.ext_type
            })
            .then(this.$api.checkResult)
            .then((data=>{
              this.lists=data.items
              }))
        }
      },
      methods:{
        back(){
          window.history.back()
        },
          countOpacity:function (index) {
            if(index<5){
              return  1-0.12*index;
            }
            else{
              return 0.4;
            }
          },
        number_color(index) {
          if(index<5){
            if(this.$route.query.ext_type==='10'||this.$route.query.ext_type==='11'){
              return "#F91C88"
            }
            else if(this.$route.query.ext_type==='12'){
              return "#0274FF"
            }
          }
          else{
            return "#ffffff"
          }

        },
        num_color(index){
          if(index<5){
            return "#ffffff"
          }
          else{
            if(this.$route.query.ext_type==='10'||this.$route.query.ext_type==='11'){
              return "#F91C88"
            }
            else if(this.$route.query.ext_type==='12'){
              return  "#0274FF"
            }
          }
        },
        list_color(){
          if(this.$route.query.ext_type==='10'||this.$route.query.ext_type==='11'){
            return "#F91C88"
          }
          else if(this.$route.query.ext_type==='12'){
            return "#0274FF"
          }
        },
        onClick(ext_type,id){
          if(ext_type==10){
            this.$router.go('/cao-topic?ext_type=10&label_id='+id)
          }
          else if(ext_type==11){
            this.$router.go('/cao-topic?ext_type=11&label_id='+id)
          }
          else if(ext_type==12)
            this.$router.go('/answer-topic?label_id='+id)

        }

      },

    }
</script>
