package com.inHere.web;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.inHere.annotation.Authorization;
import com.inHere.annotation.CurrentToken;
import com.inHere.annotation.Params;
import com.inHere.constant.Code;
import com.inHere.dto.ParamsListDto;
import com.inHere.dto.ReturnBaseDto;
import com.inHere.entity.Token;
import com.inHere.service.AskReplyService;
import com.inHere.validator.AskReplyValidator;

/**
 * 问答+吐槽Controller <br>
 * TODO 参数校验一定要做，order_by、order有sql注入危险
 * 
 * @author lwh
 *
 */
@RestController
public class AskReplyController {

	@Autowired
	private AskReplyService askReplyService;

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
		params.setTokenEntity(token);

		JSONObject data = askReplyService.getList(params);

		ReturnBaseDto<JSONObject> result = new ReturnBaseDto<JSONObject>();
		result.setCode(Code.Success.getCode());
		result.setStatus(Code.Success.getStatus());
		result.setData(data);
		return result;
	}

	@Authorization
	@RequestMapping(path = "/ask_reply/{item_id}", method = RequestMethod.GET)
	public ReturnBaseDto<JSONObject> getOneAskReply(@PathVariable Integer item_id, @CurrentToken Token token) {
		ReturnBaseDto<JSONObject> result = new ReturnBaseDto<JSONObject>();
		ParamsListDto params = new ParamsListDto();

		// 实体关联的评论列表分页初始化
		params.setItem_id(item_id);
		params.setLimit(10);
		params.setOffset(0);
		params.setTokenEntity(token);

		result.setCode(Code.Success.getCode());
		result.setStatus(Code.Success.getStatus());
		JSONObject data = askReplyService.getOneAskReply(params);
		result.setData(data == null ? new JSONObject() : data);
		return result;
	}

}
