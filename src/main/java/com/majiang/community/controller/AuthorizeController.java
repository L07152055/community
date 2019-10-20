package com.majiang.community.controller;

import com.majiang.community.dto.AccessTokeDTO;
import com.majiang.community.dto.GithubUser;
import com.majiang.community.mapper.UserMapper;
import com.majiang.community.model.User;
import com.majiang.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private UserMapper userMapper;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response) {
        AccessTokeDTO accessTokeDTO = new AccessTokeDTO();
        accessTokeDTO.setClient_id(clientId);
        accessTokeDTO.setCode(code);
        accessTokeDTO.setState(state);
        accessTokeDTO.setClient_secret(clientSecret);
        accessTokeDTO.setRedirect_uri(redirectUri);
        String accessToken = githubProvider.getAccessToken(accessTokeDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);

        if(null != githubUser && githubUser.getId() != null) {
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setName(githubUser.getName());
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setAvatarUrl(githubUser.getAvatarUrl());
            // 写入DB相当于写入Session
            userMapper.insert(user);
            response.addCookie(new Cookie("token", token));
            //request.getSession().setAttribute("user", user);
            // 重定向
            return "redirect:/";
            // 登录成功,写Session和Cookie
        } else {
            // 登录失败
            return "redirect:/";
        }
    }
}
