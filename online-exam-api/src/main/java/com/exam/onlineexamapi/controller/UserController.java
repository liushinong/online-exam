package com.exam.onlineexamapi.controller;

import com.exam.onlineexamapi.result.RestResult;
import com.exam.onlineexamapi.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService userService;

    @PostMapping("/findByName")
    public RestResult findByName(@RequestParam String username) {
        return userService.findByName(username);
    }
}
