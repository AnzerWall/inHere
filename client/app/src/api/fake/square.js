/**
 * Created by anzer on 2016/9/6.
 */
import _ from 'lodash'
import  moment from 'moment'
let data = {
  activity: { //活动
    cover_img: {
      src: "http://ww1.sinaimg.cn/mw1024/6f924588gw1f32g8p67w6j20qo123wjv.jpg",
      h: 960,
      w: 1371,
      min: ""
    },

    title: "小清新微电影《一半》正片上映",
    user_name: "影视文化协会",
    start_time: moment("2016-7-12 0:0", "YYYY-MM-DD HH:mm").valueOf(),
    end_time: moment("2016-8-31 0:0", "YYYY-MM-DD HH:mm").valueOf()
  },
  target_list: { //有诺必行
    items: [
      {
        id: 2, //清单编号
        title: "从修电脑到过夜"
      }, {
        id: 3, //清单编号
        title: "如何成为一枚学神"
      }, {
        id: 4, //清单编号
        title: "吃货攻略清单"
      }, {
        id: 5, //清单编号
        title: "来到肇庆学院必去的5个地点"
      }, {
        id: 1, //清单编号
        title: "it达人大一篇"
      }
      // ......五条
    ]
  },
  ask_reply: { //有问必答
    items: [
      {
        id: 11,
        title: "军训服丢了怎么办？",
      },
      {
        id: 11,
        title: "学费最晚什么时候交？"
      },
      {
        id: 11,
        title: "怎么才能上网"
      },
      {
        id: 11,
        title: "能带电脑吗"
      }
    ],
    best_reply: {  //最火的回答
      id: 12,
      title: "哪家饭堂最好吃",
      reply_id: 14,
      best_answer: "四饭，不解释",
    }
  },
  teasing: {　//吐槽
    items: [
      {
        id: 11,
        title: "你们饭堂有什么奇葩的菜式"
      }, {
        id: 11,
        title: "说说哪些让你睡着的老师"
      }, {
        id: 11,
        title: "女朋友"
      }, {
        id: 11,
        title: "男票"
      }
      // ......五条
    ],
    best_reply: {  //最火的回答
      id: 12,
      title: "你的室友做过哪些让你意想不到的事情",
      reply_id: 14,
      best_answer: "我宿友上课的时候坐我旁边总是摸我大腿怎么办，急，在线",
    }
  }
};

export default{
  getList(req){
    return {
      ok: true,
      body: {
        code: 200,
        status: "success",
        data: _.cloneDeep(data)

      }
    };

  }
}
