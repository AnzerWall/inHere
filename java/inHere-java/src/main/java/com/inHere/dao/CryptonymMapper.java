package com.inHere.dao;

import com.inHere.entity.Cryptonym;

public interface CryptonymMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cryptonym record);

    int insertSelective(Cryptonym record);

    Cryptonym selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cryptonym record);

    int updateByPrimaryKey(Cryptonym record);
}