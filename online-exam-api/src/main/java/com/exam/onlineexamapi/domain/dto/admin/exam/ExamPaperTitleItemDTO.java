package com.exam.onlineexamapi.domain.dto.admin.exam;

import com.exam.onlineexamapi.domain.dto.admin.question.QuestionEditRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExamPaperTitleItemDTO {
    private String name;
    private List<QuestionEditRequestDTO> questionItems;
}
