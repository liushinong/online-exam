package com.exam.onlineexamapi.service;

import com.exam.onlineexamapi.domain.dto.student.exam.ExamPaperSubmitItemVM;
import com.exam.onlineexamapi.domain.entity.ExamPaperQuestionCustomerAnswer;
import com.exam.onlineexamapi.domain.other.ExamPaperAnswerUpdate;

import java.util.List;

public interface ExamPaperQuestionCustomerAnswerService extends CurdService<ExamPaperQuestionCustomerAnswer>{
    /**
     * 试卷提交答案入库
     * @param examPaperQuestionCustomerAnswers
     */
    void insertList(List<ExamPaperQuestionCustomerAnswer> examPaperQuestionCustomerAnswers);

    List<ExamPaperQuestionCustomerAnswer> selectListByPaperAnswerId(Integer id);

    ExamPaperSubmitItemVM examPaperQuestionCustomerAnswerToVM(ExamPaperQuestionCustomerAnswer qa);

    Integer updateScore(List<ExamPaperAnswerUpdate> examPaperAnswerUpdates);
}
