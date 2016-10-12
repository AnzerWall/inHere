<template>
  <div class="chat-wrapper">

    <div class="chat-head">
      <span class="chat-title" @click="back">《 {{data.title}}</span>
      <span class="chat-source">原文</span>
    </div>
    <div class="chat-head-placeholder"></div>
    <div class="chat-content-wrapper" v-el:wrapper>
      <div class="chat-content" v-el:content>
        <div v-for="item in list" class="chat-item-wrapper"  :class="{'fx-end':item.rev_user!=user_id} ">
          <div class="chat-time-name"
               v-if="$index===0||list[$index].rev_user!==list[$index-1].rev_user"><span class="chat-name" v-if="item.send_user!=user_id">{{item.rev_name}}</span>{{item.create_time|fromNowAndFormat}}
          </div>
          <div class="chat-item-content-wrapper"
               :style="{'flex-direction':item.rev_user!=user_id?'row-reverse':'row'}">

          <div class="chat-item-message-wrapper"    >
            <div class="chat-item-message"
                 :class="{'fx-end':item.rev_user!=user_id,'bg-e7':item.rev_user!=user_id,'bg-main-color':item.rev_user==user_id}">
              <span v-if="item.content_type===0"> {{item.content}}</span>
              <img v-if="item.content_type===1" :src="item.content">
            </div>

          </div>
            <div class="chat-item-status" v-if="item.status===1" >发送中</div>
            <div class="chat-item-status"  v-if="item.status===2" >发送失败</div>
          </div>
        </div>
        <div class="chat-typing" v-if="typing">输入中...</div>

      </div>
    </div>
    <div class="input-field-placeholder"></div>
    <div class="input-field-wrapper">

      <div class="image-input-field" >
        <image-uploader @change-image="sendImage"></image-uploader>
        <!--<input type="file" @change="sendImage" v-el:image-input class="hidden-file-input" >-->
          <image-icon></image-icon>
      </div>
      <div class="input-field">
        <textarea @input="textInputThrottled"  @keyup.enter="sendText" :change="textChange" :rows="textarea_rows" wrap="hard" placeholder="你们一群还魂尸......" v-model="text"></textarea>
      </div>
    </div>


<!---->


  </div>
</template>
<style src="./chat.scss" scoped lang="scss">

