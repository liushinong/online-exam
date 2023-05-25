package com.exam.onlineexamapi.listener;

import com.exam.onlineexamapi.domain.DO.ExamPaperAnswerInfo;
import com.exam.onlineexamapi.domain.entity.ExamPaper;
import com.exam.onlineexamapi.domain.entity.ExamPaperAnswer;
import com.exam.onlineexamapi.domain.entity.ExamPaperQuestionCustomerAnswer;
import com.exam.onlineexamapi.domain.entity.TextContent;
import com.exam.onlineexamapi.domain.enums.QuestionTypeEnum;
import com.exam.onlineexamapi.event.CalculateExamPaperAnswerCompleteEvent;
import com.exam.onlineexamapi.service.ExamPaperAnswerService;
import com.exam.onlineexamapi.service.ExamPaperQuestionCustomerAnswerService;
import com.exam.onlineexamapi.service.TextContentService;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Component
public class CalculateExamPaperAnswerListener implements ApplicationListener<CalculateExamPaperAnswerCompleteEvent> {

    @Resource
    ExamPaperAnswerService examPaperAnswerService;
    @Resource
    TextContentService textContentService;
    @Resource
    ExamPaperQuestionCustomerAnswerService examPaperQuestionCustomerAnswerService;

    @Override
    @Transactional
    public void onApplicationEvent(CalculateExamPaperAnswerCompleteEvent calculateExamPaperAnswerCompleteEvent) {
        Date now = new Date();

        ExamPaperAnswerInfo examPaperAnswerInfo = (ExamPaperAnswerInfo) calculateExamPaperAnswerCompleteEvent.getSource();
        ExamPaper examPaper = examPaperAnswerInfo.getExamPaper();
        ExamPaperAnswer examPaperAnswer = examPaperAnswerInfo.getExamPaperAnswer();
        List<ExamPaperQuestionCustomerAnswer> examPaperQuestionCustomerAnswers = examPaperAnswerInfo.getExamPaperQuestionCustomerAnswers();

        examPaperAnswerService.insertByFilter(examPaperAnswer);
        examPaperQuestionCustomerAnswers.stream().filter(a -> QuestionTypeEnum.needSaveTextContent(a.getQuestionType())).forEach(d -> {
            TextContent textContent = new TextContent(d.getAnswer(), now);
            textContentService.save(textContent);
            d.setTextContentId(textContent.getId());
            d.setAnswer(null);
        });
        examPaperQuestionCustomerAnswers.forEach(d -> {
            d.setExamPaperAnswerId(examPaperAnswer.getId());
        });
        examPaperQuestionCustomerAnswerService.insertList(examPaperQuestionCustomerAnswers);
    }
}
