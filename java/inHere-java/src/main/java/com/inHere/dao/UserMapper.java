package com.inHere.dao;

import com.inHere.entity.Roles;
import com.inHere.entity.User;

import java.util.List;

public interface UserMapper {

	// 删除一个账号
	int deleteByPrimaryKey(String userId);

	// 值选择插入
	int insertSelective(User record);

	// 获取一个账号
	User selectByPrimaryKey(String userId);

	// 值选择更新
	int updateByPrimaryKeySelective(User record);

	// 查找用户角色集合
	List<Roles> selectUserRoles(String user_id);
}
