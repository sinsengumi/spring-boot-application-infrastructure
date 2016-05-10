package com.example.controller.screen.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin/sqlInjection")
public class SQLInjectionController {

    private static final String VIEW_BASE = "screen/admin/sqlInjection/";

    @Autowired
    private UserService service;

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public String search(@RequestParam(value = "condition", defaultValue = "1") String condition, Model model) {
        model.addAttribute("users", service.search(condition));
        return VIEW_BASE + "search";
    }
}
