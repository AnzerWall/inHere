package com.inHere.service.impl;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.inHere.constant.Field;
import com.inHere.service.PraiseService;

/**
 * 点赞业务
 * 
 * @author lwh
 *
 */
@Service
public class PraiseServiceImpl implements PraiseService {

	/**
	 * 获取点赞数目
	 * 
	 * @return
	 */
	@Override
	public Integer getPraiseSize(String praise) {
		if (praise == null)
			return 0;
		return JSON.parseObject(praise).size();
	}

	/**
	 * 判断用户是否点赞了
	 */
	@Override
	public Integer praised(String praise, String user_id) {
		if (praise == null)
			return 0;
		// 判断用户是否点赞了
		boolean flag = JSON.parseObject(praise).containsKey(user_id);
		return flag ? Field.Praised_YES : Field.Praised_NO;
	}

}
