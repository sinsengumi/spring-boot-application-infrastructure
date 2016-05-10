package com.example.controller.api.external;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.exception.AuthorizationException;

@RestController
public class HelloController {

    @RequestMapping(value = "/api/hello", method = RequestMethod.GET)
    public String hello(@RequestParam("name") String name) {
        if (name.equals("yoshida")) {
            throw new AuthorizationException("AuthorizationException error.");
        }

        return "Hello. " + name + "さん";
    }
}
