-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2016-08-27 08:10:32.99
DROP DATABASE IF EXISTS inHere;

CREATE DATABASE inHere;

USE inHere;

-- tables
-- Table: tb_activity_ad 活动广告
DROP TABLE IF EXISTS tb_activity_ad;

CREATE TABLE tb_activity_ad (
    ac_ad_id INT(10) NOT NULL AUTO_INCREMENT COMMENT '活动编号',
    ac_ad_title VARCHAR(300) NOT NULL COMMENT '标题',
    ac_ad_subtitle VARCHAR(200) NULL COMMENT '副标题',
    label_id INT(10) NOT NULL COMMENT '活动标签',
    label_data VARCHAR(100) NULL COMMENT '标签数据',
    praise_num TEXT NULL COMMENT '赞数目',
    start_time VARCHAR(50) NOT NULL COMMENT '开始时间',
    end_time VARCHAR(50) NULL COMMENT '结束时间',
    cover_img VARCHAR(100) NOT NULL COMMENT '封面',
    url_type TINYINT(1) NOT NULL COMMENT '链接类别，0-url，1-model模板，2-fullEdit富文本',
    url_content TEXT NOT NULL COMMENT '类别内容',
    has_ended TINYINT(1) NOT NULL COMMENT '是否已结束，0-未，1-已',
    user_id_push VARCHAR(100) NOT NULL COMMENT '发布的组织',
    create_time datetime DEFAULT NULL COMMENT '创建时间',
    update_time datetime DEFAULT NULL COMMENT '更新时间',
    CONSTRAINT tb_activity_ad_pk PRIMARY KEY (ac_ad_id)
)ENGINE=innodb DEFAULT CHARSET=utf8;

-- Table: tb_beg_help 有求必应
DROP TABLE IF EXISTS tb_beg_help;

CREATE TABLE tb_beg_help (
    help_id INT(10) NOT NULL AUTO_INCREMENT COMMENT '求应编号',
    lable_id INT(10) NOT NULL COMMENT '标签类别',
    label_data VARCHAR(100) NULL COMMENT '标签数据',
    word_desc VARCHAR(300) NOT NULL COMMENT '文字描述',
    pic_desc VARCHAR(300) NULL COMMENT '图片描述',
    praise_num TEXT NULL COMMENT '赞数目',
    has_resolved INT NOT NULL COMMENT '是否已解决',
    user_id_beg VARCHAR(100) NOT NULL COMMENT '请求人',
    create_time datetime DEFAULT NULL COMMENT '创建时间',
    update_time datetime DEFAULT NULL COMMENT '更新时间',
    CONSTRAINT tb_beg_help_pk PRIMARY KEY (help_id)
)ENGINE=innodb DEFAULT CHARSET=utf8;

-- Table: tb_break_barrier 有诺必行
DROP TABLE IF EXISTS tb_break_barrier;

CREATE TABLE tb_break_barrier (
    bar_id INT(10) NOT NULL AUTO_INCREMENT COMMENT '关卡编号，障碍(barrier)',
    bar_name VARCHAR(100) NOT NULL COMMENT '关卡名称',
    label_id INT(10) NULL COMMENT '标签编号',
    label_data VARCHAR(100) NULL COMMENT '标签数据',
    bar_process VARCHAR(100) NOT NULL COMMENT '过程状态，完成度',
    praise_num TEXT NULL COMMENT '点赞数量',
    low_num TEXT NULL COMMENT '踩的人数',
    begin_time VARCHAR(50) NOT NULL COMMENT '开始时间',
    end_time VARCHAR(50) NULL COMMENT '结束时间',
    has_pass TINYINT(1) NOT NULL COMMENT '关卡是否已通过，0-未，1-已',
    user_id_create VARCHAR(100) NOT NULL COMMENT '关卡创建者',
    create_time datetime DEFAULT NULL COMMENT '创建时间',
    update_time datetime DEFAULT NULL COMMENT '更新时间',
    CONSTRAINT tb_break_barrier_pk PRIMARY KEY (bar_id)
)ENGINE=innodb DEFAULT CHARSET=utf8;

-- Table: tb_comment 评论表
DROP TABLE IF EXISTS tb_comment;

