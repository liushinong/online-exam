package com.exam.onlineexamapi.controller;

import com.exam.onlineexamapi.page.PageRequest;
import com.exam.onlineexamapi.result.RestResult;
import com.exam.onlineexamapi.result.RestResultBuilder;
import com.exam.onlineexamapi.service.UserService;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/selectInformation")
    public  RestResult selectInformation(@RequestParam Integer studentId){
        return  new RestResultBuilder<>().success(userService.selectInformation(studentId));
    }
    @PostMapping("/selectStudentSubject")
    public  RestResult selectStudentSubject(@RequestBody PageRequest pageRequest){
        return new RestResultBuilder<>().success(userService.findSubjectByPage(pageRequest));
    }

}
