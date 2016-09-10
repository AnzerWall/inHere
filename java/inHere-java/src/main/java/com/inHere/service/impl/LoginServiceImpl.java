package com.inHere.service.impl;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.inHere.dao.UserMapper;
import com.inHere.dto.UserDto;
import com.inHere.entity.Token;
import com.inHere.entity.User;
import com.inHere.redis.TokenManage;
import com.inHere.service.LoginService;
import com.inHere.service.SecurityService;

/**
 * 登陆业务
 * 
 * @author lwh
 *
 */
@Service
public class LoginServiceImpl implements LoginService {

	Logger log = Logger.getLogger(getClass());

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private TokenManage tokenManage;

	@Autowired
	private SecurityService securityService;

	/**
	 * 登陆登陆校验
	 * 
	 * @param account
	 * @param password
	 * @return
	 * @throws IOException
	 */
	public UserDto login(String user_id, String password) throws IOException {
		User user = userMapper.selectByPrimaryKey(user_id);
		if (user == null)
			return null;
		log.info(user.toString());
		String saltKey = user.getSaltKey();
		// 获取加盐加密密码
		String safePwd = securityService.encrypt(saltKey, password);
		// 校验密码正确性
		if (user.getPasswd().equals(safePwd)) {
			// 创建返回user传输对象
			UserDto userDto = new UserDto();
			userDto.setUser_id(user.getUserId());
			userDto.setUser_name(user.getUserName());
			userDto.setHead_img(JSON.parseObject(user.getHeadImg()));
			JSONObject temp = JSON.parseObject(user.getContactWay());
			userDto.setContact_way(temp);
			userDto.setArea(user.getArea());
			userDto.setRole_id(user.getRoleId());
			userDto.setSchool_id(user.getSchoolId());
			userDto.setSchool(user.getSchool().getSchool());
			userDto.setSex(user.getSex());
			// 创建Token
			Token token = tokenManage.createToken(user);
			userDto.setToken(token.getKey());
			return userDto;
		}
		return null;
	}

	/**
	 * 退出登陆
	 * 
	 * @param token
	 * @return
	 */
	public void logout(Token token) {
		tokenManage.deleteToken(token);
	}

}
