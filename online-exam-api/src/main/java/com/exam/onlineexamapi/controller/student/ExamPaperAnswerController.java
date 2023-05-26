package com.exam.onlineexamapi.controller.student;

import com.exam.onlineexamapi.domain.DO.ExamPaperAnswerInfo;
import com.exam.onlineexamapi.domain.dto.student.exam.ExamPaperSubmitVM;
import com.exam.onlineexamapi.domain.entity.ExamPaperAnswer;
import com.exam.onlineexamapi.domain.entity.User;
import com.exam.onlineexamapi.domain.entity.UserEventLog;
import com.exam.onlineexamapi.event.CalculateExamPaperAnswerCompleteEvent;
import com.exam.onlineexamapi.event.UserEvent;
import com.exam.onlineexamapi.page.PageRequest;
import com.exam.onlineexamapi.result.RestResult;
import com.exam.onlineexamapi.result.RestResultBuilder;
import com.exam.onlineexamapi.service.ExamPaperAnswerService;
import com.exam.onlineexamapi.service.UserService;
import com.exam.onlineexamapi.utils.ExamUtil;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController("StudentExamPaperAnswerController")
@RequestMapping("/student/exam/paper/answer")
public class ExamPaperAnswerController {

    @Resource
    ExamPaperAnswerService examPaperAnswerService;
    @Resource
    UserService userService;
    @Resource
    ApplicationEventPublisher eventPublisher;

    @PostMapping("/answerSubmit")
    public RestResult answerSubmit(@RequestBody ExamPaperSubmitVM examPaperSubmitVM) {
        ExamPaperAnswerInfo examPaperAnswerInfo = examPaperAnswerService.calculateExamPaperAnswer(examPaperSubmitVM, examPaperSubmitVM.getUserId());
        if (null == examPaperAnswerInfo) {
            return new RestResultBuilder<>().error("试卷不能重复做");
        }
        ExamPaperAnswer examPaperAnswer = examPaperAnswerInfo.getExamPaperAnswer();
        Integer userScore = examPaperAnswer.getUserScore();
        String scoreVm = String.valueOf(userScore);
        User user = userService.findById(examPaperSubmitVM.getUserId());
        UserEventLog userEventLog = new UserEventLog(user.getId(), user.getUserName(), user.getRealName(), new Date());
        String content = user.getUserName() + " 提交试卷: " + examPaperAnswerInfo.getExamPaper().getName() + " 得分: " + scoreVm
                + " 耗时: " + ExamUtil.secondToVM(examPaperAnswer.getDoTime());
        userEventLog.setContent(content);
        eventPublisher.publishEvent(new CalculateExamPaperAnswerCompleteEvent(examPaperAnswerInfo));
        eventPublisher.publishEvent(new UserEvent(userEventLog));
        return new RestResultBuilder<>().success(scoreVm);
    }

    @PostMapping("/pageList")
    public RestResult pageList(@RequestBody PageRequest pageRequest) {
        return new RestResultBuilder<>().success(examPaperAnswerService.findPageByUserId(pageRequest));
    }

}