CREATE TABLE tb_comment (
    comment_id INT(10) NOT NULL AUTO_INCREMENT COMMENT '评论编号',
    tab_name VARCHAR(50) NOT NULL COMMENT '栏目名称: break_barrier、beg_help、activity_ad、talk_circle、que_ans',
    record_id INT(10) NOT NULL COMMENT '对应记录编号',
    comment_content VARCHAR(200) NOT NULL COMMENT '评论内容',
    comment_time VARCHAR(50) NOT NULL COMMENT '评论时间',
    user_id_push VARCHAR(100) NOT NULL COMMENT '评论者',
    at_user_id VARCHAR(100) NULL COMMENT '@谁',
    praise_num TEXT NULL COMMENT '点赞数量',
    low_num TEXT NULL COMMENT '踩的人数',
    update_time datetime DEFAULT NULL COMMENT '更新时间',
    CONSTRAINT tb_comment_pk PRIMARY KEY (comment_id)
)ENGINE=innodb DEFAULT CHARSET=utf8;

-- Table: tb_dynamic 动态
DROP TABLE IF EXISTS tb_dynamic;

CREATE TABLE tb_dynamic (
    dy_id INT(10) NOT NULL AUTO_INCREMENT COMMENT '动态编号',
    begin_time VARCHAR(100) NOT NULL COMMENT '开始时间',
    dy_point VARCHAR(50) NOT NULL COMMENT '指向: { "tab_id" : xxx, "record_id" : xxx, "comment_id" : xxx }',
    user_id_master VARCHAR(100) NOT NULL COMMENT '动态所属人',
    create_time datetime DEFAULT NULL COMMENT '创建时间',
    update_time datetime DEFAULT NULL COMMENT '更新时间',
    CONSTRAINT tb_dynamic_pk PRIMARY KEY (dy_id)
)ENGINE=innodb DEFAULT CHARSET=utf8;

-- Table: tb_label 标签库
DROP TABLE IF EXISTS tb_label;

CREATE TABLE tb_label (
    label_id INT(10) NOT NULL AUTO_INCREMENT COMMENT '标签编号',
    label_name VARCHAR(50) NOT NULL COMMENT '标签名称',
    label_config TEXT NULL COMMENT '标签配置',
    create_time datetime DEFAULT NULL COMMENT '创建时间',
    update_time datetime DEFAULT NULL COMMENT '更新时间',
    CONSTRAINT tb_label_pk PRIMARY KEY (label_id)
)ENGINE=innodb DEFAULT CHARSET=utf8;

--  初始化标签库
insert into tb_label (label_id, label_name, label_config, create_time, update_time) values 
 (1, '快递', '{"pay":{"name":"酬金","edit":false,"type":"double","style":"text","values":null},"word_desc":{"name":"文字描述","edit":false,"type":"string","style":"text","values":null},"photo_desc":{"name":"图片描述","edit":false,"type":"array","style":"img","values":null}}', NOW(), null)
,(2, '转让', '{"quality":{"name":"成色","edit":false,"type":"array","style":"select","values":["一成","二成","三成","四成","五成","六成","七成","八成","九成","十成"]},"buy_time":{"name":"购买时间","edit":false,"type":"date","style":"text","values":null},"original_price":{"name":"原价","edit":false,"type":"double","style":"text","values":null},"price":{"name":"售价","edit":false,"type":"double","style":"text","values":null},"word_desc":{"name":"文字描述","edit":false,"type":"string","style":"text","values":null},"photo_desc":{"name":"图片描述","edit":false,"type":"array","style":"img","values":null}}', NOW(), null)
,(3, '帮忙', '{"word_desc":{"name":"文字描述","edit":false,"type":"string","style":"text","values":null},"photo_desc":{"name":"图片描述","edit":false,"type":"array","style":"img","values":null},"resolved":{"name":"是否解决","edit":false,"type":"boolean","style":"radio","values":[0,1]}}', NOW(), null)
,(4, '丢失', '{"thing":{"name":"丢失的东西","edit":false,"type":"string","style":"text","values":null},"word_desc":{"name":"文字描述","edit":false,"type":"string","style":"text","values":null},"photo_desc":{"name":"图片描述","edit":false,"type":"array","style":"img","values":null},"lose_time":{"name":"丢失时间","edit":false,"type":"date","style":"text","values":null}}', NOW(), null)
,(5, '捡到', '{"thing":{"name":"捡到的东西","edit":false,"type":"string","style":"text","values":null},"pickeup_time":{"name":"捡到时间","edit":false,"type":"date","style":"text","values":null},"word_desc":{"name":"文字描述","edit":false,"type":"string","style":"text","values":null},"photo_desc":{"name":"图片描述","edit":false,"type":"array","style":"img","values":null}}', NOW(), null)
,(6, '走起', '{"gathering_time":{"name":"集中时间","edit":false,"type":"date","style":"text","values":null},"start_time":{"name":"开始时间","edit":false,"type":"date","style":"text","values":null},"end_time":{"name":"结束时间","edit":false,"type":"date","style":"text","values":null},"want_sex":{"name":"希望性别","edit":false,"type":"array","style":"select","values":[0,1,2]},"gathering_place":{"name":"集中地点","edit":false,"type":"string","style":"text","values":null},"place":{"name":"活动地点","edit":false,"type":"string","style":"text","values":null},"join_count":{"name":"参与人数","edit":false,"type":"int","style":"text","values":null},"join_list":{"name":"参与的人的列表","edit":false,"type":"array","style":null,"values":null},"word_desc":{"name":"文字描述","edit":false,"type":"string","style":"text","values":null}}', NOW(), null)

