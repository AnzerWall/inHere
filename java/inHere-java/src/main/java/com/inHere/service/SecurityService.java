package com.inHere.service;

import java.util.List;

public interface SecurityService {

	/**
	 * 加密
	 * 
	 * @param passwd
	 * @return 加盐加密密码和盐值list.get(0) : password | list.get(1) : saltKey
	 * 
	 */
	public List<String> encrypt(String password);

	/**
	 * 加密
	 * 
	 * @param password
	 * @return safePwd加密后的密码
	 */
	public String encrypt(String saltKey, String password);

	/**
	 * token创建
	 * 
	 * @param userId
	 * @return
	 */
	public String createToken(String userId);

}
