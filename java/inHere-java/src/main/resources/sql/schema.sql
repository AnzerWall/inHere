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

/*Table structure for table `tb_ask_reply` */

DROP TABLE IF EXISTS `tb_ask_reply`;

CREATE TABLE `tb_ask_reply` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `ext_type` int(11) NOT NULL COMMENT '类别',
  `ext_data` json DEFAULT NULL COMMENT '类别数据',
  `label_id` int(11) NOT NULL COMMENT '标签id',
  `title` varchar(100) NULL COMMENT '标题',
  `content` varchar(512) DEFAULT NULL COMMENT '内容',
  `photos` varchar(300) DEFAULT NULL COMMENT '图片',
  `comment_num` int(11) DEFAULT NULL COMMENT '评论人数',
  `user_id` varchar(100) NOT NULL COMMENT '用户id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `praise` json DEFAULT NULL COMMENT '点赞用户列表',
  `low` json DEFAULT NULL COMMENT '踩用户列表',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `tb_ask_reply_user` */

DROP TABLE IF EXISTS `tb_ask_reply_user`;

CREATE TABLE `tb_ask_reply_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'follow关注编号',
  `user_id` varchar(100) NOT NULL COMMENT '用户账号',
  `ask_reply_id` int(11) NOT NULL COMMENT '问答id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `tb_comment` */

DROP TABLE IF EXISTS `tb_comment`;

CREATE TABLE `tb_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论编号',
  `ext_type` int(11) NOT NULL COMMENT '类别',
  `record_id` int(11) NOT NULL COMMENT '对应表记录编号',
  `content` varchar(500) DEFAULT NULL COMMENT '评论内容',
  `user_id` varchar(100) NOT NULL COMMENT '评论者',
  `name` varchar(128) NOT NULL COMMENT '匿名',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `low` json DEFAULT NULL COMMENT '踩用户列表',
  `praise` json DEFAULT NULL COMMENT '点赞用户列表',
  `floor` int(11) DEFAULT NULL COMMENT '评论楼层',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Table structure for table `tb_cryptonym` */

DROP TABLE IF EXISTS `tb_cryptonym`;

CREATE TABLE `tb_cryptonym` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '匿名编号',
  `name` varchar(50) NOT NULL COMMENT '匿名名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8894 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Table structure for table `tb_dynamic` */

DROP TABLE IF EXISTS `tb_dynamic`;

CREATE TABLE `tb_dynamic` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '动态编号',
  `ext_type` int(11) NOT NULL COMMENT '类别',
  `ext_data` json NOT NULL COMMENT '类别数据',
  `record_id` int(11) NOT NULL COMMENT '对应表的记录编号',
  `user_id` varchar(100) NOT NULL COMMENT '用户id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `tb_label` */

DROP TABLE IF EXISTS `tb_label`;

CREATE TABLE `tb_label` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标签编号',
  `ext_type` int(11) NOT NULL COMMENT '类别',
  `name` varchar(100) NOT NULL COMMENT '标签名称',
  `num` int(11) DEFAULT NULL COMMENT '标签数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `tb_note_notice` */

DROP TABLE IF EXISTS `tb_note_notice`;

