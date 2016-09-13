/**
 * Created by anzer on 2016/9/12.
 */
'use strict';
import crypto from "crypto"
let redis = think.service("redis",'home');
//let config=think.config("token");

export default  class  extends think.service.base{

    //genToken (username, password, ip) {
    //    var now = Date.now();
    //    var random = Math.random();
    //    var str = "" + username + now + password + ip + "h]YAgc+P" + random + "s7,>.kva";
    //    return think.sha256(str);
    //}
    //async saveUserInfo(token,user_id,user_info){
    //    await redis.setAsync("user:"+user_id,JSON.stringify(user_info));
    //    await redis.expireAsync("user:"+user_id,config.expire);
    //    await redis.setAsync("token:"+token,user_id+"");
    //    await redis.expireAsync("token:"+token,config.expire);
    //}
    async verifyTokenAndGetInfo(token) {
        let info ={
            school_id:await redis.hgetAsync("token:" + token,"school_id"),
            user_id:await redis.hgetAsync("token:" + token,"user_id"),
            role_id:await redis.hgetAsync("token:" + token,"role_id")
        };
       // console.log(await redis.hkeysAsync("token:" + token));
        //console.log(info);
        return info;

    }

}

