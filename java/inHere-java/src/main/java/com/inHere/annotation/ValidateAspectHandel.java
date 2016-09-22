package com.inHere.annotation;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.inHere.constant.Code;
import com.inHere.exception.SystemException;

/**
 * 使用AOP进行验证拦截，@Params校验注解处理类
 * 
 * @see com.inHere.annotation.Authorization
 * @author lwh
 */
@Component
@Aspect
public class ValidateAspectHandel implements ApplicationContextAware {

	Logger log = Logger.getLogger(getClass());

	private ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context = applicationContext;
	}

	@Pointcut("@annotation(com.inHere.annotation.Params)")
	public void pointCut() {
	}

	@Around("pointCut()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		log.info("进入ValidateAspectHandel进行参数校验");
		// 获取方法签名
		MethodSignature msig = (MethodSignature) joinPoint.getSignature();
		// 截取目标所在的类字节对象
		// Object target = joinPoint.getTarget();
		// 截取目标所在的方法对象
		Method targetMethod = msig.getMethod();
		// Method targetMethod = this.getMethodByClassAndName(target.getClass(),
		// methodName);
		// 获取拦截的方法名
		String methodName = targetMethod.getName();
		// 获取拦截的注解
		Params paramsAnn = targetMethod.getAnnotation(Params.class);
		// 获取方法的参数集合
		Object[] args = joinPoint.getArgs();
		// 获取方法的参数类型集合
		Class<?>[] types = targetMethod.getParameterTypes();
		// 获取要被调用的校验类的字节对象
		Class<?> validatorClass = paramsAnn.value();
		// 从spring容器获取对象
		Object obj = context.getBean(validatorClass);

		if (args != null && args.length > 0) {
			// 获取要被调用的校验方法对象，要求校验方法名与@Params拦截的方法的名字要相同,
			// 校验方法的参数有且只能有一个，类型和返回类型都要和拦截方法的一样
			Method validatorMethod = validatorClass.getDeclaredMethod(methodName, types[0]);
			Object view = validatorMethod.invoke(obj, args[0]);
			if (view == null) {
				return joinPoint.proceed();
			} else {
				return view;
			}
		}
		throw new SystemException(Code.Error.getCode(), Code.Error.getStatus(), "使用@Params参数校验时，方法参数不能为空");
	}


}
