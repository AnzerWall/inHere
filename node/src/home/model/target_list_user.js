'use strict';
import Base from  './base.js'
/**
 Field           Type          Collation        Null    Key     Default  Extra           Privileges                       Comment
 --------------  ------------  ---------------  ------  ------  -------  --------------  -------------------------------  ----------------------------
 id              int(11)       (NULL)           NO      PRI     (NULL)   auto_increment  select,insert,update,references  主键
 target_list_id  int(11)       (NULL)           NO              (NULL)                   select,insert,update,references  清单列表编号
 user_id         varchar(100)  utf8_general_ci  NO              (NULL)                   select,insert,update,references  用户编号
 process         double        (NULL)           YES             (NULL)                   select,insert,update,references  任务进度
 has_give_up     tinyint(4)    (NULL)           YES             (NULL)                   select,insert,update,references  是否放弃，0-否，1-是
 status         tinyint(4)    (NULL)           YES             (NULL)                   select,insert,update,references  是否通过，0-未，1-已
 */
export default class extends Base {
    async getMine(target_list_id, operator_id) {
        return await this.where({
                target_list_id: target_list_id,
                user_id: operator_id
            })
            .find();
    }

    async create(target_list_id, operator_id) {
        return await this.add({
            target_list_id: target_list_id,
            user_id: operator_id,
            status: 0,
            nuo_process: 0,
            give_up_count: 0
        })
    }

    async joinIt(target_list_id, operator_id) {
        return await this.execute(`INSERT INTO ${this.getTableName()} set target_list_id=${target_list_id},user_id='${operator_id}',nuo_process=0,give_up_count=0,status=1
        ON DUPLICATE KEY UPDATE   status=1`)
    }

    async giveUp(target_list_id, operator_id) {
        return await this.where({
            target_list_id: target_list_id,
            user_id: operator_id
        }).update({
            status: 0
        })
            .increment('give_up_count',1)
    }

    async updateProcess(target_list_id, operator_id,process) {


        let user_id=this.parseValue(operator_id);
        let sql=`
        INSERT INTO ${this.getTableName()}
        SET  target_list_id=${target_list_id},
         user_id=${user_id},
         nuo_process=${process}
        ON DUPLICATE KEY UPDATE
         nuo_process=${process}
        `;
        return this.execute(sql);


    }

}