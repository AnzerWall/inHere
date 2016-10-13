<template>
  <div class="profile-wrapper">
<!--112 8-->
    <div class="profile-head-wrapper">
      <div class="profile-head">
        <div class="text">
          {{user_id}} / {{school}}
        </div>
        <div class="icon" @click="clickMenu()"><menu-icon style="fill:#ccc"></menu-icon></div>
      </div>
    </div>
    <div class="profile-content">
      <div class="profile-tools-inner" :style="{'width': grid_field_width}">
        <div class="grid"  v-for="item in tools" :style="{'background-image': 'url('+ item.svg.src+')',width:grid_width,height:grid_width}" @click="clickTool(item)">
            <div class="title" :style="{color:item.color}">{{item.title}}</div>
        </div>

      </div>
      <div class="profile-timeline-wrapper">

      </div>
    </div>
    <menu v-ref:menu></menu>
    <noti v-ref:noti></noti>
  </div>
</template>
<style scoped>
  .profile-wrapper{
    display: flex;
    /*height: 100%;*/
    width: 100%;
    flex-direction: column;
    position: relative;
  }
  .profile-wrapper .profile-head-wrapper{
    flex-shrink: 0;
    flex-grow: 0;
    height: 60px;
    width: 100%;
    position: relative;
  }
  .profile-wrapper .profile-head-wrapper .profile-head{
    top: 0;
    left: 0;
    height: 60px;
    width: 100%;
    position: fixed;
    border-bottom: solid 1px rgba(213,213,213,.5);
    align-items: center;
    justify-content: space-between;
    background-color: #FAFAFA;
    z-index: 100;
    display: flex;


  }
  .profile-wrapper .profile-head-wrapper .profile-head .text{
    font-weight: bold;
    margin-left: 15px;;
  }
  .profile-wrapper .profile-head-wrapper .profile-head .icon{
    margin-right: 15px;
  }
  .profile-wrapper .profile-content{
    flex-shrink: 1;
    flex-grow: 1;
    height: 100%;
    width: 100%;
    display:flex;
    justify-content: center;
    /*text-align: center;*/
    position: relative;
    overflow: auto;
  }
  .profile-wrapper .profile-content .profile-tools-inner {
      /*display: inline-block;*/
      /*text-align: left;*/
       /*padding: 0 auto;*/
      padding: 10px 0;
      width: 100%;
      display: flex;
      flex-wrap: wrap;


    /*justify-content: center;*/


  }
  .profile-wrapper .profile-content .profile-timeline-wrapper{

  }
  .profile-wrapper .profile-content .profile-tools-inner .grid{
    /*display: inline-block;*/
    border-radius:4px;
    border:solid 1px rgba(213,213,213,.5);
    width:112px;
    margin :4px ;
    height:112px;
    position: relative;
    background-repeat: no-repeat;
    background-position-y: 40%;
    background-position-x: center;

    text-align: center;
    background-size:40% 40%;
    /*background-image: url('../../svg/profile/Fankajilu.svg');*/

  }
  .profile-wrapper .profile-content .profile-tools-inner .grid .title{
    position: absolute;
    left:50%;
    bottom:5px;
    font-size:1vw;
    white-space: nowrap;
    transform: translateX(-50%);
  }


