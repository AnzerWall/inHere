package com.inHere.exception;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.inHere.constant.Code;
import org.apache.log4j.Logger;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.HashMap;
import java.util.Map;

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

    @ExceptionHandler(ShiroException.class)
    public ModelAndView handlerShiroEx(Exception ex) {
        log.error("Shiro Exception --> " + ex.getMessage(), ex);

        // 创建返回视图
        ModelAndView mv = new ModelAndView();
        FastJsonJsonView view = new FastJsonJsonView();
        Map<String, Object> result = new HashMap<>();
        result.put("code", Code.InputErr.getCode());
        result.put("status", Code.InputErr.getStatus());

        // 帐号异常
        if (ex instanceof UnknownAccountException) {
            result.put("message", "用户帐号不存在");
        } else if (ex instanceof AuthenticationException) {
            result.put("message", "用户帐号不存在");
        } else if (ex instanceof CredentialsException) {
            result.put("message", "用户帐号或密码错误");
        } else {
            result.put("message", "登录失败");
        }

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
        Map<String, Object> result = new HashMap<>();

        // Required request body is missing
        if (ex instanceof HttpMessageNotReadableException) {
            result.put("code", Code.InputErr.getCode());
            result.put("status", Code.InputErr.getStatus());
            result.put("message", ex.getMessage());
            // 填充返回数据
            view.setAttributesMap(result);

            mv.setView(view);
            return mv;
        }

        // Request method 'POST' not supported
        if (ex instanceof HttpRequestMethodNotSupportedException) {
            result.put("code", Code.NotFound.getCode());
            result.put("status", Code.NotFound.getStatus());
            result.put("message", ex.getMessage());
            // 填充返回数据
            view.setAttributesMap(result);
            mv.setView(view);
            return mv;
        }

        /**
         * JDK的java doc是这么解释UndeclaredThrowableException的：<br>
         * 如果代理实例的调用处理程序的 invoke 方法抛出一个经过检查的异常（不可分配给 RuntimeException 或 Error 的 Throwable），
         * 且该异常不可分配给该方法的throws子局声明的任何异常类，则由代理实例上的方法调用抛出此异常
         */
        if (ex instanceof UndeclaredThrowableException) {
            ex.printStackTrace();
            Throwable InvoEx = ((UndeclaredThrowableException) ex).getUndeclaredThrowable();
            // 反射调用异常
            if (InvoEx instanceof InvocationTargetException) {
                Throwable SysEx = ((InvocationTargetException) InvoEx).getTargetException();
                // 用户自定义异常
                if (SysEx instanceof SystemException) {
                    result.put("code", Code.InputErr.getCode());
                    result.put("status", Code.InputErr.getStatus());
                    result.put("message", SysEx.getMessage());
                    // 填充返回数据
                    view.setAttributesMap(result);
                    mv.setView(view);
                    return mv;
                }
            }
        }

        // 未知异常信息返回
        result.put("code", Code.Error.getCode());
        result.put("status", Code.Error.getStatus());
        // 填充返回数据
        view.setAttributesMap(result);
        mv.setView(view);
        return mv;
    }

}
