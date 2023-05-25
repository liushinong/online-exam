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
public class ExamPaperSubmitItemVM {
    private Integer id;
    private Integer questionId;
    private Boolean doRight;
    private String content;
    private Integer itemOrder;
    private List<String> contentArray;
    private String score;
    private String questionScore;
}
