'use strict';
/**
 * model
 */
import Base from  './base.js'
export default class extends Base {
    async isUserExist(user_id){
        let ret=await this.where({user_id:user_id}).find();

        return !think.isEmpty(ret);
    }
}