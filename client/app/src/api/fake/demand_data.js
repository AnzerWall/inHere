/**
 * Created by anzer on 2016/9/3.
 */
import  moment from 'moment'

import MockJs from 'mockjs';
let Random=MockJs.Random;
//1 express
// 2 sell
//3 help
//4 lost
//5 found
//6 dating
//预设的demand数据
export default [
  {
    id: 1,
    ext_type: 1,
    ext_data: {		//私有属性
      pay: 3//酬金
    },
    user_id:Random.integer(1,10),
    praise: 1,
    praised:true,
    photos: [],
    is_end: 0,
    text: "求帮拿个快递。。。f1  3块有人愿意吗",
    create_time: moment("2016-8-27 13:44", "YYYY-MM-DD HH:mm").valueOf(),
    update_time: moment("2016-8-27 13:44", "YYYY-MM-DD HH:mm").valueOf()
  },
  {
    id: 2,
    ext_type: 1,
    ext_data: {		//私有属性
      pay: 5//酬金
    },
    user_id:Random.integer(1,10),
    praise: 0,
    praised:false,
    photos: [],
    is_end: 1,
    text: "谁帮我那个快递到厚德书院啊，5块钱，直接悄悄话我",
    create_time: moment("2016-8-23 9:25", "YYYY-MM-DD HH:mm").valueOf(),
    update_time: moment("2016-8-23 9:25", "YYYY-MM-DD HH:mm").valueOf()
  },
  {
    id: 3,
    ext_type: 2,
    ext_data: {		//私有属性
      price: 49,//酬金
      quality: 9.9,
      buy_time: moment("2016-8-1 0:00", "YYYY-MM-DD HH:mm").valueOf(),
      original_price: 89
    },
    user_id:Random.integer(1,10),
    praise: 0,
    praised:false,

    photos: [
      {

        src: 'http://ww4.sinaimg.cn/mw690/e910bd00jw1f7c80cfvyoj20qo0zk75i.jpg',
        w: 360,
        h: 480

      },
      {
        src: 'http://ww1.sinaimg.cn/mw690/e910bd00jw1f7c1ndknjwj20qo0zktat.jpg',
        w: 690,
        h: 920
      },
      {
        src: 'http://ww3.sinaimg.cn/mw690/e910bd00jw1f7c3j00tqij20qo0zkjts.jpg',
        w: 360,
        h: 480
      }
    ],
    is_end: 0,
    text: "出一条全新的牛仔a字裙，49元，适合平时穿SM码数的美眉，想买的评论",
    create_time: moment("2016-8-23 9:25", "YYYY-MM-DD HH:mm").valueOf(),
    update_time: moment("2016-8-23 9:25", "YYYY-MM-DD HH:mm").valueOf()
  },
  {
    id: 4,
    ext_type: 3,
    ext_data: {		//私有属性

    },
    user_id:Random.integer(1,10),
    praise: 0,
    photos: [],
    praised:false,

    is_end: 0,
    text: "谁能帮嘉彦妹纸我打个饭",
    create_time: moment("2016-8-23 10:25", "YYYY-MM-DD HH:mm").valueOf(),
    update_time: moment("2016-8-23 10:25", "YYYY-MM-DD HH:mm").valueOf()
  },
  {
    id: 5,
    ext_type: 4,
    ext_data: {
      thing: "银行卡"
    },
    user_id:Random.integer(1,10),
    praise: 0,
    praised:false,
    photos: [{
      src: 'http://ww3.sinaimg.cn/mw690/e910bd00jw1f5exrm7glwj20qo0zkq55.jpg',
      w: 360,
      h: 480
    }],
    is_end: 1,
    text: "我丢了张银行卡，就图上那张，是在3饭到G栋那边丢的，有谁看到过吗，要交学费了，很急",
    create_time: moment("2016-8-23 9:25", "YYYY-MM-DD HH:mm").valueOf(),
    update_time: moment("2016-8-23 9:25", "YYYY-MM-DD HH:mm").valueOf()
  },
  {
    id: 6,
    ext_type: 5,
    ext_data: {		//私有属性
      thing: "苹果"//酬金
    },
    user_id:Random.integer(1,10),
    praise: 0,
    praised:false,

    photos: [],
    is_end: 1,
    text: "灰姑娘今天捡到了一个有毒的苹果",
    create_time: moment("2016-8-23 9:25", "YYYY-MM-DD HH:mm").valueOf(),
    update_time: moment("2016-8-23 9:25", "YYYY-MM-DD HH:mm").valueOf()
  },
  {
    id: 7,
    ext_type: 6,
    ext_data: {		//私有属性
      money: 55,//预计消费
      want_sex: 2,
      address: "北岭山",
      start_time: moment("2016-8-23 9:25", "YYYY-MM-DD HH:mm").valueOf(),
      wait_location: "后山商业街",
      end_time: moment("2016-8-23 9:25", "YYYY-MM-DD HH:mm").valueOf()
    },
    user_id:Random.integer(1,10),
    praise: 0,
    praised:false,

    photos: [],
    is_end: 0,
    text: "有没有妹纸想一起去北岭山？",
    create_time: moment("2016-8-23 9:25", "YYYY-MM-DD HH:mm").valueOf(),
    update_time: moment("2016-8-23 9:25", "YYYY-MM-DD HH:mm").valueOf()
  },
  {
    id: 8,
    ext_type: 6,
    ext_data: {		//私有属性
      money: 0,//预计消费
      want_sex: 1,
      address: "玩具厂",
      start_time: moment("2016-8-23 9:25", "YYYY-MM-DD HH:mm").valueOf(),
      wait_location: "校门口",
      end_time: moment("2016-8-23 9:25", "YYYY-MM-DD HH:mm").valueOf()

    },
    user_id:Random.integer(1,10),
    praise: 23,
    praised:false,

    photos: [],
    is_end: 0,
    text: "有没有男生想一起去打工的？",
    create_time: moment("2016-8-23 9:25", "YYYY-MM-DD HH:mm").valueOf(),
    update_time: moment("2016-8-23 9:25", "YYYY-MM-DD HH:mm").valueOf()
  },
  {
    id: 9,
    ext_type: 6,
    ext_data: {		//私有属性
      money: 55,//预计消费
      want_sex: 2,
      address: "下午F栋篮球场",

      start_time: moment("2016-8-23 9:25", "YYYY-MM-DD HH:mm").valueOf(),
      wait_location: "F栋门口",
      end_time: moment("2016-8-23 9:25", "YYYY-MM-DD HH:mm").valueOf()
    },
    user_id:Random.integer(1,10),
    praise: 15,
    praised:false,
    photos: [],
    is_end: 1,
    text: "打波？",
    create_time: moment("2016-8-23 9:25", "YYYY-MM-DD HH:mm").valueOf(),
    update_time: moment("2016-8-23 9:25", "YYYY-MM-DD HH:mm").valueOf()
  }
]
