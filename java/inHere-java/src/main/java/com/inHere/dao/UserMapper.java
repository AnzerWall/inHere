package com.inHere.dao;

import com.inHere.entity.Roles;
import com.inHere.entity.User;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 查找用户列表
     *
     * @return
     */
    List<User> selectUserList(@Param("offset") Integer offset,
                              @Param("limit") Integer limit,
                              @Param("is_admin") Integer is_admin,
                              @Param("user_id") String user_id,
                              @Param("user_name") String user_name);

    /**
     * 获取总条数
     *
     * @param offset
     * @param limit
     * @param is_admin
     * @param user_id
     * @param user_name
     * @return
     */
    Integer getCount(@Param("offset") Integer offset,
                     @Param("limit") Integer limit,
                     @Param("is_admin") Integer is_admin,
                     @Param("user_id") String user_id,
                     @Param("user_name") String user_name);
}
