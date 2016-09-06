DROP DATABASE IF EXISTS `inHere`;

CREATE DATABASE `inHere`;

USE `inHere`;

/*Table structure for table `tb_activity` */

DROP TABLE IF EXISTS `tb_activity`;

CREATE TABLE `tb_activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '活动编号',
  `title` varchar(100) NOT NULL COMMENT '标题',
  `ext_type` int(11) NOT NULL COMMENT '类别',
  `ext_data` json DEFAULT NULL COMMENT '类别数据',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `cover_img` varchar(200) DEFAULT NULL COMMENT '封面',
  `url_type` tinyint(4) NOT NULL COMMENT '链接类别，0-url，1-model模板',
  `url_content` text COMMENT '活动内容',
  `user_id` varchar(100) NOT NULL COMMENT '发布的组织',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `praise` json DEFAULT NULL COMMENT '点赞用户列表',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_activity` */

/*Table structure for table `tb_ask_reply` */

DROP TABLE IF EXISTS `tb_ask_reply`;

CREATE TABLE `tb_ask_reply` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `title` varchar(100) NOT NULL COMMENT '标题',
  `ext_type` int(11) NOT NULL COMMENT '类别',
  `ext_data` json DEFAULT NULL COMMENT '类别数据',
  `label_id` int(11) NOT NULL COMMENT '标签id',
  `comment_num` int(11) DEFAULT NULL COMMENT '评论人数',
  `user_id` varchar(100) NOT NULL COMMENT '用户id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `praise` json DEFAULT NULL COMMENT '点赞用户列表',
  `low` json DEFAULT NULL COMMENT '踩用户列表',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_ask_reply` */

/*Table structure for table `tb_comment` */

DROP TABLE IF EXISTS `tb_comment`;

CREATE TABLE `tb_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论编号',
  `ext_type` int(11) NOT NULL COMMENT '类别',
  `record_id` int(11) NOT NULL COMMENT '对应表记录编号',
  `content` varchar(500) DEFAULT NULL COMMENT '评论内容',
  `user_id` varchar(100) NOT NULL COMMENT '评论者',
  `cretae_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `low` json DEFAULT NULL COMMENT '踩用户列表',
  `praise` json DEFAULT NULL COMMENT '点赞用户列表',
  `floor` int(11) DEFAULT NULL COMMENT '评论楼层',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_comment` */

/*Table structure for table `tb_cryptonym` */

DROP TABLE IF EXISTS `tb_cryptonym`;

CREATE TABLE `tb_cryptonym` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '匿名编号',
  `name` varchar(50) NOT NULL COMMENT '匿名名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_cryptonym` */

/*Table structure for table `tb_cryptonym_used` */

DROP TABLE IF EXISTS `tb_cryptonym_used`;

CREATE TABLE `tb_cryptonym_used` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ext_type` int(11) NOT NULL COMMENT '类别',
  `record_id` int(11) NOT NULL COMMENT '对应表的记录编号',
  `user_id` varchar(100) NOT NULL COMMENT '用户id',
  `cryptonym_id` int(11) NOT NULL COMMENT '匿名id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_cryptonym_used` */

/*Table structure for table `tb_demand` */

DROP TABLE IF EXISTS `tb_demand`;

CREATE TABLE `tb_demand` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '需求编号',
  `ext_type` int(11) NOT NULL COMMENT '类别',
  `ext_data` json DEFAULT NULL COMMENT '类别数据',
  `text` varchar(500) DEFAULT NULL COMMENT '文字描述',
  `photos` json DEFAULT NULL COMMENT '图片描述',
  `is_end` tinyint(4) NOT NULL COMMENT '是否结束，0-未结束，1-结束',
  `user_id` varchar(50) NOT NULL COMMENT '用户id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `praise` json DEFAULT NULL COMMENT '点赞用户列表',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_demand` */

/*Table structure for table `tb_dynamic` */

DROP TABLE IF EXISTS `tb_dynamic`;

CREATE TABLE `tb_dynamic` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '动态编号',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `ext_type` int(11) NOT NULL COMMENT '类别',
  `ext_data` json NOT NULL COMMENT '类别数据',
  `record_id` int(11) NOT NULL COMMENT '对应表的记录编号',
  `user_id` varchar(100) NOT NULL COMMENT '用户id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_dynamic` */

/*Table structure for table `tb_label` */

DROP TABLE IF EXISTS `tb_label`;

CREATE TABLE `tb_label` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标签编号',
  `name` varchar(100) NOT NULL COMMENT '标签名称',
  `num` int(11) DEFAULT NULL COMMENT '标签数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_label` */

/*Table structure for table `tb_not_notice` */

DROP TABLE IF EXISTS `tb_not_notice`;

