package com.inHere.dao;

import com.inHere.dto.ParamsListDto;
import com.inHere.entity.AskReply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 吐槽+问答数据库操作接口
 *
 * @author lwh
 */
public interface AskReplyMapper {

    /**
     * 插入一条吐槽或问答
     *
     * @param askReply
     * @return
     */
    int insertSelective(AskReply askReply);

    /**
     * 获取吐槽列表
     *
     * @param params
     * @return
     */
    List<AskReply> selectList(ParamsListDto params);


    /**
     * 获取总条数
     *
     * @return
     */
    Integer getCount(ParamsListDto params);

    /**
     * 获取一条评论数
     *
     * @param params
     * @return
     */
    AskReply selectOne(ParamsListDto params);


    /**
     * 用户点赞销赞
     * @param user_id
     * @param id
     * @return
     */
    Integer praise(@Param("user_id") String user_id, @Param("id") Integer id);

}
