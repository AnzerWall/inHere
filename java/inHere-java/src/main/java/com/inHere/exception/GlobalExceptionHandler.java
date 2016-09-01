package com.inHere.exception;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.inHere.constant.Code;

/**
 * <h1>系统全局异常统一处理</h1>
 * <p>
 * 捕获异常，使用FastJson提供的FastJsonJsonView视图返回错误信息
 * </p>
 * 
 * @author lwh
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	Logger log = Logger.getLogger(getClass());

	/**
	 * 系统异常处理
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(SystemException.class)
	public ModelAndView handlerSystemEx(Exception ex) {
		log.error("System Exception --> " + ex.getMessage(), ex);
		SystemException sysException = (SystemException) ex;
		ModelAndView mv = new ModelAndView();
		// 创建返回视图
		FastJsonJsonView view = new FastJsonJsonView();
		// 返回信息实体
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", sysException.getCode());
		result.put("status", sysException.getStatus());
		result.put("message", sysException.getMessage());
		// 填充返回数据
		view.setAttributesMap(result);
		mv.setView(view);
		return mv;
	}

	/**
	 * 未知异常处理
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public ModelAndView handlerOtherEx(Exception ex) {
		log.error("Unkown Exception --> " + ex.getMessage(), ex);
		ModelAndView mv = new ModelAndView();
		// 创建返回视图
		FastJsonJsonView view = new FastJsonJsonView();
		// 返回信息实体
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", Code.Error.getCode());
		result.put("status", Code.Error.getStatus());
		// 填充返回数据
		view.setAttributesMap(result);
		mv.setView(view);
		return mv;
	}

}