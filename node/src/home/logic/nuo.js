'use strict';
import Base from './base.js'
/**
 * logic
 * @param  {} []
 * @return {}     []
 */

export default class extends Base {
    async createAction(){
        let Joi=this.Joi;
        let post=this.post();
        this.data={
            title:post.title,
            text:post.text,
            target_list:post.target_list
        };
        this.schema=Joi.object().keys({
            title:Joi.string().required(),
            text:Joi.string().allow('').default(''),
            target_list:Joi.array().required().items(Joi.object().keys({
                type:Joi.number().valid([1,2,3,4,5]).required(),
                title:Joi.string().required(),
                text:Joi.string().allow('').default(''),
                total_count:Joi.number().min(1).max(10000).when('type',{is:1,then:Joi.required()}),
                todo_list:Joi.array().items(Joi.string()).when('type',{is:2,then:Joi.required()}),
                specific_month:Joi.number().min(0).max(12).when('type',{is:3,then:Joi.required()}),
                specific_day:Joi.number().min(0).max(31).when('type',{is:3,then:Joi.required()}),
                specific_week_day:Joi.number().min(0).max(7).when('type',{is:3,then:Joi.required()}),
                hour_min:Joi.number().min(0).max(24).when('type',{is:Joi.valid([3,4]),then:Joi.required()}),
                hour_max:Joi.number().min(0).max(24).when('type',{is:Joi.valid([3,4]),then:Joi.required()}),
                sign_in_total:Joi.number().min(1).max(10000).when('type',{is:4,then:Joi.required()}),
                sign_in_continuously:Joi.boolean().when('type',{is:4,then:Joi.required()}),
                question:Joi.string().when('type',{is:5,then:Joi.required()}),
                answer:Joi.string().when('type',{is:5,then:Joi.required()})
            }))
        });

    }
    async updateAction(){
        let Joi=this.Joi;
        let post=this.post();
        let id=this.get('id');
        this.data={
            title:post.title,
            text:post.text,
            id:id
        };
        this.schema=Joi.object().keys({
            text:Joi.string().allow(''),
            title:Joi.string().allow('')
        });
    }
    async praiseAction(){
        let Joi=this.Joi;
        this.data={
            id:this.get('id')
        };
        this.schema=Joi.object().keys({
            id:Joi.number().integer()
        });

    }
    async lowAction(){
        let Joi=this.Joi;
        this.data={
            id:this.get('id')
        };
        this.schema=Joi.object().keys({
            id:Joi.number().integer()
        });
    }

    async getListAction(){
        let Joi=this.Joi;
        let Get=this.get();
        this.data={
            limit:Get.limit,
            offset:Get.offset
        };
        this.schema=Joi.object().keys({
            limit:Joi.number().integer().min(1).max(50).default(10),
            offset:Joi.number().integer().min(0).default(0)
        });
    }
    async giveUpAction(){
        let Joi=this.Joi;
        this.data={
            id:this.get('id')
        };
        this.schema=Joi.object().keys({
            id:Joi.number().integer()
        });

    }
    async joinItAction() {
        let Joi=this.Joi;
        this.data={
            id:this.get('id')
        };
        this.schema=Joi.object().keys({
            id:Joi.number().integer()
        });
    }
    async getAction(){
        let Joi=this.Joi;
        this.data={
            id:this.get('id')
        };
        this.schema=Joi.object().keys({
            id:Joi.number().integer()
        });
    }
}