package com.inHere.validator;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.alibaba.fastjson.JSONObject;
import com.inHere.constant.Code;
import com.inHere.dto.ReturnBaseDto;

/**
 * 要求校验方法名与@Params拦截的方法的名字要相同, 校验方法的参数有且只能有一个，参数类型和返回类型都要和拦截方法的一样
 * 
 * @author lwh
 *
 */
@Component
public class LoginValidator {

	Logger log = Logger.getLogger(getClass());

	/**
	 * 用户登陆校验
	 * 
	 * @param params
	 * @return
	 */
	public ReturnBaseDto<JSONObject> login(@RequestBody Map<String, Object> params) {
		log.info("login参数校验");
		String user_id = (String) params.get("user_id");
		String passwd = (String) params.get("passwd");
		// return null通过校验
		if (user_id != null && passwd != null) {
			return null;
		}
		ReturnBaseDto<JSONObject> result = new ReturnBaseDto<JSONObject>();
		result.setCode(Code.Error.getCode());
		result.setStatus(Code.Error.getStatus());
		result.setMessage("用户名或密码有错");
		return result;
	}

}
