/**
 * Created by anzer on 2016/9/8.
 */
import _ from 'lodash'
import  moment from 'moment'
import {selectOneRandomUser } from './user.js'

//content_type 0文字 1图片
"use strict";
/**
 * Created by anzer on 2016/9/8.
 */

let data = [
  {
    id: 1,
    ext_type: 1,
    record_id: 1,
    send_user: selectOneRandomUser(),
    accept_user: "",
    title: "之前在树洞看到有转让kenefe音乐会的门票的微博，现在找不到了，能麻烦发布者联系下我吗",
    content_type: 0,
    content: "这个东西不能在降价了",
    total: 2
  },
  {
    id: 2,
    ext_type: 1,
    record_id:5,
    send_user: selectOneRandomUser(),
    accept_user: "",
    title: "北岭山，约吗",
    content_type:0,
    content: "你好，请问什么时候过去",
    total: 1
  },
  {
    id: 2,
    ext_type: 1,
    record_id:6,
    send_user: selectOneRandomUser(),
    accept_user: "",
    title: "谁能帮小k我买个早餐啊，万分感谢DAZE ~☆",
    content_type:0,
    content: "你在哪里？",
    total: 0,
  }];

export default{
  getList(req){
    return {
      ok: true,
      body: {
        code: 200,
        status: "success",
        data: {
          items: _.cloneDeep(data)
        }
      }
    };

  }
}
