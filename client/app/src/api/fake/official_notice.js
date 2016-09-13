"use strict";
/**
 * Created by anzer on 2016/9/8.
 */
import _ from 'lodash'
import  moment from 'moment'
let data=[
  {
    id:1,
    school_id:12,
    school: '肇庆学院',
    title: '肇庆学院',
    user_id:"zqu",
    content: '【重磅| 学生宿舍第一批空调已运抵！可以！这很夏天！】',
    create_time:moment("2016-8-23 9:25", "YYYY-MM-DD HH:mm").valueOf(),
    edit_time:moment("2016-8-23 9:25", "YYYY-MM-DD HH:mm").valueOf()
  },
  {
    id:2,
    school_id:12,
    school: '肇庆学院',
    title: '图书馆关闭通知',
    user_id:"zqu",
    content:  '10月图书馆要进行内部翻修，会暂停使用大约15天，具体结束时间待通知。（以上都是我编的',
    create_time:moment("2016-8-23 9:25", "YYYY-MM-DD HH:mm").valueOf(),
    edit_time:moment("2016-8-23 9:25", "YYYY-MM-DD HH:mm").valueOf()
  },
  {
    id:3,
    school_id:12,
    school: '肇庆学院',
    title: '天翼一卡通',
    user_id:"zqu",
    content: '温馨提示：国庆期间天翼一卡通服务中心上班时间为2,4,6号，8号后正常上班。由于校园网上学期办理的包学期有效期到2013年9月30日，为了避免10月1日断网，建议大四需要充值校园网的尽量在国庆放假前前往一卡通服务中心的前台充值。请周知！（30元/月，尽量自备零钱哦亲）',
    create_time:moment("2016-8-23 9:25", "YYYY-MM-DD HH:mm").valueOf(),
    edit_time:moment("2016-8-23 9:25", "YYYY-MM-DD HH:mm").valueOf()
  }];

export default{
  getList(req){
    return {
      ok: true,
      body: {
        code: 200,
        status:"success",
        data:{
          total:data.length,
          items: _.cloneDeep(data)
        }
      }
    };

  }
}
