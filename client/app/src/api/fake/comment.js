/**
 * Created by anzer on 2016/9/5.
 */
import MockJs from 'mockjs';
import UserCryptonym from './user_cryptonym.js'
let Random=MockJs.Random;
import {selectOneRandomUser,UserIdList } from './user.js'

export default{
  //生成num条虚假评论
  genFakeComment(num){
    let items=[];
    let ranTime=Date.now();
    for(let i=0;i<num;i++){
      let user_id=selectOneRandomUser();
      items.push({
        id:num-i,
        user_id:user_id,
        name:UserCryptonym[user_id].cryptonym,
        content:Random.cparagraph(1,3),
        floor:num-i,
        praise:0,
        praised:false,
        create_time:ranTime-=Random.integer(1,180)*60*1000

      });
    }
    return items;
  }
}
