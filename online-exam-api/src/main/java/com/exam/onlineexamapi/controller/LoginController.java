package com.exam.onlineexamapi.controller;

import com.exam.onlineexamapi.domain.dto.LoginDTO;
import com.exam.onlineexamapi.domain.dto.LogonDTO;
import com.exam.onlineexamapi.domain.entity.User;
import com.exam.onlineexamapi.domain.vo.admin.LoginVO;
import com.exam.onlineexamapi.mapper.UserMapper;
import com.exam.onlineexamapi.result.RestResult;
import com.exam.onlineexamapi.result.RestResultBuilder;
import com.exam.onlineexamapi.security.JwtAuthenticationToken;
import com.exam.onlineexamapi.service.LoginService;
import com.exam.onlineexamapi.service.UserService;
import com.exam.onlineexamapi.utils.PasswordUtils;
import com.exam.onlineexamapi.utils.SecurityUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @Resource
    UserMapper userMapper;

    @Resource
    LoginService loginService;

    @Resource
    AuthenticationManager authenticationManager;

    @PostMapping("/user/login")
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
        LoginVO loginVO = LoginVO.builder()
                .userId(user.getId())
                .username(user.getUserName())
                .token(token.getToken())
                .build();
        return new RestResultBuilder<>().success(loginVO);
    }

    @GetMapping("/user/info")
    public RestResult getInfo() {
        Map map = new HashMap();
        map.put("roles", new String[]{"admin"});
        map.put("introduction", "I am a super administrator");
        map.put("avatar", "https://xuekaikai.oss-cn-shanghai.aliyuncs.com/campus_navigatic/sXWNh3ynRPx.jpg");
        map.put("name", "Super Admin");
        return new RestResultBuilder<>().success(map);
    }

    @PostMapping("/logon")
    public RestResult logon(@RequestBody LogonDTO logonDTO) {
        return loginService.logon(logonDTO);
    }
}
