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
    
    public List<Demand> selectByParams(ParamsListDto params);
    
    public Integer getCount(ParamsListDto params);
}