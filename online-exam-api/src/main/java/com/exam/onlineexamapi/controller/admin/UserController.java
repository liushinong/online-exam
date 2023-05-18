package com.exam.onlineexamapi.controller.admin;

import com.exam.onlineexamapi.page.PageRequest;
import com.exam.onlineexamapi.result.RestResult;
import com.exam.onlineexamapi.result.RestResultBuilder;
import com.exam.onlineexamapi.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController("AdminUserController")
@RequestMapping("/admin/user")
public class UserController {
    @Resource
    UserService userService;

    @PostMapping("/findByPage")
    public RestResult findByPage(@RequestBody PageRequest pageRequest) {
        return new RestResultBuilder<>().success(userService.findByPage(pageRequest));
    }
    
}
