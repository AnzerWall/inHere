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
    async getTitle(id) {
        let ret = await this.where({id: id}).field("title").find();
        if (think.isEmpty(ret))return undefined;
        else return ret.title || "(空)";
    }

    async create(data, operator_id, school_id) {
        let now = new Date().toMysqlFormat();
        let doc = {
            title: data.title,
            text: data.text,
            type: 0,
            type_data: '{}',
            creator_id: operator_id,
            create_time: now,
            update_time: now,
            praise: '{}',
            low: '{}',
            nuo_total: data.nuo_total,
            school_id: school_id,
            removed: 0
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

    async praise(id, operator_id) {
        return await this.execute(`update ${this.getTableName()} set praise=JSON_SET(praise,'$.${operator_id}',1),low=JSON_SET(praise,'$.${operator_id}',0) where id=${id}`);
    }

    async low(id, operator_id) {

        return await this.execute(`update ${this.getTableName()} set praise=JSON_SET(praise,'$.${operator_id}',0),low=JSON_SET(praise,'$.${operator_id}',1) where id=${id}`);
    }

    async get(id, operator_id) {
        let user_id=this.parseValue(operator_id);

        let ret = await this.where(`id=${id} `)
            .field('id,title,text,type,type_data,creator_id,create_time,update_time,praise,low,school_id,data.give_up_count,data.status,data.nuo_process,nuo_total')
            .join(`tb_target_list_user AS data ON tb_target_list.id = data.target_list_id AND removed=0 AND (user_id=${user_id} OR user_id IS NULL)`)
            .find();
        console.log(ret);
        if (!think.isEmpty(ret)) {
            ret.praise = JSON.parse(ret.praise);
            ret.low = JSON.parse(ret.low);
            ret.praised = ret.praise[operator_id] == 1;
            ret.is_low = ret.low[operator_id] == 1;
            ret.praise_count = _.reduce(ret.praise, (sum, n)=>(sum += (n == 1 ? 1 : 0)), 0);
            ret.low_count = _.reduce(ret.praise, (sum, n)=>(sum += (n == 0 ? 1 : 0)), 0);
            ret.status = ret.status || 0;
            ret.nuo_process = ret.nuo_process || 0;

            ret.give_up_count = ret.give_up_count || 0;
            ret.target_list_id = ret.target_list_id === null ? 0 : ret.target_list_id;
            ret.create_time = new Date(ret.create_time).getTime();
            ret.update_time = new Date(ret.update_time).getTime();
            delete ret.type_data;
            delete ret.type;
            delete ret.praise;
            delete ret.low;
        }

        return ret;

    }

    async getList(param, operator_id, school_id, mine = 0) {
        let user_id = this.parseValue(operator_id);
        mine = mine === 1;
        let ret;
        if (mine) {
            ret = await this.join(`tb_target_list_user AS data ON tb_target_list.id = data.target_list_id AND  school_id=${school_id} AND (data.user_id=${user_id} OR data.user_id IS  NULL )`)
                .where(`school_id=1 AND removed=0 AND (data.user_id IS NULL OR data.status IS NOT NULL OR creator_id=${user_id}) `)
                .field('id,title,text,type,type_data,creator_id,create_time,update_time,praise,low,school_id,data.give_up_count,data.status,data.nuo_process,nuo_total')
                .limit(param.offset, param.limit)
                .countSelect();

        } else {
            ret = await this.join(`tb_target_list_user AS data ON tb_target_list.id = data.target_list_id  AND (data.user_id=${user_id} OR data.user_id IS  NULL )`)
                .where(`school_id=1 AND removed=0`)
                .limit(param.offset, param.limit)
                .field('id,title,text,type,type_data,creator_id,create_time,update_time,praise,low,school_id,data.give_up_count,data.status,data.nuo_process,nuo_total')
                .countSelect();
        }

        let items = ret.data;
        if (think.isArray(items)) {
            items.map((item)=> {
                // item.type_data = JSON.parse(item.type_data);
                item.praise = JSON.parse(item.praise);
                item.low = JSON.parse(item.low);
                item.praised = item.praise[operator_id] == 1;
                item.is_low = item.low[operator_id] == 1;
                item.praise_count = _.reduce(item.praise, (sum, n)=>(sum += (n == 1 ? 1 : 0)), 0);
                item.low_count = _.reduce(item.praise, (sum, n)=>(sum += (n == 0 ? 1 : 0)), 0);
                item.status = item.status || 0;
                item.nuo_process = item.nuo_process || 0;
                item.give_up_count = item.give_up_count || 0;
                item.target_list_id = item.target_list_id === null ? 0 : item.target_list_id;
                item.create_time = new Date(item.create_time).getTime();
                item.update_time = new Date(item.update_time).getTime();
                delete item.type_data;
                delete item.type;
                delete item.praise;
                delete item.low;
            });
        }
        return {
            count: ret.count,
            items: items,
            has_next: items.length + param.offset < ret.count,
            limit: param.limit,
            offset: param.offset
        };
    }


}