CREATE TABLE `tb_not_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `notification_id` int(11) NOT NULL COMMENT '信息id',
  `user_id` varchar(100) NOT NULL COMMENT '用户账号',
  `has_accept` tinyint(4) NOT NULL COMMENT '是否已接受',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_not_notice` */

/*Table structure for table `tb_notification` */

DROP TABLE IF EXISTS `tb_notification`;

CREATE TABLE `tb_notification` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '信息编号',
  `ext_type` int(11) NOT NULL COMMENT '类别',
  `ext_data` json DEFAULT NULL COMMENT '类别数据',
  `user_id` varchar(100) NOT NULL COMMENT '用户id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_notification` */

/*Table structure for table `tb_permissions` */

DROP TABLE IF EXISTS `tb_permissions`;

CREATE TABLE `tb_permissions` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限编号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_permissions` */

/*Table structure for table `tb_role` */

DROP TABLE IF EXISTS `tb_role`;

CREATE TABLE `tb_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色编号',
  `name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_role` */

/*Table structure for table `tb_target` */

DROP TABLE IF EXISTS `tb_target`;

CREATE TABLE `tb_target` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(100) NOT NULL COMMENT '标题',
  `text` varchar(500) DEFAULT NULL COMMENT '目标描述',
  `ext_type` int(11) DEFAULT NULL COMMENT '类别',
  `ext_data` json DEFAULT NULL COMMENT '类别数据',
  `has_finish` tinyint(4) NOT NULL COMMENT '是否完成',
  `target_list_id` int(11) NOT NULL COMMENT '所属清单id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_target` */

/*Table structure for table `tb_target_list` */

DROP TABLE IF EXISTS `tb_target_list`;

CREATE TABLE `tb_target_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '清单编号',
  `title` varchar(100) NOT NULL COMMENT '清单标题',
  `ext_type` int(11) NOT NULL COMMENT '类别',
  `ext_data` json DEFAULT NULL COMMENT '类别数据',
  `process` varchar(50) DEFAULT NULL COMMENT '过程状态，完成度',
  `user_id` varchar(100) NOT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `praise` json DEFAULT NULL,
  `low` json DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_target_list` */

/*Table structure for table `tb_target_list_user` */

DROP TABLE IF EXISTS `tb_target_list_user`;

CREATE TABLE `tb_target_list_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `target_list_id` int(11) DEFAULT NULL COMMENT '清单列表编号',
  `user_id` varchar(100) DEFAULT NULL COMMENT '用户编号',
  `is_pass` tinyint(4) DEFAULT NULL COMMENT '是否通过，0-未，1-已',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_target_list_user` */

/*Table structure for table `tb_target_user` */

DROP TABLE IF EXISTS `tb_target_user`;

CREATE TABLE `tb_target_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `target_id` int(11) NOT NULL COMMENT '目标编号',
  `user_id` varchar(100) NOT NULL COMMENT '用户编号',
  `ext_data` json DEFAULT NULL COMMENT '用户目标数据',
  `has_finish` tinyint(4) NOT NULL COMMENT '是否完成，0-未，1-已',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_target_user` */

/*Table structure for table `tb_tools` */

DROP TABLE IF EXISTS `tb_tools`;

