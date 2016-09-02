package com.inHere.web;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inHere.constant.Code;

/**
 * <h1>请求异常处理</h1>
 * 
 * @author lwh
 *
 */
@RestController
@RequestMapping("/")
public class NotFoundController {

	Logger log = Logger.getLogger(getClass());

	/**
	 * 注意，使用通配符时"*"代表一个占位如：/404，使用"**"时，代表多个占位如：/error/404/page....
	 * 
	 * @return
	 */
	@RequestMapping("**")
	public Map<String, Object> noHandlerFound() {
		log.error(Code.NoHandlerFound.getStatus());
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", Code.NoHandlerFound.getCode());
		result.put("status", Code.NoHandlerFound.getStatus());
		return result;
	}

	/**
	 * 配了上面的noHandlerFound，当前这个404方法可以去掉
	 * 
	 * @return
	 */
	@RequestMapping("error/404")
	public Map<String, Object> notFound() {
		log.error(Code.NotFound.getStatus());
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", Code.NotFound.getCode());
		result.put("status", Code.NotFound.getStatus());
		return result;
	}

	@RequestMapping("error/500")
	public Map<String, Object> serverError() {
		log.error(Code.ServerError.getStatus());
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", Code.ServerError.getCode());
		result.put("status", Code.ServerError.getStatus());
		return result;
	}

}
