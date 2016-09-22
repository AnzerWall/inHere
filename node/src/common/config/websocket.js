/**
 * Created by anzer on 2016/9/19.
 */
//
import redis from "socket.io-redis";
export default {
    on: true, //是否开启 WebSocket
    type: "socket.io",
    allow_origin: "",
    sub_protocal: "",
    //adapter: undefined,
    path: "", //url path for websocket
    //adapter: {
    //    "socket.io": {
    //        adp: function(){
    //            return redis({ host: "localhost", port: 6379 });
    //        }
    //    }
    //},
    //adp: function () {
    //    return redis({host: "localhost", port: 6379});
    //},
    messages: {
        open: 'home/websocket/open',
        close: 'home/websocket/close',
        auth: 'home/websocket/auth',
        chat: 'home/websocket/chat',
        leave: 'home/websocket/leave',
        get_unread_chat:'home/websocket/get_unread_chat',
        get_chat:'home/websocket/get_chat',
        typing:'home/websocket/typing',
        end_typing:'home/websocket/end_typing',
        test:"home/websocket/test"
    }
};