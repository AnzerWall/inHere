'use strict';

import Base from './base.js';
import _ from 'lodash'
import moment from 'moment'
export default class extends Base {
    constructor(...arg) {
        super(...arg);
        this.Model = this.model("target_list");
        this.RelationModel = this.model("target_list_user");
        this.TargetModel = this.model("target")
        this.TargetRelationModel = this.model("target_user");

    }


    async createAction() {
        let id=await this.Model.create({
            title:this.data.title,
            text:this.data.text,
            target_total:this.data.target_list.length
        }, this.operator.user_id);
        let list=await this.TargetModel.createMany(this.data.target_list,id);
        return this.success({
            id:id,
            list:list
        });
    }
    isFinish(target){


            switch(target.type){
                case 1:
                    return (target.user_data.total_count||0)>=(target.type_data.total_count||0);
                case 2:
                    let user_todo_list=target.user_data.todo_list||[];
                    let data_todo_list=target.type_data.todo_list||[];
                    for(let val of data_todo_list){
                        if(user_todo_list.indexOf(val)===-1){
                            return false;
                        }
                    }
                    return true;
                case 3:
                    return target.user_data.completed===true;
                case 4:
                    return (target.user_data.sign_in_total||0)>=(target.type_data.sign_in_total||0);
                case 5:
                    return (target.user_data.answer||"")===(target.type_data.answer||"");
            }
            return false;

    }

