'use strict';
/**
 * model
 */
import Base from  './base.js'
export default class extends Base {
    async create(param){
        let now =new Date().toMysqlFormat();
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
    async getChat(module_type,module_id,user_id,chat_user) {
        user_id = this.parseValue(user_id);
        chat_user = this.parseValue(chat_user);
        let sql = `select * from tb_chat where id>=(select id from tb_chat where ((rev_user=${chat_user} AND send_user=${user_id}) or (rev_user=${user_id} AND send_user=${chat_user})) AND  \`read\`=0 AND module_type=${module_type} AND module_id=${module_id} order by  id limit 1) AND ((rev_user=${chat_user} AND send_user=${user_id}) or (rev_user=${user_id} AND send_user=${chat_user})) AND module_type=${module_type} AND module_id=${module_id};`

        return await this.query(sql);

    }
}