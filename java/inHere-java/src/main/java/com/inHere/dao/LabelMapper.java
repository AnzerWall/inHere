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
     * 获取最火的标签列表
     *
     * @param type      类型
     * @param offset    偏移量
     * @param limit     条数
     * @param excLabID  排除标签
     * @param onlyLabID 只搜标签
     * @return
     */
    List<Label> selectHotLabel(@Param("type") Integer type,
                               @Param("offset") Integer offset,
                               @Param("limit") Integer limit,
                               @Param("excLabID") Integer excLabID,
                               @Param("onlyLabID") Integer onlyLabID);

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

    /**
     * 获取吐槽或问答某模块标签总数
     *
     * @param type
     * @return
     */
    Integer getCount(@Param("type") Integer type);

    /**
     * 获取某标签的总吐槽数或总问答数
     *
     * @param type
     * @param lab_id
     * @return
     */
    Integer getCountForLab(@Param("type") Integer type, @Param("lab_id") Integer lab_id);
}