package com.example;

import java.io.IOException;
import java.util.stream.Stream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.example.controller.Site;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // CSRF 保護を無効化する URL
        String[] ignoreCsrfPatterns = Stream.of(Site.values())
                .filter(l -> !l.isProtectCsrf()).map(l -> l.getBaseUrl() + "/**")
                .toArray(String[]::new);

        http.csrf()
            .ignoringAntMatchers(ignoreCsrfPatterns).and() // CSRF 保護を無効化する URL を指定する
            .exceptionHandling().accessDeniedHandler(accessDeniedHandler); // CSRF に引っかっかった場合の例外ハンドリングを設定する
    }

    @Value("${server.error.path:${error.path:/error}}")
    private String errorPath;

    private AccessDeniedHandler accessDeniedHandler = new AccessDeniedHandler() {
        @Override
        public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
                throws IOException, ServletException {
            if (!response.isCommitted()) {
                request.setAttribute(RequestDispatcher.ERROR_EXCEPTION, accessDeniedException);
                response.setStatus(HttpStatus.FORBIDDEN.value());
                request.getRequestDispatcher(errorPath).forward(request, response);
            }
        }
    };
}
