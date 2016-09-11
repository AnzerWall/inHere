package com.inHere.service;

import java.io.IOException;

import com.inHere.dto.UserDto;
import com.inHere.entity.Token;

public interface LoginService {

	/**
	 * 登陆操作
	 * 
	 * @param account
	 * @param password
	 * @return
	 */
	public UserDto login(String user_id, String password) throws IOException;

	/**
	 * 退出登陆
	 * 
	 * @param token
	 * @return
	 */
	public void logout(Token token);

}
