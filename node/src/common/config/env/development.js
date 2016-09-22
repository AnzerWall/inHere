'use strict';

export default {
    db: {
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
    },
    coredis: {
        host: "115.28.67.181",
        port: 6379,
        password: "li0708",
        db: 0,
        timeout: 0,
        log_connect: true
    }
};