package com.inHere.web;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.inHere.constant.Code;
import com.inHere.dto.BaseResultDto;
import com.inHere.dto.ListDto;
import com.inHere.dto.TasksDto;
import com.inHere.exception.SystemException;
import com.inHere.service.DemandService;

/**
 * 有求必应Controller
 * 
 * @author lwh
 *
 */
@RestController
@RequestMapping("/demand")
public class DemandController {

	@Autowired
	private DemandService demandService;

	@RequestMapping(path = "/tasks", method = RequestMethod.POST)
	public BaseResultDto getDemandTaskList(@RequestBody Map<String, Object> params, String token) throws IOException {
		BaseResultDto result = new BaseResultDto();
		String message = null;
		if (token != null) {
			try {
				Integer limit = Integer.parseInt(params.get("limit").toString());
				Integer offset = Integer.parseInt(params.get("offset").toString());
				limit = limit < 1 ? 10 : limit;
				offset = offset < 0 ? 0 : offset;

				ListDto<TasksDto> list = demandService.getTasksToDto(offset, limit, null, null, null);

				result.setCode(Code.Success.getCode());
				result.setStatus(Code.Success.getStatus());
				result.setData(list);
				
				return result;
			} catch (ClassCastException ex) {
				throw new SystemException(Code.InputErr.getCode(), Code.InputErr.getStatus(), "输入内容格式有错。");
			}
		} else {
			message = "缺少用户凭证(Token)";
		}
		result.setCode(Code.InputErr.getCode());
		result.setStatus(Code.InputErr.getStatus());
		result.setMessage(message);
		return result;
	}

}