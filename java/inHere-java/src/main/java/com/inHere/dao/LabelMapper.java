package com.inHere.dao;

import com.inHere.entity.Label;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 标签数据库操作接口
 *
 * @author lwh
 */
public interface LabelMapper {


    /**
     * 获取最火的5条标签列表
     *
     * @return Label的list集合
     */
    List<Label> selectHotLabel(@Param("type") Integer type, @Param("offset") Integer offset, @Param("limit") Integer limit);

    /**
     * 插入一条标签
     *
     * @param label 新创建的标签
     * @return 受影响行数
     */
    int insertOneLabel(@Param("label") Label label);

    /**
     * 查找相同名称的标签
     *
     * @param name
     * @return
     */
    Label selectSameNameLabel(String name);
}