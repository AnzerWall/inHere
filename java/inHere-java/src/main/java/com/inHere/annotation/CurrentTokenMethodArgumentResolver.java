package com.inHere.annotation;

import org.apache.log4j.Logger;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.inHere.constant.Code;
import com.inHere.entity.Token;
import com.inHere.exception.SystemException;

/**
 * 自定义参数Token处理器
 * 
 * @author lwh
 *
 */
public class CurrentTokenMethodArgumentResolver implements HandlerMethodArgumentResolver {

	Logger log = Logger.getLogger(getClass());

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		if (parameter.getParameterType().isAssignableFrom(Token.class)
				&& parameter.hasParameterAnnotation(CurrentToken.class)) {
			return true;
		}
		return false;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		log.info("进入自定义参数的@CurrentToken处理器中...");
		// 从request领域取值
		Object token = webRequest.getAttribute("token", RequestAttributes.SCOPE_REQUEST);
		if (token != null && token instanceof Token) {
			return token;
		}
		throw new SystemException(Code.Error.getCode(), Code.Error.getStatus(), "使用@CurrentToken自定义参数，方法头部没有添加@Authorization权限注解控制");
	}

}
