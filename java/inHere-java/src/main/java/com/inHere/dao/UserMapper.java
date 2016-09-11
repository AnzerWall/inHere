package com.inHere.dao;

import com.inHere.entity.User;

public interface UserMapper {

	// 删除一个账号
	public int deleteByPrimaryKey(String userId);

	// 值选择插入
	public int insertSelective(User record);

	// 获取一个账号
	public User selectByPrimaryKey(String userId);

	// 值选择更新
	public int updateByPrimaryKeySelective(User record);
}