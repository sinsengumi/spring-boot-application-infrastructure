package com.example.controller.screen.admin;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CsrfController {

    @RequestMapping(value = "/admin/csrf", method = RequestMethod.GET)
    public String show() {
        return "screen/admin/csrf";
    }

    @RequestMapping(value = "/admin/csrf", method = RequestMethod.POST)
    public String post(@Validated SampleForm form, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return show();
        }

        redirectAttributes.addFlashAttribute("completeMessage", "登録しました。");
        return "redirect:/admin/csrf";
    }

    @ModelAttribute
    public SampleForm setUpForm() {
        return new SampleForm();
    }

    @Data
    public static class SampleForm implements Serializable {

        private static final long serialVersionUID = 1L;

        @NotEmpty
        private String name;

        @NotNull
        private Integer age;
    }
}
