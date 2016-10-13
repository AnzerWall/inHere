package com.inHere.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.inHere.dao.UserMapper;
import com.inHere.entity.User;
import com.inHere.service.SecurityService;
import com.inHere.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    /**
     * 获取用户信息
     *
     * @param user_id
     * @return
     * @throws IOException
     */
    @Override
    public JSONObject userInfo(String user_id) throws IOException {
        User user = userMapper.selectByPrimaryKey(user_id);
        JSONObject userDto = new JSONObject();
        userDto.put("user_id", user.getUserId());
        userDto.put("user_name", user.getUserName());
        userDto.put("sex", user.getSex());
        userDto.put("school", user.getSchool().getSchool());
        JSONObject contact = user.getContactWay() != null ? JSON.parseObject(user.getContactWay()) : new JSONObject();
        userDto.put("phone", contact.get("phone"));
        userDto.put("qq", contact.get("qq"));
        return userDto;
    }

    /**
     * 修改密码
     *
     * @param user_id
     * @param oldPwd
     * @param newPwd
     * @return
     */
    @Transactional
    @Override
    public boolean changePwd(String user_id, String oldPwd, String newPwd) {
        User user = userMapper.selectByPrimaryKey(user_id);
        String salt = user.getSaltKey();
        String oldPwdSecurity = securityService.encrypt(salt, oldPwd);
        // 校验密码
        if (user.getPasswd().equals(oldPwdSecurity)) {
            String newPwdSecurity = securityService.encrypt(salt, newPwd);
            User newUserInfo = new User();
            newUserInfo.setUserId(user_id);
            newUserInfo.setPasswd(newPwdSecurity);
            userMapper.updateByPrimaryKeySelective(newUserInfo);
            return true;
        }
        return false;
    }

    /**
     * 修改用户信息
     *
     * @param params
     * @param user_id
     */
    @Override
    public void change(Map<String, Object> params, String user_id) throws IOException {
        User user = userMapper.selectByPrimaryKey(user_id);
        String user_name = (String) params.get("user_name");
        Integer sex = (Integer) params.get("sex");
        String phone = (String) params.get("phone");
        String qq = (String) params.get("qq");
        JSONObject contact = user.getContactWay() != null ? JSON.parseObject(user.getContactWay()) : new JSONObject();

        User userInfo = new User();
        userInfo.setUserId(user_id);

        if (user_name != null)
            userInfo.setUserName(user_name);
        if (sex != null)
            userInfo.setSex(sex);
        if (phone != null)
            contact.put("phone", phone);
        if (qq != null)
            contact.put("qq", qq);

        userInfo.setContactWay(contact.toJSONString());

        userMapper.updateByPrimaryKeySelective(userInfo);
    }

}
