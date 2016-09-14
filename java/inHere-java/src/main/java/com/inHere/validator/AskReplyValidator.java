package com.inHere.validator;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.inHere.constant.Code;
import com.inHere.constant.Field;
import com.inHere.dto.ParamsListDto;
import com.inHere.dto.ReturnBaseDto;

/**
 * 吐槽+问答的校验类
 * 
 * @author lwh
 *
 */
public class AskReplyValidator {

	Logger log = Logger.getLogger(getClass());

	public ReturnBaseDto<JSONObject> getAskReplyList(ParamsListDto params) {
		// 参数中ext_type类型校验
		if (params.getExt_type() != null && params.getExt_type().length > 0) {
			Integer type = params.getExt_type()[0];
			// 正确类型的判断
			boolean flag = type == Field.ExtType_InTeasing || type == Field.ExtType_OutTeasing
					|| type == Field.ExtType_AskAnwser;
			if (flag) {
				return null;
			}
		}
		log.info(params);
		ReturnBaseDto<JSONObject> result = new ReturnBaseDto<JSONObject>();
		result.setCode(Code.InputErr.getCode());
		result.setStatus(Code.InputErr.getStatus());
		result.setMessage("参数有错");
		return result;
	}

}
