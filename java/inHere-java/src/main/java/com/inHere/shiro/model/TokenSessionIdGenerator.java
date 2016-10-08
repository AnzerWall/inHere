package com.inHere.shiro.model;

import com.inHere.service.SecurityService;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * sessionId生成工具类
 */
public class TokenSessionIdGenerator implements SessionIdGenerator {

    @Autowired
    private SecurityService securityService;

    @Override
    public Serializable generateId(Session session) {
        return securityService.createToken();
    }
}
