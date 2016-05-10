package com.example.controller.api.internal;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.controller.GlobalErrorController;
import com.example.model.ErrorResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ErrorController {

    @RequestMapping(value = "/api/internal/error")
    public ErrorResponse error(HttpServletRequest request) {
        Throwable e = (Throwable) request.getAttribute(GlobalErrorController.ERROR_EXCEPTION);
        String errorCode = RandomStringUtils.randomAlphabetic(15);

        log.error(e.getMessage(), e);

        return ErrorResponse.build(errorCode, e);
    }
}
