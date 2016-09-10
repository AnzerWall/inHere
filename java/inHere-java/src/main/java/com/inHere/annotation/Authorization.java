package com.inHere.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * token校验注解
 * 
 * @Documented —— 指明拥有这个注解的元素可以被javadoc此类的工具文档化。
 * @Retention——指明了该Annotation被保留的时间长短，RetentionPolicy取值为SOURCE,CLASS,RUNTIME
 * @Inherited——指明该注解类型被自动继承
 * @Target——指明该类型的注解可以注解的程序元素的范围，该元注解的取值可以为TYPE,METHOD,CONSTRUCTOR,FIELD
 * @author lwh
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = { ElementType.METHOD, ElementType.TYPE })
public @interface Authorization {

}
