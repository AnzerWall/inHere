package com.inHere.dao;

import com.inHere.entity.Permissions;
import com.inHere.entity.Roles;

import java.util.List;

public interface RolesMapper {

    /**
     * 查找用户角色
     *
     * @param user_id
     * @return
     */
    Roles selectRole(String user_id);

    /**
     * 获取角色列表
     *
     * @return
     */
    List<Roles> getRoles();

    /**
     * 获取权限列表
     *
     * @return
     */
    List<Permissions> getPermissions();

}
