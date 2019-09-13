package com.majiang.community.service;

import com.majiang.community.mapper.UserMapper;
import com.majiang.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndexService {
    @Autowired
    private UserMapper userMapper;

    public User getUserByToken(String token) {
        return userMapper.getUserByToken(token);
    }

}
