package com.inHere.dao;

import com.inHere.dto.ParamsListDto;
import com.inHere.entity.Demand;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DemandMapper {

    public int deleteByPrimaryKey(Integer id);

    public int insertSelective(@Param("demand") Demand demand, @Param("ext_data") Map<String, Object> ext_data);

    public Demand selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Demand record);

    // 获取列表
    public List<Demand> selectByParams(ParamsListDto params);

    // 获取总条数
    public Integer getCount(ParamsListDto params);

    // 通过ID获取一条需求
    public Demand selectOneById(ParamsListDto params);
}