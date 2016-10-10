package com.inHere.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.inHere.annotation.Authorization;
import com.inHere.annotation.CurrentToken;
import com.inHere.annotation.Params;
import com.inHere.constant.Code;
import com.inHere.dto.ReturnBaseDto;
import com.inHere.dto.UserDto;
import com.inHere.entity.Token;
import com.inHere.entity.User;
import com.inHere.service.CommonService;
import com.inHere.service.LoginService;
import com.inHere.service.UserService;
import com.inHere.validator.LoginValidator;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

/**
 * 登陆模块
 *
 * @author lwh
 */
@RestController
public class LoginController {

    Logger log = Logger.getLogger(getClass());

    @Autowired
    private LoginService loginService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private UserService userService;

    /**
     * 登陆，创建一个Token资源
     *
     * @return
     * @throws IOException
     */
    @Params(LoginValidator.class)
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ReturnBaseDto<UserDto> login(@RequestBody Map<String, Object> params) throws IOException {
        ReturnBaseDto<UserDto> result = new ReturnBaseDto<UserDto>();
        log.info("进入login()中-----");
        String user_id = (String) params.get("user_id");
        String passwd = (String) params.get("passwd");

        UserDto userDto = loginService.login(user_id, passwd);
        if (userDto == null) {
            result.setCode(Code.InputErr.getCode());
            result.setStatus(Code.InputErr.getStatus());
            result.setMessage("用户账号或密码错误");
        } else {
            result.setCode(Code.Success.getCode());
            result.setStatus(Code.Success.getStatus());
            result.setData(userDto);
        }
        return result;
    }

    /**
     * @param token
     * @return
     */
    @Authorization
    @RequestMapping(path = "/logout", method = RequestMethod.DELETE)
    public ReturnBaseDto<JSONObject> logout(@CurrentToken Token token) {
        log.info("进入logout()中-----");
        // 退出登陆
        loginService.logout(token);
        ReturnBaseDto<JSONObject> result = new ReturnBaseDto<>();
        result.setCode(Code.Success.getCode());
        result.setStatus(Code.Success.getStatus());
        return result;
    }

    /**
     * 注册
     *
     * @return
     */
    @Params(LoginValidator.class)
    @RequestMapping(path = "/logup", method = RequestMethod.POST)
    public ReturnBaseDto<JSONObject> logup(@RequestBody Map<String, Object> params) {
        String user_id = (String) params.get("user_id");
        String passed = (String) params.get("passwd");
        Integer school_id = (Integer) params.get("school_id");

        userService.regUser(user_id, passed, school_id);

        ReturnBaseDto<JSONObject> result = new ReturnBaseDto<>();
        result.setCode(Code.Success.getCode());
        result.setStatus(Code.Success.getStatus());
        return result;
    }

    /**
     * 检查用户user_id是否已存在
     */
    @RequestMapping(path = "check/{user_id}", method = RequestMethod.GET)
    public ReturnBaseDto<JSONObject> checkUserId(@PathVariable String user_id){
        JSONObject data = userService.checkUserIdExists(user_id);

        ReturnBaseDto<JSONObject> result = new ReturnBaseDto<>();
        result.setCode(Code.Success.getCode());
        result.setStatus(Code.Success.getStatus());
        result.setData(data);
        return result;
    }

    /**
     * 获取学校列表
     *
     * @return
     */
    @RequestMapping(path = "/schools", method = RequestMethod.GET)
    public ReturnBaseDto<JSONObject> schoolList() {
        JSONObject data = commonService.getSchools();

        ReturnBaseDto<JSONObject> result = new ReturnBaseDto<>();
        result.setCode(Code.Success.getCode());
        result.setStatus(Code.Success.getStatus());
        result.setData(data);
        return result;
    }


    /**
     * 后台登录
     */
    @RequestMapping(path = "/admin/login", method = RequestMethod.POST)
    public ReturnBaseDto<UserDto> adminLogin(@RequestBody Map<String, Object> params) throws IOException {
        ReturnBaseDto<UserDto> result = new ReturnBaseDto<>();
        String user_id = (String) params.get("user_id");
        String passwd = (String) params.get("passwd");

        if (user_id == null || passwd == null) {
            result.setCode(Code.InputErr.getCode());
            result.setStatus(Code.InputErr.getStatus());
            result.setMessage("请填写完整帐号和密码");
            return result;
        }

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user_id, passwd);
        subject.login(token);

        User user = (User) subject.getPrincipal();

        // 返回用户信息
        UserDto userDto = new UserDto();
        userDto.setUser_id(user.getUserId());
        userDto.setUser_name(user.getUserName());
        userDto.setHead_img(JSON.parseObject(user.getHeadImg()));
        JSONObject temp = JSON.parseObject(user.getContactWay());
        userDto.setContact_way(temp);
        userDto.setArea(user.getArea());
        userDto.setSchool_id(user.getSchoolId());
        userDto.setSchool(user.getSchool().getSchool());
        userDto.setSex(user.getSex());
        userDto.setToken(subject.getSession().getId().toString());

        result.setCode(Code.Success.getCode());
        result.setStatus(Code.Success.getStatus());
        result.setData(userDto);
        return result;
    }


}
