package com.inHere.dao;

import com.inHere.entity.TargetUser;

public interface TargetUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TargetUser record);

    int insertSelective(TargetUser record);

    TargetUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TargetUser record);

    int updateByPrimaryKey(TargetUser record);
}