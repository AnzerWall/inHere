"use strict";
/**
 * Created by anzer on 2016/9/8.
 */
import _ from 'lodash'
import MockJs from 'mockjs';
let Random=MockJs.Random;


export  let UserList={
  "kenefe":{

  },
  "anzerwall":{

  },
  "yibiaoiii":{

  },
  "lrs":{

  },
  "nimenhao":{

  },
  "ly":{

  },
  "test1":{

  },
  "test2":{

  },
  "test3":{

  },
  "test4":{

  }
};


export  let UserIdList= _.keys(UserList);
export function  selectOneRandomUser(){
  let num =MockJs.Random.integer(0,UserIdList.length-1);
  return UserIdList[num];
}
