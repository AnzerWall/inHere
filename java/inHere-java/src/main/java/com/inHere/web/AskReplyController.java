package com.inHere.web;

import com.alibaba.fastjson.JSONObject;
import com.inHere.annotation.Authorization;
import com.inHere.annotation.CurrentToken;
import com.inHere.annotation.Params;
import com.inHere.constant.Code;
import com.inHere.dto.ParamsListDto;
import com.inHere.dto.ReturnBaseDto;
import com.inHere.entity.Token;
import com.inHere.service.AskReplyService;
import com.inHere.service.LabelService;
import com.inHere.validator.AskReplyValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 问答+吐槽Controller <br>
 *
 * @author lwh
 */
@RestController
public class AskReplyController {

    @Autowired
    private AskReplyService askReplyService;

    @Autowired
    private LabelService labelService;

    @Authorization
    @Params(AskReplyValidator.class)
    @RequestMapping(path = "/ask_reply", method = RequestMethod.GET)
    public ReturnBaseDto<JSONObject> getAskReplyList(ParamsListDto params, @CurrentToken Token token)
            throws IOException {
        // 初始化参数
        Integer offset = params.getOffset() == null ? 0 : params.getOffset();
        Integer limit = params.getLimit() == null ? 10 : params.getLimit();
        params.setOffset(offset);
        params.setLimit(limit);
        params.setType(params.getExt_type()[0]);
        params.setTokenEntity(token);

        JSONObject data = askReplyService.getList(params);

        ReturnBaseDto<JSONObject> result = new ReturnBaseDto<>();
        result.setCode(Code.Success.getCode());
        result.setStatus(Code.Success.getStatus());
        result.setData(data);
        return result;
    }

    @Authorization
    @RequestMapping(path = "/ask_reply/{item_id}", method = RequestMethod.GET)
    public ReturnBaseDto<JSONObject> getAskReply(@PathVariable Integer item_id, @CurrentToken Token token)
            throws IOException {
        ParamsListDto params = new ParamsListDto();
        // 实体关联的评论列表分页初始化
        params.setItem_id(item_id);
        params.setLimit(10);
        params.setOffset(0);
        params.setTokenEntity(token);

        JSONObject data = askReplyService.getOneAskReply(params);

        ReturnBaseDto<JSONObject> result = new ReturnBaseDto<>();
        result.setCode(Code.Success.getCode());
        result.setStatus(Code.Success.getStatus());
        result.setData(data);
        return result;
    }

    /**
     * 创建一个吐槽或问答资源
     *
     * @return
     */
    @Authorization
    @Params(AskReplyValidator.class)
    @RequestMapping(path = "/ask_reply", method = RequestMethod.POST)
    public ReturnBaseDto<JSONObject> createAskReply(HttpServletRequest request, @CurrentToken Token token) throws IOException {
        // 将request变成多部分request
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

        // 提交数据库创建一条吐槽或问答资源
        askReplyService.createAskReply(multiRequest, token);

        ReturnBaseDto<JSONObject> result = new ReturnBaseDto<>();
        result.setCode(Code.Success.getCode());
        result.setStatus(Code.Success.getStatus());
        return result;
    }

    /**
     * 获取问答、吐槽的热门标签
     */
    @Authorization
    @Params(AskReplyValidator.class)
    @RequestMapping(path = "/ask_reply/labels", method = RequestMethod.GET)
    public ReturnBaseDto<JSONObject> getHotLabel(ParamsListDto params) {
        // 初始化参数
        Integer offset = params.getOffset() == null ? 0 : params.getOffset();
        Integer limit = params.getLimit() == null ? 10 : params.getLimit();
        params.setOffset(offset);
        params.setLimit(limit);
        params.setType(params.getExt_type()[0]);

        // 获取热门的标签列表
        JSONObject data = labelService.getHotList(params);

        ReturnBaseDto<JSONObject> result = new ReturnBaseDto<>();
        result.setCode(Code.Success.getCode());
        result.setStatus(Code.Success.getStatus());
        result.setData(data);
        return result;
    }

    /**
     * 关注问题
     *
     * @param item_id
     * @param token
     * @return
     */
    @Authorization
    @Params(AskReplyValidator.class)
    @RequestMapping(path = "/ask_reply/follow/{item_id}", method = RequestMethod.POST)
    public ReturnBaseDto<JSONObject> follow(@PathVariable Integer item_id, @CurrentToken Token token) {

        askReplyService.followAnIssue(token.getUser_id(), item_id);

        ReturnBaseDto<JSONObject> result = new ReturnBaseDto<>();
        result.setCode(Code.Success.getCode());
        result.setStatus(Code.Success.getStatus());
        return result;
    }

}
