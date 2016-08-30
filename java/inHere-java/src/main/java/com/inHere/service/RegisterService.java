package com.inHere.service;

import com.inHere.entity.TbUser;

public interface RegisterService {

	/**
	 * 普通用户注册
	 * 
	 * @param userId
	 * @param password
	 * @return
	 */
	public TbUser generalRegister(String userId, String password);

}
