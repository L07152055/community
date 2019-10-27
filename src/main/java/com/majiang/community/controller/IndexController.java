package com.majiang.community.controller;

import com.majiang.community.dto.PaginationDTO;
import com.majiang.community.dto.QuestionDTO;
import com.majiang.community.model.Question;
import com.majiang.community.model.User;
import com.majiang.community.service.IndexService;
import com.majiang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private IndexService indexService;
    @Autowired
    private QuestionService questionService;

    /*
     * @Author 刘志强
     * @Description //TODO
     * @Date 0:42 2019/9/14
     * @Param [httpRequest]
     * @return java.lang.String
    **/
    @GetMapping("/")
    public String hello(HttpServletRequest httpRequest, Model model,
                        @RequestParam(name = "page", defaultValue = "1")Integer page,
                        @RequestParam(name = "size", defaultValue = "5")Integer size) {
        Cookie[] cookies = httpRequest.getCookies();
        if(null != cookies && cookies.length != 0) {
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
        }

        PaginationDTO pagination = questionService.list(page, size);
        model.addAttribute("pagination", pagination);
        return "index";
    }
}
