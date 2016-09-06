package com.inHere.dao;

import com.inHere.entity.CryptonymUsed;

public interface CryptonymUsedMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CryptonymUsed record);

    int insertSelective(CryptonymUsed record);

    CryptonymUsed selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CryptonymUsed record);

    int updateByPrimaryKey(CryptonymUsed record);
}