package com.exam.onlineexamapi.security;

import com.exam.onlineexamapi.utils.PasswordEncoder;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 身份验证提供者
 */
public class JwtAuthenticationProvider extends DaoAuthenticationProvider {
    public JwtAuthenticationProvider(UserDetailsService userDetailsService) {
        setUserDetailsService(userDetailsService);
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) {
        if (authentication.getCredentials() == null) { // 获取证书为空
            logger.debug("Authentication failed: no credentials provided");
            throw new BadCredentialsException(messages.getMessage("***", "bad credentials"));
        }
        String presentedPassword = authentication.getCredentials().toString();
        String salt = ((JwtUserDetails) userDetails).getSalt();
        if (!new PasswordEncoder(salt).matches(userDetails.getPassword(), presentedPassword)) {
            // 覆写密码验证逻辑
            logger.debug("Authentication failed: password does not match");
            throw new BadCredentialsException(messages.getMessage("***", "bad credentials"));
        }
    }
}
