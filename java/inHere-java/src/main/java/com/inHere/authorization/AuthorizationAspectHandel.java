package com.inHere.authorization;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import com.inHere.constant.Code;
import com.inHere.exception.SystemException;

/**
 * 使用AOP进行Token验证拦截，@Authorization校验注解处理类 <br>
 * 弃用，侵入太强
 * 
 * @see com.inHere.authorization.Authorization
 * @author lwh
 */
@Deprecated
//@Component
//@Aspect
//@Order(1)
public class AuthorizationAspectHandel {

	Logger log = Logger.getLogger(getClass());

//	@Autowired
//	private TokenManage tokenManage;

	// @Pointcut("execution(* com.inHere.web..*.*(..))")
	public void pointCut() {
	}

	// @Around("pointCut()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		log.info("进入Token权限校验");
		Object target = joinPoint.getTarget();
		Authorization auth = target.getClass().getDeclaredAnnotation(Authorization.class);
		// 获取方法签名
		MethodSignature ms = (MethodSignature) joinPoint.getSignature();
		Method targetMethod = ms.getMethod();
		if (auth == null) {
			auth = targetMethod.getDeclaredAnnotation(Authorization.class);
			if (auth == null) {
				// 无需Token检验，直接通过
				return joinPoint.proceed();
			}
		}
		// do something token check , true proceed, false throw Exception
		Object[] args = joinPoint.getArgs();
		for (Object arg : args) {
			if (arg instanceof HttpServletRequest) {
//				HttpServletRequest req = (HttpServletRequest) arg;
//				String token = req.getParameter("token");
				// tokenManage.get
			}
		}
		throw new SystemException(Code.NoLogin.getCode(), Code.NoLogin.getStatus(), "请登录");
	}

}
