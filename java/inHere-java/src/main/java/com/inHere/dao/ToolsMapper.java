package com.inHere.dao;

import com.inHere.entity.Tools;

import java.util.List;

/**
 *  工具数据操作接口
 */
public interface ToolsMapper {

    /*查找工具列表*/
    List<Tools> selectTools(Integer school_id);


    /* 以下为自动生成代码 */
    int deleteByPrimaryKey(Integer toolId);

    int insert(Tools record);

    int insertSelective(Tools record);

    Tools selectByPrimaryKey(Integer toolId);

    int updateByPrimaryKeySelective(Tools record);

    int updateByPrimaryKey(Tools record);
}
