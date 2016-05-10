package com.example.controller.screen.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.exception.AuthorizationException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HelloController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(@RequestParam("name") String name, Model model) {
        if (name.equals("yoshida")) {
            throw new AuthorizationException("AuthorizationException error.");
        }

        model.addAttribute("name", name);

        return "screen/admin/hello";
    }
}
