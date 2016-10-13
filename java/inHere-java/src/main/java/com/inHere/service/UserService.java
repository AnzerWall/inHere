package com.inHere.service;

import com.alibaba.fastjson.JSONObject;
import com.inHere.entity.User;

import java.io.IOException;
import java.util.Map;

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

    /**
     * 获取用户信息
     *
     * @param user_id
     * @return
     */
    JSONObject userInfo(String user_id) throws IOException;

    /**
     * 修改密码
     *
     * @param user_id
     * @param oldPwd
     * @param newPwd
     */
    boolean changePwd(String user_id, String oldPwd, String newPwd);

    /**
     * 修改用户信息
     *
     * @param params
     * @param user_id
     */
    void change(Map<String, Object> params, String user_id) throws IOException;
}
