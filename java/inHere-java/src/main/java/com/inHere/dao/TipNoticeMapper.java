package com.inHere.dao;

import com.inHere.entity.TipNotice;

public interface TipNoticeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TipNotice record);

    int insertSelective(TipNotice record);

    TipNotice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TipNotice record);

    int updateByPrimaryKey(TipNotice record);
}