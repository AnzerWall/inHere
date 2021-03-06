package com.inHere.service;

import com.alibaba.fastjson.JSONObject;
import com.inHere.dto.ParamsListDto;
import com.inHere.entity.Token;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;

public interface AskReplyService {

    /**
     * 获取吐槽+问答的列表
     */
    JSONObject getList(ParamsListDto params) throws IOException;

    /**
     * 获取一条详细吐槽或问答信息
     */
    JSONObject getOneAskReply(ParamsListDto params) throws IOException;

    /**
     * 创建一个吐槽或问答资源
     */
    void createAskReply(MultipartHttpServletRequest multiRequest, Token token) throws IOException;

    /**
     * 检查问题是否被关注
     *
     * @param user_id
     * @param item_id
     * @return
     */
    boolean checkFollowed(String user_id, Integer item_id);

    /**
     * 关注问题
     *
     * @param user_id
     * @param item_id
     * @return
     */
    void followAnIssue(String user_id, Integer item_id);
}
