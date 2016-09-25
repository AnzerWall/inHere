package com.inHere.service;

import com.inHere.dto.ParamsListDto;
import com.inHere.dto.ReturnDemandDto;
import com.inHere.dto.ReturnListDto;
import com.inHere.entity.Token;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;

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
	ReturnListDto getList(ParamsListDto params) throws IOException;

	/**
	 * 获取一条有求必应
	 * 
	 * @return
	 */
	ReturnDemandDto selectOneById(ParamsListDto params) throws IOException;

	/**
	 * 创建一个需求
	 *
	 * @return
	 */
	boolean createDemand(MultipartHttpServletRequest multiRequest, Token token) throws IOException;

}
