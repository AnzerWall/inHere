'use strict';


import Joi from 'joi';
import fs from 'fs';
import uuid from 'uuid';
import path from 'path'
export default class extends think.controller.base {
    constructor(...args) {
        super(...args);
        let Service = this.service('room');
        this.Room = new Service();
        let TokenService = this.service("token");
        this.Token = new TokenService();
        this.Operator = this.service("operator");
        //定义聊天图片上传路径
        this.chat_upload=think.ROOT_PATH + '/asset/chat/';
        this.writeFile=think.promisify(fs.writeFile,fs);
        this.readFile=think.promisify(fs.readFile,fs);
    }

    openAction() {        //socket打开事件

        let socket = this.http.socket;
        think.log(`[${socket.id}] connect`, 'Websocket');
    }

    async auth(token) {        //用户登录事件
        let socket = this.http.socket;
        let Room = this.Room;
        let Operator = this.Operator;
        let user_info = await this.Token.verifyTokenAndGetInfo(token);
        let operator = new Operator({
            ...user_info,
            socket_id: socket.id
        });
        if (operator.isLogin()) {

            think.log(`[${socket.id}][${operator.user_id}] auth`, 'Websocket');
            await Room.online(operator);

        }
    }

    authLogic() {
        let data = this.http.data;
        let schema = Joi.object()
            .keys({token: Joi.string().required()})
            .required();
        let result = Joi.validate(data, schema);
        return think.isEmpty(result.error);
    }

    async testAction() {
        think.log("test", "Webscoket");

        //  this.emit("test",new Buffer("123"));
    }

    getUnreadChatLogic() {
        let data = this.http.data;
        let schema = Joi.object()
            .keys({
                request_key: Joi.string().length(20).required(),
                data: Joi.object()
                    .keys({token: Joi.string().required()})
                    .required()
            })
            .required();
        let result = Joi.validate(data, schema);
        if (think.isEmpty(result.error))return true;
        else {

            return this.emitError(data.request_key, 10000, "参数异常");
        }
    }

    async getUnreadChatAction() {
        think.log("getUnreadChatAction", "Webscoket");
        if (this.getUnreadChatLogic()) {

            let Model = this.model('chat');
            let socket = this.http.socket;
            let data = this.http.data.data;
            let request_key = this.http.data.request_key;
            let Room = this.Room;

            if (!(await Room.isOnlineBySocketId(socket.id))) {
                await this.auth(data.token)
            }

            let operator = await Room.getOperatorBySocketId(socket.id);

            if (!operator.isLogin()) {
                return this.emitError(data.request_key, 10102, "鉴权失败");
            }
            let ret = await Model.where({rev_user: operator.user_id, read: 0}).select();

            if (think.isArray(ret)) {
                let id_list = ret.map(item=>item.id);
                for(let item of ret){
                    item.create_time = new Date(item.create_time).getTime();
                    if(item.content_type===1){
                        path.resolve(this.chat_upload,item.content)
                        item.content=await this.readFile(   path.resolve(this.chat_upload,item.content));
                    }
                }



                // await Model.where({rev_user:operator.user_id}).select();
                // console.log(ret);
                think.log(id_list, "debug");

                socket.emit("end_request", {
                    request_key: request_key,
                    data: ret
                });
            }

        }


    }

    async authAction() {
        let socket = this.http.socket;
        let Room = this.Room;

        if (this.authLogic() && !(await Room.isOnlineBySocketId(socket.id))) {
            let data = this.http.data;
            await this.auth(data.token)
        }
    }

    async getTitle(module_type, module_id) {
        if ([1, 2, 3, 4, 5, 6].includes(module_type))return await this.model('demand').getTitle(module_id);
        else if (module_type === 7)return await this.model("target_list").getTitle(module_id);
        else if ([8, 9].includes(module_type))return this.model('activity').getTitle(module_id);
        else if ([10, 11, 12].includes(module_type))return this.model('ask_reply').getTitle(module_id);
        else return undefined;
    }

    emitError() {
        console.error(arguments)
    }

