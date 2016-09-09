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
import com.inHere.constant.Code;
import com.inHere.dto.ReturnBaseDto;
import com.inHere.dto.UserDto;
import com.inHere.entity.User;
import com.inHere.service.LoginService;
import com.inHere.validator.LoginValidator;
import com.inHere.validator.Params;

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
	
	

	@RequestMapping(path = "/log_out", method = RequestMethod.POST)
	public ReturnBaseDto<JSONObject> logout(@RequestBody User user) {
		log.info("进入login()中-----");
		ReturnBaseDto<JSONObject> result = new ReturnBaseDto<JSONObject>();
		result.setCode(Code.Success.getCode());
		result.setStatus(Code.Success.getStatus());
		result.getData().put("userId", user.getUserId());
		result.getData().put("passwd", user.getPasswd());
		return result;
	}

}
