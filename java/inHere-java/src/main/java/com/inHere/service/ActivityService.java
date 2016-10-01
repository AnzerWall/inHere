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
	public JSONObject getList(ParamsListDto params) throws IOException;

	/**
	 * 获取一条活动详情
	 * 
	 * @param params
	 * @return
	 */
	public JSONObject getOneActivity(ParamsListDto params) throws IOException;

	/**
	 * Get square data
	 * @param user_id
	 * @return
	 */
	public JSONObject square(String user_id) throws IOException;

}
