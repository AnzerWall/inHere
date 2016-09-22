package com.inHere.service.impl;

import com.alibaba.fastjson.JSON;
import com.inHere.constant.Field;
import com.inHere.dao.CommentMapper;
import com.inHere.entity.Token;
import com.inHere.service.PraiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 点赞业务
 * 
 * @author lwh
 *
 */
@Service
public class PraiseServiceImpl implements PraiseService {

	@Autowired
	private CommentMapper commentMapper;

	/**
	 * 获取点赞数目
	 * 
	 * @return
	 */
	@Override
	public Integer getPraiseSize(String praise) {
		if (praise == null)
			return 0;
		return JSON.parseObject(praise).size();
	}

	/**
	 * 判断用户是否点赞了
	 */
	@Override
	public Integer praised(String praise, String user_id) {
		if (praise == null)
			return 0;
		// 判断用户是否点赞了
		boolean flag = JSON.parseObject(praise).containsKey(user_id);
		return flag ? Field.Praised_YES : Field.Praised_NO;
	}

    /**
     * 点赞销赞
     * @return
     */
	public boolean praise(Integer ext_type, Integer item_id, Token token){
        // 评论点赞销赞
        if ( ext_type == null ){
			commentMapper.praise(token.getUser_id(), item_id);
        }
        // 走起点赞销赞
        if( Field.ExtType_Dating == ext_type ){

        }
        // 活动或广告点赞销赞
        if( Field.ExtType_Activity == ext_type || Field.ExtType_AD == ext_type ){

        }
        // 校内或校外吐槽点赞销赞
        if ( Field.ExtType_InTeasing == ext_type || Field.ExtType_OutTeasing == ext_type ){

        }
        return true;
    }



}
