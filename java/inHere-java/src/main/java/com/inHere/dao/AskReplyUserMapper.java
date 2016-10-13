package com.inHere.dao;

import org.apache.ibatis.annotations.Param;

import com.inHere.entity.AskReplyUser;

import java.util.List;

public interface AskReplyUserMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(AskReplyUser record);

	int insertSelective(AskReplyUser record);

	List<AskReplyUser> selectByAskReplyId(Integer ask_reply_id);

	int updateByPrimaryKeySelective(AskReplyUser record);

	int updateByPrimaryKey(AskReplyUser record);

	/**
	 * 选择关注某问答的用户
	 * 
	 * @param user_id
	 * @param ask_reply_id
	 * @return
	 */
	public AskReplyUser selectFollowUser(@Param("user_id") String user_id, @Param("ask_reply_id") Integer ask_reply_id);
}
