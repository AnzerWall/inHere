package com.inHere.validator;

import com.alibaba.fastjson.JSONObject;
import com.inHere.constant.Code;
import com.inHere.constant.Field;
import com.inHere.dto.ParamsListDto;
import com.inHere.dto.ReturnBaseDto;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 吐槽+问答的校验类
 * 
 * @author lwh
 *
 */
@Component
public class AskReplyValidator {

	Logger log = Logger.getLogger(getClass());

	/**
	 * TODO 获取问答、吐槽列表参数校验
	 * @param params
	 * @return
	 */
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

	/**
	 * TODO 使用spring的util工具加载spring容器进来，对 label_id 是否存在进行校验
	 * @param request
	 * @return
	 */
	public ReturnBaseDto<JSONObject> createAskReply(HttpServletRequest request) {
		ReturnBaseDto obj = null;
		return obj;
	}

	/**
	 * TODO 获取最火标签列表校验
	 * @param params
	 * @return
	 */
	public ReturnBaseDto<JSONObject> getHotLabel(ParamsListDto params){

		return null;
	}

}
