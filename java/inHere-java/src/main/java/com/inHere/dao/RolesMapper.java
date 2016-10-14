package com.inHere.dao;

import com.inHere.entity.Roles;

public interface RolesMapper {

    /**
     * 查找用户角色
     *
     * @param user_id
     * @return
     */
    Roles selectRole(String user_id);

}
