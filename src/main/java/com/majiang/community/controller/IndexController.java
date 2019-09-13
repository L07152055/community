package com.majiang.community.controller;

import com.majiang.community.model.User;
import com.majiang.community.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired
    private IndexService indexService;

    @GetMapping("/")
    public String hello(HttpServletRequest httpRequest) {
        Cookie[] cookies = httpRequest.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                String token = cookie.getValue();
                User user = indexService.getUserByToken(token);
                if (null != user) {
                    httpRequest.getSession().setAttribute("user", user);
                }
                break;
            }
        }
        return "index";
    }
}
