package com.inHere.dao;

import com.inHere.dto.ParamsListDto;
import com.inHere.entity.Activity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ActivityMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Activity record);

    int insertSelective(Activity record);

    /**
     * 获取一条活动
     *
     * @param id
     * @return
     */
    Activity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKey(Activity record);

    /**
     * 获取未结束的活动列表
     *
     * @param params
     * @return
     */
    List<Activity> selectActivityList(ParamsListDto params);

    /**
     * 获取总条数
     *
     * @param school_id
     * @return
     */
    Integer getCount(@Param("school_id") Integer school_id);


    /**
     * 用户点赞销赞
     *
     * @param user_id
     * @param id
     * @return
     */
    Integer praise(@Param("user_id") String user_id, @Param("id") Integer id);

    /**
     * Get last item
     */
    Activity selectLastItem();

    /**
     * 关闭一条活动
     * @param id
     * @return
     */
    Integer closeActivity(Integer id);
}
