package com.inHere.dao;

import com.inHere.entity.TbUser;

/**
 * 用户表操作映射接口
 * 
 * @author lwh
 * @date 2016-08-27
 */
public interface TbUserMapper {

	/**
	 * 通过账号查询用户信息
	 * 
	 * @param userId
	 * @return
	 */
	public TbUser getUserByUserId(String userId);

	/**
	 * 新增账号
	 * 
	 * @param user
	 * @return
	 */
	public int insertUser(TbUser user);

}