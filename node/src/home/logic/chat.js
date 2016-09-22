'use strict';
import Base from './base.js'

/**
 * logic
 * @param  {} []
 * @return {}     []
 */
export default class extends Base{
  /**
   * index action logic
   * @return {} []
   */
  getListAction(){

  }
  sendAction(){
    let Joi=this.Joi;
    let post=this.post();
    this.data={
      module_type:post.module_type,
      module_id:post.module_id,
      rev_user:post.rev_user,
      content_type:post.content_type,
      content:post.content
    };
    this.schema=Joi.object().keys({
      module_type:Joi.number().valid([1,2,3,4,5,6,7,8,9,10,11,12]).required(),
      module_id:Joi.number().required(),
      rev_user:Joi.string().required(),
      content_type:Joi.number().valid([0,1]).required(),
      content:Joi.string().required()
    });
  }
}