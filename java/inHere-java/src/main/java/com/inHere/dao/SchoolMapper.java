package com.inHere.dao;

import com.inHere.entity.School;

import java.util.List;

public interface SchoolMapper {

    // 获取所有学校
    List<School> selectAll();

    //* 自动生成  */
    int deleteByPrimaryKey(Integer id);

    int insert(School record);

    int insertSelective(School record);

    School selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(School record);

    int updateByPrimaryKey(School record);
}