/**
 * Created by anzer on 2016/9/12.
 */
'use strict';
import redis from "redis";
//import RedisPool from 'redis-connection-pool';
import bluebird from "bluebird";

bluebird.promisifyAll(redis.RedisClient.prototype);
bluebird.promisifyAll(redis.Multi.prototype);
var config=think.config("coredis");
var  client = redis.createClient({
    host:config.host,
    port: config.port,
    password:config.password,
    db:config.db,
    options:{
        retry_strategy: function (options) {
            console.log(error);
            if (options.error.code === 'ECONNREFUSED') {
                // End reconnecting on a specific error and flush all commands with a individual error
                return new Error('The server refused the connection');
            }
            if (options.total_retry_time > 1000 * 60 * 60) {
                // End reconnecting after a specific timeout and flush all commands with a individual error
                return new Error('Retry time exhausted');
            }
            if (options.times_connected > 10) {
                // End reconnecting with built in error
                return undefined;
            }
            // reconnect after
            return Math.max(options.attempt * 100, 3000);
        }
    }
});
client.on("error", function (err) {
    think.log(err,"REDIS");
});
export default client;