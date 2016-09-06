/**
 * Created by anzer on 2016/9/5.
 */
import MockJs from 'mockjs';
import UserCryptonym from './user_cryptonym.js'
let Random=MockJs.Random;
export default{
  //生成num条虚假评论
  genFakeComment(num){
    let items=[];
    let ranTime=Random.integer(0,2*24*60*60*1000);
    for(let i=0;i<num;i++){
      let user_id=Random.natural(1,10)
      items.push({
        id:i+1,
        content:Random.cparagraph(1,3),
        user_id:user_id,
        cryptonym:UserCryptonym[user_id-1].cryptonym,
        floor:i+1,
        create_time:Date.now()-ranTime

      });
      ranTime=Random.integer(0,ranTime);
    }
    return items;
  }
}
