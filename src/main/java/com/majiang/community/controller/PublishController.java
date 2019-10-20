package com.majiang.community.controller;

import com.majiang.community.model.Question;
import com.majiang.community.model.User;
import com.majiang.community.service.IndexService;
import com.majiang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName PublishController
 * @Description TODO
 * @Author 刘志强
 * @Date 2019/9/14 0:35
 * @Version v1.0
 **/
@Controller
public class PublishController {
    @Autowired
    IndexService indexService;
    @Autowired
    QuestionService questionService;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam(value = "title", required = false) String title,
                            @RequestParam(value = "description", required = false) String description,
                            @RequestParam(value = "tag",required = false) String tag,
                            HttpServletRequest httpServletRequest,
                            Model model) {
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);

        if(null == title || "" == title) {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }

        if(null == description || "" == description) {
            model.addAttribute("error", "描述不能为空");
            return "publish";
        }

        if(null == tag || "" == tag) {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }

        User user = null;
        Cookie[] cookies = httpServletRequest.getCookies();
        if(null != cookies && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    user = indexService.getUserByToken(token);
                    if (null != user) {
                        httpServletRequest.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }

        if(null == user) {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());

        questionService.creat(question);

        return "redirect:/";
    }
}
