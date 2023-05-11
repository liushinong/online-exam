package com.exam.onlineexamapi.service;

import com.exam.onlineexamapi.result.RestResult;

public interface UserService {
    RestResult findByName(String username);
}
