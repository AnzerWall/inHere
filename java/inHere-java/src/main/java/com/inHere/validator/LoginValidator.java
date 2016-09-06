package com.inHere.validator;

import com.alibaba.fastjson.JSONObject;
import com.inHere.constant.Code;
import com.inHere.dto.ReturnBaseDto;
import com.inHere.entity.User;

/**
 * 要求校验方法名与@Params拦截的方法的名字要相同, 校验方法的参数有且只能有一个，参数类型和返回类型都要和拦截方法的一样
 * 
 * @author lwh
 *
 */
public class LoginValidator {

	public ReturnBaseDto<JSONObject> login(User user) {
		System.out.println("lllllllllll");
		ReturnBaseDto<JSONObject> result = new ReturnBaseDto<JSONObject>();
		result.setCode(Code.Success.getCode());
		result.setStatus(Code.Success.getStatus());
		result.setData(new JSONObject());
		result.getData().put("userId", null);
		result.getData().put("passwd", user.getPasswd());
		return null;
	}

}
