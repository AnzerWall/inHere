package com.inHere.shiro.filter;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inHere.constant.Code;
import com.inHere.exception.SystemException;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 后台登录判断
 */
public class LoginFilter extends AccessControlFilter {

    Logger log = Logger.getLogger(getClass());

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        HttpServletRequest req = (HttpServletRequest) request;
        log.info("--->"+mappedValue);

        if (SecurityUtils.getSubject().isAuthenticated()) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) {
        log.info("登录拦截, 用户未登录");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            JSONObject result = new JSONObject();
            result.put("code", Code.NotFound.getCode());
            result.put("status", Code.NotFound.getStatus());
            out.write(objectMapper.writeValueAsString(result));
        } catch (IOException e) {
            throw new SystemException(Code.Error.getCode(), Code.Error.getStatus());
        }finally {
            out.close();
        }
        return Boolean.FALSE;
    }
}
