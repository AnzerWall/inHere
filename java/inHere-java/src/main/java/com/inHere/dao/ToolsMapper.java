package com.inHere.dao;

import com.inHere.entity.Tools;

public interface ToolsMapper {
    int deleteByPrimaryKey(Integer toolId);

    int insert(Tools record);

    int insertSelective(Tools record);

    Tools selectByPrimaryKey(Integer toolId);

    int updateByPrimaryKeySelective(Tools record);

    int updateByPrimaryKey(Tools record);
}