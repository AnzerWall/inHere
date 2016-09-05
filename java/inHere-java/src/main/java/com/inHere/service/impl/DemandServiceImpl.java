package com.inHere.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.inHere.constant.Label;
import com.inHere.dao.TbBegHelpMapper;
import com.inHere.dao.TbLabelMapper;
import com.inHere.dto.ListDto;
import com.inHere.dto.PhotoDto;
import com.inHere.dto.TasksDto;
import com.inHere.entity.TbBegHelp;
import com.inHere.entity.TbLabel;
import com.inHere.service.DemandService;

/**
 * 有求必应业务逻辑
 * 
 * @author lwh
 *
 */
@Service
@Transactional
public class DemandServiceImpl implements DemandService {

	Logger log = Logger.getLogger(getClass());

	@Autowired
	private TbBegHelpMapper begHelpMapper;

	@Autowired
	private TbLabelMapper labelMapper;

	/**
	 * 查找任务列表
	 * 
	 * @param limit
	 * @param offset
	 * @param filter_label
	 *            标签过滤
	 * @param filter_end
	 *            是否结束过滤
	 * @param sort_time
	 *            时间排序
	 * @return
	 * @throws IOException
	 */
	public ListDto<TasksDto> getTasksToDto(Integer offset, Integer limit, Integer[] filter_label, Integer filter_end,
			Integer sort_time) throws IOException {
		ListDto<TasksDto> listDto = new ListDto<TasksDto>();

		// list里的Items属性解析
		List<TbBegHelp> list = this.getTasks(offset, limit, filter_label, filter_end, sort_time);
		List<TasksDto> items = new ArrayList<TasksDto>();

		for (TbBegHelp tmp : list) {
			// 任务的基本属性赋值
			TasksDto task = new TasksDto(tmp.getHelpId(), tmp.getLableId(), tmp.getCreateTime(), tmp.getWordDesc(),
					tmp.getUserIdBeg(), tmp.getHasResolved());

			// TODO 优化标签名字查询，标签名字赋值
			TbLabel label = labelMapper.selectLabelById(tmp.getLableId());
			task.setLabel_name(label.getLabelName());

			// 解析图片
			List<PhotoDto> photos = this.pictureResolution(tmp.getPicDesc());
			task.setPhoto_desc(photos);

			// 解析不同任务的私有属性
			task.setPrivate_data(JSON.parseObject(tmp.getLabelData()));

			items.add(task);
		}
		listDto.setItems(items);

		// 设置分页属性
		Integer total = begHelpMapper.selectCount();
		Integer total_size = total / limit;
		listDto.setTotal(total);
		listDto.setPage_size(total_size);
		listDto.setPage(offset / limit + 1);
		listDto.setPage_size(limit);
		listDto.setOffset(offset);
		listDto.setLimit(limit);
		return listDto;
	}

	/**
	 * 图片解析成List&lt;PhotoDto&gt;传输对象
	 * 
	 * @return
	 * @throws IOException
	 */
	public List<PhotoDto> pictureResolution(String photos) throws IOException {
		// 判断是否有图片
		if (photos != null && !photos.trim().equals("")) {
			List<PhotoDto> list = new ArrayList<PhotoDto>();

			// 解析图片信息
			JSONArray photoArray = JSON.parseArray(photos);
			int len = photoArray.size();
			for (int i = 0; i < len; i++) {
				PhotoDto photo = new PhotoDto();

				JSONObject obj = photoArray.getJSONObject(i);
				String src = obj.getString("src");

				// 获取项目根路径和图片路径
				String root = System.getProperty("inHere.root");
				String minSrc = root + File.separator + "WEB-INF" + File.separator
						+ src.replace("max", "min").replace("/", File.separator);

				log.info(minSrc);

				// 小图转Base64传输
				File file = new File(minSrc);
				FileInputStream inputFile = new FileInputStream(file);
				byte[] buffer = new byte[(int) file.length()];
				inputFile.read(buffer);
				inputFile.close();
				String min = Base64.encodeBase64String(buffer);

				Integer w = obj.getInteger("w");
				Integer h = obj.getInteger("h");

				photo.setMin(min);
				photo.setSrc(src);
				photo.setW(w);
				photo.setH(h);
				list.add(photo);
			}
			return list;
		}
		return null;
	}

	/**
	 * 获取任务列表
	 * 
	 * @param limit
	 * @param offset
	 * @return
	 */
	public List<TbBegHelp> getTasks(Integer offset, Integer limit, Integer[] filter_label, Integer filter_end,
			Integer sort_time) {
		if (filter_label == null) {
			filter_label = new Integer[] { Label.Expressage, Label.Transfer, Label.Help };
		}
		List<TbBegHelp> list = this.getBegHelpList(offset, limit, filter_label, filter_end, sort_time);
		return list;
	}

	/**
	 * 获取有求必应列表
	 * 
	 * @param offset
	 * @param limit
	 * @param filter_label
	 * @param filter_end
	 * @param sort_time
	 * @return
	 */
	public List<TbBegHelp> getBegHelpList(Integer offset, Integer limit, Integer[] filter_label, Integer filter_end,
			Integer sort_time) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("limit", limit);
		params.put("offset", offset);
		params.put("lable_ids", filter_label);
		if (filter_end != null) {
			params.put("params", filter_end);
		}
		if (sort_time != null) {
			params.put("sort_time", sort_time);
		}
		List<TbBegHelp> list = begHelpMapper.selectTasksList(params);
		return list;
	}

}
