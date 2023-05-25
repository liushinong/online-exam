package com.exam.onlineexamapi.service;

import com.exam.onlineexamapi.domain.entity.ExamPaperQuestionCustomerAnswer;

import java.util.List;

public interface ExamPaperQuestionCustomerAnswerService {
    /**
     * 试卷提交答案入库
     * @param examPaperQuestionCustomerAnswers
     */
    void insertList(List<ExamPaperQuestionCustomerAnswer> examPaperQuestionCustomerAnswers);
}