    chatLogic() {
        let data = this.http.data;
        let schema = Joi.object()
            .keys({
                request_key: Joi.string().length(20).required(),
                data: Joi.object().keys({
                    token: Joi.string().required(),
                    module_type: Joi.number().valid([1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]).required(),
                    module_id: Joi.number().required(),
                    rev_user: Joi.string().required(),
                    content_type: Joi.number().valid([0, 1]).required(),
                    content: Joi.string().required()
                }).required()
            })
            .required();
        //let schema = Joi.object().keys({
        //    token: Joi.string().required(),
        //    module_type: Joi.number().valid([1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]).required(),
        //    module_id: Joi.number().required(),
        //    rev_user: Joi.string().required(),
        //    content_type: Joi.number().valid([0, 1]).required(),
        //    content: Joi.string().required()
        //}).required();
        let result = Joi.validate(data, schema);
        if (think.isEmpty(result.error))return true;
        else {
            console.log(result.error);
            return this.emitError(data.request_key, 10000, "参数异常");
        }
    }
    typingLogic() {
        let data = this.http.data;
        let schema = Joi.object().keys({
            token: Joi.string().required(),
            module_type: Joi.number().valid([1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]).required(),
            module_id: Joi.number().required(),
           chat_user: Joi.string().required()
        }).required();
        let result = Joi.validate(data, schema);
        if (think.isEmpty(result.error))return true;
        else {
            console.log(result.error);
            return this.emitError(10000, "参数异常");
        }
    }
    async endTypingAction(){
        if(this.typingLogic()){
            let data=this.http.data;
            let socket=this.http.socket;
            let Room = this.Room;
            if (!(await Room.isOnlineBySocketId(socket.id))) {
                await this.auth(data.token)
            }
            let operator = await Room.getOperatorBySocketId(socket.id);
            if (!operator.isLogin()) {
                return this.emitError(10102, "鉴权失败");

            }
            let isOnline=await Room.isOnlineByUserId(data.chat_user);
            if (isOnline) {
                let rev_user = await Room.getOperatorByUserId(data.chat_user);
                socket.to(rev_user.socket_id).emit("end_typing",{
                    chat_user:operator.user_id,
                    module_type:data.module_type,
                    module_id:data.module_id

                });

            }
            think.log(`${operator.user_id}  end_typing`,"Websocket");
        }

    }
    async typingAction(){
        if(this.typingLogic()){
            let data=this.http.data;
            let socket=this.http.socket;
            let Room = this.Room;
            if (!(await Room.isOnlineBySocketId(socket.id))) {
                await this.auth(data.token)
            }
            let operator = await Room.getOperatorBySocketId(socket.id);
            if (!operator.isLogin()) {
                return this.emitError(10102, "鉴权失败");

            }
            let isOnline=await Room.isOnlineByUserId(data.chat_user);

            if (isOnline) {
                let rev_user = await Room.getOperatorByUserId(data.chat_user);
                socket.to(rev_user.socket_id).emit("typing",{
                    chat_user:operator.user_id,
                    module_type:data.module_type,
                    module_id:data.module_id

                });

            }
            think.log(`${operator.user_id}  typing`,"Websocket");
        }

    }

