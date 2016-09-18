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
    let model=this.model('name');
    //let total=100000;
    //let list=await model.select();
    //list.map((item)=>{
    //  delete item.id;
    //
    //  item.random=Math.floor(Math.random()*total);
    //});
    //this.model('name').addMany(list);
    return  this.success(await model.create(0,1,'test3'));
  }
}