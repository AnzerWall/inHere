package com.inHere.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.inHere.annotation.Authorization;
import com.inHere.annotation.CurrentToken;
import com.inHere.dto.ParamsListDto;
import com.inHere.dto.ReturnBaseDto;
import com.inHere.entity.Token;

/**
 * 问答+吐槽Controller <br>
 * TODO 参数校验一定要做，order_by、order有sql注入危险
 * 
 * @author lwh
 *
 */
@RestController
public class AskReplyController {

	@Authorization
	// @Params(AskReplyValidator.class)
	@RequestMapping(path = "/ask_reply", method = RequestMethod.GET)
	public ReturnBaseDto<JSONArray> getAskReplyList(ParamsListDto params, @CurrentToken Token token) {
		// 初始化参数
		Integer offset = params.getOffset() == null ? 0 : params.getOffset();
		Integer limit = params.getLimit() == null ? 10 : params.getLimit();
		params.setOffset(offset);
		params.setLimit(limit);
		params.setTokenEntity(token);
		
		return null;
	}

}
