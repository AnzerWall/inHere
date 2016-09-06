insert  into tb_user(user_id, passwd, salt_key, head_img, contact_way, user_sex, user_area, user_school, role_id, create_time, update_time) 
values 
('anzcrwall','a1395cd7c963fad9647491b0f6116e44bba247c6acfb33d90fd070840a4f8986','506892fe4ec04169925795d24b7db05c',NULL,NULL,NULL,NULL,NULL, 1,NULL,NULL),
('kenefe','40634bbadd4a06331835b64adf936c99a6dd69f458b9c1c0fab5db3113d654e0','49d16e1b403940b58d53faf20b074d92',NULL,NULL,NULL,NULL,NULL, 1,NULL,NULL),
('LisaChan','fbef8dea6b8e41612eac6e76e56fe74e6eda1c5754e24a59c88f7c79910b1a56','3c7e03863203476695e759478a0f339d',NULL,NULL,NULL,NULL,NULL, 1,NULL,NULL),
('LLB','baa669b97f1e2f8572e0ba9c6af6293d346efb1f320744fdabbf0488353c7196','91b48aa7fc564c5c8bcd4f4b717b7115',NULL,NULL,NULL,NULL,NULL, 1,NULL,NULL),
('ni_menhao','62ec7c0cde5d74b3de38522781a66e7433ea7662551d610b007b0682aa81c99b','00a2146e966d4035b8148ff348525dac',NULL,NULL,NULL,NULL,NULL, 1,NULL,NULL),
('yibiao','95855389d40bd320bb24bbf35d4fccad3b3ebe1f1df0aeeac1b90ce32e155c34','ee254f4d96ee48bea244ee101808ffa6',NULL,NULL,NULL,NULL,NULL, 1,NULL,NULL);


INSERT  INTO tb_comment 
(id, ext_type, record_id, content, user_id, create_time, update_time, low, praise, FLOOR) 
VALUES
(1, 6, 6,'昨夜西风凉如雨， 破晓方知畅月浓。瘦草凄凄凄老树', 'LisaChan', NOW(),NULL,NULL,NULL,NULL),
(3, 6, 6,'残汁剩骨筹新宴， 落月西风送旧辰。', 'yibiao', NOW(),NULL,NULL,NULL,NULL),
(4, 6, 6,'残汁剩骨筹新宴， 落月西风送旧辰。', 'yibiao', NOW(),NULL,NULL,NULL,NULL),
(5, 6, 6,'残汁剩骨筹新宴， 落月西风送旧辰。', 'yibiao', NOW(),NULL,NULL,NULL,NULL),
(6, 6, 6,'残汁剩骨筹新宴， 落月西风送旧辰。', 'yibiao', NOW(),NULL,NULL,NULL,NULL),
(7, 6, 6,'残汁剩骨筹新宴， 落月西风送旧辰。', 'yibiao', NOW(),NULL,NULL,NULL,NULL),
(8, 6, 6,'残汁剩骨筹新宴， 落月西风送旧辰。', 'yibiao', NOW(),NULL,NULL,NULL,NULL),
(9, 6, 6,'残汁剩骨筹新宴， 落月西风送旧辰。', 'yibiao', NOW(),NULL,NULL,NULL,NULL),
(10, 6, 6,'残汁剩骨筹新宴， 落月西风送旧辰。', 'yibiao', NOW(),NULL,NULL,NULL,NULL);


--  初始化标签库
insert into `tb_type` (`id`, `name`, `config`, `create_time`, `update_time`) values 
 (1, '快递', '{"pay":{"name":"酬金","edit":false,"type":"double","style":"text","values":null},"word_desc":{"name":"文字描述","edit":false,"type":"string","style":"text","values":null},"photo_desc":{"name":"图片描述","edit":false,"type":"array","style":"img","values":null}}', NOW(), null)
,(2, '转让', '{"quality":{"name":"成色","edit":false,"type":"array","style":"select","values":["一成","二成","三成","四成","五成","六成","七成","八成","九成","十成"]},"buy_time":{"name":"购买时间","edit":false,"type":"date","style":"text","values":null},"original_price":{"name":"原价","edit":false,"type":"double","style":"text","values":null},"price":{"name":"售价","edit":false,"type":"double","style":"text","values":null},"word_desc":{"name":"文字描述","edit":false,"type":"string","style":"text","values":null},"photo_desc":{"name":"图片描述","edit":false,"type":"array","style":"img","values":null}}', NOW(), null)
,(3, '帮忙', '{"word_desc":{"name":"文字描述","edit":false,"type":"string","style":"text","values":null},"photo_desc":{"name":"图片描述","edit":false,"type":"array","style":"img","values":null},"resolved":{"name":"是否解决","edit":false,"type":"boolean","style":"radio","values":[0,1]}}', NOW(), null)
,(4, '丢失', '{"thing":{"name":"丢失的东西","edit":false,"type":"string","style":"text","values":null},"word_desc":{"name":"文字描述","edit":false,"type":"string","style":"text","values":null},"photo_desc":{"name":"图片描述","edit":false,"type":"array","style":"img","values":null},"lose_time":{"name":"丢失时间","edit":false,"type":"date","style":"text","values":null}}', NOW(), null)
,(5, '捡到', '{"thing":{"name":"捡到的东西","edit":false,"type":"string","style":"text","values":null},"pickeup_time":{"name":"捡到时间","edit":false,"type":"date","style":"text","values":null},"word_desc":{"name":"文字描述","edit":false,"type":"string","style":"text","values":null},"photo_desc":{"name":"图片描述","edit":false,"type":"array","style":"img","values":null}}', NOW(), null)
,(6, '走起', '{"gathering_time":{"name":"集中时间","edit":false,"type":"date","style":"text","values":null},"start_time":{"name":"开始时间","edit":false,"type":"date","style":"text","values":null},"end_time":{"name":"结束时间","edit":false,"type":"date","style":"text","values":null},"want_sex":{"name":"希望性别","edit":false,"type":"array","style":"select","values":[0,1,2]},"gathering_place":{"name":"集中地点","edit":false,"type":"string","style":"text","values":null},"place":{"name":"活动地点","edit":false,"type":"string","style":"text","values":null},"join_count":{"name":"参与人数","edit":false,"type":"int","style":"text","values":null},"join_list":{"name":"参与的人的列表","edit":false,"type":"array","style":null,"values":null},"word_desc":{"name":"文字描述","edit":false,"type":"string","style":"text","values":null}}', NOW(), null);




