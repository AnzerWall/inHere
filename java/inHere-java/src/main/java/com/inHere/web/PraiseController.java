package com.inHere.web;

import com.alibaba.fastjson.JSONObject;
import com.inHere.annotation.Authorization;
import com.inHere.annotation.CurrentToken;
import com.inHere.constant.Code;
import com.inHere.dto.ReturnBaseDto;
import com.inHere.entity.Token;
import com.inHere.service.PraiseService;
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
    //@Params()
    @RequestMapping(path = "/praise", method = RequestMethod.POST)
    public ReturnBaseDto<JSONObject> praise(@RequestBody Map<String, Object> params, @CurrentToken Token token){
        Integer item_id = Integer.parseInt( params.get("item_id").toString() );
        log.info("--->" + item_id);
        log.info("--->" + token.getUser_id());
        // 点赞业务
        praiseService.praise(null , item_id, token);

        ReturnBaseDto<JSONObject> result = new ReturnBaseDto<>();
        result.setCode(Code.Success.getCode());
        result.setStatus(Code.Success.getStatus());
        return result;
    }

}