</style>
<script type="text/ecmascript-6">
  import {token,login_state,is_login,school,user_id,tool_list} from '../../vuex/getters.js'
  import {fetchToolList} from '../../vuex/actions/tool-action.js'
  import MenuIcon from 'svg/common/Menu.vue'
  import noti from '../../components/noti.vue'
  import menu from '../../components/menu.vue'

  export default{
    route: {
      data(){
        var token = this.token;
        return this.$request
          .get(`${this.$api.url_base}/tools?token=${token}`)
          .then(this.$api.checkResult)//处理code等信息，返回data
          .then((data)=> {
            //处理数据
            this.tools = data.items;
          })
          .catch((e)=> {
            if (e.type === 'API_ERROR') {//判断是api访问出错还是其他错，仅限被checkResult处理过。。详见checkResult。。
              if (e.code === 23333) {//根据code判断出错类型,比如未登录时候跳转啊
                return this.$refs.noti.warning(`参数验证失败`,{
                  timeout:1500
                })//这里以及后边的return是为了结束函数。。。仅此而已 ，常用技巧  : )
              } else if (e.code === 401) {
                return this.$router.go({
                  path: '/login',
                  query: {
                    __ref: this.$route.path//告诉login页面要跳转回来的页面
                  }
                });
              } else {
                return this.$refs.noti.warning(`与服务器通讯失败:${e.message}`,{
                  timeout:1500
                })
              }
            } else {
              console.error(e.stack||e);
              return this.$refs.noti.warning(`网络出错啦:${e.message}`,{
                timeout:2000
              })
            }
            //后续显示重试按钮
          })
      }
    },
    components:{
      MenuIcon,menu,noti
    },
    vuex: {
      actions: {
        fetchToolList
      },
      getters: {
        login_state,
        token,
        is_login,
        school,
        user_id,
        tool_list
      }
    },

    data(){
     return {
       grid_field_width:this.getGridFieldWidth(),
       grid_width:this.getGridWidth(),
//       username:"小泽",
//       school:"肇庆学院",
       tools:[{
         url:"http://m.kuaidi100.com/",
         svg: {
           src: "/static/image/profile/Fankajilu.svg"
         },
         title:"饭卡记录",
         color:"#FF3A3A"
       },{
         url:"http://m.kuaidi100.com/",
         svg: {
           src: "/static/image/profile/Kuaidichaxun.svg"
         },
         title:"快递查询",
         color:"#16A82E"
       },{
         url:"http://m.kuaidi100.com/",
         svg: {
           src: "/static/image/profile/Qimochengji.svg"
         },
         title:"期末成绩",
         color:"#4F8794"
       },{
         url:"http://m.kuaidi100.com/",
         svg: {
           src: "/static/image/profile/Shuifeichaxun.svg"
         },
         title:"水费查询",
         color:"#0098FF"
       },{
         url:"http://m.kuaidi100.com/",
         svg: {
           src: "/static/image/profile/siliujichengji.svg"
         },
         title:"四六级成绩",
         color:"#FFA600"
       },{
         url:"http://m.kuaidi100.com/",
         svg: {
           src: "/static/image/profile/Wodehuida.svg"
         },
         title:"我的回答",
         color:"#09CE88"
       },{
         url:"http://m.kuaidi100.com/",
         svg: {
           src: "/static/image/profile/Wodemubiao.svg"
         },
         title:"我的目标",
         color:"#7300FF"
       }]
     }
    },
    ready(){
      this.fetchToolList(this.token,this.user_id);
      window.onresize=()=>{
        this.grid_field_width=this.getGridFieldWidth();
        this.grid_width=this.getGridWidth();
      }
    },
    methods:{
      clickMenu(){
        var token = this.token;
        var is_login = this.is_login;
        var self = this;
        var api_base = this.$api.url_base;
        this.$refs.menu.show({
          title:'菜单',
          btns:[{
            title:'关于',
            event(){
              alert('关于');
              return true;
            }
          },{
            title:'登出',
            event(){

              if (is_login == true){
                self.$request
                  .del(`${api_base}/logout?token=${token}`)
                  .end(function (err, res) {
                    console.log("123456");
                    if (err){
                      self.$refs.noti.warning(`出了点小问题,未能成功登出`,{
                        timeout:1500
                      })
                    } else if (res.body.code == 200 || res.body.code == 401){
                      self.$router.go('/login');
                      return true;
                    } else {
                      self.$refs.noti.warning(`出了点小问题,未能成功登出`,{
                        timeout:1500
                      })
                    }
                  })
              } else {
                self.$router.go('/login');
              }
              return true;
            }
          }]
        })
      },
      clickTool(item){
        this.$router.go({
          path:'/tool',
          query:{
            title:item.title,
            url:item.url
          }
        })
      },
      getGridFieldWidth(){
            let body_width=document.body.clientWidth;
            let num=Math.floor(body_width/122);
            if(num<=2)return Math.floor(body_width/3)*3;
            return (Math.floor(body_width/122)*122)+'px'

      },
      getGridWidth(){
        let body_width=document.body.clientWidth;
        let num=Math.floor(body_width/122);

        if(num<=2)return (Math.floor(body_width/3)-10)+'px';
        else return '112px'
      }
    }
  }
</script>

