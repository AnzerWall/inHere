package com.inHere.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.inHere.constant.Field;
import com.inHere.dao.CommentMapper;
import com.inHere.dto.ParamsListDto;
import com.inHere.dto.ReturnCommentDto;
import com.inHere.dto.ReturnListDto;
import com.inHere.entity.Comment;
import com.inHere.entity.Token;
import com.inHere.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	Logger log = Logger.getLogger(getClass());

	@Autowired
	private CommentMapper commentMapper;

	/**
	 * 获取点赞数目
	 * 
	 * @param praise
	 * @return
	 */
	public Integer getPraiseSize(String praise) {
		JSONArray array = JSON.parseArray(praise);
		return array.size();
	}

	/**
	 * 获取列表
	 * 
	 * @param comments
	 * @return
	 * @throws IOException
	 */
	public ReturnListDto getList(ParamsListDto params) {
		return null;
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
		// TODO
		JSONArray praiseArray = JSON.parseArray(tmp.getPraise());
		if (praiseArray != null) {
			// 点赞数目
			commentDto.setPraise(praiseArray.size());
			boolean flag = praiseArray.contains(token.getUser_id());
			// 是否点赞
			commentDto.setPraised(flag ? Field.Praised_YES : Field.Praised_NO);
		} else {
			commentDto.setPraise(0);
			commentDto.setPraised(Field.Praised_NO);
		}
		commentDto.setFloor(tmp.getFloor());
		return commentDto;
	}

}
