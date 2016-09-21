package com.inHere.dao;

import com.inHere.entity.Name;

public interface NameMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Name record);

    int insertSelective(Name record);

    Name selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Name record);

    int updateByPrimaryKey(Name record);
}