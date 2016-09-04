package com.inHere.dao;

import com.inHere.entity.TbLabel;

/**
 * 标签表操作接口
 * 
 * @author lwh
 *
 */
public interface TbLabelMapper {

	/**
	 * 查找Label
	 * 
	 * @return
	 */
	public TbLabel selectLabelById(Integer label_id);

}