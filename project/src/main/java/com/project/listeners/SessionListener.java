package com.project.listeners;


import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

import java.util.HashMap;
import java.util.Map;

@WebListener
public class SessionListener implements HttpSessionListener {
    private static final Map<String, HttpSession> opens = new HashMap<>();
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        String sID = se.getSession().getId();
        opens.put(sID, se.getSession());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        String sID = se.getSession().getId();
        opens.remove(sID);
    }

    public static HttpSession getOpenSession(String sID) {
        return opens.get(sID);
    }
}
