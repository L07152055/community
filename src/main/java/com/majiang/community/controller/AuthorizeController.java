package com.majiang.community.controller;

import com.majiang.community.dto.AccessTokeDTO;
import com.majiang.community.dto.GithubUser;
import com.majiang.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state) {
        AccessTokeDTO accessTokeDTO = new AccessTokeDTO();
        accessTokeDTO.setClient_id("bab8e840f32b2660e3b0");
        accessTokeDTO.setCode(code);
        accessTokeDTO.setState(state);
        accessTokeDTO.setClient_secret("5f1ec6b96cffc46f8af5d386aee6f584f87ff056");
        accessTokeDTO.setRedirect_uri("http://localhost:8888/callback");
        String accessToken = githubProvider.getAccessToken(accessTokeDTO);
        GithubUser user = githubProvider.getUser(accessToken);

        System.out.println(user.getName());
        return "index";
    }
}
