'use strict';

import Base from './base.js';
//1 – 快递
//2 – 转让
//3 – 帮忙
//4 – 失物
//5 – 捡到
//6 – 走起
//7 – 目标清单
//8 – 活动
//9 – 广告
//10 – 校内吐槽
//11 – 校外吐槽
//12 – 问答
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

   return this.success(1)
  }
}