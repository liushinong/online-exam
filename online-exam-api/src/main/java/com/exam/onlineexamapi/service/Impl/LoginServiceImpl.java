package com.exam.onlineexamapi.service.Impl;

import com.exam.onlineexamapi.domain.dto.LoginDTO;
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
    public RestResult logon(LoginDTO loginDTO) {
        String salt = "exam";
        PasswordEncoder passwordEncoder = new PasswordEncoder(salt);
        String newPassword = passwordEncoder.encode(loginDTO.getPassword());
        return new RestResultBuilder<>().success(loginMapper.logon(loginDTO.getUsername(), newPassword, salt));
    }
}
