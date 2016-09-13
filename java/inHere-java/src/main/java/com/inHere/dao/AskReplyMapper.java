package com.inHere.dao;

import java.util.List;

import com.inHere.dto.ParamsListDto;
import com.inHere.entity.AskReply;

/**
 * 吐槽+问答数据库操作接口
 * 
 * @author lwh
 *
 */
public interface AskReplyMapper {

	int deleteByPrimaryKey(Integer id);

	int insertSelective(AskReply record);

	AskReply selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(AskReply record);

	/**
	 * 获取吐槽列表
	 * 
	 * @param params
	 * @return
	 */
	public List<AskReply> selectList(ParamsListDto params);


	/**
	 * 获取总条数
	 * 
	 * @return
	 */
	public Integer getCount(ParamsListDto params);

	/**
	 * 获取一条评论数
	 * 
	 * @param params
	 * @return
	 */
	public AskReply selectOne(ParamsListDto params);
}