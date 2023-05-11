package com.exam.onlineexamapi.utils;

import com.exam.onlineexamapi.security.JwtAuthenticationToken;
import jdk.nashorn.internal.ir.ReturnNode;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.servlet.http.HttpServletRequest;

public class SecurityUtils {

    public static JwtAuthenticationToken login(HttpServletRequest request, String username, String password, AuthenticationManager authenticationManager) {
        JwtAuthenticationToken token = new JwtAuthenticationToken(username, password);
        token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        // 执行登录认证过程
        Authentication authentication = authenticationManager.authenticate(token);
        // 存储认证成功信息到上下文
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 生成令牌返回客户端
        token.setToken(JwtTokenUtils.generateToken(authentication));
        return token;
    }

    /**
     * 获取令牌并进行认证
     * @param request
     */
    public static void checkAuthentication(HttpServletRequest request) {
        // 获取令牌并更具令牌获取登录认证信息
        Authentication authentication = JwtTokenUtils.getAuthenticationFromToken(request);
        // 设置登录认证信息到上下文
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    /**
     * 获取当前登录认证信息
     * @return
     */
    public static Authentication getAuthentication() {
        if (SecurityContextHolder.getContext() == null) {
            return null;
        }
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取当前用户名
     * @return
     */
    public static String getUsername() {
        String username = null;
        Authentication authentication = getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal != null && principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            }
        }
        return username;
    }

    /**
     * 获取用户名
     * @param authentication
     * @return
     */
    public static String getUsername(Authentication authentication) {
        String username = null;
        Object principal = authentication.getPrincipal();
        if (principal != null && principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }
        return username;
    }
}
