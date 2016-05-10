package com.example.controller.screen.user;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.controller.GlobalErrorController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ErrorController {

    @RequestMapping("/error")
    public String error(HttpServletRequest request, Model model) {
        Throwable e = (Throwable) request.getAttribute(GlobalErrorController.ERROR_EXCEPTION);
        String errorCode = RandomStringUtils.randomAlphabetic(15);

        log.error(e.getMessage(), e);

        model.addAttribute("errorCode", errorCode);
        model.addAttribute("stackTrace", ExceptionUtils.getStackTrace(e));
        return "screen/admin/error";
    }
}
