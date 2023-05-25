package com.exam.onlineexamapi.controller.student;

import com.exam.onlineexamapi.page.PageRequest;
import com.exam.onlineexamapi.result.RestResult;
import com.exam.onlineexamapi.result.RestResultBuilder;
import com.exam.onlineexamapi.service.ExamPaperService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController("StudentExamPaperController")
@RequestMapping("/student/exam/paper")
public class ExamPaperController {
    @Resource
    ExamPaperService examPaperService;

    @PostMapping("/pageList")
    public RestResult pageList(@RequestBody PageRequest pageRequest) {
        return new RestResultBuilder<>().success(examPaperService.studentPage(pageRequest));
    }

    @PostMapping("/select")
    public RestResult select(@RequestParam Integer id) {
        return new RestResultBuilder<>().success(examPaperService.examPaperToVM(id));
    }
}
