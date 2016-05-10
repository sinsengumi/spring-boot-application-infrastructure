package com.example.controller.screen.admin.system;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.NameValueExpression;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.example.controller.Site;

import lombok.Data;

@Controller
public class RouterController {

    @Autowired
    private RequestMappingHandlerMapping handlerMapping;

    @RequestMapping(value = "/admin/system/router", method = RequestMethod.GET)
    public String routers(Model model) {
        List<OriginalRequestMappingInfo> result = OriginalRequestMappingInfo.of(handlerMapping);
        model.addAttribute("requestMappingInfos", result.stream().sorted().collect(Collectors.toList()));
        return "screen/admin/system/router";
    }

    @Data
    public static class OriginalRequestMappingInfo implements Serializable, Comparable<OriginalRequestMappingInfo> {
        private static final long serialVersionUID = -1L;
        private Site site;
        private Set<RequestMethod> methods;
        private Set<String> patterns;
        private Set<String> authorizedRoles;
        private Set<String> params;

        @Override
        public int compareTo(OriginalRequestMappingInfo o) {
            int result = this.site.toString().compareTo(o.getSite().toString());
            if (result == 0) {
                String pattern1 = this.getPatterns().iterator().next();
                String pattern2 = o.getPatterns().iterator().next();

                result = pattern1.compareTo(pattern2);
                if (result == 0) {
                    RequestMethod method1 = this.getMethods().iterator().next();
                    RequestMethod method2 = o.getMethods().iterator().next();

                    result = method1.compareTo(method2);
                }
            }

            return result;
        }

        public static List<OriginalRequestMappingInfo> of(RequestMappingHandlerMapping handlerMapping) {
            List<OriginalRequestMappingInfo> result = new ArrayList<OriginalRequestMappingInfo>();

            for (Entry<RequestMappingInfo, HandlerMethod> entry : handlerMapping.getHandlerMethods().entrySet()) {
                RequestMappingInfo requestMappingInfo = entry.getKey();

                OriginalRequestMappingInfo o = new OriginalRequestMappingInfo();
                o.setSite(Site.of(requestMappingInfo.getPatternsCondition().getPatterns().iterator().next()));
                o.setMethods(requestMappingInfo.getMethodsCondition().getMethods());
                o.setPatterns(requestMappingInfo.getPatternsCondition().getPatterns());

                Set<String> params = new HashSet<>();
                for (NameValueExpression<String> nameValueExpression : requestMappingInfo.getParamsCondition().getExpressions()) {
                    params.add(nameValueExpression.toString());
                }
                o.setParams(params);

                result.add(o);
            }

            return result.stream().sorted().collect(Collectors.toList());
        }
    }
}
