package com.example.controller.screen.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LogController {

    @RequestMapping(value = "/admin/log", method = RequestMethod.GET)
    public String log() {
        log.info("Log1");
        log.info("Log2");
        return "screen/admin/log";
    }
}
