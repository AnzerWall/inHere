/**
 * Created by anzer on 2016/9/9.
 */
import  moment from 'moment'
let label={
  10:[
    {
      id: 1, //标签编号
      name: "你们饭堂有什么奇葩的菜式"
    },
    {
      id:2, //标签编号
      name: "说说哪些让你睡着的老师"
    },
    {
      id: 3, //标签编号
      name: "一起来吐槽女朋友"
    },
    {
      id: 4, //标签编号
      name: "一起来吐槽男票"
    }
  ],
  11:[
    {
      id: 1, //标签编号
      name: "小道消息"
    },
    {
      id:2, //标签编号
      name: "老师"
    },
    {
      id: 3, //标签编号
      name: "饭堂"
    },
    {
      id: 4, //标签编号
      name: "情感"
    }
  ],
  12:[
    {
      id: 1, //标签编号
      name: "宿舍"
    },
    {
      id:2, //标签编号
      name: "新生入学"
    },
    {
      id: 3, //标签编号
      name: "补考"
    },
    {
      id: 4, //标签编号
      name: "学习"
    }
  ]
};
let data=[{
  id: 1, //实体id
  ext_type: 10, // 类别，10-校内吐槽，11-校外吐槽，12-问答
  ext_data: {},  //私有属性
  user_id: "用户编号",
  title: "标题",
  photos: [
    {
      min: xxxx, // 小图数据base64
      src: url, // 大图url
      w: 100,	  //长
      h: 100    //高
    },
    // .........四张
  ],
  label_id: 2, //标签编号
  label_name: "标签名称",
  create_time: 223232312334, // 创建时间
  comment_num: 233, //评论数
  praise: 88  //赞数目
},
  {
    id: 1, //实体id
    ext_type: 12, // 类别，10-校内吐槽，11-校外吐槽，12-问答
    ext_data: {   //私有属性
      follow: 0, //关注帖子, 0-未，1-已
    },
    user_id: "用户编号",
    title: "标题",
    photos: [],
    label_id: 2, //标签编号
    label_name: "标签名称",
    create_time: 223232312334, // 创建时间
    comment_num: 233, //评论数
    praise: 88  //赞数目
  }];
