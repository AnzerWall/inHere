package com.inHere.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.inHere.dao.CommentMapper;
import com.inHere.dao.DemandMapper;
import com.inHere.dto.ParamsListDto;
import com.inHere.dto.ReturnDemandDto;
import com.inHere.dto.ReturnListDto;
import com.inHere.entity.Comment;
import com.inHere.entity.Demand;
import com.inHere.service.CommentService;
import com.inHere.service.CommonService;
import com.inHere.service.DemandService;

/**
 * 有求必应业务逻辑
 * 
 * @author lwh
 *
 */
@Service
@Transactional
public class DemandServiceImpl implements DemandService {

	Logger log = Logger.getLogger(getClass());

	@Autowired
	private DemandMapper demandMapper;

	@Autowired
	private CommentMapper commentMapper;

	@Autowired
	private CommonService commonService;

	@Autowired
	private CommentService commentService;

	/**
	 * 获取列表
	 * 
	 * @param params
	 * @return
	 * @throws IOException
	 */
	public ReturnListDto getList(ParamsListDto params) throws IOException {
		log.info("调用getList()业务");
		ReturnListDto listDto = new ReturnListDto();
		List<Demand> demandList = demandMapper.selectByParams(params);

		// 获取总条数
		Integer total = demandMapper.getCount(params);
		Integer total_page = (total / params.getLimit()) + 1;
		listDto.setLimit(params.getLimit());
		listDto.setOffset(params.getOffset());
		listDto.setTotal(total);
		listDto.setTotal_page(total_page);

		JSONArray items = this.setItems(demandList);
		listDto.setItems(items);
		return listDto;
	}

	/**
	 * 获取一条有求必应
	 * 
	 * @param id
	 * @return
	 * @throws IOException
	 */
	public ReturnDemandDto selectOneById(ParamsListDto params) throws IOException {
		Demand demand = demandMapper.selectOneById(params);
		if (demand == null) {
			return new ReturnDemandDto();
		} else {
			// 当前实体拥有的评论列表
			List<Comment> comments = demand.getComments();

			// 获取“有求必应实体”DemandDto传输对象
			ReturnDemandDto demandDto = this.setDemandDto(demand);

			// 设置当前需求实体的“评论列表”
			ReturnListDto listDto = new ReturnListDto();

			// 获取评论总条数
			Integer total = commentMapper.getCount(demand.getExtType(), demand.getId());
			Integer total_page = (total / params.getLimit()) + 1;
			listDto.setLimit(params.getLimit());
			listDto.setOffset(params.getOffset());
			listDto.setTotal(total);
			listDto.setTotal_page(total_page);

			// 设置评论列表
			JSONArray commentArray = commentService.setItems(comments, params.getUser());
			listDto.setItems(commentArray);

			// 有求必应实体添加对应评论列表返回。。。。
			demandDto.setComment(listDto);
			return demandDto;
		}
	}

	/**
	 * 设置各类型Items
	 * 
	 * @param listDto
	 * @param demandList
	 * @return
	 * @throws IOException
	 */
	public JSONArray setItems(List<Demand> demandList) throws IOException {
		JSONArray array = new JSONArray();
		for (Demand tmp : demandList) {
			ReturnDemandDto demandDto = this.setDemandDto(tmp);
			array.add(demandDto);
		}
		return array;
	}

	/**
	 * 给ReturnDemandDto对象赋值
	 * 
	 * @param tmp
	 * @return
	 * @throws IOException
	 */
	public ReturnDemandDto setDemandDto(Demand tmp) throws IOException {
		ReturnDemandDto demandDto = new ReturnDemandDto();
		demandDto.setId(tmp.getId());
		demandDto.setExt_type(tmp.getExtType());
		demandDto.setText(tmp.getText());
		// 解析图片
		demandDto.setPhotos(commonService.photoResolution(tmp.getPhotos()));
		demandDto.setCreate_time(tmp.getCreateTime().getTime());
		demandDto.setUpdate_time(tmp.getUpdateTime().getTime());
		demandDto.setUser_id(tmp.getUserId());
		demandDto.setExt_data(JSONObject.parseObject(tmp.getExtData()));
		demandDto.setIs_end(tmp.getIsEnd());
		return demandDto;
	}

}
