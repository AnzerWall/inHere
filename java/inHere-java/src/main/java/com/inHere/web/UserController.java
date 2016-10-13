package com.inHere.web;

import com.alibaba.fastjson.JSONObject;
import com.inHere.annotation.Authorization;
import com.inHere.annotation.CurrentToken;
import com.inHere.constant.Code;
import com.inHere.dto.ReturnBaseDto;
import com.inHere.entity.Token;
import com.inHere.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

/**
 * 管理员Controller
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * TODO 创建管理员, 添加字段is_admin（是否为管理员）、available（是否可用）
     */

    /**
     * TODO 获取用户列表(过滤、检索)
     */

    /**
     * TODO 获取角色列表
     */

    /**
     * TODO 获取权限列表
     */

    /**
     * TODO 获取用户权限列表
     */

    /**
     * TODO 分配角色
     */

    /**
     * TODO 禁用账户
     */

    /**
     * 获取用户个人信息
     */
    @Authorization
    @RequestMapping(path = "/user", method = RequestMethod.GET)
    public ReturnBaseDto<JSONObject> userInfo(@CurrentToken Token token) throws IOException {

        JSONObject data = userService.userInfo(token.getUser_id());

        ReturnBaseDto<JSONObject> result = new ReturnBaseDto<>();
        result.setCode(Code.Success.getCode());
        result.setStatus(Code.Success.getStatus());
        result.setData(data);
        return result;
    }

    /**
     * 修改用户信息
     */
    @Authorization
    @RequestMapping(path = "/user/change", method = RequestMethod.POST)
    public ReturnBaseDto<JSONObject> change(@RequestBody Map<String, Object> params, @CurrentToken Token token) throws IOException {

        userService.change(params, token.getUser_id());

        ReturnBaseDto<JSONObject> result = new ReturnBaseDto<>();
        result.setCode(Code.Success.getCode());
        result.setStatus(Code.Success.getStatus());
        return result;
    }

    /**
     * 修改密码
     */
    @Authorization
    @RequestMapping(path = "/user/change_pwd", method = RequestMethod.POST)
    public ReturnBaseDto<JSONObject> changePwd(@RequestBody Map<String, Object> params, @CurrentToken Token token) {
        ReturnBaseDto<JSONObject> result = new ReturnBaseDto<>();

        String oldPwd = (String) params.get("old_pwd");
        String newPwd = (String) params.get("new_pwd");

        boolean flag = userService.changePwd(token.getUser_id(), oldPwd, newPwd);
        if (!flag) {
            result.setCode(Code.InputErr.getCode());
            result.setStatus(Code.InputErr.getStatus());
            result.setMessage("原密码有错~");
            return result;
        }
        result.setCode(Code.Success.getCode());
        result.setStatus(Code.Success.getStatus());
        return result;
    }

}
