/**
 * Created by anzer on 2016/10/7.
 */
'use strict';
import Base from  './base.js'

export default class extends Base {
    async getList(school_id){
        let items=await this.where({school_id:school_id,removed:0}).select();
        return {
            items: items.map((item)=>{
                item.create_time=new Date(item.create_time).getTime();
                item.update_time=new Date(item.update_time).getTime();
                return item;
            })
        };
    }
}