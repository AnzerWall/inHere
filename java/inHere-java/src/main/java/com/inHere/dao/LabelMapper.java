package com.inHere.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.inHere.entity.Label;

/**
 * 标签数据库操作接口
 * 
 * @author lwh
 *
 */
public interface LabelMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(Label record);

	int insertSelective(Label record);

	Label selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Label record);

	int updateByPrimaryKey(Label record);

	/**
	 * 获取最火的5条标签列表
	 * 
	 * @return
	 */
	public List<Label> selectHotLabel(@Param("type") Integer type);
}