package com.inHere.dao;

import java.util.List;

import com.inHere.entity.Cryptonym;

public interface CryptonymMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(Cryptonym record);

	int insertSelective(Cryptonym record);

	Cryptonym selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Cryptonym record);

	int updateByPrimaryKey(Cryptonym record);

	/**
	 * 插入匿名库
	 * 
	 * @param list
	 * @return
	 */
	public Integer insertCryptonyms(List<Cryptonym> list);
}