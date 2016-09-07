package com.inHere.web;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.inHere.constant.Code;
import com.inHere.dto.ParamsListDto;
import com.inHere.dto.ReturnBaseDto;
import com.inHere.dto.ReturnListDto;
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

	@Autowired
	private DemandService demandService;

	@Params(DemandValidator.class)
	@RequestMapping(path = "/demand", method = { RequestMethod.GET, RequestMethod.POST })
	public ReturnBaseDto<ReturnListDto> getDemandList(@RequestBody ParamsListDto params) throws IOException {
		ReturnBaseDto<ReturnListDto> result = new ReturnBaseDto<ReturnListDto>();
		result.setCode(Code.Success.getCode());
		result.setStatus(Code.Success.getStatus());
		result.setData(demandService.getList(params));
		return result;
	}

}