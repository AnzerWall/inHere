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
	 * (坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑)
	 * (注意，使用通配符时"*"代表一个占位如：/404，使用"**"时，代表多个占位如：/error/404/page.... )
	 * (注意：除非做全服务(没有图片等等文件)，否者不要用/**把所有请求给拦截，会报如下异常 )
	 * (坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑坑)
	 * 
	 * @see org.springframework.web.HttpMediaTypeNotAcceptableException
	 * @return
	 */
	// @RequestMapping("**")
	// public Map<String, Object> noHandlerFound() {
	// log.error(Code.NotFound.getStatus());
	// Map<String, Object> result = new HashMap<String, Object>();
	// result.put("code", Code.NotFound.getCode());
	// result.put("status", Code.NotFound.getStatus());
	// return result;
	// }

	/**
	 * 用户未授权，不予操作
	 * 
	 * @return
	 */
	@RequestMapping("error/401")
	public Map<String, Object> unauthorized() {
		log.error(Code.NotFound.getStatus());
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", Code.NoLogin.getCode());
		result.put("status", Code.NoLogin.getStatus());
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
		log.error(Code.Error.getStatus());
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", Code.Error.getCode());
		result.put("status", Code.Error.getStatus());
		return result;
	}

}
