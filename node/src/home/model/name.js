/**
 * Created by anzer on 2016/9/18.
 */
'use strict';
import Base from  './base.js'

export default class extends Base {
    async getNameId(module_type,module_id,user_id){
        let ret= await this.query(`SELECT tb_name.id,tb_name.name FROM tb_name,tb_name_used WHERE tb_name.id=tb_name_used.name_id AND  module_type=${module_type} AND module_id=${module_id} AND user_id=${this.parseValue(user_id)} limit 1`);
        if(think.isEmpty(ret))return {};
        return ret[0];
    }
    async create(module_type,module_id,user_id){
        user_id=this.parseValue(user_id);
        let sql=`insert into tb_name_used set module_type=${module_type},module_id=${module_id},user_id=${user_id},name_id=(select id from (select id,abs(tb_name.random-floor(rand()*100000)) as diff from tb_name
 where id not in
 (select tb_name.id from tb_name,tb_name_used where tb_name.id=tb_name_used.name_id AND module_type=${module_type} AND module_id=${module_id})
 order by diff
 limit 1) as tb)`
        return await this.execute(sql);
    }
}