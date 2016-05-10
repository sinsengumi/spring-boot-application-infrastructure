package com.example.controller.screen.admin;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.model.User;
import com.example.service.UserService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin/user")
public class UserController {

    private static final String VIEW_BASE = "screen/admin/user/";

    @Autowired
    private UserService service;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("users", service.findAll());
        return VIEW_BASE + "index";
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create() {
        return VIEW_BASE + "create";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create(@Validated UserForm form, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return create();
        }

        User user = new User();
        BeanUtils.copyProperties(form, user);
        service.create(user);

        redirectAttributes.addFlashAttribute("completeMessage", "登録しました。");
        return "redirect:/admin/user";
    }

    @ModelAttribute
    public UserForm setUpForm() {
        return new UserForm();
    }

    @Data
    public static class UserForm implements Serializable {

        private static final long serialVersionUID = 1L;

        @NotNull
        private Integer id;
        @NotEmpty
        private String name;
        @NotNull
        private Integer age;
    }
}
