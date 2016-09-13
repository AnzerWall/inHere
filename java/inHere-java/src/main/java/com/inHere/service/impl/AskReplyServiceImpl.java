package com.inHere.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.inHere.dao.AskReplyMapper;
import com.inHere.dao.LabelMapper;
import com.inHere.dto.ParamsListDto;
import com.inHere.dto.ReturnListDto;
import com.inHere.entity.AskReply;
import com.inHere.entity.Label;
import com.inHere.service.AskReplyService;
import com.inHere.service.CommonService;

@Service
public class AskReplyServiceImpl implements AskReplyService {

	Logger log = Logger.getLogger(getClass());

	@Autowired
	private AskReplyMapper askReplyMapper;

	@Autowired
	private LabelMapper labelMapper;

	@Autowired
	private CommonService commonService;

	/**
	 * 获取吐槽+问答的列表
	 * 
	 * @param params
	 * @return
	 * @throws IOException
	 */
	public JSONObject getList(ParamsListDto params) throws IOException {
		JSONObject data = new JSONObject();
		log.info("进入AskReplyServiceImpl.getList()");
		List<AskReply> askReplyList = askReplyMapper.selectAskReplyByParams(params);
		ReturnListDto list = new ReturnListDto();

		// 获取总条数
		Integer total = askReplyMapper.getCount();
		Integer total_page = (total / params.getLimit()) + 1;
		list.setLimit(params.getLimit());
		list.setOffset(params.getOffset());
		list.setTotal(total);
		list.setTotal_page(total_page);
		// 构造返回items
		list.setItems(this.setItems(askReplyList));
		data.put("list", list);

		// Label列表参数格式化
		List<Label> labels = labelMapper.selectHotLabel();
		JSONArray lableArray = new JSONArray();
		for (Label tmp : labels) {
			lableArray.add(tmp.toMap());
		}
		data.put("labels", lableArray);

		log.info(data.toJSONString());
		return data;
	}

	public JSONArray setItems(List<AskReply> askReplyList) throws IOException {
		if (askReplyList.size() < 1)
			return new JSONArray();
		Integer ext_type = askReplyList.get(0).getExtType();
		// AskReply列表参数格式化
		JSONArray askReplyArray = new JSONArray();
		// 判断是否为校内吐槽
		if (ext_type == 10 || ext_type == 11) {
			for (AskReply tmp : askReplyList) {
				Map<String, Object> map = tmp.toMap();
				map.put("ext_data", this.setTestingExtData(tmp));
				askReplyArray.add(map);
			}
		} else {
			for (AskReply tmp : askReplyList) {
				Map<String, Object> map = tmp.toMap();
				map.put("ext_data", this.setAskAnwser(tmp));
				askReplyArray.add(map);
			}
		}
		return askReplyArray;
	}

	// 处理吐槽ext_data数据
	public Map<String, Object> setTestingExtData(AskReply tmp) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("content", tmp.getContent());
		map.put("photos", commonService.photoResolution(tmp.getPhotos()));
		return map;
	}

	// 处理问答ext_type数据
	public Map<String, Object> setAskAnwser(AskReply tmp) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", tmp.getTitle());
		map.put("best_reply",
				tmp.getBestReply() == null ? new HashMap<String, Object>() : tmp.getBestReply().toBestReply());
		return map;
	}

	/**
	 * 获取一条吐槽+问答的详细信息
	 * 
	 * @param params
	 * @return
	 */
	public JSONObject getOneAskReply(ParamsListDto params) {
		JSONObject obj = new JSONObject();
		
		return obj;
	}

}
