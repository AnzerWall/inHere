'use strict';
/**
 * model
 */
import Base from  './base.js'
export default class extends Base {
    async getTitle(id){
        let ret=await this.where({id:id}).field("text").find();

        if(think.isEmpty(ret))return undefined;
        else return ret.text||"(空)";
    }
}