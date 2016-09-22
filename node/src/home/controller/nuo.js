'use strict';

import Base from './base.js';
import _ from 'lodash'
export default class extends Base {
    constructor(...arg) {
        super(...arg);
        this.Model = this.model("target_list");
        this.RelationModel = this.model("target_list_user");
        this.TargetModel = this.model("target")
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
            return this.fail(10002,"NUO NOT FOUND");

        ret.target_list=await this.TargetModel.getList(id,operator_id);
        return this.success(ret);
    }



}