package com.inHere.web;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.inHere.constant.Code;
import com.inHere.dto.BaseResultDto;
import com.inHere.entity.TbUser;

@RestController
@RequestMapping("/user")
public class LoginController {

	Logger log = Logger.getLogger(getClass());

	@RequestMapping(path = "/log_in/{name}", method = RequestMethod.PUT)
	public BaseResultDto<Map<String, Object>> login(@PathVariable String name, @RequestBody TbUser user) {
		BaseResultDto<Map<String, Object>> result = BaseResultDto.create();
		result.setCode(Code.Success.getCode());
		result.setStatus(Code.Success.getStatus());
		result.put("name", name);
		result.put("password", user.getPasswd());
		return result;
	}

}