-- Table: tb_notification 通知+小纸条
DROP TABLE IF EXISTS tb_notification;

CREATE TABLE tb_notification (
    notice_id INT(10) NOT NULL AUTO_INCREMENT COMMENT '信息编号',
    notice_type TINYINT(1) NOT NULL COMMENT '信息类别：0-通知、1-小纸条',
    `current_time` VARCHAR(50) NOT NULL COMMENT '当前时间',
    user_id_push VARCHAR(100) NOT NULL COMMENT '发送人',
    wait_accept_users TEXT NULL COMMENT '待接收人: [ id_1, id_2, id_3, .... ]',
    has_accept_users TEXT NULL COMMENT '已接收人: [ id_1, id_2, id_3, .... ]',
    notice_content VARCHAR(300) NOT NULL COMMENT '信息内容',
    notice_point VARCHAR(50) NOT NULL COMMENT '指向: { "tab_id" : xxx, "record_id" : xxx, "comment_id" : xxx }',
    is_over TINYINT(1) NOT NULL COMMENT '信息是否推送完毕，0-未，1-已',
    create_time datetime DEFAULT NULL COMMENT '创建时间',
    update_time datetime DEFAULT NULL COMMENT '更新时间',
    CONSTRAINT tb_notification_pk PRIMARY KEY (notice_id)
)ENGINE=innodb DEFAULT CHARSET=utf8;

-- Table: tb_permissions 权限
DROP TABLE IF EXISTS tb_permissions;

CREATE TABLE tb_permissions (
    perm_id INT NOT NULL AUTO_INCREMENT,
    create_time datetime DEFAULT NULL COMMENT '创建时间',
    update_time datetime DEFAULT NULL COMMENT '更新时间',
    CONSTRAINT tb_permissions_pk PRIMARY KEY (perm_id)
)ENGINE=innodb DEFAULT CHARSET=utf8;

-- Table: tb_que_ans 有问必答
DROP TABLE IF EXISTS tb_que_ans;

CREATE TABLE tb_que_ans (
    q_a_id INT(10) NOT NULL AUTO_INCREMENT COMMENT '问答编号',
    q_content VARCHAR(300) NOT NULL COMMENT '提问内容',
    q_time VARCHAR(50) NOT NULL COMMENT '提问时间',
    label_id INT(10) NOT NULL COMMENT '问题标签',
    label_data VARCHAR(100) NULL COMMENT '标签数据',
    praise_num TEXT NULL COMMENT '赞数目',
    low_num TEXT NULL COMMENT '踩数目',
    user_id_que VARCHAR(100) NOT NULL COMMENT '提问人',
    update_time datetime DEFAULT NULL COMMENT '更新时间',
    CONSTRAINT tb_que_ans_pk PRIMARY KEY (q_a_id)
)ENGINE=innodb DEFAULT CHARSET=utf8;

-- Table: tb_role 角色
DROP TABLE IF EXISTS tb_role;

CREATE TABLE tb_role (
    role_id INT NOT NULL AUTO_INCREMENT,
    create_time datetime DEFAULT NULL COMMENT '创建时间',
    update_time datetime DEFAULT NULL COMMENT '更新时间',
    CONSTRAINT tb_role_pk PRIMARY KEY (role_id)
)ENGINE=innodb DEFAULT CHARSET=utf8;

-- Table: tb_talk_circle 有槽必吐
DROP TABLE IF EXISTS tb_talk_circle;

