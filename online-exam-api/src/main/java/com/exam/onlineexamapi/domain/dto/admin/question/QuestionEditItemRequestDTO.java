package com.exam.onlineexamapi.domain.dto.admin.question;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionEditItemRequestDTO {
    private String prefix;
    private String content;
    private String score;
}
