package com.inHere.dao;

import java.util.List;

import com.inHere.dto.ParamsListDto;
import com.inHere.entity.Demand;

/**
 * 有求必应DAO模块
 * 
 * @author lwh
 *
 */
public interface DemandMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(Demand record);

	int insertSelective(Demand record);

	Demand selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Demand record);

	int updateByPrimaryKey(Demand record);

	/**
	 * 获取有求必应模块列表
	 * 
	 * @param params
	 * @return
	 */
	public List<Demand> selectByParams(ParamsListDto params);

	/**
	 * 获取表总条数
	 * 
	 * @return
	 */
	public Integer getCount(ParamsListDto params);
}