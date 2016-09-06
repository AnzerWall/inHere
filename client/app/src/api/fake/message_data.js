/**
 * Created by anzer on 2016/9/6.
 */

// 1 官方通知
// 2 有求必应被at
// 3 有求必应被评论
// 4 有问必答被评论
// 5 有问必答被at
// 6 有槽必吐被at
// 7 有槽必吐被评论
// 8 有问必答关注的问题被评论

export default[
  {
  id:1,
  ext_type:1,
  user_id:1,
  ext_data: {
    title: '肇庆学院',
    text: '【重磅| 学生宿舍第一批空调已运抵！可以！这很夏天！】'
  },
  praised:true,
  create_time:moment("2016-8-23 9:25", "YYYY-MM-DD HH:mm").valueOf(),
  edit_time:moment("2016-8-23 9:25", "YYYY-MM-DD HH:mm").valueOf()
},
  {
  id:2,
  ext_type:1,
  user_id:1,
  ext_data: {
    title: '天翼校园一卡通',
    text: '温馨提示：国庆期间天翼一卡通服务中心上班时间为2,4,6号，8号后正常上班。由于校园网上学期办理的包学期有效期到2013年9月30日，为了避免10月1日断网，建议大四需要充值校园网的尽量在国庆放假前前往一卡通服务中心的前台充值。请周知！（30元/月，尽量自备零钱哦亲）'
  },
  praised:true,
  create_time:moment("2016-8-23 9:25", "YYYY-MM-DD HH:mm").valueOf(),
  edit_time:moment("2016-8-23 9:25", "YYYY-MM-DD HH:mm").valueOf()
},
  {
    id:3,
  ext_type:1,
  user_id:1,
  ext_data: {
    title: '图书馆关闭通知',
    text: '10月图书馆要进行内部翻修，会暂停使用大约15天，具体结束时间待通知。（以上都是我编的'
  },
  praised:true,
  create_time:moment("2016-8-23 9:25", "YYYY-MM-DD HH:mm").valueOf(),
  edit_time:moment("2016-8-23 9:25", "YYYY-MM-DD HH:mm").valueOf()
},
  {
    id:4,
  ext_type:1,
  user_id:0,
  ext_data: {

  },
  praised:true,
  create_time:moment("2016-8-23 9:25", "YYYY-MM-DD HH:mm").valueOf(),
  edit_time:moment("2016-8-23 9:25", "YYYY-MM-DD HH:mm").valueOf()
},
  {
    id:5,
  ext_type:1,
  user_id:0,
  ext_data: {
    title: '图书馆关闭通知',
    text: '10月图书馆要进行内部翻修，会暂停使用大约15天，具体结束时间待通知。（以上都是我编的'
  },
  praised:true,
  create_time:moment("2016-8-23 9:25", "YYYY-MM-DD HH:mm").valueOf(),
  edit_time:moment("2016-8-23 9:25", "YYYY-MM-DD HH:mm").valueOf()
}

]
