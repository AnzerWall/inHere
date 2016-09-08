package com.inHere.web;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.inHere.authorization.Authorization;
import com.inHere.constant.Code;
import com.inHere.dto.ParamsListDto;
import com.inHere.dto.ReturnBaseDto;
import com.inHere.dto.ReturnDemandDto;
import com.inHere.dto.ReturnListDto;
import com.inHere.entity.User;
import com.inHere.service.DemandService;
import com.inHere.validator.DemandValidator;
import com.inHere.validator.Params;

/**
 * 有求必应Controller
 * 
 * @author lwh
 *
 */
@RestController
public class DemandController {

	Logger log = Logger.getLogger(getClass());

	@Autowired
	private DemandService demandService;

	/**
	 * TODO 去掉 RequestMethod.POST 请求方式
	 * 
	 * @param params
	 * @return
	 * @throws IOException
	 */
	@Authorization
	@Params(DemandValidator.class)
	@RequestMapping(path = "/demand", method = { RequestMethod.GET, RequestMethod.POST })
	public ReturnBaseDto<ReturnListDto> getDemandList(@RequestBody ParamsListDto params) throws IOException {
		ReturnBaseDto<ReturnListDto> result = new ReturnBaseDto<ReturnListDto>();
		result.setCode(Code.Success.getCode());
		result.setStatus(Code.Success.getStatus());

		// TODO 去掉测试模块，完成业务
		User user = new User();
		user.setUserId("ni_menhao");
		user.setSchoolId(1);// 肇庆学院
		params.setUser(user);

		result.setData(demandService.getList(params));
		return result;
	}

	/**
	 * TODO 去掉 RequestMethod.POST 请求方式
	 * 
	 * @param item_id
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(path = "/demand/{item_id}", method = { RequestMethod.GET, RequestMethod.POST })
	public ReturnBaseDto<ReturnDemandDto> getDemandItem(@PathVariable Integer item_id) throws IOException {
		log.info("参数" + item_id);
		ReturnBaseDto<ReturnDemandDto> result = new ReturnBaseDto<ReturnDemandDto>();
		ParamsListDto params = new ParamsListDto();

		// 初始化分页页
		User user = new User();
		user.setUserId("ni_menhao");
		user.setSchoolId(1);
		params.setItem_id(item_id);
		params.setLimit(10);
		params.setOffset(0);
		params.setUser(user);

		result.setCode(Code.Success.getCode());
		result.setStatus(Code.Success.getStatus());
		ReturnDemandDto data = demandService.selectOneById(params);
		result.setData(data == null ? new ReturnDemandDto() : data);
		return result;
	}

}