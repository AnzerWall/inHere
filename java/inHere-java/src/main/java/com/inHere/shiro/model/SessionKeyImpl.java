package com.inHere.shiro.model;

import org.apache.shiro.session.mgt.SessionKey;

import java.io.Serializable;

/**
 * SessionKey实现类
 */
public class SessionKeyImpl implements SessionKey {

    private Serializable sessionId;

    public SessionKey setSessionId(Serializable sessionId){
        this.sessionId = sessionId;
        return this;
    }

    @Override
    public Serializable getSessionId() {
        return sessionId;
    }
}
