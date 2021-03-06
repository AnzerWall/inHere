'use strict';
import Base from  './base.js'
/**

 Field           Type          Collation        Null    Key     Default  Extra           Privileges                       Comment
 --------------  ------------  ---------------  ------  ------  -------  --------------  -------------------------------  --------------
 id              int(11)       (NULL)           NO      PRI     (NULL)   auto_increment  select,insert,update,references  主键
 title           varchar(100)  utf8_general_ci  NO              (NULL)                   select,insert,update,references  标题
 text            varchar(500)  utf8_general_ci  YES             (NULL)                   select,insert,update,references  目标描述
 ext_type        int(11)       (NULL)           YES             (NULL)                   select,insert,update,references  类别
 ext_data        json          (NULL)           YES             (NULL)                   select,insert,update,references  类别数据  select,insert,update,references  是否完成
 target_list_id  int(11)       (NULL)           NO              (NULL)                   select,insert,update,references  所属清单id
 index
 select,insert,update,references  更新时间
 */
import _ from 'lodash'
export default class extends Base {
    async createMany(list, target_list_id) {
        let lst = list.map((data, index)=> {
            let doc = {
                title: data.title,
                text: data.text,
                index: index,
                target_list_id: target_list_id,
                type: data.type
            };
            if (data.type === 1) {
                doc.type_data = JSON.stringify({
                    total_count: data.total_count
                });
            }
            else if (data.type === 2) {
                doc.type_data = JSON.stringify({
                    todo_list: _.uniq(data.todo_list)
                });
            }
            else if (data.type === 3) {
                doc.type_data = JSON.stringify({
                    specific_month: data.specific_month,
                    specific_day: data.specific_day,
                    specific_week_day: data.specific_week_day,
                    hour_min: data.hour_min,
                    hour_max: data.hour_max
                });
            }
            else if (data.type === 4) {
                doc.type_data = JSON.stringify({
                    hour_min: data.hour_min,
                    hour_max: data.hour_max,
                    sign_in_total: data.sign_in_total,
                    sign_in_continuously: data.sign_in_continuously
                });
            }
            else if (data.type === 5) {
                doc.type_data = JSON.stringify({
                    question: data.question,
                    answer: data.answer
                });
            }
            return doc;
        });
        return await this.addMany(lst);
    }

    isFinish(target) {


        switch (target.type) {
            case 1:
                return (target.user_data.total_count || 0) >= (target.type_data.total_count || 0);
            case 2:
                let user_todo_list = target.user_data.todo_list || [];
                let data_todo_list = target.type_data.todo_list || [];
                for (let val of data_todo_list) {
                    if (user_todo_list.indexOf(val) === -1) {
                        return false;
                    }
                }
                return true;
            case 3:
                return target.user_data.completed === true;
            case 4:
                return (target.user_data.sign_in_total || 0) >= (target.type_data.sign_in_total || 0);
            case 5:
                return (target.user_data.answer || "") === (target.type_data.answer || "");
        }
        return false;
    }

    async getList(id, operator_id) {
        let user_id=this.parseValue(operator_id);
        let items = await this.where({target_list_id: id})
            .field('target_id,title,text,type,type_data,index,data.user_data,data.completed')
            .join(`tb_target_user AS data ON tb_target.target_id = data.relation_target_id
            AND user_id=${user_id}
            `)
            .select();

        if (think.isArray(items)) {
            items.map((item)=> {
                item.type_data = JSON.parse(item.type_data);
                item.user_data = item.user_data ? JSON.parse(item.user_data || {}) : {};
                item.completed = item.completed === 1;

            });
        }
        return items;
    }


}