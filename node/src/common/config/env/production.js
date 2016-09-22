'use strict';

export default {
  resource_on: false,
  db: {
    type: 'mysql',
    adapter: {
      mysql: {
        host: '127.0.0.1',
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
  },
  coredis: {
    host: "127.0.0.1",
    port: 6379,
    password: "li0708",
    db: 0,
    timeout: 0,
    log_connect: true
  }
};