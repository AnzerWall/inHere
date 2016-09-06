package com.inHere.dao;

import com.inHere.entity.AskReply;

public interface AskReplyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AskReply record);

    int insertSelective(AskReply record);

    AskReply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AskReply record);

    int updateByPrimaryKey(AskReply record);
}