package com.exam.onlineexamapi.domain.dto.student.exam;

import com.exam.onlineexamapi.domain.dto.admin.exam.ExamPaperEditRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamPaperReadVM {
    // 添加试卷的参数
    private ExamPaperEditRequestDTO paper;
    // 用户提交的答案
    private ExamPaperSubmitVM answer;
}
