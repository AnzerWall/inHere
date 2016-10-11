package com.inHere.web;

import com.alibaba.fastjson.JSONObject;
import com.inHere.annotation.Authorization;
import com.inHere.annotation.CurrentToken;
import com.inHere.annotation.Params;
import com.inHere.constant.Code;
import com.inHere.dto.CommentDto;
import com.inHere.dto.ParamsListDto;
import com.inHere.dto.ReturnBaseDto;
import com.inHere.entity.Token;
import com.inHere.service.CommentService;
import com.inHere.validator.CommentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 评论Controller
 *
 * @author lwh
 */
@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Authorization
    @Params(CommentValidator.class)
    @RequestMapping(path = "/comments", method = RequestMethod.GET)
    public ReturnBaseDto<JSONObject> getComments(ParamsListDto params, @CurrentToken Token token) {
        // 初始化参数
        Integer offset = params.getOffset() == null ? 0 : params.getOffset();
        Integer limit = params.getLimit() == null ? 10 : params.getLimit();
        params.setOffset(offset);
        params.setLimit(limit);
        params.setTokenEntity(token);
        params.setType(params.getExt_type()[0]);

        JSONObject data = commentService.getList(params);

        ReturnBaseDto<JSONObject> result = new ReturnBaseDto<>();
        result.setCode(Code.Success.getCode());
        result.setStatus(Code.Success.getStatus());
        result.setData(data);
        return result;
    }

    /**
     * 评论
     * TODO 信息队列, 提醒
     *
     * @param commentDto
     * @param token
     * @return
     */
    @Authorization
    @Params(CommentValidator.class)
    @RequestMapping(path = "/comments", method = RequestMethod.POST)
    public ReturnBaseDto<JSONObject> createComment(@RequestBody CommentDto commentDto, @CurrentToken Token token) {

        // 创建一条评论
        commentService.createComment(commentDto, token);

        ReturnBaseDto<JSONObject> result = new ReturnBaseDto<>();
        result.setCode(Code.Success.getCode());
        result.setStatus(Code.Success.getStatus());
        return result;
    }

}
