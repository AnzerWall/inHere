package com.inHere.shiro.model;

import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * 集成websession管理器，重写getSessionId实现自己的需求(web项目不使用HttpSession)
 */
public class TokenSessionManager extends DefaultWebSessionManager {

    /**
     * 自定义获取SessionID方式，用户每次访问都会调用
     * @param request
     * @param response
     */
    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        HttpServletRequest req= (HttpServletRequest) request;
        String token = req.getParameter("token");
        return token;
    }

}
