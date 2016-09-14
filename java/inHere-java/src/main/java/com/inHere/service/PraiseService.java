package com.inHere.service;

public interface PraiseService {

	/**
	 * 获取点赞数目
	 * 
	 * @return
	 */
	public Integer getPraiseSize(String praise);

	/**
	 * 用户是否点赞了
	 * 
	 * @param praise
	 * @param user_id
	 * @return
	 */
	public Integer praised(String praise, String user_id);

}
