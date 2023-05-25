package com.exam.onlineexamapi.controller.student;

import com.exam.onlineexamapi.result.RestResult;
import com.exam.onlineexamapi.result.RestResultBuilder;
import com.exam.onlineexamapi.service.SubjectService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/student/education")
public class EducationController {

    @Resource
    SubjectService subjectService;

    @PostMapping("/subject/list")
    public RestResult list(@RequestParam Integer id) {
        return new RestResultBuilder<>().success(subjectService.getSubjectByStuId(id));
    }

    @PostMapping("/subject/select")
    public RestResult select(@RequestParam Integer id) {
        return null;
    }
}