CREATE TABLE `tb_tools` (
  `tool_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '工具编号',
  `tool_name` varchar(50) NOT NULL COMMENT '工具名称',
  `tool_pic` varchar(100) NOT NULL COMMENT '工具图标',
  `tool_url` varchar(100) NOT NULL COMMENT '工具链接',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`tool_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_tools` */

/*Table structure for table `tb_type` */

DROP TABLE IF EXISTS `tb_type`;

CREATE TABLE `tb_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标签编号',
  `name` varchar(100) NOT NULL COMMENT '标签名称',
  `config` json DEFAULT NULL COMMENT '标签配置',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `tb_type` */

insert  into `tb_type`(`id`,`name`,`config`,`create_time`,`update_time`) values (1,'快递','{\"pay\": {\"edit\": false, \"name\": \"酬金\", \"type\": \"double\", \"style\": \"text\", \"values\": null}, \"word_desc\": {\"edit\": false, \"name\": \"文字描述\", \"type\": \"string\", \"style\": \"text\", \"values\": null}, \"photo_desc\": {\"edit\": false, \"name\": \"图片描述\", \"type\": \"array\", \"style\": \"img\", \"values\": null}}','2016-09-05 17:43:33',NULL),(2,'转让','{\"price\": {\"edit\": false, \"name\": \"售价\", \"type\": \"double\", \"style\": \"text\", \"values\": null}, \"quality\": {\"edit\": false, \"name\": \"成色\", \"type\": \"array\", \"style\": \"select\", \"values\": [\"一成\", \"二成\", \"三成\", \"四成\", \"五成\", \"六成\", \"七成\", \"八成\", \"九成\", \"十成\"]}, \"buy_time\": {\"edit\": false, \"name\": \"购买时间\", \"type\": \"date\", \"style\": \"text\", \"values\": null}, \"word_desc\": {\"edit\": false, \"name\": \"文字描述\", \"type\": \"string\", \"style\": \"text\", \"values\": null}, \"photo_desc\": {\"edit\": false, \"name\": \"图片描述\", \"type\": \"array\", \"style\": \"img\", \"values\": null}, \"original_price\": {\"edit\": false, \"name\": \"原价\", \"type\": \"double\", \"style\": \"text\", \"values\": null}}','2016-09-05 17:43:33',NULL),(3,'帮忙','{\"resolved\": {\"edit\": false, \"name\": \"是否解决\", \"type\": \"boolean\", \"style\": \"radio\", \"values\": [0, 1]}, \"word_desc\": {\"edit\": false, \"name\": \"文字描述\", \"type\": \"string\", \"style\": \"text\", \"values\": null}, \"photo_desc\": {\"edit\": false, \"name\": \"图片描述\", \"type\": \"array\", \"style\": \"img\", \"values\": null}}','2016-09-05 17:43:33',NULL),(4,'丢失','{\"thing\": {\"edit\": false, \"name\": \"丢失的东西\", \"type\": \"string\", \"style\": \"text\", \"values\": null}, \"lose_time\": {\"edit\": false, \"name\": \"丢失时间\", \"type\": \"date\", \"style\": \"text\", \"values\": null}, \"word_desc\": {\"edit\": false, \"name\": \"文字描述\", \"type\": \"string\", \"style\": \"text\", \"values\": null}, \"photo_desc\": {\"edit\": false, \"name\": \"图片描述\", \"type\": \"array\", \"style\": \"img\", \"values\": null}}','2016-09-05 17:43:33',NULL),(5,'捡到','{\"thing\": {\"edit\": false, \"name\": \"捡到的东西\", \"type\": \"string\", \"style\": \"text\", \"values\": null}, \"word_desc\": {\"edit\": false, \"name\": \"文字描述\", \"type\": \"string\", \"style\": \"text\", \"values\": null}, \"photo_desc\": {\"edit\": false, \"name\": \"图片描述\", \"type\": \"array\", \"style\": \"img\", \"values\": null}, \"pickeup_time\": {\"edit\": false, \"name\": \"捡到时间\", \"type\": \"date\", \"style\": \"text\", \"values\": null}}','2016-09-05 17:43:33',NULL),(6,'走起','{\"place\": {\"edit\": false, \"name\": \"活动地点\", \"type\": \"string\", \"style\": \"text\", \"values\": null}, \"end_time\": {\"edit\": false, \"name\": \"结束时间\", \"type\": \"date\", \"style\": \"text\", \"values\": null}, \"want_sex\": {\"edit\": false, \"name\": \"希望性别\", \"type\": \"array\", \"style\": \"select\", \"values\": [0, 1, 2]}, \"join_list\": {\"edit\": false, \"name\": \"参与的人的列表\", \"type\": \"array\", \"style\": null, \"values\": null}, \"word_desc\": {\"edit\": false, \"name\": \"文字描述\", \"type\": \"string\", \"style\": \"text\", \"values\": null}, \"join_count\": {\"edit\": false, \"name\": \"参与人数\", \"type\": \"int\", \"style\": \"text\", \"values\": null}, \"start_time\": {\"edit\": false, \"name\": \"开始时间\", \"type\": \"date\", \"style\": \"text\", \"values\": null}, \"gathering_time\": {\"edit\": false, \"name\": \"集中时间\", \"type\": \"date\", \"style\": \"text\", \"values\": null}, \"gathering_place\": {\"edit\": false, \"name\": \"集中地点\", \"type\": \"string\", \"style\": \"text\", \"values\": null}}','2016-09-05 17:43:33',NULL);

/*Table structure for table `tb_user` */

DROP TABLE IF EXISTS `tb_user`;

CREATE TABLE `tb_user` (
  `user_id` varchar(100) NOT NULL COMMENT '账号',
  `passwd` varchar(200) NOT NULL COMMENT '密码',
  `salt_key` varchar(100) NOT NULL COMMENT '盐值',
  `head_img` varchar(100) DEFAULT NULL COMMENT '用户头像',
  `contact_way` json DEFAULT NULL COMMENT '联系方式：{  "phone": xxxx, "qq": xxx, "wechat": xxx  }',
  `user_sex` tinyint(1) DEFAULT NULL COMMENT '用户性别, 0-女，1-男',
  `user_area` varchar(100) DEFAULT NULL COMMENT '所在地区',
  `user_school` varchar(100) DEFAULT NULL COMMENT '所在学校',
  `role_id` int(11) NOT NULL COMMENT '角色编号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_user` */
