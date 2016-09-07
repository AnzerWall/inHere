package com.inHere.dao;

import com.inHere.entity.OfficialNotice;

public interface OfficialNoticeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OfficialNotice record);

    int insertSelective(OfficialNotice record);

    OfficialNotice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OfficialNotice record);

    int updateByPrimaryKeyWithBLOBs(OfficialNotice record);

    int updateByPrimaryKey(OfficialNotice record);
}