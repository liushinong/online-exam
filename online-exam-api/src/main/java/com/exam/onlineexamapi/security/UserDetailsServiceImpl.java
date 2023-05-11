package com.exam.onlineexamapi.security;

import com.exam.onlineexamapi.domain.entity.User;
import com.exam.onlineexamapi.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户登录认证信息查询
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("该用户不存在");
        }
        return new JwtUserDetails(user.getUserName(), user.getPassword(), user.getSalt());
    }
}
