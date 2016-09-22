package com.inHere.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.inHere.dto.ParamsListDto;
import com.inHere.entity.Activity;

public interface ActivityMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Activity record);

	int insertSelective(Activity record);

	/**
	 * 获取一条活动
	 * 
	 * @param id
	 * @return
	 */
	public Activity selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Activity record);

	int updateByPrimaryKeyWithBLOBs(Activity record);

	int updateByPrimaryKey(Activity record);

	/**
	 * 获取未结束的活动列表
	 * 
	 * @param params
	 * @return
	 */
	List<Activity> selectActivityList(ParamsListDto params);

	/**
	 * 获取总条数
	 * 
	 * @param school_id
	 * @return
	 */
	Integer getCount(@Param("school_id") Integer school_id);


	/**
	 * 用户点赞销赞
	 * @param user_id
	 * @param id
	 * @return
	 */
	Integer praise(@Param("user_id") String user_id, @Param("id") Integer id);
}
