package com.inHere.web;

import com.alibaba.fastjson.JSONObject;
import com.inHere.annotation.Authorization;
import com.inHere.annotation.CurrentToken;
import com.inHere.annotation.Params;
import com.inHere.constant.Code;
import com.inHere.constant.Field;
import com.inHere.dto.ReturnBaseDto;
import com.inHere.dto.ReturnListDto;
import com.inHere.entity.Token;
import com.inHere.service.UserService;
import com.inHere.validator.LoginValidator;
import org.apache.log4j.Logger;
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

    Logger log = Logger.getLogger(getClass());

    @Autowired
    private UserService userService;

    /**
     * 创建管理员, TODO 未做校验
     */
    @Params(LoginValidator.class)
    @RequestMapping(path = "/admin", method = RequestMethod.POST)
    public ReturnBaseDto<JSONObject> adminLogup(@RequestBody Map<String, Object> params) {
        String user_id = (String) params.get("user_id");
        String passed = (String) params.get("passwd");
        Integer school_id = (Integer) params.get("school_id");

        userService.regUser(user_id, passed, school_id, Field.Is_Admin_Yes);

        ReturnBaseDto<JSONObject> result = new ReturnBaseDto<>();
        result.setCode(Code.Success.getCode());
        result.setStatus(Code.Success.getStatus());
        return result;
    }

    /**
     * 获取用户列表(过滤、检索), TODO 未做校验
     */
    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public ReturnBaseDto<ReturnListDto> userList(Integer offset, Integer limit, Integer is_admin, String user_id, String user_name) throws IOException {

        log.info("--->" + offset);
        if( offset == null ){
            offset = 0;
        }
        if (limit == null){
            limit = 10;
        }

        ReturnListDto data = userService.getList(offset, limit, is_admin, user_id, user_name);

        ReturnBaseDto<ReturnListDto> result = new ReturnBaseDto<>();
        result.setCode(Code.Success.getCode());
        result.setStatus(Code.Success.getStatus());
        result.setData(data);
        return result;
    }

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
