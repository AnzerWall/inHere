package com.inHere.web;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.inHere.annotation.Authorization;
import com.inHere.annotation.CurrentToken;
import com.inHere.annotation.Params;
import com.inHere.constant.Code;
import com.inHere.constant.Field;
import com.inHere.dto.ParamsListDto;
import com.inHere.dto.ReturnBaseDto;
import com.inHere.entity.Token;
import com.inHere.service.ActivityService;
import com.inHere.validator.ActivityValidator;

/**
 * 活动
 * 
 * @author lwh
 *
 */
@RestController
public class ActivityController {

	@Autowired
	private ActivityService activityService;

	/**
	 * 获取活动列表
	 * 
	 * @param params
	 * @param token
	 * @return
	 * @throws IOException
	 */
	@Authorization
	@Params(ActivityValidator.class)
	@RequestMapping(path = "/activity", method = RequestMethod.GET)
	public ReturnBaseDto<JSONObject> getActivityList(ParamsListDto params, @CurrentToken Token token)
			throws IOException {

		// 初始化参数
		Integer offset = params.getOffset() == null ? 0 : params.getOffset();
		Integer limit = params.getLimit() == null ? 10 : params.getLimit();
		params.setOffset(offset);
		params.setLimit(limit);
		// 默认类型为活动和广告
		Integer[] ext_type = new Integer[] { Field.ExtType_Activity, Field.ExtType_AD };
		params.setExt_type(ext_type);
		params.setTokenEntity(token);

		JSONObject data = activityService.getList(params);

		ReturnBaseDto<JSONObject> result = new ReturnBaseDto<JSONObject>();
		result.setCode(Code.Success.getCode());
		result.setStatus(Code.Success.getStatus());
		result.setData(data);
		return result;
	}

	@Authorization
	@RequestMapping(path = "/activity/{item_id}", method = RequestMethod.GET)
	public ReturnBaseDto<JSONObject> getOneActivity(@PathVariable Integer item_id, @CurrentToken Token token)
			throws IOException {
		ParamsListDto params = new ParamsListDto();
		// 实体关联的评论列表分页初始化
		params.setItem_id(item_id);
		params.setLimit(10);
		params.setOffset(0);
		params.setTokenEntity(token);

		JSONObject data = activityService.getOneActivity(params);

		ReturnBaseDto<JSONObject> result = new ReturnBaseDto<JSONObject>();
		result.setCode(Code.Success.getCode());
		result.setStatus(Code.Success.getStatus());
		result.setData(data);
		return result;
	}

}
