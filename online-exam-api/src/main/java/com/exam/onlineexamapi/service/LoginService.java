package com.exam.onlineexamapi.service;

import com.exam.onlineexamapi.domain.dto.LoginDTO;
import com.exam.onlineexamapi.result.RestResult;

public interface LoginService {
    RestResult logon(LoginDTO loginDTO);
}
