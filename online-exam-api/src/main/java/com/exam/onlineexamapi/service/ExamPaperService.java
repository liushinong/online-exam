package com.exam.onlineexamapi.service;

import com.exam.onlineexamapi.domain.dto.admin.exam.ExamPaperEditRequestDTO;
import com.exam.onlineexamapi.domain.entity.ExamPaper;

public interface ExamPaperService extends CurdService<ExamPaper> {
    ExamPaper savePaperFromVM(ExamPaperEditRequestDTO examPaperEditRequestDTO);
    Integer delExamPaper(Integer id);
    ExamPaperEditRequestDTO examPaperToVM(Integer id);
}
