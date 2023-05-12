package com.exam.onlineexamapi.service.Impl;

import com.exam.onlineexamapi.domain.entity.User;
import com.exam.onlineexamapi.mapper.UserMapper;
import com.exam.onlineexamapi.page.MybatisPageHelper;
import com.exam.onlineexamapi.page.PageRequest;
import com.exam.onlineexamapi.page.PageResult;
import com.exam.onlineexamapi.result.RestResult;
import com.exam.onlineexamapi.result.RestResultBuilder;
import com.exam.onlineexamapi.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public RestResult findByName(String username) {
        return new RestResultBuilder<>().success(userMapper.findByName(username));
    }

    @Override
    public int save(User record) {
        return 0;
    }

    @Override
    public int delete(User record) {
        return 0;
    }

    @Override
    public int delete(List<User> records) {
        return 0;
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public PageResult findByPage(PageRequest pageRequest) {
        return MybatisPageHelper.findByPage(pageRequest, userMapper);
    }
}
