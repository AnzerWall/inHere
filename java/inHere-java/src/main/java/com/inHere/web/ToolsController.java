package com.inHere.web;

import com.alibaba.fastjson.JSONObject;
import com.inHere.annotation.Authorization;
import com.inHere.annotation.CurrentToken;
import com.inHere.dto.ReturnBaseDto;
import com.inHere.entity.Token;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 工具控制类
 */
@RestController
public class ToolsController {

    Logger log = Logger.getLogger(getClass());

    /**
     * 获取工具列表
     */
    @Authorization
    @RequestMapping(path = "/tools", method = RequestMethod.GET)
    public ReturnBaseDto<JSONObject> tools(@CurrentToken Token token) {
        ReturnBaseDto<JSONObject> result = new ReturnBaseDto<>();

        return result;
    }

}
