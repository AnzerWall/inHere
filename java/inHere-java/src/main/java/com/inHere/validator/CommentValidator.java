package com.inHere.validator;

import com.alibaba.fastjson.JSONObject;
import com.inHere.dto.CommentDto;
import com.inHere.dto.ParamsListDto;
import com.inHere.dto.ReturnBaseDto;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by Administrator on 2016/9/21.
 */
public class CommentValidator {

    /**
     * 获取评论列表参数校验
     *
     * @param params
     * @return
     */
    public ReturnBaseDto<JSONObject> getComments(ParamsListDto params) {

        return null;
    }

    public ReturnBaseDto<JSONObject> createComment(@RequestBody CommentDto commentDto){
        return null;
    }

}
