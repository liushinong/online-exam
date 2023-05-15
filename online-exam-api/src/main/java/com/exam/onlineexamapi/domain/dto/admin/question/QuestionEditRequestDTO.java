package com.exam.onlineexamapi.domain.dto.admin.question;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionEditRequestDTO {
    private Integer userId;
    private Integer id;
    private Integer questionType;
    private Integer subjectId;
    private String title;
    private List<QuestionEditItemRequestDTO> items;
    private String analyze;
    private List<String> correctArray;
    private String correct;
    private String score;
    private Integer difficult;
    private Integer itemOrder;
}
