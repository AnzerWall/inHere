package com.inHere.service;

import java.util.List;

import com.inHere.entity.TbUser;

public interface SecurityService {

	/**
	 * 获取加盐加密密码和盐值
	 * 
	 * @param passwd
	 * @return list.get(0) --> password <br>
	 *         list.get(1) --> saltKey <br>
	 */
	public List<String> getPasswordAndSalt(String password);

	/**
	 * 密码验证
	 * 
	 * @param password
	 * @return
	 */
	public boolean verify(TbUser user, String password);

}
