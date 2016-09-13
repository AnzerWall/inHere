package com.inHere.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.inHere.constant.Field;
import com.inHere.constant.LabelEnum;
import com.inHere.dao.AskReplyMapper;
import com.inHere.dao.AskReplyUserMapper;
import com.inHere.dao.CommentMapper;
import com.inHere.dao.LabelMapper;
import com.inHere.dto.ParamsListDto;
import com.inHere.dto.ReturnListDto;
import com.inHere.entity.AskReply;
import com.inHere.entity.AskReplyUser;
import com.inHere.entity.Comment;
import com.inHere.entity.Label;
import com.inHere.entity.Token;
import com.inHere.service.AskReplyService;
import com.inHere.service.CommentService;
import com.inHere.service.CommonService;
import com.inHere.service.PraiseService;

@Service
public class AskReplyServiceImpl implements AskReplyService {

	Logger log = Logger.getLogger(getClass());

	@Autowired
	private AskReplyMapper askReplyMapper;

	@Autowired
	private CommentMapper commentMapper;

	@Autowired
	private LabelMapper labelMapper;

	@Autowired
	private AskReplyUserMapper askReplyUserMapper;

	@Autowired
	private CommentService commentService;

	@Autowired
	private CommonService commonService;

	@Autowired
	private PraiseService praiseService;

	/**
	 * 获取吐槽+问答的列表
	 * 
	 * @param params
	 * @return
	 * @throws IOException
	 */
	public JSONObject getList(ParamsListDto params) throws IOException {
		JSONObject data = new JSONObject();
		log.info("进入AskReplyServiceImpl.getList()");

		// 为空时，查找全部标签数据
		if (params.getLabel_id() == null) {
			// 返回最火五条标签数据
			data.put("labels", this.getHotLabel(params.getType()));
		}

		// 创建返回列表对象
		ReturnListDto listDto = new ReturnListDto();
		// 获取总条数
		Integer total = askReplyMapper.getCount(params);
		Integer total_page = (total / params.getLimit()) + 1;
		listDto.setLimit(params.getLimit());
		listDto.setOffset(params.getOffset());
		listDto.setTotal(total);
		listDto.setTotal_page(total_page);
		// 获取列表数据
		JSONArray items = this.getItems(params);
		listDto.setItems(items);

		// 返回列表数据
		data.put("list", listDto);
		return data;
	}

	/**
	 * 获取列表数据
	 * 
	 * @param params
	 * @return
	 * @throws IOException
	 */
	public JSONArray getItems(ParamsListDto params) throws IOException {
		JSONArray items = new JSONArray();
		List<AskReply> list = askReplyMapper.selectList(params);

		for (AskReply obj : list) {
			JSONObject item = new JSONObject();
			item.put("id", obj.getId());
			item.put("ext_type", obj.getExtType());

			// 吐槽的私有数据
			if (params.getType() == Field.ExtType_InTeasing || params.getType() == Field.ExtType_OutTeasing) {
				item.put("ext_data", this.getTeasingExtData(obj, params.getTokenEntity()));
			}
			// 问答的私有数据
			if (params.getType() == Field.ExtType_AskAnwser) {
				item.put("ext_data", this.getAskAnwserDetailExtData(obj, params.getTokenEntity()));
			}

			item.put("user_id", obj.getUserId());
			item.put("label_id", obj.getLabel().getId());
			item.put("label_name", obj.getLabel().getName());
			item.put("create_time", obj.getCreateTime().getTime());
			items.add(item);
		}
		return items;
	}

	/**
	 * 获取最火的5条标签
	 * 
	 * @param type
	 * @return
	 */
	public JSONArray getHotLabel(Integer type) {
		List<Label> labels = labelMapper.selectHotLabel(type);
		JSONArray list = new JSONArray();

		if (type == Field.ExtType_InTeasing) {
			// 校内吐槽，第一条标签为小道消息
			JSONObject hearsay = new JSONObject();
			hearsay.put("id", LabelEnum.Hearsay.getId());
			hearsay.put("name", LabelEnum.Hearsay.getName());
			list.add(0, hearsay);

			for (int i = 1; i < labels.size() && i < 5; i++) {
				JSONObject obj = new JSONObject();
				obj.put("id", labels.get(i).getId());
				obj.put("name", labels.get(i).getName());
				list.add(i, obj);
			}

		} else {
			for (int i = 0; i < labels.size(); i++) {
				JSONObject obj = new JSONObject();
				obj.put("id", labels.get(i).getId());
				obj.put("name", labels.get(i).getName());
				list.add(i, obj);
			}
		}
		return list;
	}

