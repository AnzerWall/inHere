package com.inHere.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.inHere.constant.Field;
import com.inHere.dao.ActivityMapper;
import com.inHere.dto.ParamsListDto;
import com.inHere.entity.Activity;
import com.inHere.service.ActivityService;
import com.inHere.service.CommentService;
import com.inHere.service.CommonService;
import com.inHere.service.PraiseService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

	Logger log = Logger.getLogger(getClass());

	@Autowired
	private ActivityMapper activityMapper;

	@Autowired
	private PraiseService praiseService;

	@Autowired
	private CommentService commentService;

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
		Integer total_page = (total == 0 ? total : total / params.getLimit() + 1);

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
			obj.put("ext_type", tmp.getExtType());

			// 解析图片
			JSONArray photos = commonService.photoResolution(tmp.getCoverImg());

			obj.put("cover_img", photos.size() > 0 ? photos.get(0) : "");
			obj.put("title", tmp.getTitle());
			obj.put("user_name", tmp.getUser().getUserName());
			obj.put("start_time", tmp.getStartTime());
			obj.put("end_time", tmp.getEndTime());
			obj.put("praise", praiseService.getPraiseSize(tmp.getPraise()));
			obj.put("praised", praiseService.praised(tmp.getPraise(), params.getTokenEntity().getUser_id()));

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

	/**
	 * 获取一条活动详情
	 * 
	 * @param params
	 * @return
	 * @throws IOException
	 */
	public JSONObject getOneActivity(ParamsListDto params) throws IOException {
		JSONObject data = new JSONObject();
		Activity activity = activityMapper.selectByPrimaryKey(params.getItem_id());
		if (activity != null) {
			data.put("id", activity.getId());
			data.put("ext_type", activity.getExtType());
			// 解析图片
			JSONArray photos = commonService.photoResolution(activity.getCoverImg());
			data.put("cover_img", photos.size() > 0 ? photos.get(0) : "");
			data.put("title", activity.getTitle());
			data.put("user_name", activity.getUser().getUserName());

			// 获取扩展数据
			JSONObject ext_data = JSON.parseObject(activity.getExtData());
			Object place = ext_data.get("place");

			data.put("place", place != null ? place.toString() : "");
			data.put("praise", praiseService.getPraiseSize(activity.getPraise()));
			data.put("praised", praiseService.praised(activity.getPraise(), params.getTokenEntity().getUser_id()));
			data.put("start_time", activity.getStartTime());
			data.put("end_time", activity.getEndTime());
			data.put("content", activity.getUrlContent());

			// 设置栏目类型
			params.setType(activity.getExtType());
			// 获取评论列表
			data.put("comment", commentService.getList(params));
		}
		return data;
	}

}
