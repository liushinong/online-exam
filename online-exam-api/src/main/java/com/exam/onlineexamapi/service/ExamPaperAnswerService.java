package com.exam.onlineexamapi.service;

import com.exam.onlineexamapi.domain.DO.ExamPaperAnswerInfo;
import com.exam.onlineexamapi.domain.dto.student.exam.ExamPaperSubmitVM;
import com.exam.onlineexamapi.domain.entity.ExamPaperAnswer;

public interface ExamPaperAnswerService {
    ExamPaperAnswerInfo calculateExamPaperAnswer(ExamPaperSubmitVM examPaperSubmitVM, Integer userId);

    Integer insertByFilter(ExamPaperAnswer examPaperAnswer);
}