</style>
<script type="text/ecmascript-6">
  import {token,is_login,user_id} from '../../vuex/getters.js'
  import {pushUnreadChatList,recordSendingMessage,failSendingMessage,successSendingMessage,readChat} from '../../vuex/actions/chat-action.js'
  import {fromNowAndFormat}  from '../../filter/time.js'
  import Websocket from '../../util/websocket_helper.js'
  import ImageIcon from 'svg/common/Image.vue'
  import ImageUploader from 'components/image-uploader/image-uploader.vue'
  import _ from 'lodash'
  const TYPING_TIMEOUT=2000;
  export default{
    components:{
      ImageIcon,
      ImageUploader
    },
    filters: {
      fromNowAndFormat
    },
    sockets: {

      typing(data){

        let chat_user = this.$route.query.chat_user;
        let module_type = _.toNumber(this.$route.query.module_type);
        let module_id = _.toNumber(this.$route.query.module_id);
        if(data.chat_user===chat_user&&data.module_type===module_type&&data.module_id===module_id){
          this.typing=true;
          this.last_typing_time=Date.now();
          setTimeout(()=>{
            let now=Date.now();
            if(now-this.last_typing_time>=TYPING_TIMEOUT){
              this.typing=false;
            }
          },TYPING_TIMEOUT);

        }
      },
      end_typing(data){
        let chat_user = this.$route.query.chat_user;
        let module_type = _.toNumber(this.$route.query.module_type);
        let module_id = _.toNumber(this.$route.query.module_id);
        if(data.chat_user===chat_user&&data.module_type===module_type&&data.module_id===module_id){
          this.typing=false;
        }
      }

    },
    data(){
      return {
        text:"",
        typing:false,
        last_typing_time:0,
        textarea_rows:"1"
      }
    },
    methods:{
      sendImage(dataUrl){

        if(dataUrl){
          let chat_user = this.$route.query.chat_user;
          let module_type = _.toNumber(this.$route.query.module_type);
          let module_id = _.toNumber(this.$route.query.module_id);
          let doc={
            token:this.token,
            module_type:module_type,
            module_id:module_id,
            rev_user:chat_user,
            content_type:1,
            content:dataUrl
          };
          //  console.log(doc);
          this.$socket.emit("end_typing",{
            token:this.token,
            module_type,
            module_id,
            chat_user
          });
          let promise=Websocket.startRequest(this.$socket, "chat", doc);
          let request_key=promise.request_key;
          this.recordSendingMessage(doc,request_key);
          promise.then((data)=> {

              this.pushUnreadChatList(data,this.user_id);
              this.successSendingMessage(request_key);
            })
            .catch((data)=>{
              //  console.log();
              this.failSendingMessage(request_key);
            });
          this.text="";
        }


      },
      textChange(){

      },

      textInput(){
       // let rows=this.text.split("\n").length;
        //this.textarea_rows=(Math.min(2,rows)).toString();
        let chat_user = this.$route.query.chat_user;
        let module_type = _.toNumber(this.$route.query.module_type);
        let module_id = _.toNumber(this.$route.query.module_id);
        console.log('typing');
        this.$socket.emit("typing",{
          token:this.token,
          module_type,
          module_id,
          chat_user
        })

      },
      back(){
        window.history.back();
      },
      sendText(){
        if(this.text){
          let chat_user = this.$route.query.chat_user;
          let module_type = _.toNumber(this.$route.query.module_type);
          let module_id = _.toNumber(this.$route.query.module_id);
          let doc={
            token:this.token,
            module_type:module_type,
            module_id:module_id,
            rev_user:chat_user,
            content_type:0,
            content:this.text
          };
        //  console.log(doc);
          this.$socket.emit("end_typing",{
            token:this.token,
            module_type,
            module_id,
            chat_user
          });
          let promise=Websocket.startRequest(this.$socket, "chat", doc);
          let request_key=promise.request_key;
          this.recordSendingMessage(doc,request_key);
            promise.then((data)=> {

              this.pushUnreadChatList(data,this.user_id);
                this.successSendingMessage(request_key);
            })
            .catch((data)=>{
            //  console.log();
              this.failSendingMessage(request_key);
            });
          this.text="";
        }

      },
      scrollToBottom(){
        let wrapper=this.$els.wrapper;
        let content=this.$els.content;
        wrapper.scrollTop=content.offsetHeight-wrapper.offsetHeight;
      }
    },
    vuex: {
      getters: {
        token,
        is_login,
        user_id
      },
      actions: {
        readChat,
        pushUnreadChatList,
        recordSendingMessage,
        successSendingMessage,
        failSendingMessage
      }
    },
    computed: {
      textInputThrottled(){
        return _.throttle(this.textInput,1000);
      },
      data(){
        let chat_user = this.$route.query.chat_user;
        let module_type = _.toNumber(this.$route.query.module_type);
        let module_id = _.toNumber(this.$route.query.module_id);
        let chat_list = this.$store.state.chat.chat_list;
        let index = _.findIndex(chat_list, (item)=>item.module_type === module_type && item.module_id === module_id && item.chat_user === chat_user)
        let ret;
        if (index === -1)ret= {items:[]};
        else ret= chat_list[index];

        return ret;
      },
      list(){
        let ret= this.data.items.concat(this.$store.state.chat.send_chat_list);

        ret.sort((a,b)=>a.create_time> b.create_time);
        return ret;
      }
    },
    watch:{
      list(){
        this.scrollToBottom();


      },
      typing(){
        this.scrollToBottom();

      }
    },
    route: {
      data(){
        this.scrollToBottom();
        this.typing=false;
        this.last_typing_time=0;

        let chat_user = this.$route.query.chat_user;
        let module_type = _.toNumber(this.$route.query.module_type);
        let module_id = _.toNumber(this.$route.query.module_id);
        this.readChat(module_type,module_id,chat_user,this.user_id);

        if (this.is_login) {
          console.log(`[Websocket] fetch  chat `);
          Websocket.startRequest(this.$socket, "get_chat", {
              token: this.token,
              chat_user: chat_user,
              module_type: module_type,
              module_id: module_id
            })
            .then((data)=> {
              console.log(`[Websocket] fetched chat,length=${ data instanceof Array ? data.length : 0}`);
              this.pushUnreadChatList(data, this.user_id);
            });

        }

      }
    }
  }
</script>
