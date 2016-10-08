package com.inHere.service;

import com.inHere.entity.Roles;

import java.util.List;

/**
 * 权限与角色业务
 */
public interface RoleAndPermService {

    /**
     * 获取角色集合
     *
     * @param user_id
     * @return
     */
    List<Roles> getRoles(String user_id);

}
