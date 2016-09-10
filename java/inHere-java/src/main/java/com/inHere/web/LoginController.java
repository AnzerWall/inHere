package com.inHere.web;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.inHere.annotation.Authorization;
import com.inHere.annotation.CurrentToken;
import com.inHere.annotation.Params;
import com.inHere.constant.Code;
import com.inHere.dto.ReturnBaseDto;
import com.inHere.dto.UserDto;
import com.inHere.entity.Token;
import com.inHere.service.LoginService;
import com.inHere.validator.LoginValidator;

/**
 * 登陆模块
 * 
 * @author lwh
 *
 */
@RestController
public class LoginController {

	Logger log = Logger.getLogger(getClass());

	@Autowired
	private LoginService loginService;

	/**
	 * 登陆，创建一个Token资源
	 * 
	 * @return
	 * @throws IOException
	 */
	@Params(LoginValidator.class)
	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public ReturnBaseDto<UserDto> login(@RequestBody Map<String, Object> params) throws IOException {
		ReturnBaseDto<UserDto> result = new ReturnBaseDto<UserDto>();
		log.info("进入login()中-----");
		String user_id = (String) params.get("account");
		String password = (String) params.get("password");

		UserDto userDto = loginService.login(user_id, password);
		if (userDto == null) {
			result.setCode(Code.InputErr.getCode());
			result.setStatus(Code.InputErr.getStatus());
			result.setMessage("用户账号或密码错误");
		} else {
			result.setCode(Code.Success.getCode());
			result.setStatus(Code.Success.getStatus());
			result.setData(userDto);
		}
		return result;
	}

	/**
	 * 退出删除一个一个Token资源
	 * @param token
	 * @return
	 */
	@Authorization
	@RequestMapping(path = "/logout", method = RequestMethod.DELETE)
	public ReturnBaseDto<JSONObject> logout(@CurrentToken Token token) {
		log.info("进入logout()中-----");
		// 退出登陆
		loginService.logout(token);
		ReturnBaseDto<JSONObject> result = new ReturnBaseDto<JSONObject>();
		result.setCode(Code.Success.getCode());
		result.setStatus(Code.Success.getStatus());
		return result;
	}
	
	/**
	 * 注册一个Token资源
	 * @return
	 */
	@RequestMapping(path = "/logup", method = RequestMethod.POST)
	public ReturnBaseDto<JSONObject> logup(){
		
		return null;
	}
}
