package com.xmhx.cnlife.buzz.common;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

/**
 * SessionContext.java类文件描述说明.
 * 
 * @author lhq
 * @since JDK 1.6
 * @see
 */
public class SessionContext {

    private static SessionContext sessionContext;
    private HashMap<String, HttpSession> sessionMap;

    private SessionContext() {
        sessionMap = new HashMap<String, HttpSession>();
    }

    public static SessionContext getInstance() {
        if (sessionContext == null) {
            sessionContext = new SessionContext();
        }
        return sessionContext;
    }

    public synchronized void AddSession(HttpSession session) {
        if (session != null) {
            sessionMap.put(session.getId(), session);
        }
    }

    public synchronized void DelSession(HttpSession session) {
        if (session != null) {
            sessionMap.remove(session.getId());
        }
    }

    public synchronized HttpSession getSession(String sessionId) {
        if (sessionId == null) {
            return null;
        }
        return (HttpSession) sessionMap.get(sessionId);
    }

}
