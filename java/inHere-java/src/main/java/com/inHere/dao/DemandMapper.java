package com.inHere.dao;

import com.inHere.dto.ParamsListDto;
import com.inHere.entity.Demand;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DemandMapper {

    public int deleteByPrimaryKey(Integer id);

    public int insertSelective(@Param("demand") Demand demand, @Param("ext_data") Map<String, Object> ext_data);

    public Demand selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Demand record);

    // 获取列表
    List<Demand> selectByParams(ParamsListDto params);

    // 获取总条数
    Integer getCount(ParamsListDto params);

    // 通过ID获取一条需求
    Demand selectOneById(ParamsListDto params);

    /**
     * 用户点赞销赞
     * @param user_id
     * @param id
     * @return
     */
    Integer praise(@Param("user_id") String user_id, @Param("id") Integer id);
}
