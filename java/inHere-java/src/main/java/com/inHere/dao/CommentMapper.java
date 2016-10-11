package com.inHere.dao;

import com.inHere.dto.CommentDto;
import com.inHere.dto.ParamsListDto;
import com.inHere.entity.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentMapper {

    /**
     * 获取评论总数
     *
     * @param ext_type
     * @param record_id
     * @return
     */
    Integer getCount(@Param("ext_type") Integer ext_type, @Param("record_id") Integer record_id);

    /**
     * 获取评论列表
     *
     * @param params
     * @return
     */
    List<Comment> selectList(ParamsListDto params);

    /**
     * 获取最火的评论
     *
     * @return
     */
    Comment selectBestComment(@Param("type") Integer type, @Param("record_id") Integer record_id);

    /**
     * 插入一条评论
     *
     * @param comment
     * @param use_id
     * @param name
     * @return
     */
    Integer insertComment(@Param("comment") CommentDto comment,
                          @Param("user_id") String use_id, @Param("name") String name);

    /**
     * 用户点赞销赞
     * @param user_id
     * @param id
     * @return
     */
    Integer praise(@Param("user_id") String user_id, @Param("id") Integer id);

    /**
     * 设置emoji的支持编码
     */
    void setNamesUtf8mb4();
}