package com.inHere.web;

import com.inHere.constant.Code;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <h1>Servlet全局异常处理</h1>
 *
 * @author lwh
 */
@RestController
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
     * 找不到资源，404错误
     *
     * @return
     */
    @RequestMapping("/error/404")
    public Map<String, Object> notFound() {
        log.error(Code.NotFound.getStatus());
        Map<String, Object> result = new HashMap<>();
        result.put("code", Code.NotFound.getCode());
        result.put("status", Code.NotFound.getStatus());
        return result;
    }

    /**
     * Servlet捕获异常错误，【转发】到这里处理
     *
     * @param request
     * @return
     */
    @RequestMapping("/error/500")
    public Map<String, Object> serverError(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();

        // 该属性给出异常产生的信息，信息可被存储，并在存储为 java.lang.Throwable 数据类型后可被分析。
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        // 该属性给出状态码，状态码可被存储，并在存储为 java.lang.Integer 数据类型后可被分析。
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        // 该属性给出确切错误消息的信息，信息可被存储，并在存储为 java.lang.String 数据类型后可被分析。
        String message = (String) request.getAttribute("javax.servlet.error.message");
        // 该属性给出异常类型的信息，异常类型可被存储，并在存储为 java.lang.Class 数据类型后可被分析。
        Class<?> type = (Class<?>) request.getAttribute("javax.servlet.error.exception_type");
        // 该属性给出 Servlet 的名称，名称可被存储，并在存储为 java.lang.String 数据类型后可被分析。
        String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
        // 该属性给出有关 URL 调用 Servlet 的信息，信息可被存储，并在存储为 java.lang.String 数据类型后可被分析。
        String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");

        log.error(message, throwable);

        result.put("code", Code.Error.getCode());
        result.put("status", Code.Error.getStatus());
        return result;
    }

}
