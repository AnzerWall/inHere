package com.inHere.dao;

import java.util.List;
import java.util.Map;

import com.inHere.entity.TbBegHelp;

/**
 * 有求必应数据表操作接口
 * 
 * @author lwh
 *
 */
public interface TbBegHelpMapper {

	/**
	 * 插入一条请求帮忙记录
	 * 
	 * @return
	 */
	public int insertOneBegHelp(TbBegHelp help);

	/**
	 * 查找请求帮忙记录，返回列表数据
	 * 
	 * @param params
	 * @return
	 */
	public List<TbBegHelp> selectTasksList(Map<String, Object> params);

	/**
	 * 获取数据库总数
	 * 
	 * @return
	 */
	public Integer selectCount();

}