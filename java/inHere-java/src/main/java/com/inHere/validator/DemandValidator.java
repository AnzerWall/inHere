package com.inHere.validator;

import org.apache.log4j.Logger;

import com.inHere.dto.ParamsListDto;
import com.inHere.dto.ReturnBaseDto;
import com.inHere.dto.ReturnListDto;

/**
 * 要求校验方法名与@Params拦截的方法的名字要相同, 校验方法的参数有且只能有一个，参数类型和返回类型都要和拦截方法的一样
 * 
 * @author lwh
 *
 */
public class DemandValidator {

	Logger log = Logger.getLogger(getClass());

	/**
	 * DemandController.getDemandList()方法的校验
	 * 
	 * @param params
	 * @return
	 */
	public ReturnBaseDto<ReturnListDto> getDemandList(ParamsListDto params) {
		log.info("进入到DemandController.getDemandList()方法的校验");
		return null;
	}

}
