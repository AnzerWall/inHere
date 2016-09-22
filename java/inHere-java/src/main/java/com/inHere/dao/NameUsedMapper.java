package com.inHere.dao;

import com.inHere.entity.Name;
import com.inHere.entity.NameUsed;
import org.apache.ibatis.annotations.Param;

/**
 * 匿名库关联用户 操作接口
 */
public interface NameUsedMapper {

    /**
     * 插入随机不重复的匿名值
     *
     * @return
     */
    Integer insertRandomName(@Param("nameUsed") NameUsed nameUsed);

    /**
     * 检索是否已使用匿名
     *
     * @param type    类型
     * @param item_id 记录编号
     * @param user_id 用户账号
     * @return
     */
    NameUsed searchName(@Param("type") Integer type,
                        @Param("item_id") Integer item_id, @Param("user_id") String user_id);

    /**
     * 通过用户/匿名关系表查找匿名
     *
     * @param nameUsedID
     * @return
     */
    Name selectNameByUsedID(@Param("nameUsedID") Integer nameUsedID);

}