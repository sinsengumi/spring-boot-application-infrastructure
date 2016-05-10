package com.example.controller.screen.admin.system;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.collect.ImmutableMap;

@Controller
public class BatchController {

    @RequestMapping(value = "/admin/system/batch", method = RequestMethod.GET)
    public String batch(Model model) {
        model.addAttribute("batches", getBatches());
        return "screen/admin/system/batch";
    }

    private List<Map<String, Object>> getBatches() {
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(true);

        return provider.findCandidateComponents("com.example.task").stream()
                .map(bean -> classForName(bean.getBeanClassName()))
                .flatMap(clazz -> Stream.of(clazz.getDeclaredMethods()))
                .filter(method -> method.getAnnotation(Scheduled.class) != null)
                .map(method -> {
                    Scheduled scheduled = method.getAnnotation(Scheduled.class);
                    return ImmutableMap.of("method", method, "cron", scheduled.cron());
                })
                .collect(Collectors.toList());
    }

    private static Class<?> classForName(String name) {
        try {
            return Class.forName(name);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
