package com.inHere.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.inHere.constant.Field;
import com.inHere.dao.CommentMapper;
import com.inHere.dto.ParamsListDto;
import com.inHere.dto.ReturnCommentDto;
import com.inHere.entity.Comment;
import com.inHere.entity.Token;
import com.inHere.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	Logger log = Logger.getLogger(getClass());

	@Autowired
	private CommentMapper commentMapper;

	/**
	 * 获取评论列表
	 * 
	 * @param comments
	 * @return
	 * @throws IOException
	 */
	public JSONObject getList(ParamsListDto params) {
		JSONObject data = new JSONObject();

		// 设置总页数
		Integer total = this.getCount(params.getType(), params.getItem_id());
		Integer total_page = total / params.getLimit() + 1;

		// 获取评论列表
		List<Comment> comments = commentMapper.selectList(params);

		data.put("offset", params.getOffset());
		data.put("limit", params.getLimit());
		data.put("total", total);
		data.put("total_page", total_page);
		data.put("items", this.setItems(comments, params.getTokenEntity()));
		return data;
	}

	/**
	 * 设置评论列表
	 * 
	 * @param comments
	 * @return
	 */
	public JSONArray setItems(List<Comment> comments, Token token) {
		JSONArray array = new JSONArray();
		for (Comment tmp : comments) {
			ReturnCommentDto commentDto = this.setCommentDto(tmp, token);
			array.add(commentDto);
		}
		return array;
	}

	/**
	 * 评论传输对象赋值
	 * 
	 * @param tmp
	 * @return
	 */
	public ReturnCommentDto setCommentDto(Comment tmp, Token token) {
		ReturnCommentDto commentDto = new ReturnCommentDto();
		commentDto.setId(tmp.getId());
		commentDto.setUser_id(tmp.getUserId());
		commentDto.setName(tmp.getName());
		commentDto.setCreate_time(tmp.getCreateTime().getTime());
		commentDto.setContent(tmp.getContent());

		JSONObject praiseObj = JSON.parseObject(tmp.getPraise());
		if (praiseObj != null) {
			// 点赞数目
			commentDto.setPraise(praiseObj.size());
			boolean flag = praiseObj.containsKey(token.getUser_id());
			// 是否点赞
			commentDto.setPraised(flag ? Field.Praised_YES : Field.Praised_NO);
		} else {
			commentDto.setPraise(0);
			commentDto.setPraised(Field.Praised_NO);
		}
		commentDto.setFloor(tmp.getFloor());
		return commentDto;
	}

	@Override
	public Integer getCount(Integer ext_type, Integer record_id) {
		return commentMapper.getCount(ext_type, record_id);
	}

}
