package com.inHere.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.inHere.dao.UserMapper;
import com.inHere.entity.User;
import com.inHere.service.SecurityService;
import com.inHere.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SecurityService securityService;

    @Override
    public User getUserByUserId(String user_id) {
        return userMapper.selectByPrimaryKey(user_id);
    }

    @Override
    public JSONObject checkUserIdExists(String user_id) {
        User user = this.getUserByUserId(user_id);
        boolean is_exists = false;
        if (user != null) {
            is_exists = true;
        }
        JSONObject data = new JSONObject();
        data.put("is_exists", is_exists);
        return data;
    }

    /**
     * 用户注册
     *
     * @param user_id
     * @param passwd
     * @param school_id
     */
    @Transactional
    @Override
    public void regUser(String user_id, String passwd, Integer school_id) {
        List<String> security = securityService.encrypt(passwd);
        String password = security.get(0);
        String saltKey = security.get(1);

        User user = new User();
        user.setUserId(user_id);
        user.setPasswd(password);
        user.setSaltKey(saltKey);
        user.setSchoolId(school_id);
        user.setCreateTime(new Date());

        userMapper.insertSelective(user);

    }
}
