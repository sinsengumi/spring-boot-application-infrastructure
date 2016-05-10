package com.example.controller.screen.admin.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.infrastructure.GitRepositoryState;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BuildController {

    @Value("${info.version}")
    private String version;

    @Autowired
    private GitRepositoryState gitRepositoryState;

    @RequestMapping(value = "/admin/system/build", method = RequestMethod.GET)
    public String git(Model model) {

        model.addAttribute("version", version);
        model.addAttribute("gitRepositoryState", gitRepositoryState);

        return "screen/admin/system/build";
    }
}
