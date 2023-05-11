package com.exam.onlineexamapi.security;

import com.exam.onlineexamapi.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录认证过滤器
 * 负责登录认证时检查并产生令牌保存到上下文，系统从上下文中获取令牌校验接口访问权限
 */
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {
    @Autowired
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        SecurityUtils.checkAuthentication(request);
        chain.doFilter(request, response);
    }
}
