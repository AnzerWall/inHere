package com.inHere.dao;

import java.util.List;

import com.inHere.entity.Label;

public interface LabelMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Label record);

	int insertSelective(Label record);

	Label selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Label record);

	int updateByPrimaryKey(Label record);

	/**
	 * 获取最火的标签列表
	 * 
	 * @return
	 */
	public List<Label> selectHotLabel();
}