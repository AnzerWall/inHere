package com.inHere.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.inHere.dao.AskReplyMapper;
import com.inHere.dao.LabelMapper;
import com.inHere.dto.ParamsListDto;
import com.inHere.entity.AskReply;
import com.inHere.entity.Label;
import com.inHere.service.AskReplyService;

@Service
public class AskReplyServiceImpl implements AskReplyService {

	Logger log = Logger.getLogger(getClass());

	@Autowired
	private AskReplyMapper askReplyMapper;

	@Autowired
	private LabelMapper labelMapper;

	/**
	 * 获取吐槽+问答的列表
	 * 
	 * @param params
	 * @return
	 */
	public JSONObject getList(ParamsListDto params) {
		JSONObject data = new JSONObject();
		log.info("进入AskReplyServiceImpl.getList()");
		// AskReply列表参数格式化
		List<AskReply> items = askReplyMapper.selectByParams(params);
		JSONArray skReplyArray = new JSONArray();
		for( AskReply tmp : items ){
			skReplyArray.add(tmp);
		}
		data.put("list", skReplyArray);
		
		// Label列表参数格式化
		List<Label> labels = labelMapper.selectHotLabel();
		JSONArray lableArray = new JSONArray();
		for (Label tmp : labels) {
			lableArray.add(tmp.toMap());
		}
		data.put("labels", lableArray);
		
		log.info(data.toJSONString());
		return null;
	}

}
