package com.inHere.service;

import java.io.IOException;

import com.inHere.dto.UserDto;

public interface LoginService {

	/**
	 * 登陆操作
	 * 
	 * @param account
	 * @param password
	 * @return
	 */
	public UserDto login(String user_id, String password)  throws IOException ;

}
