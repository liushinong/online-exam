package com.exam.onlineexamapi.domain.dto.student.exam;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamPaperSubmitVM {
    private Integer userId;
    private Integer id;
    private Integer doTime;
    private String score;
    private List<ExamPaperSubmitItemVM> answerItems;
}
