package com.demo.config;

import com.mysql.cj.Session;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MyFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest rq = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String path = rq.getServletPath();
        if (!path.equals("/login") && !path.equals("/register") && !path.equals("/sign-in")) {
            HttpSession session = rq.getSession();
            String username = (String) session.getAttribute("username-ss");
            if (username == null || username.equals("")) {
                request = new HttpServletRequestWrapper(rq) {
                    @Override
                    public String getRequestURI() {
                        return "/login";
                    }
                };
            }
        }
        chain.doFilter(request, response);
    }
}