CREATE TABLE `tb_note_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '纸条编号',
  `ext_type` int(11) NOT NULL COMMENT '类型',
  `record_id` int(11) NOT NULL COMMENT '记录编号',
  `send_user` varchar(100) NOT NULL COMMENT '发送者',
  `send_name` varchar(100) DEFAULT NULL COMMENT '发送者匿名',
  `accept_user` varchar(100) NOT NULL COMMENT '接送者',
  `accept_name` varchar(100) DEFAULT NULL COMMENT '接送者匿名',
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  `content_type` tinyint(4) NOT NULL COMMENT '内容类别',
  `content` varchar(500) DEFAULT NULL COMMENT '内容',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `has_read` tinyint(4) NOT NULL COMMENT '是否已读，0-未读，1-已读',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `tb_official_notice` */

DROP TABLE IF EXISTS `tb_official_notice`;

CREATE TABLE `tb_official_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '官方通知编号',
  `school_id` int(11) NOT NULL COMMENT '学校编号',
  `school` varchar(100) DEFAULT NULL COMMENT '学习名称',
  `user_id` varchar(100) NOT NULL COMMENT '创建者',
  `title` varchar(200) DEFAULT NULL COMMENT '标题',
  `content` text COMMENT '内容',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_hide` tinyint(4) DEFAULT NULL COMMENT '是否隐藏，0-否，1-是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `tb_permissions` */

DROP TABLE IF EXISTS `tb_permissions`;

CREATE TABLE `tb_permissions` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限编号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `tb_role` */

DROP TABLE IF EXISTS `tb_role`;

CREATE TABLE `tb_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色编号',
  `name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `tb_school` */

DROP TABLE IF EXISTS `tb_school`;

CREATE TABLE `tb_school` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '学校编号',
  `school` varchar(256) NOT NULL COMMENT '学校名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

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

/*Table structure for table `tb_target_list` */

DROP TABLE IF EXISTS `tb_target_list`;

CREATE TABLE `tb_target_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '清单编号',
  `title` varchar(100) NOT NULL COMMENT '清单标题',
  `text` varchar(300) DEFAULT NULL COMMENT '清单描述',
  `ext_type` int(11) NOT NULL COMMENT '类别',
  `ext_data` json DEFAULT NULL COMMENT '类别数据',
  `user_id` varchar(100) NOT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `praise` json DEFAULT NULL COMMENT '赞用户列表',
  `low` json DEFAULT NULL COMMENT '踩用户列表',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `tb_target_list_user` */

DROP TABLE IF EXISTS `tb_target_list_user`;

CREATE TABLE `tb_target_list_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `target_list_id` int(11) NOT NULL COMMENT '清单列表编号',
  `user_id` varchar(100) NOT NULL COMMENT '用户编号',
  `process` double DEFAULT NULL COMMENT '任务进度',
  `has_give_up` tinyint(4) DEFAULT NULL COMMENT '是否放弃，0-否，1-是',
  `is_pass` tinyint(4) DEFAULT NULL COMMENT '是否通过，0-未，1-已',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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

/*Table structure for table `tb_tip_notice` */

DROP TABLE IF EXISTS `tb_tip_notice`;

CREATE TABLE `tb_tip_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '提醒通知编号',
  `ext_type` int(11) NOT NULL COMMENT '类型',
  `record_id` int(11) NOT NULL COMMENT '记录编号',
  `accept_user` varchar(100) NOT NULL COMMENT '接受者',
  `action` tinyint(4) DEFAULT NULL COMMENT '动作, 1-@,2-新评论，3-被评论',
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  `content` varchar(500) DEFAULT NULL COMMENT '标题内容',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `has_read` tinyint(4) NOT NULL COMMENT '是否已读，0-未读，1已读',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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

/*Table structure for table `tb_user` */

DROP TABLE IF EXISTS `tb_user`;

CREATE TABLE `tb_user` (
  `user_id` varchar(100) NOT NULL COMMENT '账号',
  `passwd` varchar(200) NOT NULL COMMENT '密码',
  `salt_key` varchar(100) NOT NULL COMMENT '盐值',
  `user_name` varchar(100) DEFAULT NULL COMMENT '用户或组织名称',
  `head_img` varchar(100) DEFAULT NULL COMMENT '用户头像',
  `contact_way` json DEFAULT NULL COMMENT '联系方式：{  "phone": xxxx, "qq": xxx, "wechat": xxx  }',
  `sex` tinyint(1) DEFAULT NULL COMMENT '用户性别, 0-女，1-男',
  `area` varchar(100) DEFAULT NULL COMMENT '所在地区',
  `school_id` int(11) DEFAULT NULL COMMENT '学校id',
  `role_id` int(11) NOT NULL COMMENT '角色编号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
