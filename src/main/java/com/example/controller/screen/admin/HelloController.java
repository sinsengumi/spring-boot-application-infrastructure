package com.example.controller.screen.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.exception.AuthorizationException;

@Controller
public class HelloController {

    @RequestMapping(value = "/admin/hello", method = RequestMethod.GET)
    public String hello(@RequestParam("name") String name, Model model) {
        if (name.equals("yoshida")) {
            throw new AuthorizationException("AuthorizationException error.");
        }

        model.addAttribute("name", name);

        return "screen/admin/hello";
    }
}
