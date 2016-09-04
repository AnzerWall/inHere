package com.inHere.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inHere.constant.Label;
import com.inHere.dao.TbBegHelpMapper;
import com.inHere.dao.TbLabelMapper;
import com.inHere.dto.ListDto;
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
	 */
	public ListDto<TasksDto> getTasksToDto(Integer offset, Integer limit, Integer[] filter_label, Integer filter_end,
			Integer sort_time) {
		List<TbBegHelp> list = this.getTasks(offset, limit, filter_label, filter_end, sort_time);
		for (TbBegHelp tmp : list) {
			TasksDto task = new TasksDto(tmp.getHelpId(), tmp.getLableId(), tmp.getCreateTime(), tmp.getWordDesc(),
					tmp.getUserIdBeg(), tmp.getHasResolved());
			TbLabel label = labelMapper.selectLabelById(tmp.getLableId());
			task.setLabel_name(label.getLabelName());
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