	/**
	 * 获取一条详细吐槽或问答信息
	 * 
	 * @param params
	 * @return
	 * @throws IOException
	 */
	public JSONObject getOneAskReply(ParamsListDto params) throws IOException {
		AskReply obj = askReplyMapper.selectOne(params);
		JSONObject data = new JSONObject();
		if (obj != null) {
			Map<String, Object> map = obj.toMap();

			// 拼接吐槽私有属性
			if (obj.getExtType() == Field.ExtType_InTeasing || obj.getExtType() == Field.ExtType_OutTeasing) {
				JSONObject ext_data = this.getTeasingExtData(obj, params.getTokenEntity());
				map.put("ext_data", ext_data);
			}
			// 拼接问答私有属性
			if (obj.getExtType() == Field.ExtType_AskAnwser) {
				JSONObject ext_data = this.getAskAnwserExtData(obj, params.getTokenEntity());
				map.put("ext_data", ext_data);
			}

			// 拼接评论列表
			params.setType(obj.getExtType());
			List<Comment> comments = commentMapper.selectList(params);

			// 设置当前需求实体的“评论列表”
			ReturnListDto listDto = new ReturnListDto();

			// 获取评论总条数
			Integer total = commentMapper.getCount(obj.getExtType(), obj.getId());
			Integer total_page = (total / params.getLimit()) + 1;
			listDto.setLimit(params.getLimit());
			listDto.setOffset(params.getOffset());
			listDto.setTotal(total);
			listDto.setTotal_page(total_page);

			// 设置评论列表
			JSONArray commentArray = commentService.setItems(comments, params.getTokenEntity());
			listDto.setItems(commentArray);

			map.put("reply_list", listDto);
			data.putAll(map);
			return data;
		}
		return data;
	}

	/**
	 * 获取吐槽私有数据
	 * 
	 * @param obj
	 * @return
	 * @throws IOException
	 */
	private JSONObject getTeasingExtData(AskReply obj, Token token) throws IOException {
		JSONObject data = new JSONObject();
		data.put("content", obj.getContent());
		data.put("comment_num", commentService.getCount(obj.getExtType(), obj.getId()));
		data.put("praise", praiseService.getPraiseSize(obj.getPraise()));
		data.put("praised", praiseService.praised(obj.getPraise(), token.getUser_id()));
		data.put("photos", commonService.photoResolution(obj.getPhotos()));
		return data;
	}

	/**
	 * 获取问答私有数据
	 * 
	 * @param obj
	 * @param token
	 * @return
	 */
	private JSONObject getAskAnwserExtData(AskReply obj, Token token) {
		JSONObject data = new JSONObject();
		data.put("title", obj.getTitle());
		data.put("content", obj.getContent());
		AskReplyUser followUser = askReplyUserMapper.selectFollowUser(token.getUser_id(), obj.getId());
		// 判断用户是否关注该问答
		Integer follow = followUser == null ? 0 : 1;
		data.put("follow", follow);
		return data;
	}

	/**
	 * 获取问答私有数据，包含最火的回答
	 * 
	 * @param obj
	 * @param token
	 * @return
	 */
	private JSONObject getAskAnwserDetailExtData(AskReply obj, Token token) {
		JSONObject data = new JSONObject();
		data.put("title", obj.getTitle());
		data.put("comment_num", commentService.getCount(obj.getExtType(), obj.getId()));

		// 获取最火的评论
		Comment comment = commentMapper.selectBestComment(obj.getExtType(), obj.getId());
		if (comment != null) {
			// 最火的评论的点赞数和用户是否点赞
			data.put("praise", praiseService.getPraiseSize(comment.getPraise()));
			data.put("praised", praiseService.praised(comment.getPraise(), token.getUser_id()));
			// 获取最火的答案返回
			JSONObject bestReply = new JSONObject();
			bestReply.put("id", comment.getId());
			bestReply.put("best_answer", comment.getContent());
			data.put("best_reply", bestReply);
		}
		return data;
	}

}
