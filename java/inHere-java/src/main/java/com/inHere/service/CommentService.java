package com.inHere.service;

import java.io.IOException;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.inHere.dto.ParamsListDto;
import com.inHere.dto.ReturnListDto;
import com.inHere.entity.Comment;
import com.inHere.entity.Token;

public interface CommentService {

	/**
	 * 获取列表
	 * 
	 * @param comments
	 * @return
	 * @throws IOException
	 */
	public ReturnListDto getList(ParamsListDto params);

	/**
	 * 设置评论列表
	 * 
	 * @param comments
	 * @return
	 */
	public JSONArray setItems(List<Comment> comments, Token token);

}