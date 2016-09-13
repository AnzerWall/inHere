package com.inHere.service;

import java.io.IOException;

import com.alibaba.fastjson.JSONObject;
import com.inHere.dto.ParamsListDto;

public interface AskReplyService {

	/**
	 * 获取吐槽+问答的列表
	 * 
	 * @param params
	 * @return
	 */
	public JSONObject getList(ParamsListDto params) throws IOException;

	/**
	 * 获取一条吐槽+问答的详细信息
	 * 
	 * @param params
	 * @return
	 */
	public JSONObject getOneAskReply(ParamsListDto params);
}
