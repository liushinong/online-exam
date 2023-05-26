package com.exam.onlineexamapi.service.Impl;

import com.exam.onlineexamapi.domain.dto.LoginDTO;
import com.exam.onlineexamapi.domain.dto.LogonDTO;
import com.exam.onlineexamapi.mapper.LoginMapper;
import com.exam.onlineexamapi.result.RestResult;
import com.exam.onlineexamapi.result.RestResultBuilder;
import com.exam.onlineexamapi.service.LoginService;
import com.exam.onlineexamapi.utils.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    LoginMapper loginMapper;

    @Override
    public RestResult logon(LogonDTO logonDTO) {
        String salt = "exam";
        PasswordEncoder passwordEncoder = new PasswordEncoder(salt);
        String newPassword = passwordEncoder.encode(logonDTO.getPassword());
        return new RestResultBuilder<>().success(loginMapper.logon(logonDTO.getUsername(), newPassword, salt, logonDTO.getRole()));
    }
}
