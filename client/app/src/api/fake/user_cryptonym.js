/**
 * Created by anzer on 2016/9/5.
 */
import MockJs from 'mockjs';
let Random=MockJs.Random;
let users=[];
for(let i=1;i<=10;i++){
  users.push({
    id:i,
    cryptonym:Random.cword(3)
  })
}
export default users;
