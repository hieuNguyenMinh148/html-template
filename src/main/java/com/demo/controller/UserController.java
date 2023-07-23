package com.demo.controller;

import com.demo.model.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login/login";
    }

    @PostMapping("/sign-in")
    public String signIn(@ModelAttribute User user, HttpServletResponse response, HttpServletRequest request) {
        Cookie cookie = new Cookie("username", user.getUsername());
        cookie.setMaxAge(100);
        Cookie cookie1 = new Cookie("hieutest", user.getUsername());
        cookie1.setMaxAge(50);
        response.addCookie(cookie);
        response.addCookie(cookie1);

        HttpSession session = request.getSession();
        session.setAttribute("username-ss", user.getUsername());
        session.setMaxInactiveInterval(30);
        return "redirect:/employee";
    }

    @GetMapping("/logout")
    public String logout(HttpServletResponse response, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("username-ss");
        return "redirect:/login";
    }
}
