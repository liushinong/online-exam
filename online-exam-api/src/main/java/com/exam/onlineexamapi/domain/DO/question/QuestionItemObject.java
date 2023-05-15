package com.exam.onlineexamapi.domain.DO.question;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 选项类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionItemObject {
    private String prefix;

    private String content;

    private String score;
}
