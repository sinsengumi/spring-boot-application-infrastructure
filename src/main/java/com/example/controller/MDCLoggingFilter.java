package com.example.controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.MDC;

public class MDCLoggingFilter implements Filter {

    private static final String KEY_REQUEST_ID = "REQUEST_ID";
    private static final String KEY_LOGIN_USER_ID = "LOGIN_USER_ID";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        putMDC(request);
        try {
            chain.doFilter(request, response);
        } finally {
            clearMDC();
        }
    }

    private void putMDC(ServletRequest request) {
       if (request instanceof HttpServletRequest) {
           //HttpServletRequest httpRequest = (HttpServletRequest) request;
           //String requestId = httpRequest.getHeader("X-Request-Id");
           String requestId = UUID.randomUUID().toString();
           if (requestId != null) {
               MDC.put(KEY_REQUEST_ID, requestId);
           }

           String loginUserId = "t-yoshida"; // session 等からユーザ情報を取得
           if (loginUserId != null) {
               MDC.put(KEY_LOGIN_USER_ID, loginUserId);
           }
       }

    }

    private void clearMDC() {
        MDC.remove(KEY_REQUEST_ID);
        MDC.remove(KEY_LOGIN_USER_ID);
    }

    @Override
    public void destroy() {
    }
}
