/**
 * Created by anzer on 2016/9/3.
 */
import  moment from 'moment'
export default [
  {
  item_id:1,
  label_id:1,
  label_name:"快递",
  photo_desc:[],
  user_id:1,
  word_desc:"求帮拿个快递。。。f1  3块有人愿意吗",
  create_time: moment("2016-8-27 13:44").valueOf(),
  private_data: {		//私有属性
    pay:3//酬金
  },
  is_resolved: 0 // 是否已结束，0未结束，1结束
  },
  {
    item_id:1,
    label_id:1,
    label_name:"快递",
    photo_desc:[],
    user_id:1,
    word_desc:"谁帮我那个快递到厚德书院啊，5块钱，直接悄悄话我",
    create_time: moment("2016-8-27 13:44").valueOf(),
    private_data: {		//私有属性
      pay:5//酬金
    },
    is_resolved: 1 // 是否已结束，0未结束，1结束
  },
  {
    item_id:1,
    label_id:1,
    label_name:"快递",
    photo_desc:[],
    user_id:1,
    private_data: {		//私有属性
      pay:15//酬金
    },
    is_resolved: 0 // 是否已结束，0未结束，1结束
  },
]
/*


},
{
  item_id: 24，// 记录编号
  label_id: 2,
    label_name: "转让",
  create_time: "创建时间",
  word_desc: "文字描述",
  photo_desc: [ //图片描述
  {
    min: xxxx, // 小图数据base64
    src: url, // 大图url
    w: 100,	  //长
    h: 100    //高
  }
],
  user_id: "创建者",
  private_data: {		//私有属性
  quality: "成色",
    buy_time: "购买时间",
    original_price: "原价",
    price: "售价"
}
  is_resolved: 0, // 是否已结束，0未结束，1结束
},
{
  item_id: 25，// 记录编号
  label_id: 3,
    label_name: "帮忙",
  create_time: "创建时间",
  word_desc: "文字描述",
  photo_desc: [ //图片描述
  {
    min: xxxx, // 小图数据base64
    src: url, // 大图url
    w: 100,	  //长
    h: 100    //高
  }
  // ..............
],
  user_id: "创建者",
  private_data: null,	//私有属性
  is_resolved: 0, // 是否已结束，0未结束，1结束
}
// ...................
]

*/
