package com.exam.onlineexamapi.controller.admin;

import com.exam.onlineexamapi.domain.dto.admin.exam.ExamPaperEditRequestDTO;
import com.exam.onlineexamapi.domain.entity.ExamPaper;
import com.exam.onlineexamapi.page.PageRequest;
import com.exam.onlineexamapi.result.RestResult;
import com.exam.onlineexamapi.result.RestResultBuilder;
import com.exam.onlineexamapi.service.ExamPaperService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/exam/paper")
public class ExamPaperController {

    @Resource
    ExamPaperService examPaperService;

    @PostMapping("/select")
    public RestResult select(@RequestParam Integer id) {
        return new RestResultBuilder<>().success(examPaperService.examPaperToVM(id));
    }

    @PostMapping("/edit")
    public RestResult edit(@RequestBody ExamPaperEditRequestDTO examPaperEditRequestDTO) {
        ExamPaper examPaper = examPaperService.savePaperFromVM(examPaperEditRequestDTO);
        return new RestResultBuilder<>().success();
    }

    @PostMapping("/delete")
    public RestResult del(@RequestParam Integer id) {
        return new RestResultBuilder<>().success(examPaperService.delExamPaper(id));
    }

    @PostMapping("/page")
    public RestResult pageList(@RequestBody PageRequest pageRequest) {
        return new RestResultBuilder<>().success(examPaperService.findByPage(pageRequest));
    }
}
