'use strict';
import Base from  './base.js'
/**
 * model
 *
 */
export default class extends Base {
    async upsertInfo(target_id,operator_id,user_data,completed=0){
        let user_id=this.parseValue(operator_id);
        let data=this.parseValue(JSON.stringify(user_data))
        let sql=`
        INSERT INTO ${this.getTableName()}
        SET relation_target_id=${target_id},
        user_id=${user_id},
        user_data=${data},
        completed=${completed}
        ON DUPLICATE KEY UPDATE
         user_data=${data},
         completed=${completed}

        `;
        return this.execute(sql);
    }


}