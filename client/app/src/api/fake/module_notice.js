/**
 * Created by anzer on 2016/9/8.
 */
import _ from 'lodash'
import  moment from 'moment'
//ext_type
//1 – demand
//7 – quz
//8 – 活动
//9 – 广告
//10 – 校外吐槽
//11 – 校内吐槽
//12 – 问答

//action
//1 - 被评论



let data = [
  {
    id: 1, // 实体id
    ext_type: 1,
    record_id: 1,
    accept_user: "LLB",
    action: 2,
    content: "之前在树洞看到有转让kenefe音乐会的门票的微博，现在找不到了，能麻烦发布者联系下我吗",
    total: 22,
    create_time: moment("2016-8-23 9:25", "YYYY-MM-DD HH:mm").valueOf(),
  },
  {
    id: 2, // 实体id
    ext_type: 1,
    record_id: 2,
    accept_user: "谁能帮小k我买个早餐啊，万分感谢DAZE ~☆",
    action: 1,
    content: "内容",
    total: 2,
    create_time:moment("2016-8-23 9:25", "YYYY-MM-DD HH:mm").valueOf(),
  },
  {
    id: 3, // 实体id
    ext_type: 1,
    record_id: 3,
    accept_user: "LLB",
    action: 2,
    content: "北岭山，约吗",
    total: 0,
    create_time: moment("2016-8-23 9:25", "YYYY-MM-DD HH:mm").valueOf(),
  }
];


export default{
  getList(req){
    return {
      ok: true,
      body: {
        code: 200,
        status: "success",
        data: {
          total: data.length,
          items: _.cloneDeep(data)
        }
      }
    };

  }
}
