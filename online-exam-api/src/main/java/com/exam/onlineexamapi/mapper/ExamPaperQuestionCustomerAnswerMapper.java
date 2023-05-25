package com.exam.onlineexamapi.mapper;

import com.exam.onlineexamapi.domain.entity.ExamPaperQuestionCustomerAnswer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExamPaperQuestionCustomerAnswerMapper {
    Integer insertList(List<ExamPaperQuestionCustomerAnswer> examPaperQuestionCustomerAnswers);
}
