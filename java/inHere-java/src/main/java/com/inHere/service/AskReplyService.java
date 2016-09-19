package com.inHere.service;

import com.alibaba.fastjson.JSONObject;
import com.inHere.dto.ParamsListDto;
import com.inHere.entity.AskReply;

import java.io.IOException;

public interface AskReplyService {

	/**
	 * 获取吐槽+问答的列表
	 * 
	 * @param params
	 * @return
	 */
	public JSONObject getList(ParamsListDto params) throws IOException;

	/**

	 * 获取一条详细吐槽或问答信息
	 * 
	 * @param params
	 * @return
	 */
	public JSONObject getOneAskReply(ParamsListDto params)  throws IOException ;

	/**
	 * 创建一个吐槽或问答资源
	 *
	 * @return
	 */
	public boolean createAskReply(AskReply askReply);
}