    async chatAction() {
        if (this.chatLogic()) {

            let socket = this.http.socket;
            //let data = this.http.data;
            let data = this.http.data.data;
            let request_key=this.http.data.request_key;
            let Room = this.Room;

            if (!(await Room.isOnlineBySocketId(socket.id))) {
                await this.auth(data.token)
            }
            let operator = await Room.getOperatorBySocketId(socket.id);
            if (!operator.isLogin()) {
                return this.emitError(data.request_key, 10102, "鉴权失败");

            }
            let title = await this.getTitle(data.module_type, data.module_id);
            if (title === undefined)
                return this.emitError(data.request_key, 10101, "模块未找到");
            if (!(await this.model("user").isUserExist(data.rev_user)))
                return this.emitError(data.request_key, 10100, "接收消息的用户不存在");

            let NameModel = this.model('name');
            let operator_fake_name = (await NameModel.getNameAndCreateWhenNotExist(data.module_type, data.module_id, operator.user_id)).name;
            let rev_user_fake_name = (await NameModel.getNameAndCreateWhenNotExist(data.module_type, data.module_id, data.rev_user)).name;
            let doc = {
                module_type: data.module_type,
                module_id: data.module_id,
                send_user: operator.user_id,
                send_name: operator_fake_name,
                rev_user: data.rev_user,
                rev_name: rev_user_fake_name,
                title: title,
                content_type: data.content_type,
                content: data.content,
                read: false
            };
            let  upload_path =path.resolve(this.chat_upload, operator.user_id);
            think.mkdir(upload_path);

            let fileName=uuid.v1();

            await this.writeFile(path.resolve(upload_path,fileName),data.content);
            doc.content= `${operator.user_id}/${fileName}`;


            let isOnline=await Room.isOnlineByUserId(data.rev_user);
            if(isOnline) doc.read = true;
            let id = await this.model('chat').create(doc);
            doc.id=id;

            doc.content=data.content;
            if (isOnline) {
                let rev_user = await Room.getOperatorByUserId(data.rev_user);
                socket.to(rev_user.socket_id).emit("chat", doc);
                //console.log("rev_user online " + rev_user.socket_id);

            }
            //socket.emit("chat", doc);
            //console.log(request_key,doc);
            socket.emit("end_request", {
                request_key: request_key,
                data: doc
            });

            //console.log((await Room.isOnlineBySocketId(socket.id)) ? "online" : "offline")
            //console.log((await Room.getOperatorBySocketId(socket.id)))
        }

    }

    getChatLogic() {
        let data = this.http.data;
        // console.log(data);
        let schema = Joi.object()
            .keys({
                request_key: Joi.string().length(20).required(),
                data: Joi.object()
                    .keys({
                        token: Joi.string().required(),
                        module_type: Joi.number().valid([1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]).required(),
                        module_id: Joi.number().required(),
                        chat_user: Joi.string().required()
                    })
                    .required()
            })
            .required();
        let result = Joi.validate(data, schema);
        if (think.isEmpty(result.error))return true;
        else {
            console.log(result.error);
            return this.emitError(data.request_key, 10000, "参数异常");
        }
    }

    async getChatAction() {
        think.log("getChatAction", "Webscoket");
        if (this.getChatLogic()) {
            let Model = this.model('chat');
            let socket = this.http.socket;
            let data = this.http.data.data;
            let request_key = this.http.data.request_key;
            let Room = this.Room;

            if (!(await Room.isOnlineBySocketId(socket.id))) {
                await this.auth(data.token)
            }

            let operator = await Room.getOperatorBySocketId(socket.id);

            if (!operator.isLogin()) {
                return this.emitError(data.request_key, 10102, "鉴权失败");
            }
            let ret = await Model.getChat(data.module_type, data.module_id, operator.user_id, data.chat_user);

            if (think.isArray(ret)) {
                let id_list = ret.map(item=>item.id);
                for(let item of ret){
                    item.create_time = new Date(item.create_time).getTime();
                    if(item.content_type===1){
                        console.log( path.resolve(this.chat_upload,item.content));
                        item.content=await this.readFile(   path.resolve(this.chat_upload,item.content));
                        item.content=item.content.toString();

                    }
                }

                think.log(id_list, "debug");
                //  console.log(ret)
                socket.emit("end_request", {
                    request_key: request_key,
                    data: ret
                });
            }
        }
    }

    async leaveAction() {

        //let socket = this.http.socket;
        //let Room = this.Room;
        //if (await Room.isOnlineBySocketId(socket.id)) {
        //    let operator = await Room.getOperatorBySocketId(socket.id);
        //    if (operator.isThisSocket(socket.id)) {
        //        await Room.offline(operator);
        //        think.log(`[${socket.id}][${operator.user_id}] offline`, 'Websocket');
        //
        //    }
        //}

    }

    async closeAction() {
        let socket = this.http.socket;
        await this.leaveAction();

        think.log(`[${socket.id}] disconnect`, 'Websocket');
    }
}