CREATE TABLE tb_talk_circle (
    talk_id INT(10) NOT NULL AUTO_INCREMENT COMMENT '吐槽编号',
    talk_place TINYINT(1) NOT NULL COMMENT '0-校内，1-校外',
    label_id INT(10) NOT NULL COMMENT '标签自定义，校内可选小道消息，校外全自定义',
    label_data VARCHAR(100) NULL COMMENT '标签数据',
    talk_content VARCHAR(300) NULL COMMENT '吐槽文字',
    talk_pic VARCHAR(200) NULL COMMENT '吐槽图片',
    talk_time VARCHAR(50) NOT NULL COMMENT '吐槽时间',
    praise_num TEXT NULL COMMENT '赞数目',
    row_num TEXT NULL COMMENT '踩数目',
    user_id_talk VARCHAR(100) NOT NULL COMMENT '吐槽者',
    update_time datetime DEFAULT NULL COMMENT '更新时间',
    CONSTRAINT tb_talk_circle_pk PRIMARY KEY (talk_id)
)ENGINE=innodb DEFAULT CHARSET=utf8;

-- Table: tb_target 目标表
DROP TABLE IF EXISTS tb_target;

CREATE TABLE tb_target (
    tar_id INT(10) NOT NULL AUTO_INCREMENT COMMENT '目标编号',
    tar_name VARCHAR(50) NOT NULL COMMENT '目标名称',
    tar_desc VARCHAR(300) NULL COMMENT '目标描述',
    label_id INT(10) NULL COMMENT '标签编号',
    label_data VARCHAR(100) NULL COMMENT '标签数据',
    has_finish TINYINT(1) NOT NULL COMMENT '是否完成, 0-未，1-已',
    bar_id INT(10) NOT NULL COMMENT '所属关卡编号',
    create_time datetime DEFAULT NULL COMMENT '创建时间',
    update_time datetime DEFAULT NULL COMMENT '更新时间',
    CONSTRAINT tb_target_pk PRIMARY KEY (tar_id)
)ENGINE=innodb DEFAULT CHARSET=utf8;

-- Table: tb_tools 工具表
DROP TABLE IF EXISTS tb_tools;

CREATE TABLE tb_tools (
    tool_id INT NOT NULL AUTO_INCREMENT COMMENT '工具编号',
    tool_name VARCHAR(50) NOT NULL COMMENT '工具名称',
    tool_pic VARCHAR(100) NOT NULL COMMENT '工具图标',
    tool_url VARCHAR(100) NOT NULL COMMENT '工具链接',
    create_time datetime DEFAULT NULL COMMENT '创建时间',
    update_time datetime DEFAULT NULL COMMENT '更新时间',
    CONSTRAINT tb_tools_pk PRIMARY KEY (tool_id)
)ENGINE=innodb DEFAULT CHARSET=utf8;

-- Table: tb_user 用户表
DROP TABLE IF EXISTS tb_user;

CREATE TABLE tb_user (
    user_id VARCHAR(100) NOT NULL COMMENT '账号',
    passwd VARCHAR(100) NOT NULL COMMENT '密码',
    salt_key VARCHAR(100) NOT NULL COMMENT '盐值',
    head_img VARCHAR(100) NULL COMMENT '用户头像',
    contact_way VARCHAR(100) NULL COMMENT '联系方式：{  "phone": xxxx, "qq": xxx, "wechat": xxx  }',
    user_sex TINYINT(1) NULL COMMENT '用户性别, 0-女，1-男',
    user_area VARCHAR(200) NULL COMMENT '所在地区',
    user_school VARCHAR(50) NULL COMMENT '所在学校',
    create_time datetime DEFAULT NULL COMMENT '创建时间',
    update_time datetime DEFAULT NULL COMMENT '更新时间',
    CONSTRAINT tb_user_pk PRIMARY KEY (user_id)
)ENGINE=innodb DEFAULT CHARSET=utf8;

-- Table: tb_cryptonym 匿名表
DROP TABLE IF EXISTS tb_cryptonym;

CREATE TABLE tb_cryptonym(
	crypt_id INT NOT NULL AUTO_INCREMENT COMMENT '匿名编号',
	crypt_name VARCHAR(50) NOT NULL COMMENT '匿名名称',
	create_time datetime DEFAULT NULL COMMENT '创建时间',
	CONSTRAINT tb_user_pk PRIMARY KEY (crypt_id)
)ENGINE=innodb DEFAULT CHARSET=utf8;

-- End of file.

