'use strict';
/**
 * model
 */
import Base from  './base.js'
export default class extends Base {
    async create(param,operator_id){
        let now =Date.now();
        return await  this.add({
            module_type:param.module_type,
            module_id:param.module_id,
            send_user:param.send_user,
            send_name:param.send_name,
            rev_user:param.rev_user,
            rev_name:param.rev_name,
            title:param.title,
            content_type:param.content_type,
            content:param.content,
            create_time:now,
            read:param.read===true
        })
    }
}