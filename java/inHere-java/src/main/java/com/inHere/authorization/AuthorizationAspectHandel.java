package com.inHere.authorization;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.inHere.constant.Code;
import com.inHere.exception.SystemException;

/**
 * 使用AOP进行Token验证拦截，@Authorization校验注解处理类
 * 
 * @see com.inHere.authorization.Authorization
 * @author lwh
 */
@Component
@Aspect
@Order(1)
public class AuthorizationAspectHandel {

	Logger log = Logger.getLogger(getClass());

	@Pointcut("execution(* com.inHere.web..*.*(..))")
	public void pointCut() {
	}

	@Around("pointCut()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		log.info("进入Token权限校验");
		Object target = joinPoint.getTarget();
		Authorization auth = target.getClass().getDeclaredAnnotation(Authorization.class);
		if (auth == null) {
			// 获取方法签名
			MethodSignature ms = (MethodSignature) joinPoint.getSignature();
			Method targetMethod = ms.getMethod();
			auth = targetMethod.getDeclaredAnnotation(Authorization.class);
			if (auth == null) {
				// 无需Token检验，直接通过
				return joinPoint.proceed();
			}
		}
		// do something token check , true proceed, false throw Exception
		throw new SystemException(Code.NoLogin.getCode(), Code.NoLogin.getStatus(), "请登录");
	}

}
