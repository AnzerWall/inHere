package com.inHere.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.inHere.dto.CommentDto;
import com.inHere.dto.ParamsListDto;
import com.inHere.entity.Comment;
import com.inHere.entity.Token;

import java.io.IOException;
import java.util.List;

public interface CommentService {

	/**
	 * 获取列表
	 * 
	 * @param params
	 * @return
	 * @throws IOException
	 */
	JSONObject getList(ParamsListDto params);

	/**
	 * 设置评论列表
	 * 
	 * @param comments
	 * @return
	 */
	JSONArray setItems(List<Comment> comments, Token token, Integer maxFloor);

	/**
	 * 获取评论总数
	 * 
	 * @param ext_type
	 * @param record_id
	 * @return
	 */
	Integer getCount(Integer ext_type, Integer record_id);

	/**
	 * 创建一条评论
	 *
	 * @param commentDto
	 * @param token
	 */
	void createComment(CommentDto commentDto, Token token);

}
