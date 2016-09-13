'use strict';
/**
 * db config
 * @type {Object}
 */
//IP :  115.28.67.181   port：3306   账号：lwh   密码：li0708
export default {
  type: 'mysql',
  adapter: {
    mysql: {
      host: '115.28.67.181',
      port: '3306',
      database: 'inHere',
      user: 'lwh',
      password: 'li0708',
      prefix: 'tb_',
      encoding: 'utf8'
    },
    //mongo: {
    //
    //}
  }
};