    async targetCountAction(){
        let {id,index}=this.data;
        let operator_id = this.operator.user_id;
        //console.log(id,index);
        let nuo=await this.Model.get(id,operator_id);
        if(think.isEmpty(nuo))
            return this.fail(10002,"无法找到该有诺必行");
        nuo.target_list=await this.TargetModel.getList(id,operator_id);
        if(!think.isArray(   nuo.target_list))
            return this.fail(10003,"该有诺必行的目标列表为空");
        if(index>=nuo.target_list.length){
            return this.fail(10004,"目标索引错误");
        }
        if(index!==0&&!this.isFinish(nuo.target_list[index-1]))
            return this.fail(10005,"你需要完成上一个目标");
        let target=nuo.target_list[index];

        if(target.type!==1)
            return this.fail(10006,"无法对非计数型的目标进行计数");
        //console.log(target);
        //return this.success({});

        let ret=await this.TargetRelationModel.upsertInfo(target.target_id,this.operator.user_id, {
                total_count:Math.min((target.user_data.total_count+1)||1,target.type_data.total_count||0)
            });
       // console.log(ret);
        return this.success({
            affect_row:ret
        })

    }
    async targetDoTodoAction(){
        let {id,index,todo}=this.data;
        let operator_id = this.operator.user_id;
        //console.log(id,index);
        let nuo=await this.Model.get(id,operator_id);
        if(think.isEmpty(nuo))
            return this.fail(10002,"无法找到该有诺必行");
        nuo.target_list=await this.TargetModel.getList(id,operator_id);
        if(!think.isArray(   nuo.target_list))
            return this.fail(10003,"该有诺必行的目标列表为空");
        if(index>=nuo.target_list.length){
            return this.fail(10004,"目标索引错误");
        }
        if(index!==0&&!this.isFinish(nuo.target_list[index-1]))
            return this.fail(10005,"你需要完成上一个目标");
        let target=nuo.target_list[index];
        if(target.type!==2)
            return this.fail(10006,"无法得完成型的目标进行完成目标");
        let user_todo_list=target.user_data.todo_list||[];
        let data_todo_list=target.type_data.todo_list||[];

        if(data_todo_list.indexOf(todo)===-1){
            return this.fail(10007,"该项需要做的事情不在要求列表");

        }
        if(user_todo_list.indexOf(todo)!==-1){
            return this.fail(10008,"该项需要做的事情已经完成");
        }
        user_todo_list.push(todo);
        //console.log(target);
        //return this.success({});

        let ret=await this.TargetRelationModel.upsertInfo(target.target_id,this.operator.user_id, {
            todo_list:user_todo_list
        });
        // console.log(ret);
        return this.success({
            affect_row:ret
        })
    }
    //async targetUndoTodoAction(){
    //    let {id,index}=this.data;
    //    let operator_id = this.operator.user_id;
    //    //console.log(id,index);
    //    let nuo=await this.Model.get(id,operator_id);
    //    if(think.isEmpty(nuo))
    //        return this.fail(10002,"无法找到该有诺必行");
    //    nuo.target_list=await this.TargetModel.getList(id,operator_id);
    //    if(!think.isArray(   nuo.target_list))
    //        return this.fail(10003,"该有诺必行的目标列表为空");
    //    if(index>=nuo.target_list.length){
    //        return this.fail(10004,"目标索引错误");
    //    }
    //    if(index!==0&&!this.isFinish(nuo.target_list[index-1]))
    //        return this.fail(10005,"你需要完成上一个目标");
    //    let target=nuo.target_list[index];
    //    if(target.type!==1)
    //        return this.fail(10006,"无法得非计数型的目标进行计数");
    //    //console.log(target);
    //    //return this.success({});
    //
    //    let ret=await this.TargetRelationModel.upsertInfo(target.target_id,this.operator.user_id, {
    //        total_count:Math.min((target.user_data.total_count+1)||1,target.type_data.total_count||0)
    //    });
    //    // console.log(ret);
    //    return this.success({
    //        affect_row:ret
    //    })
    //}
    async targetCompleteAction(){
        let {id,index}=this.data;
        let operator_id = this.operator.user_id;
        //console.log(id,index);
        let nuo=await this.Model.get(id,operator_id);
        if(think.isEmpty(nuo))
            return this.fail(10002,"无法找到该有诺必行");
        nuo.target_list=await this.TargetModel.getList(id,operator_id);
        if(!think.isArray(   nuo.target_list))
            return this.fail(10003,"该有诺必行的目标列表为空");
        if(index>=nuo.target_list.length){
            return this.fail(10004,"目标索引错误");
        }
        if(index!==0&&!this.isFinish(nuo.target_list[index-1]))
            return this.fail(10005,"你需要完成上一个目标");
        let target=nuo.target_list[index];
        if(target.type!==3)
            return this.fail(10006,"无法对非特定时间目标进行完成");
        //console.log(target);
        //return this.success({});
        let now=moment();
        console.log(now.month(),now.hour(),now.date(),now.weekday());


        let hour=now.hour();
        if(target.type_data.hour_min!==0){
            if(hour<target.type_data.hour_min){
                return  this.fail(10007,"不在可以完成时间范围内");
            }
        }
        if(target.type_data.hour_max!==0){
            if(hour>target.type_data.hour_max){
                return  this.fail(10007,"不在可以完成时间范围内");
            }
        }
        if(target.type_data.specific_month!==0){
            if(now.month()!=target.type_data.specific_month){
                return  this.fail(10007,"不在可以完成时间范围内");
            }
        }
        if(target.type_data.specific_day!==0){
            if(now.date()!=target.type_data.specific_day){
                return  this.fail(10007,"不在可以完成时间范围内");
            }
        }
        if(target.type_data.specific_week_day!==0){
            if(now.weekday()+1!=target.type_data.specific_week_day){
                return  this.fail(10007,"不在可以完成时间范围内");
            }
        }
        let ret=await this.TargetRelationModel.upsertInfo(target.target_id,this.operator.user_id, {
            completed:true
        });
        // console.log(ret);
        return this.success({
            affect_row:ret
        })
    }
    async targetSignInAction(){
        let {id,index}=this.data;
        let operator_id = this.operator.user_id;
        //console.log(id,index);
        let nuo=await this.Model.get(id,operator_id);
        if(think.isEmpty(nuo))
            return this.fail(10002,"无法找到该有诺必行");
        nuo.target_list=await this.TargetModel.getList(id,operator_id);
        if(!think.isArray(   nuo.target_list))
            return this.fail(10003,"该有诺必行的目标列表为空");
        if(index>=nuo.target_list.length){
            return this.fail(10004,"目标索引错误");
        }
        if(index!==0&&!this.isFinish(nuo.target_list[index-1]))
            return this.fail(10005,"你需要完成上一个目标");
        let target=nuo.target_list[index];
        if(target.type!==4)
            return this.fail(10006,"无法对非签到型进行签到");
        let now=moment();

        let hour=now.hour();
        if(target.type_data.hour_min!==0){
            if(hour<target.type_data.hour_min){
                return  this.fail(10007,"不在可以签到时间范围内");
            }
        }
        if(target.type_data.hour_max!==0){
            if(hour>target.type_data.hour_max){
                return  this.fail(10007,"不在可以签到时间范围内");
            }
        }
        if(target.user_data.last_sign_in_time!==undefined){
           let last_time=moment(target.user_data.last_sign_in_time);
            let hour_diff=Math.abs(now.diff(last_time,'hour'));
            let days_diff=Math.abs(moment([now.year(),now.month(),now.date()]).diff([last_time.year(),last_time.month(),last_time.date()],'days'));
            console.log(days_diff,hour_diff,now.year(),now.month(),now.date(),last_time.year(),last_time.month(),last_time.date());

            if(days_diff<1)
                return  this.fail(10008,"今天已经签到");
            if(target.type_data.sign_in_continuously===true){
                    if(hour_diff>24){
                        return  this.fail(10008,"需要连续签到（所以你只能放弃~");
                    }

            }
        }
        //console.log(moment([la]).diff(now,'hour'),moment(target.user_data.last_sign_time).diff(now,'hour'));

        let ret=await this.TargetRelationModel.upsertInfo(target.target_id,this.operator.user_id, {
            sign_in_total:Math.min(((target.user_data.sign_in_total||0)+1)||1,target.type_data.sign_in_total||0),
            last_sign_in_time:now.valueOf()
        });
        // console.log(ret);
        return this.success({
            affect_row:ret
        })
    }
    async targetAnswerAction(){
        let {id,index,answer}=this.data;
        let operator_id = this.operator.user_id;
        //console.log(id,index);
        let nuo=await this.Model.get(id,operator_id);
        if(think.isEmpty(nuo))
            return this.fail(10002,"无法找到该有诺必行");
        nuo.target_list=await this.TargetModel.getList(id,operator_id);
        if(!think.isArray(   nuo.target_list))
            return this.fail(10003,"该有诺必行的目标列表为空");
        if(index>=nuo.target_list.length){
            return this.fail(10004,"目标索引错误");
        }
        if(index!==0&&!this.isFinish(nuo.target_list[index-1]))
            return this.fail(10005,"你需要完成上一个目标");
        let target=nuo.target_list[index];
        if(target.type!==5)
            return this.fail(10006,"无法对非回答型进行回答操作");

        if(answer!==target.type_data.answer){
            return this.fail(10007,"错误的回答");
        }
        let ret=await this.TargetRelationModel.upsertInfo(target.target_id,this.operator.user_id, {
            answer:answer
        });
        // console.log(ret);
        return this.success({
            affect_row:ret
        })
    }
    async updateAction() {
        return this.success({
            affect_row: await this.Model.updateInfo(this.get("id"), this.data)
        });
    }

