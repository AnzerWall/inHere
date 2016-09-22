package com.inHere.validator;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.inHere.dto.ParamsListDto;
import com.inHere.dto.ReturnBaseDto;
import org.springframework.stereotype.Component;

@Component
public class ActivityValidator extends BaseValidator {

	Logger log = Logger.getLogger(getClass());

	public ReturnBaseDto<JSONObject> getActivityList(ParamsListDto params) {
		return null;
	}

}
