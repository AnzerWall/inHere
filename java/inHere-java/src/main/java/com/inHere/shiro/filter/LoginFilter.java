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
        //log.info("--->"+mappedValue);
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
            /**
             * 设置response：
             * 1、采用PrintWriter方式（response.getWriter()），需要在调用getPrintWriter()之前调用设置，如 setContentType、setCharacterEncoding等
             * 2、采用ServletOutputStream方式（response.getOutputStream()），不受此限，（有问题，待解决）
             */
            response.setContentType("application/json; charset=utf-8");

            out = response.getWriter();
            JSONObject result = new JSONObject();
            result.put("code", Code.NoLogin.getCode());
            result.put("status", Code.NoLogin.getStatus());
            log.info("--->" + result.toJSONString());
            out.print(result.toJSONString());
        } catch (IOException e) {
            throw new SystemException(Code.Error.getCode(), Code.Error.getStatus());
        } finally {
            if (out != null) out.close();
        }
        return Boolean.FALSE;
    }
}
