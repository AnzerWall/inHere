package com.inHere.annotation;

import com.alibaba.fastjson.JSON;
import com.inHere.constant.Code;
import com.inHere.entity.Token;
import com.inHere.redis.TokenManage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * token较验拦截器
 * 
 * @see com.inHere.annotation.Authorization
 * @author lwh
 *
 */
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private TokenManage tokenManage;

	Logger log = Logger.getLogger(getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("进入token拦截器");
		// 开启跨域允许, 用过滤器统一处理
		// CORS "pre-flight" request
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		response.setHeader("Access-Control-Allow-Headers", "X-Requested-With, Origin, Content-Type, Accept");
		response.addHeader("Access-Control-Max-Age", "1296000");//15 days

		// 如果不是映射到方法直接通过
		if (!(handler instanceof HandlerMethod)) {
			return true;
		}
		log.info("进入token拦截器,拦截HandlerMethod");
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		Authorization authorization = method.getAnnotation(Authorization.class);

		// 检验方法是否需要token检验
		if (authorization != null) {
			String tokenStr = request.getParameter("token");
			Token token = tokenManage.getToken(tokenStr);
			// token存在，允许操作
			if (token != null) {
				request.setAttribute("token", token);
				return true;
			}
			/* 使用response返回 */
			response.setStatus(HttpStatus.OK.value()); // 设置状态码
			response.setContentType(MediaType.APPLICATION_JSON_VALUE); // 设置ContentType
			response.setCharacterEncoding("UTF-8"); // 避免乱码
			response.setHeader("Cache-Control", "no-cache, must-revalidate");
			try {
				Map<String, Object> map = new HashMap<>();
				map.put("code", Code.NoLogin.getCode());
				map.put("status", Code.NoLogin.getStatus());
				map.put("message", "用户未登录");
				response.getWriter().write(JSON.toJSONString(map));
				return false;
			} catch (IOException e) {
				log.error("response.getWriter().write()出错");
				return false;
			} finally {
				response.flushBuffer();
			}
		}
		// 无需token校验，直接通过
		return true;
	}

}
