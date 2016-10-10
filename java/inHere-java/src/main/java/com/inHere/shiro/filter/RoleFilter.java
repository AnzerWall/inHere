package com.inHere.shiro.filter;

import org.apache.log4j.Logger;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 角色访问过滤器
 * @author lwh
 */
public class RoleFilter extends AccessControlFilter {

    Logger log = Logger.getLogger(getClass());

    @Override
    protected boolean isAccessAllowed(ServletRequest req, ServletResponse resp, Object value) throws Exception {
        HttpServletRequest request = (HttpServletRequest) req;
        log.info("--->" + request.getMethod() + "--->" + value);
        //if(){

        //}
        return Boolean.TRUE;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest req, ServletResponse resp) throws Exception {
        return Boolean.FALSE;
    }
}
