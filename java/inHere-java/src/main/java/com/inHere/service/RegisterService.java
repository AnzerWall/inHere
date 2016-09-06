package com.inHere.service;

import com.inHere.entity.User;

public interface RegisterService {

	/**
	 * 普通用户注册
	 * 
	 * @param userId
	 * @param password
	 * @return
	 */
	public User generalRegister(String userId, String password);

}
