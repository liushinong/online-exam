package com.exam.onlineexamapi.controller;

import com.exam.onlineexamapi.domain.dto.LoginDTO;
import com.exam.onlineexamapi.domain.entity.User;
import com.exam.onlineexamapi.mapper.UserMapper;
import com.exam.onlineexamapi.result.RestResult;
import com.exam.onlineexamapi.result.RestResultBuilder;
import com.exam.onlineexamapi.security.JwtAuthenticationToken;
import com.exam.onlineexamapi.service.LoginService;
import com.exam.onlineexamapi.service.UserService;
import com.exam.onlineexamapi.utils.PasswordUtils;
import com.exam.onlineexamapi.utils.SecurityUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class LoginController {

    @Resource
    UserMapper userMapper;

    @Resource
    LoginService loginService;

    @Resource
    AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public RestResult login(@RequestBody LoginDTO loginDTO, HttpServletRequest request) {
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();

        User user = userMapper.findByName(username);
        if (user == null) {
            return new RestResultBuilder<>().error("账号不存在");
        }
        if (!PasswordUtils.matches(user.getSalt(), password, user.getPassword())) {
            return new RestResultBuilder<>().error("密码不正确");
        }
        // 系统登录认证
        JwtAuthenticationToken token = SecurityUtils.login(request, username, password, authenticationManager);
        return new RestResultBuilder<>().success(token);
    }

    @PostMapping("/logon")
    public RestResult logon(@RequestBody LoginDTO loginDTO) {
        return new RestResultBuilder<>().success(loginService.logon(loginDTO));
    }
}
