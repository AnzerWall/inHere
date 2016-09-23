package com.inHere.web;

import com.alibaba.fastjson.JSONObject;
import com.inHere.annotation.Authorization;
import com.inHere.annotation.CurrentToken;
import com.inHere.annotation.Params;
import com.inHere.constant.Code;
import com.inHere.dto.ReturnBaseDto;
import com.inHere.entity.Token;
import com.inHere.service.PraiseService;
import com.inHere.validator.PraiseValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 点赞销赞
 */
@RestController
public class PraiseController {

    Logger log = Logger.getLogger(getClass());

    @Autowired
    private PraiseService praiseService;

    @Authorization
    @Params(PraiseValidator.class)
    @RequestMapping(path = "/praise", method = RequestMethod.POST)
    public ReturnBaseDto<JSONObject> praise(@RequestBody Map<String, String> params, @CurrentToken Token token){
        Integer item_id = Integer.parseInt( params.get("item_id"));
        String ext_type_str = params.get("ext_type");
        Integer ext_type = ext_type_str != null ? Integer.parseInt(ext_type_str) : null;

        // 点赞业务
        praiseService.praise(ext_type , item_id, token);

        ReturnBaseDto<JSONObject> result = new ReturnBaseDto<>();
        result.setCode(Code.Success.getCode());
        result.setStatus(Code.Success.getStatus());
        return result;
    }

}
