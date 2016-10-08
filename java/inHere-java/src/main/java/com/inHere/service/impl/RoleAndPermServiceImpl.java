package com.inHere.service.impl;

import com.inHere.dao.UserMapper;
import com.inHere.entity.Roles;
import com.inHere.service.RoleAndPermService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 权限与角色业务
 */
public class RoleAndPermServiceImpl implements RoleAndPermService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 获取用户角色集合
     * @param user_id
     * @return
     */
    @Override
    public List<Roles> getRoles(String user_id) {
        return userMapper.selectUserRoles(user_id);
    }
}
