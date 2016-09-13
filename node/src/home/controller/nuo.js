'use strict';

import Base from './base.js';

export default class extends Base {
    constructor(...arg){
        super(...arg);
        this.Model=this.model("target_list");
    }
    async createAction(){
        return this.success({
            id:await this.Model.create(this.post())
        });
    }
    async updateAction(){
        return this.success({
            affect_row:await this.Model.updateInfo(this.get("id"),this.post())
        });
    }
    async praiseAction(){
        return this.success({
            succeed:await this.Model.praise(this.get("id"))===1
        });
    }
    async lowAction(){
        return this.success({
            succeed:await this.Model.low(this.get("id"))===1
        });
    }
    async getListAction(){

        return this.success(await this.Model.getList(this.get(),this.operator.user_id));
    }
}