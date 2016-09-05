package com.inHere.service;

import java.io.IOException;
import java.util.List;

import com.inHere.dto.ListDto;
import com.inHere.dto.TasksDto;
import com.inHere.entity.TbBegHelp;

/**
 * 有求必应业务逻辑
 * 
 * @author lwh
 *
 */
public interface DemandService {

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
			Integer sort_time) throws IOException;

	/**
	 * 获取任务列表
	 * 
	 * @param limit
	 * @param offset
	 * @return
	 */
	public List<TbBegHelp> getTasks(Integer offset, Integer limit, Integer[] filter_label, Integer filter_end,
			Integer sort_time);

}
