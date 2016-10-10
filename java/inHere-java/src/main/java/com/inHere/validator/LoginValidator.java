package com.inHere.validator;

import com.alibaba.fastjson.JSONObject;
import com.inHere.constant.Code;
import com.inHere.dao.SchoolMapper;
import com.inHere.dto.ReturnBaseDto;
import com.inHere.entity.School;
import com.inHere.entity.User;
import com.inHere.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;
import java.util.regex.Pattern;

/**
 * 要求校验方法名与@Params拦截的方法的名字要相同, 校验方法的参数有且只能有一个，参数类型和返回类型都要和拦截方法的一样
 *
 * @author lwh
 */
@Component
public class LoginValidator extends BaseValidator {

    Logger log = Logger.getLogger(getClass());

    @Autowired
    private SchoolMapper schoolMapper;

    @Autowired
    private UserService userService;

    /**
     * 用户登陆校验
     *
     * @param params
     * @return
     */
    public ReturnBaseDto<JSONObject> login(@RequestBody Map<String, Object> params) {
        log.info("login参数校验");
        String user_id = (String) params.get("user_id");
        String passwd = (String) params.get("passwd");
        // return null通过校验
        if (user_id != null && passwd != null) {
            return null;
        }
        ReturnBaseDto<JSONObject> result = new ReturnBaseDto<>();
        result.setCode(Code.Error.getCode());
        result.setStatus(Code.Error.getStatus());
        result.setMessage("用户名或密码不能为空");
        return result;
    }

    /**
     * 校验用户注册信息
     *
     * @param params
     * @return
     */
    public ReturnBaseDto<JSONObject> logup(@RequestBody Map<String, Object> params) {
        ReturnBaseDto<JSONObject> result = new ReturnBaseDto<>();
        result.setCode(Code.InputErr.getCode());
        result.setStatus(Code.InputErr.getStatus());

        String user_id = (String) params.get("user_id");
        String passed = (String) params.get("passwd");
        Integer school_id = (Integer) params.get("school_id");

        if (user_id == null || passed == null || school_id == null) {
            return result.setMessage("请填写完整信息");
        }

        if (!Pattern.matches("^[\\w]{6,16}$", user_id)) {
            return result.setMessage("账号格式有错");
        }

        User user = userService.getUserByUserId(user_id);
        if (user != null) {
            return result.setMessage("用户名已存在");
        }

        School school = schoolMapper.selectByPrimaryKey(school_id);
        if (school == null) {
            return result.setMessage("该学校不存在");
        }

        return null;
    }

}
