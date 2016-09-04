package com.inHere.dao;

import java.util.List;

import com.inHere.entity.TbCryptonym;

/**
 * 匿名表操作接口
 * 
 * @author lwh
 *
 */
public interface TbCryptonymMapper {

	/**
	 * 插入匿名数据
	 * 
	 * @param list
	 * @return
	 */
	public int insertCryptonyms(List<TbCryptonym> list);

}
