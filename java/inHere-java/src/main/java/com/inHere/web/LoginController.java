package com.inHere.web;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.inHere.constant.Code;
import com.inHere.dto.ReturnBaseDto;
import com.inHere.entity.User;
import com.inHere.validator.LoginValidator;
import com.inHere.validator.Params;

@RestController
@RequestMapping("/user")
public class LoginController {

	Logger log = Logger.getLogger(getClass());

	@Params(LoginValidator.class)
	@RequestMapping(path = "/log_in", method = RequestMethod.POST)
	public ReturnBaseDto<JSONObject> login(@RequestBody User user) {
		log.info("进入login()中-----");

		ReturnBaseDto<JSONObject> result = new ReturnBaseDto<JSONObject>();
		result.setCode(Code.Success.getCode());
		// result.setStatus(Code.Success.getStatus());
		// result.put("code", code);
		result.getData().put("userId", user.getUserId());
		result.getData().put("passwd", user.getPasswd());
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
