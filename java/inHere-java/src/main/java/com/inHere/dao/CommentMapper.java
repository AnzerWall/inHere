package com.inHere.dao;

import org.apache.ibatis.annotations.Param;

import com.inHere.entity.Comment;

public interface CommentMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(Comment record);

	int insertSelective(Comment record);

	Comment selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Comment record);

	int updateByPrimaryKey(Comment record);

	/**
	 * 获取评论总数
	 * 
	 * @param ext_type
	 * @param record_id
	 * @return
	 */
	public Integer getCount(@Param("ext_type") Integer ext_type, @Param("record_id") Integer record_id);
}