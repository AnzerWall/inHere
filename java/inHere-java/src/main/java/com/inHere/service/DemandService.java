package com.inHere.service;

import java.io.IOException;

import com.inHere.dto.ParamsListDto;
import com.inHere.dto.ReturnDemandDto;
import com.inHere.dto.ReturnListDto;

/**
 * 有求必应业务逻辑
 * 
 * @author lwh
 *
 */
public interface DemandService {

	/**
	 * 获取列表
	 * 
	 * @param params
	 * @return
	 * @throws IOException
	 */
	public ReturnListDto getList(ParamsListDto params) throws IOException;

	/**
	 * 获取一条有求必应
	 * 
	 * @param id
	 * @return
	 */
	public ReturnDemandDto selectOneById(Integer id) throws IOException;

}
