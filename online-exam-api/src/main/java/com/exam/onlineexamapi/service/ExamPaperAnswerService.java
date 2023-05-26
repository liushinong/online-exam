package com.exam.onlineexamapi.service;

import com.exam.onlineexamapi.domain.DO.ExamPaperAnswerInfo;
import com.exam.onlineexamapi.domain.dto.student.exam.ExamPaperSubmitVM;
import com.exam.onlineexamapi.domain.entity.ExamPaperAnswer;
import com.exam.onlineexamapi.page.PageRequest;
import com.exam.onlineexamapi.page.PageResult;

public interface ExamPaperAnswerService extends CurdService<ExamPaperAnswer>{
    ExamPaperAnswerInfo calculateExamPaperAnswer(ExamPaperSubmitVM examPaperSubmitVM, Integer userId);

    Integer insertByFilter(ExamPaperAnswer examPaperAnswer);

    ExamPaperSubmitVM examPaperAnswerToVM(Integer id);

    String judge(ExamPaperSubmitVM examPaperSubmitVM);

    PageResult findPageByComplete(PageRequest pageRequest);

    PageResult findPageByUserId(PageRequest pageRequest);
}
