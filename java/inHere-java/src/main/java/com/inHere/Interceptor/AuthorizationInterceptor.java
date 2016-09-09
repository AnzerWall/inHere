package com.inHere.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * token较验拦截器
 * 
 * @author lwh
 *
 */
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

	Logger log = Logger.getLogger(getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("进入拦截器");
		// 开启跨域允许
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		return super.preHandle(request, response, handler);
	}

}
