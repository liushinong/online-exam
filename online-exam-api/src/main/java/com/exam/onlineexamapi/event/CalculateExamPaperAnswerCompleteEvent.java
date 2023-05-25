package com.exam.onlineexamapi.event;

import com.exam.onlineexamapi.domain.DO.ExamPaperAnswerInfo;
import org.springframework.context.ApplicationEvent;

public class CalculateExamPaperAnswerCompleteEvent extends ApplicationEvent {

    private ExamPaperAnswerInfo examPaperAnswerInfo;

    public CalculateExamPaperAnswerCompleteEvent(final ExamPaperAnswerInfo examPaperAnswerInfo) {
        super(examPaperAnswerInfo);
        this.examPaperAnswerInfo = examPaperAnswerInfo;
    }

    public ExamPaperAnswerInfo getExamPaperAnswerInfo() {
        return this.examPaperAnswerInfo;
    }
}
