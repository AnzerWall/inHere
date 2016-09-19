'use strict';

import Base from './base.js';

export default class extends Base {
  /**
   * index action
   * @return {Promise} []
   */
  indexAction(){
    //auto render template file index_index.html
    return this.display();
  }
  async testAction(){
    console.log(this.operator);
    let module_type=0;
    let module_id=10;
    let NameModel=this.model('name');
    let operator_fake_name=await NameModel.getNameAndCreateWhenNotExist(module_type,module_id,this.operator.user_id);
    return  this.success(operator_fake_name);
  }
}