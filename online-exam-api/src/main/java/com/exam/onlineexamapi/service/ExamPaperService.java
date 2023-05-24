package com.exam.onlineexamapi.service;

import com.exam.onlineexamapi.domain.dto.admin.exam.ExamPaperEditRequestDTO;
import com.exam.onlineexamapi.domain.entity.ExamPaper;
import com.exam.onlineexamapi.page.PageRequest;
import com.exam.onlineexamapi.page.PageResult;

public interface ExamPaperService extends CurdService<ExamPaper> {
    ExamPaper savePaperFromVM(ExamPaperEditRequestDTO examPaperEditRequestDTO);
    Integer delExamPaper(Integer id);
    ExamPaperEditRequestDTO examPaperToVM(Integer id);
    PageResult studentPage(PageRequest pageRequest);
}
