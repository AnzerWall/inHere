package com.inHere.service;

import java.io.IOException;

import com.alibaba.fastjson.JSONObject;
import com.inHere.dto.ParamsListDto;
import com.inHere.dto.ReturnDemandDto;
import com.inHere.dto.ReturnListDto;
import com.inHere.entity.Demand;
import org.springframework.transaction.annotation.Transactional;

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
	public ReturnDemandDto selectOneById(ParamsListDto params) throws IOException;

	/**
	 * 创建一个需求
	 *
	 * @param demand
	 * @return
	 */
	public boolean createDemand(Demand demand) throws IOException;

}
