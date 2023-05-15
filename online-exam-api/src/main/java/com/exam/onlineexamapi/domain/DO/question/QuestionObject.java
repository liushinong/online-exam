package com.exam.onlineexamapi.domain.DO.question;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionObject {
    /**
     * 题干
     */
    private String titleContent;

    /**
     * 解析
     */
    private String analyze;

    /**
     * 选项
     */
    private List<QuestionItemObject> questionItemObjectList;

    /**
     * 标答
     */
    private String correct;
}
