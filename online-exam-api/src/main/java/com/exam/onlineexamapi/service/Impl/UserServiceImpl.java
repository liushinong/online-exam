package com.exam.onlineexamapi.service.Impl;

import com.exam.onlineexamapi.mapper.UserMapper;
import com.exam.onlineexamapi.result.RestResult;
import com.exam.onlineexamapi.result.RestResultBuilder;
import com.exam.onlineexamapi.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public RestResult findByName(String username) {
        return new RestResultBuilder<>().success(userMapper.findByName(username));
    }
}
