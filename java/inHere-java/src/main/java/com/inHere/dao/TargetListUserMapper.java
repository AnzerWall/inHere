package com.inHere.dao;

import com.inHere.entity.TargetListUser;

public interface TargetListUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TargetListUser record);

    int insertSelective(TargetListUser record);

    TargetListUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TargetListUser record);

    int updateByPrimaryKey(TargetListUser record);
}