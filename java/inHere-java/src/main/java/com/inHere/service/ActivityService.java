package com.inHere.service;

import java.io.IOException;

import com.alibaba.fastjson.JSONObject;
import com.inHere.dto.ParamsListDto;

public interface ActivityService {

	/**
	 * 获取活动列表
	 * 
	 * @param params
	 * @return
	 */
	public JSONObject getList(ParamsListDto params)  throws IOException ;

}
