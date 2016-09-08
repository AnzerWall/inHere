/**
 * Created by anzer on 2016/9/5.
 */
import MockJs from 'mockjs';
let Random=MockJs.Random;
import {UserList,UserIdList } from './user.js'

let cryptonymList=[];
for(var id of UserIdList){
  cryptonymList[id]={
    id:id,
    cryptonym:Random.cword(3)
  };


}
export default cryptonymList;
