package com.inHere.dao;

import com.inHere.entity.NoteNotice;

public interface NoteNoticeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NoteNotice record);

    int insertSelective(NoteNotice record);

    NoteNotice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NoteNotice record);

    int updateByPrimaryKey(NoteNotice record);
}