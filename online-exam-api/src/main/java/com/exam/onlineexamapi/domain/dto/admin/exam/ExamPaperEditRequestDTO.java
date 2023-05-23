package com.exam.onlineexamapi.domain.dto.admin.exam;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExamPaperEditRequestDTO {
    private Integer userId;
    private Integer id;
    private Integer subjectId;
    private Integer paperType;
    private String name;
    private Integer suggestTime;
    private List<String> limitDateTime;
    private List<ExamPaperTitleItemDTO> titleItems;
    private String score;
}
