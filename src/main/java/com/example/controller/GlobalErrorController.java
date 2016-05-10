package com.example.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.WebRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class GlobalErrorController implements ErrorController {

    public static final String ERROR_EXCEPTION = "com.example.demo.error.exception";

    @Value("${server.error.path:${error.path:/error}}")
    private String errorPath;

    @Autowired
    private ErrorAttributes errorAttributes;

    @Override
    public String getErrorPath() {
        return errorPath;
    }

    @RequestMapping(value = "${server.error.path:${error.path:/error}}")
    public String error(HttpServletRequest req, HttpServletResponse res, WebRequest webRequest) {
        Throwable error = errorAttributes.getError(new ServletRequestAttributes(req));
        String requestURI = (String) req.getAttribute("javax.servlet.forward.request_uri");
        Site site = Site.of(requestURI);

        req.setAttribute(ERROR_EXCEPTION, error);

        log.warn("Caught an error. site = {}, requestURI = {}", site, requestURI);

        return "forward:" + site.getBaseUrl() + "/error";
    }
}