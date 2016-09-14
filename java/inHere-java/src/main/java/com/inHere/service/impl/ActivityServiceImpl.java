package com.inHere.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.inHere.constant.Field;
import com.inHere.dao.ActivityMapper;
import com.inHere.dto.ParamsListDto;
import com.inHere.entity.Activity;
import com.inHere.service.ActivityService;
import com.inHere.service.CommonService;
import com.inHere.service.PraiseService;

@Service
public class ActivityServiceImpl implements ActivityService {

	Logger log = Logger.getLogger(getClass());

	@Autowired
	private ActivityMapper activityMapper;

	@Autowired
	private PraiseService praiseService;

	@Autowired
	private CommonService commonService;

	/**
	 * 获取活动列表
	 * 
	 * @param params
	 * @return
	 * @throws IOException
	 */
	public JSONObject getList(ParamsListDto params) throws IOException {
		JSONObject data = new JSONObject();

		// 设置总页数
		Integer total = activityMapper.getCount(params.getTokenEntity().getSchool_id());
		Integer total_page = total / params.getLimit() + 1;

		data.put("offset", params.getOffset());
		data.put("limit", params.getLimit());
		data.put("total", total);
		data.put("total_page", total_page);
		data.put("items", this.getItems(params));

		log.info(data);
		return data;
	}

	public JSONArray getItems(ParamsListDto params) throws IOException {
		JSONArray items = new JSONArray();
		List<Activity> list = activityMapper.selectActivityList(params);
		for (Activity tmp : list) {
			JSONObject obj = new JSONObject();
			obj.put("id", tmp.getId());

			// 解析图片
			JSONArray photos = commonService.photoResolution(tmp.getCoverImg());

			obj.put("cover_img", photos.size() > 0 ? photos.get(0) : "");
			obj.put("title", tmp.getTitle());
			obj.put("user_name", tmp.getUser().getUserName());
			obj.put("start_time", tmp.getStartTime());
			obj.put("end_time", tmp.getEndTime());
			obj.put("praise", praiseService.getPraiseSize(tmp.getPraise()));

			// 获取扩展数据
			JSONObject ext_data = JSON.parseObject(tmp.getExtData());
			Object place = ext_data.get("place");

			obj.put("place", place != null ? place.toString() : "");
			obj.put("url_type", tmp.getUrlType());

			// url_type 为1时，内容返回空字符串
			if (tmp.getUrlType() == Field.URLType_Model) {
				tmp.setUrlContent("");
			}

			obj.put("url_content", tmp.getUrlContent());
			items.add(obj);
		}
		return items;
	}

}
