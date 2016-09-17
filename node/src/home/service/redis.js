/**
 * Created by anzer on 2016/9/12.
 */
'use strict';
import redis from "redis";
import bluebird from "bluebird";
bluebird.promisifyAll(redis.RedisClient.prototype);
bluebird.promisifyAll(redis.Multi.prototype);
var config=think.config("coredis");
var  client = redis.createClient({
    host:config.host,
    port: config.port,
    password:config.password,
    db:config.db
});
client.on("error", function (err) {
    think.log(err,"REDIS");
});
export default client;