    async praiseAction() {

        return this.success({
            succeed: await this.Model.praise(this.get("id"), this.operator.user_id) === 1
        });
    }

    async lowAction() {
        return this.success({
            succeed: await this.Model.low(this.get("id"), this.operator.user_id) === 1
        });
    }

    async getListAction() {

        return this.success(await this.Model.getList(this.data, this.operator.user_id));
    }

    async joinItAction() {
        let id = this.get("id");
        let operator_id = this.operator.user_id;
        let user_data = await this.RelationModel.getMine(id, operator_id);

        if (user_data.status === 1 || user_data.status === 2)return this.fail(10000, "你已经参与了该有诺必行");

        return this.success(await this.RelationModel.joinIt(id, operator_id));
    }

    async giveUpAction() {
        let id = this.get("id");
        let operator_id = this.operator.user_id;
        let user_data = await this.RelationModel.getMine(id, operator_id);
        if (user_data.status !== 1)return this.fail(10001, "你告诉我都没有参加，怎么放弃");
        return this.success(await this.RelationModel.giveUp(this.get("id"), this.operator.user_id));
    }
    async getAction(){
        let id = this.get("id");
        let operator_id = this.operator.user_id;
        let ret=await this.Model.get(id,operator_id);
        if(think.isEmpty(ret))
            return this.fail(10002,"无法找到该有诺必行");

        ret.target_list=await this.TargetModel.getList(id,operator_id);
        return this.success(ret);
    }


}