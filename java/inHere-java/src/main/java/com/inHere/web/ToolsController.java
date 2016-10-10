package com.inHere.web;

import com.alibaba.fastjson.JSONObject;
import com.inHere.annotation.Authorization;
import com.inHere.annotation.CurrentToken;
import com.inHere.constant.Code;
import com.inHere.dto.ReturnBaseDto;
import com.inHere.entity.Token;
import com.inHere.service.ToolsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * 工具控制类
 */
@RestController
public class ToolsController {

    Logger log = Logger.getLogger(getClass());

    @Autowired
    private ToolsService toolsService;

    /**
     * 获取工具列表
     */
    @Authorization
    @RequestMapping(path = "/tools", method = RequestMethod.GET)
    public ReturnBaseDto<JSONObject> tools(@CurrentToken Token token) throws IOException {

        ReturnBaseDto<JSONObject> result = new ReturnBaseDto<>();

        JSONObject data = toolsService.getList(token.getSchool_id());

        result.setCode(Code.Success.getCode());
        result.setStatus(Code.Success.getStatus());
        result.setData(data);
        return result;
    }

}
