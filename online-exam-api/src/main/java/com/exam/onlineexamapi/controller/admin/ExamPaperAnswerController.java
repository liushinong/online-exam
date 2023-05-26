package com.exam.onlineexamapi.controller.admin;

import com.exam.onlineexamapi.domain.dto.admin.exam.ExamPaperEditRequestDTO;
import com.exam.onlineexamapi.domain.dto.student.exam.ExamPaperReadVM;
import com.exam.onlineexamapi.domain.dto.student.exam.ExamPaperSubmitVM;
import com.exam.onlineexamapi.domain.entity.ExamPaperAnswer;
import com.exam.onlineexamapi.domain.enums.ExamPaperAnswerStatusEnum;
import com.exam.onlineexamapi.page.PageRequest;
import com.exam.onlineexamapi.result.RestResult;
import com.exam.onlineexamapi.result.RestResultBuilder;
import com.exam.onlineexamapi.service.ExamPaperAnswerService;
import com.exam.onlineexamapi.service.ExamPaperService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController("AdminExamPaperAnswerController")
@RequestMapping("/admin/exam/paper/answer")
public class ExamPaperAnswerController {

    @Resource
    ExamPaperAnswerService examPaperAnswerService;
    @Resource
    ExamPaperService examPaperService;

    @PostMapping("/pageList")
    public RestResult pageList(@RequestBody PageRequest pageRequest) {
        return new RestResultBuilder<>().success(examPaperAnswerService.findByPage(pageRequest));
    }

    @PostMapping("/pageList/complete")
    public RestResult pageListComplete(@RequestBody PageRequest pageRequest) {
        return new RestResultBuilder<>().success(examPaperAnswerService.findPageByComplete(pageRequest));
    }

    @PostMapping("/read")
    public RestResult read(@RequestParam Integer id) {
        ExamPaperAnswer examPaperAnswer = examPaperAnswerService.findById(id);
        ExamPaperReadVM vm = new ExamPaperReadVM();
        ExamPaperEditRequestDTO paper = examPaperService.examPaperToVM(examPaperAnswer.getExamPaperId());
        ExamPaperSubmitVM answer = examPaperAnswerService.examPaperAnswerToVM(examPaperAnswer.getId());
        vm.setPaper(paper);
        vm.setAnswer(answer);
        return new RestResultBuilder<>().success(vm);
    }

    @PostMapping("/edit")
    public RestResult edit(@RequestBody ExamPaperSubmitVM examPaperSubmitVM) {
        boolean notJudge = examPaperSubmitVM.getAnswerItems().stream().anyMatch(i -> i.getDoRight() == null && i.getScore() == null);
        if (notJudge) {
            return new RestResultBuilder<>().error("有题目未批改");
        }
        ExamPaperAnswer examPaperAnswer = examPaperAnswerService.findById(examPaperSubmitVM.getId());
        ExamPaperAnswerStatusEnum examPaperAnswerStatusEnum = ExamPaperAnswerStatusEnum.fromCode(examPaperAnswer.getStatus());
        if (examPaperAnswerStatusEnum == ExamPaperAnswerStatusEnum.Complete) {
            return new RestResultBuilder<>().error("试卷已批改完成");
        }
        String score = examPaperAnswerService.judge(examPaperSubmitVM);
        return new RestResultBuilder<>().success();
    }
}
