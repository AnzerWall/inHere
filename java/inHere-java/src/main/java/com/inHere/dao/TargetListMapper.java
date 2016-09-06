package com.inHere.dao;

import com.inHere.entity.TargetList;

public interface TargetListMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TargetList record);

    int insertSelective(TargetList record);

    TargetList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TargetList record);

    int updateByPrimaryKey(TargetList record);
}