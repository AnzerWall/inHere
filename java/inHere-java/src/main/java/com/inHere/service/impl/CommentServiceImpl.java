package com.inHere.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.inHere.constant.Field;
import com.inHere.dao.CommentMapper;
import com.inHere.dao.NameUsedMapper;
import com.inHere.dto.CommentDto;
import com.inHere.dto.ParamsListDto;
import com.inHere.dto.ReturnCommentDto;
import com.inHere.entity.Comment;
import com.inHere.entity.Name;
import com.inHere.entity.NameUsed;
import com.inHere.entity.Token;
import com.inHere.service.CommentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    Logger log = Logger.getLogger(getClass());

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private NameUsedMapper nameUsedMapper;

    /**
     * 获取评论列表
     *
     * @param params
     * @return
     * @throws IOException
     */
    public JSONObject getList(ParamsListDto params) {
        JSONObject data = new JSONObject();

        // 设置总页数
        Integer total = this.getCount(params.getType(), params.getItem_id());
        Integer total_page = (total == 0 ? total : total / params.getLimit() + 1);

        // 获取评论列表
        List<Comment> comments = commentMapper.selectList(params);

        data.put("offset", params.getOffset());
        data.put("limit", params.getLimit());
        data.put("total", total);
        data.put("total_page", total_page);
        data.put("items", this.setItems(comments, params.getTokenEntity(), total - params.getOffset()));
        return data;
    }

    /**
     * 设置评论列表
     *
     * @param comments
     * @return
     */
    public JSONArray setItems(List<Comment> comments, Token token, Integer maxFloor) {
        JSONArray array = new JSONArray();
        // 楼层计算
        Integer floor = maxFloor;
        for (Comment tmp : comments) {
            ReturnCommentDto commentDto = this.setCommentDto(tmp, token, floor);
            array.add(commentDto);
            -- floor;
        }
        return array;
    }

    /**
     * 评论传输对象赋值
     *
     * @param tmp
     * @return
     */
    public ReturnCommentDto setCommentDto(Comment tmp, Token token, Integer floor) {
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
        commentDto.setFloor(floor);
        return commentDto;
    }

    /**
     * 获取评论总数
     *
     * @param ext_type
     * @param record_id
     * @return
     */
    @Override
    public Integer getCount(Integer ext_type, Integer record_id) {
        return commentMapper.getCount(ext_type, record_id);
    }

    /**
     * 创建一条评论
     *
     * @param commentDto
     * @param token
     */
    @Transactional
    public void createComment(CommentDto commentDto, Token token) {
        NameUsed nameUsed = nameUsedMapper.searchName(commentDto.getExt_type(), commentDto.getItem_id(), token.getUser_id());
        if( nameUsed == null ){
            nameUsed = new NameUsed(commentDto.getExt_type(), commentDto.getItem_id(), token.getUser_id());
            // 随机插入匿名
            nameUsedMapper.insertRandomName(nameUsed);
        }
        Name nameObj = nameUsedMapper.selectNameByUsedID(nameUsed.getId());
        commentMapper.insertComment(commentDto, token.getUser_id(), nameObj.getName());
    }

}
