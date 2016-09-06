package com.inHere.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inHere.dao.UserMapper;
import com.inHere.entity.User;
import com.inHere.service.RegisterService;
import com.inHere.service.SecurityService;

/**
 * 用户注册业务 , 总是非事务地执行，并挂起任何存在的事务
 * 
 * @author lwh
 * 
 */
@Service
@Transactional
public class RegisterServiceImpl implements RegisterService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private SecurityService securityService;

	/**
	 * 普通用户注册
	 * 
	 * @param userId
	 * @param password
	 * @return
	 */
	public User generalRegister(String userId, String password) {
		User user = null;
		// TODO: 检查userID是否存在以及是否符合要求
		List<String> pwdAndSalt = securityService.encrypt(password);
		String newPwd = pwdAndSalt.get(0);
		String saltKey = pwdAndSalt.get(1);
		user = new User(userId, newPwd, saltKey);
		// userMapper.insertUser(user);
		return user;
	}
}
