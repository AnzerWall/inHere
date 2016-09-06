package com.inHere.dao;

import com.inHere.entity.NotNotice;

public interface NotNoticeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NotNotice record);

    int insertSelective(NotNotice record);

    NotNotice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NotNotice record);

    int updateByPrimaryKey(NotNotice record);
}