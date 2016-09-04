package com.inHere.web;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.inHere.constant.Code;
import com.inHere.dto.BaseResultDto;
import com.inHere.entity.TbUser;
import com.inHere.validator.LoginValidator;
import com.inHere.validator.Params;

@RestController
@RequestMapping("/user")
public class LoginController {

	Logger log = Logger.getLogger(getClass());

	@Params(LoginValidator.class)
	@RequestMapping(path = "/log_in", method = RequestMethod.POST)
	public BaseResultDto login(@RequestBody TbUser user) {
		log.info("进入login()中-----");

		BaseResultDto result = BaseResultDto.create();
		result.setCode(Code.Success.getCode());
//		result.setStatus(Code.Success.getStatus());
//		result.put("code", code);
		result.put("userId", user.getUserId());
		result.put("passwd", user.getPasswd());
		return result;
	}

	@RequestMapping(path = "/log_out", method = RequestMethod.POST)
	public BaseResultDto logout(@RequestBody TbUser user) {
		log.info("进入login()中-----");
		BaseResultDto result = BaseResultDto.create();
		result.setCode(Code.Success.getCode());
		result.setStatus(Code.Success.getStatus());
		result.put("userId", user.getUserId());
		result.put("passwd", user.getPasswd());
		return result;
	}

}
