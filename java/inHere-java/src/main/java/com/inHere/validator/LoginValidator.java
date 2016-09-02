package com.inHere.validator;

import java.util.Map;

import com.inHere.constant.Code;
import com.inHere.dto.BaseResultDto;
import com.inHere.entity.TbUser;

/**
 * 要求校验方法名与@Params拦截的方法的名字要相同, 校验方法的参数有且只能有一个，参数类型和返回类型都要和拦截方法的一样
 * 
 * @author lwh
 *
 */
public class LoginValidator {

	public BaseResultDto<Map<String, Object>> login(TbUser user) {
		System.out.println("lllllllllll");
		BaseResultDto<Map<String, Object>> result = BaseResultDto.create();
		result.setCode(Code.Success.getCode());
		result.setStatus(Code.Success.getStatus());
		result.put("----", "-------------");
		result.put("userId", null);
		result.put("passwd", user.getPasswd());
		return null;
	}

}
