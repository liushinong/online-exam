package com.exam.onlineexamapi.domain.DO.paper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamPaperTitleItemObject {
    private String name;
    private List<ExamPaperQuestionItemObject> questionItems;
}
