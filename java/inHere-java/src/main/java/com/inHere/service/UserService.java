package com.inHere.service;

import com.alibaba.fastjson.JSONObject;
import com.inHere.entity.User;

/**
 * 用户管理业务
 */
public interface UserService {

    /**
     * 获取用户信息
     *
     * @param user_id
     * @return
     */
    User getUserByUserId(String user_id);

    /**
     * 检查用户是否存在
     *
     * @param user_id
     * @return
     */
    JSONObject checkUserIdExists(String user_id);

    /**
     * 用户注册
     *
     * @param user_id
     * @param passwd
     * @param school_id
     */
    void regUser(String user_id, String passwd, Integer school_id);

}
