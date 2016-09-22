package com.inHere.service;

import com.inHere.entity.Token;

public interface PraiseService {

	/**
	 * 获取点赞数目
	 * 
	 * @return
	 */
	Integer getPraiseSize(String praise);

	/**
	 * 用户是否点赞了
	 * 
	 * @param praise
	 * @param user_id
	 * @return
	 */
	Integer praised(String praise, String user_id);

	/**
	 * 点赞销赞
	 * @return
	 */
	boolean praise(Integer ext_type, Integer item_id, Token token);

}
