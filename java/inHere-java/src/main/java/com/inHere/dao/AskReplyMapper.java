package com.inHere.dao;

import com.inHere.dto.ParamsListDto;
import com.inHere.entity.AskReply;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 吐槽+问答数据库操作接口
 *
 * @author lwh
 */
public interface AskReplyMapper {

    /**
     * 获取一条吐槽或问答
     *
     * @param id
     * @return
     */
    AskReply selectByPrimaryKey(Integer id);

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
     * 获取一条吐槽或问答
     *
     * @param params
     * @return
     */
    AskReply selectOne(ParamsListDto params);

    /**
     * 用户点赞销赞
     *
     * @param user_id
     * @param id
     * @return
     */
    Integer praise(@Param("user_id") String user_id, @Param("id") Integer id);

    /**
     * 获取热门回答列表
     *
     * @param type
     * @param offset
     * @param limit
     * @return
     */
    List<AskReply> selectHotQuestionOrTeasing(@Param("type") Integer type,
                                              @Param("offset") Integer offset, @Param("limit") Integer limit);

    /**
     * 检查问题是否被关注
     *
     * @param user_id
     * @param item_id
     * @return
     */
    Map<String, Object> checkFollowed(@Param("user_id") String user_id, @Param("item_id") Integer item_id);

    /**
     * 关注问题
     *
     * @param user_id
     * @param item_id
     * @return
     */
    Integer followAnIssue(@Param("user_id") String user_id, @Param("item_id") Integer item_id);

}
