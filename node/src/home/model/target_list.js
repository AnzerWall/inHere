'use strict';
import Base from  './base.js'
import _ from 'lodash'
/**
 Field        Type          Collation        Null    Key     Default  Extra           Privileges                       Comment
 -----------  ------------  ---------------  ------  ------  -------  --------------  -------------------------------  ---------------
 id           int(11)       (NULL)           NO      PRI     (NULL)   auto_increment  select,insert,update,references  清单编号
 title        varchar(100)  utf8_general_ci  NO              (NULL)                   select,insert,update,references  清单标题
 text         varchar(300)  utf8_general_ci  YES             (NULL)                   select,insert,update,references  清单描述
 ext_type     int(11)       (NULL)           NO              (NULL)                   select,insert,update,references  类别
 ext_data     json          (NULL)           YES             (NULL)                   select,insert,update,references  类别数据
 user_id      varchar(100)  utf8_general_ci  NO              (NULL)                   select,insert,update,references  创建者
 create_time  datetime      (NULL)           YES             (NULL)                   select,insert,update,references  创建时间
 update_time  datetime      (NULL)           YES             (NULL)                   select,insert,update,references  更新时间
 praise       json          (NULL)           YES             (NULL)                   select,insert,update,references  赞用户列表
 low          json          (NULL)           YES             (NULL)                   select,insert,update,references  踩用户列表
 */
export default class extends Base {
    async create(data) {
        let now = new Date().toMysqlFormat();
        let doc = {
            title: data.title,
            text: data.text,
            ext_type: 0,
            ext_data: '{}',
            user_id: 'anzerwall',
            create_time: now,
            update_time: now,
            praise: '{}',
            low: '{}'
        };
        return this.add(doc);
    }

    async updateInfo(id, data) {
        let now = new Date().toMysqlFormat();

        return await this.where({id: id}).update({
            title: data.title,
            text: data.text,
            update_time: now
        });
    }

    async praise(id) {
        let user_id = this.parseValue('anzerwall');
        return await this.execute(`update ${this.getTableName()} set praise=JSON_SET(praise,'$.${user_id}',1),low=JSON_SET(praise,'$.${user_id}',0) where id=${id}`);
    }

    async low(id) {
        let user_id = this.parseValue('anzerwall');
        return await this.execute(`update ${this.getTableName()} set praise=JSON_SET(praise,'$.${user_id}',0),low=JSON_SET(praise,'$.${user_id}',1) where id=${id}`);
    }

    async getList(param, operator_id) {

        let ret=await this.join(`tb_target_list_user AS data ON tb_target_list.id = data.target_list_id and data.is_pass!=1`)
            .limit(param.offset, param.limit)
            .countSelect();
        let items = ret.data;
        if (think.isArray(items)) {
            items.map((item)=> {
                item.ext_data = JSON.parse(item.ext_data);
                item.praise = JSON.parse(item.praise);
                item.low = JSON.parse(item.low);
                item.praised = item.praise[operator_id] == 1;
                item.is_low = item.low[operator_id] == 1;
                item.praise_count=_.reduce(item.praise, (sum, n)=>(sum += (n == 1 ? 1 : 0)), 0);
                item.low_count=_.reduce(item.praise, (sum, n)=>(sum += (n == 0 ? 1 : 0)), 0);
                item.has_give_up=(item.has_give_up===1);
                item.is_pass=(item.is_pass===1);
                item.process= item.process===null?0:item.process;
                item.target_list_id= item.target_list_id===null?0:item.target_list_id;
                delete item.praise;
                delete item.low;
                delete item.id;
            });
        }
        return {
            count: ret.count,
            items: items,
            limit: param.limit,
            offset: param.offset
        };
    }

}