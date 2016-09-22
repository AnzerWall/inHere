package com.inHere.service.impl;

import com.alibaba.fastjson.JSON;
import com.inHere.constant.Field;
import com.inHere.dao.ActivityMapper;
import com.inHere.dao.AskReplyMapper;
import com.inHere.dao.CommentMapper;
import com.inHere.dao.DemandMapper;
import com.inHere.entity.Token;
import com.inHere.service.PraiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Autowired
	private DemandMapper demandMapper;

	@Autowired
	private ActivityMapper activityMapper;

	@Autowired
	private AskReplyMapper askReplyMapper;

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
    @Transactional
	public boolean praise(Integer ext_type, Integer item_id, Token token){
        // 评论点赞销赞
        if ( ext_type == null ){
			commentMapper.praise(token.getUser_id(), item_id);
			return true;
        }
        // 走起点赞销赞
        if( Field.ExtType_Dating == ext_type ){
			demandMapper.praise(token.getUser_id(), item_id);
			return true;
        }
        // 活动或广告点赞销赞
        if( Field.ExtType_Activity == ext_type || Field.ExtType_AD == ext_type ){
			activityMapper.praise(token.getUser_id(), item_id);
			return true;
        }
        // 校内或校外吐槽点赞销赞
        if ( Field.ExtType_InTeasing == ext_type || Field.ExtType_OutTeasing == ext_type ){
			askReplyMapper.praise(token.getUser_id(), item_id);
			return true;
        }
        return false;
    }



}
