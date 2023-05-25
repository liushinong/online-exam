package com.exam.onlineexamapi.domain.DO;

import com.exam.onlineexamapi.domain.entity.ExamPaper;
import com.exam.onlineexamapi.domain.entity.ExamPaperAnswer;
import com.exam.onlineexamapi.domain.entity.ExamPaperQuestionCustomerAnswer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamPaperAnswerInfo {
    private ExamPaper examPaper;
    private ExamPaperAnswer examPaperAnswer;
    private List<ExamPaperQuestionCustomerAnswer> examPaperQuestionCustomerAnswers;
}
