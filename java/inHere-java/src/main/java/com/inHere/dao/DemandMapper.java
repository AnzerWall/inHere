package com.inHere.dao;

import java.util.List;

import com.inHere.dto.ParamsListDto;
import com.inHere.entity.Demand;

public interface DemandMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(Demand record);

	int insertSelective(Demand record);

	Demand selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Demand record);

	int updateByPrimaryKey(Demand record);

	// 获取列表
	public List<Demand> selectByParams(ParamsListDto params);

	// 获取总条数
	public Integer getCount(ParamsListDto params);

	// 通过ID获取一条需求
	public Demand selectOneById(ParamsListDto params);
}