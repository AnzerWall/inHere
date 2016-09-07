package com.inHere.dao;

import com.inHere.entity.AskReplyUser;

public interface AskReplyUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AskReplyUser record);

    int insertSelective(AskReplyUser record);

    AskReplyUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AskReplyUser record);

    int updateByPrimaryKey(AskReplyUser record);
}