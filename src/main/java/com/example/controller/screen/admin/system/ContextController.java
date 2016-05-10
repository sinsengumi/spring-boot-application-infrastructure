package com.example.controller.screen.admin.system;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ContextController {

    @Autowired
    private ApplicationContext applicationContext;

    @RequestMapping(value = "/admin/system/context", method = RequestMethod.GET)
    public String context(Model model) {
        BeanDefinitionRegistry registry = (BeanDefinitionRegistry) applicationContext.getAutowireCapableBeanFactory();

        Map<String, BeanDefinition> beanDefinitions = new LinkedHashMap<>();
        for (String beanDefinitionName : registry.getBeanDefinitionNames()) {
            beanDefinitions.put(beanDefinitionName, registry.getBeanDefinition(beanDefinitionName));
        }

        model.addAttribute("context", applicationContext);
        model.addAttribute("beanDefinitions", beanDefinitions);

        return "screen/admin/system/context";
    }
}
