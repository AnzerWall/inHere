package com.inHere.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inHere.dto.BaseResultDto;

@RestController
@RequestMapping("/user")
public class LoginController {
	
	@RequestMapping("/log_in")
	public BaseResultDto login(){
		BaseResultDto result = new BaseResultDto();
		
		return result;
	}

}
